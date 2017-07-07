package dev.sol.catalog;

import dev.sol.catalog.auth.BasicAuthenticator;
import dev.sol.catalog.auth.BasicAuthorizer;
import dev.sol.catalog.auth.oauth2.GoogleOAuthAuthenticator;
import dev.sol.catalog.auth.oauth2.GoogleOAuthIdTokenAuthenticator;
import dev.sol.catalog.core.User;
import dev.sol.catalog.dao.AuthorDAO;
import dev.sol.catalog.dao.BookDAO;
import dev.sol.catalog.entities.Author;
import dev.sol.catalog.entities.Book;
import dev.sol.catalog.health.TemplateHealthCheck;
import dev.sol.catalog.jaxresources.AuthorResource;
import dev.sol.catalog.jaxresources.BookResource;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthFilter;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.auth.chained.ChainedAuthFilter;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import javax.ws.rs.client.Client;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * The Application class pulls together the various bundles
 * and commands which provide basic functionality.
 *
 * @author solo
 */
public class BookCatalogApplication
        extends Application<BookCatalogConfiguration> {

    private final HibernateBundle<BookCatalogConfiguration> hibernate =
            new HibernateBundle<BookCatalogConfiguration>(Author.class, Book.class, Set.class) {

        //@Override
        public DataSourceFactory getDataSourceFactory(BookCatalogConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };


    @Override
    public String getName() {
        return "book-api";
    }

    @Override
    public void initialize(Bootstrap<BookCatalogConfiguration> bootstrap) {

        bootstrap.addBundle(hibernate);
    }

    private void registerResources(BookCatalogConfiguration bookCatalogConfiguration,
                                   Environment environment) {

        final AuthorDAO authorDAO = new AuthorDAO(hibernate.getSessionFactory());
        final BookDAO bookDAO = new BookDAO(hibernate.getSessionFactory());

        environment.jersey().register(new AuthorResource(authorDAO));
        environment.jersey().register(new BookResource(bookDAO));

    }

    private void registerAuthenticators(BookCatalogConfiguration bookCatalogConfiguration,
                                   Environment environment) {

        OAuth2Config oAuth2Cfg = bookCatalogConfiguration.getOauth2Config();
        System.out.println("[registerAuthenticators] ClientID =>"+oAuth2Cfg.getClientId());

        final Client jerseyClient = new JerseyClientBuilder(environment)
                .build("http-client");

        // default timeout value for all requests
        jerseyClient.property(ClientProperties.CONNECT_TIMEOUT, 1000);
        jerseyClient.property(ClientProperties.READ_TIMEOUT,    1000);

        AuthFilter basicAuthFilter = new BasicCredentialAuthFilter.Builder<User>()
                .setAuthenticator(new BasicAuthenticator())
                .setAuthorizer(new BasicAuthorizer())
                .setPrefix("Basic")
                .buildAuthFilter();

        AuthFilter googleOAuthFilter = new OAuthCredentialAuthFilter.Builder<User>()
                .setAuthenticator(new GoogleOAuthAuthenticator(oAuth2Cfg, jerseyClient))
                .setAuthorizer(new BasicAuthorizer())
                .setPrefix("Bearer")
                .buildAuthFilter();

        AuthFilter googleOAuthIdTokenFilter = new OAuthCredentialAuthFilter.Builder<User>()
                .setAuthenticator(new GoogleOAuthIdTokenAuthenticator(oAuth2Cfg))
                .setAuthorizer(new BasicAuthorizer())
                .setPrefix("Bearer")
                .buildAuthFilter();

        //environment.jersey().register(new AuthDynamicFeature(basicAuthFilter));
        //List<AuthFilter> filters = Arrays.asList(basicAuthFilter, googleOAuthFilter);
        List<AuthFilter> filters = Arrays.asList(googleOAuthFilter);
        environment.jersey().register(new AuthDynamicFeature(new ChainedAuthFilter(filters)));

        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));

    }

    @Override
    public void run(BookCatalogConfiguration bookCatalogConfiguration,
                    Environment environment)
            throws Exception {

        registerResources(bookCatalogConfiguration,environment);

        registerAuthenticators(bookCatalogConfiguration,environment);

        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(bookCatalogConfiguration.getTemplate());

        environment.healthChecks().register("template", healthCheck);

    }


    public static void main(String[] args) throws Exception {
        new BookCatalogApplication().run(args);
    }
}
