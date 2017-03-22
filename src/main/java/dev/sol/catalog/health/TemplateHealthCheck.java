package dev.sol.catalog.health;

import com.codahale.metrics.health.HealthCheck;


import java.util.Optional;

/**
 * Health check class to verify application is functioning correctly.
 *
 *
 * @author solo
 */
public class TemplateHealthCheck extends HealthCheck {

    private final String template;

    public TemplateHealthCheck(String  template) {
        this.template = template;
    }

    protected Result check() throws Exception {
        final String saying = String.format(template, "TEST");
        if (!saying.contains("TEST")) {
            return Result.unhealthy("template doesn't include a name");
        }
        return Result.healthy();
    }
}
