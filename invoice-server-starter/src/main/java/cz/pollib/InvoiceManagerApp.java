package cz.pollib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Entry point for the Spring Boot application.
 * <p>
 * This class contains the main method that starts the Invoice Manager application.
 * </p>
 */
@SpringBootApplication
@EnableCaching
public class InvoiceManagerApp {

    public static void main(String[] args) {
        SpringApplication.run(
                InvoiceManagerApp.class,
                args
                             );
    }
}
