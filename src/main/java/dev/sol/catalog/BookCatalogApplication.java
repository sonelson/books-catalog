package dev.sol.catalog;

import dev.sol.catalog.dao.AuthorDAO;
import dev.sol.catalog.dao.BookDAO;
import dev.sol.catalog.entities.Author;
import dev.sol.catalog.entities.Book;
import dev.sol.catalog.health.TemplateHealthCheck;
import dev.sol.catalog.jaxresources.AuthorResource;
import dev.sol.catalog.jaxresources.BookResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * The Application class pulls together the various bundles
 * and commands which provide basic functionality.
 *
 * @author solo
 */
public class BookCatalogApplication
        extends Application<BookCatalogConfiguration> {

    private final HibernateBundle<BookCatalogConfiguration> hibernate =
            new HibernateBundle<BookCatalogConfiguration>(Author.class, Book.class) {

        //@Override
        public DataSourceFactory getDataSourceFactory(BookCatalogConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };


    @Override
    public String getName() {
        return "book-catalog";
    }

    @Override
    public void initialize(Bootstrap<BookCatalogConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(BookCatalogConfiguration bookCatalogConfiguration,
                    Environment environment)
            throws Exception {

        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(bookCatalogConfiguration.getTemplate());

        final AuthorDAO authorDAO = new AuthorDAO(hibernate.getSessionFactory());
        final BookDAO bookDAO = new BookDAO(hibernate.getSessionFactory());

        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(new AuthorResource(authorDAO));
        environment.jersey().register(new BookResource(bookDAO));
    }


    public static void main(String[] args) throws Exception {
        new BookCatalogApplication().run(args);
    }
}
