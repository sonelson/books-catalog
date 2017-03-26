package dev.sol.catalog.core;

import java.security.Principal;

/**
 * @author solo
 */
public class User implements Principal {

    private final String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
