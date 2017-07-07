package dev.sol.catalog.auth.oauth2;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import dev.sol.catalog.OAuth2Config;
import dev.sol.catalog.core.User;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author solo on 6/15/2017.
 */
public class GoogleOAuthIdTokenAuthenticator implements Authenticator<String, User> {

    private String oauth2ClientID;

    public GoogleOAuthIdTokenAuthenticator(OAuth2Config oauth2Config){
        this.oauth2ClientID = oauth2Config.getClientId();

    }

    /**
     *
     * @param idTokenBearer
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Optional<User> authenticate(String idTokenBearer)
            throws AuthenticationException {

        System.out.println("OAUTH2 Google IdToken AUTHENTICATOR CALLED ....");

        User authenticatedUser = null;

        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        NetHttpTransport transport = new NetHttpTransport();

        System.out.println("Google AUTHENTICATOR ClientID =>"+oauth2ClientID);
        System.out.println("Google AUTHENTICATOR idTokenBearer =>"+idTokenBearer);

        GoogleIdTokenVerifier verifier =
                new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                        //.setAudience(Collections.singletonList(oauth2ClientID))
                        .setAudience(Arrays.asList(oauth2ClientID))
                        // Or, if multiple clients access the backend:
                        //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                        .build();

        // (Receive idTokenBearer by HTTPS POST)
        GoogleIdToken idToken = null;
        try {
            idToken = verifier.verify(idTokenBearer);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();

            // Print user identifier
            String userId = payload.getSubject();
            System.out.println("[GoogleOAuthIdTokenAuthenticator] User ID: " + userId);

            // Get profile information from payload
            // String email = payload.getEmail();
            // boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String userName = (String) payload.get("name");
            authenticatedUser = new User(userName);

        } else {
            System.out.println("Invalid ID token.");
            new AuthenticationException("Authentication Failed");
        }

        return Optional.ofNullable(authenticatedUser);
    }
}
