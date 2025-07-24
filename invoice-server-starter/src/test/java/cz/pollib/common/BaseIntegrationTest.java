package cz.pollib.common;

import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@ActiveProfiles("test")
@Slf4j
public abstract class BaseIntegrationTest {

    static final MariaDBContainer<?> mariaDB = new MariaDBContainer<>("mariadb:10.8.2")
            .withDatabaseName("invoiceapp_test")
            .withUsername("test")
            .withPassword("test")
            .withUrlParam("useSSL", "false");
    private static final Logger log = LoggerFactory.getLogger(BaseIntegrationTest.class);

    static {
        mariaDB.start();
        log.info("MariaDB container started");
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mariaDB::getJdbcUrl);
        registry.add("spring.datasource.username", mariaDB::getUsername);
        registry.add("spring.datasource.password", mariaDB::getPassword);

        registry.add("spring.flyway.url", mariaDB::getJdbcUrl);
        registry.add("spring.flyway.user", mariaDB::getUsername);
        registry.add("spring.flyway.password", mariaDB::getPassword);
        registry.add("spring.flyway.locations", () -> "classpath:db/migration,classpath:db/test-data");
    }
}