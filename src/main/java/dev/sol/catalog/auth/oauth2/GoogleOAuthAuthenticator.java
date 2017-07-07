package dev.sol.catalog.auth.oauth2;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;

import com.google.api.client.json.jackson2.JacksonFactory;
import dev.sol.catalog.OAuth2Config;
import dev.sol.catalog.core.User;
import dev.sol.catalog.jaxresponses.AccessTokenData;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;


import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

/**
 * @author solo on 6/7/2017.
 */
public class GoogleOAuthAuthenticator implements Authenticator<String, User> {

    private String oauth2ClientID;
    private Client jerseyClient;

    private final static String GOOGLE_OAUTH_URI =
            "https://www.googleapis.com//oauth2/v2/tokeninfo";

    public GoogleOAuthAuthenticator(OAuth2Config oauth2Config,
                                    Client jerseyClient){
        this.oauth2ClientID = oauth2Config.getClientId();
        this.jerseyClient = jerseyClient;
    }

    @Override
    public Optional<User> authenticate(String accessToken)
            throws AuthenticationException {

        System.out.println("OAUTH2 Google AUTHENTICATOR CALLED ....");

        User authenticatedUser = null;

        System.out.println("Google AUTHENTICATOR ClientID =>"+oauth2ClientID);
        System.out.println("Google AUTHENTICATOR accessToken =>"+accessToken);

        final Response response = jerseyClient.target(GOOGLE_OAUTH_URI)
                .queryParam("access_token",accessToken)
                .request(MediaType.APPLICATION_JSON)
                .get();

        int status = response.getStatus();
        System.out.println("Google AUTHENTICATOR response status =>"+status);

        if (Response.Status.OK ==  Response.Status.fromStatusCode(response.getStatus())) {

            AccessTokenData accessTokenData = response
                    .readEntity(AccessTokenData.class);

            // Print user identifier
            String userId = accessTokenData.getUserId();
            System.out.println("[GoogleOAuthAuthenticator] User ID: " + userId);
            authenticatedUser = new User(userId);

            int expiry = Integer.parseInt(accessTokenData.getExpiresIn());

            // fail - if token expires in 120 seconds
            if (expiry < 120) {
                System.out.println("access token expired");
                throw new AuthenticationException("Authentication Failed");
            }

        } else {
            System.out.println("Invalid access token.");
            throw new AuthenticationException("Authentication Failed");
        }

        return Optional.ofNullable(authenticatedUser);
    }

}
