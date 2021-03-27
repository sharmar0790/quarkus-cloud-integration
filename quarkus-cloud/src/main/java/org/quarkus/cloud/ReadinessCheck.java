package org.quarkus.cloud;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

@ApplicationScoped
@Readiness
public class ReadinessCheck implements HealthCheck {

    private static final Logger LOG = LoggerFactory.getLogger(Readiness.class);

    @Override
    public HealthCheckResponse call() {
        LOG.info("Checking Readiness of athe quarkus-cloud app");

        Random random = new Random();

        if (random.nextBoolean()) {
            return HealthCheckResponse
                    .named("App Health Check")
                    .down()
                    .build();
        } else {
            return HealthCheckResponse
                    .named("App Health Check")
                    .withData("App", "Quarkus-cloud")
                    .withData("DB", "DB1")
                    .up()
                    .build();
        }
    }
}
