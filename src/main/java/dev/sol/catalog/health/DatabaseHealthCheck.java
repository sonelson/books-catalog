package dev.sol.catalog.health;

import com.codahale.metrics.health.HealthCheck;
import org.hibernate.boot.model.relational.Database;


/**
 * Class to check database health
 *
 * @author solo
 */
public class DatabaseHealthCheck {

    private final Database database;

    public DatabaseHealthCheck(Database database) {
        this.database = database;
    }

    //@Override
    protected HealthCheck.Result check() throws Exception {
        if (database != null) {
            return HealthCheck.Result.healthy();
        } else {
            return HealthCheck.Result.unhealthy("Cannot connect to database");
        }
    }
}
