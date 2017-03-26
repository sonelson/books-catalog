package dev.sol.catalog.auth;

import dev.sol.catalog.core.User;
import io.dropwizard.auth.Authorizer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author solo
 */
public class BasicAuthorizer implements Authorizer<User> {

    // valid roles source
    private static Set<String> validRolesSet = new HashSet<>();

    // role <-> users mapping
    private static Map<String, Set<String>> roleUsersMap =
            new HashMap<>();

    private static Set<String> adminUsers = new HashSet<>();
    private static Set<String> developers = new HashSet<>();


    // TODO - use an external credential source
    static {
        validRolesSet.add("ADMIN");
        validRolesSet.add("DEVELOPER");
    }

    static {
        adminUsers.add("admin1");
        adminUsers.add("admin2");
        adminUsers.add("admin3");

        developers.add("user1");
        developers.add("user2");
        developers.add("user3");

        roleUsersMap.put("ADMIN", adminUsers);
        roleUsersMap.put("DEVELOPER", developers);
    }

    @Override
    public boolean authorize(User user, String role) {
        System.out.println("Authorizing user and role ::" +
         user.getName() + " in role "+role);
        Set<String> userRoles = roleUsersMap.getOrDefault(role, new HashSet<>());
        return validRolesSet.contains(role) && userRoles.contains(user.getName());
    }
}
