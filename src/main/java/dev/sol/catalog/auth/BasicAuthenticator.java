package dev.sol.catalog.auth;

import dev.sol.catalog.core.User;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author solo
 */
public class BasicAuthenticator implements Authenticator<BasicCredentials, User> {

    // valid credentials source
    private static Set<String> credsSet = new HashSet<>();

    // TODO - use an external credential source
    static {
        credsSet.add("secret1");
        credsSet.add("secret2");
        credsSet.add("secret3");
    }

    @Override
    public Optional<User> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {
        if (credsSet.contains(basicCredentials.getPassword())) {
            return Optional.of(new User(basicCredentials.getUsername()));
        }
        return Optional.empty();
    }
}
