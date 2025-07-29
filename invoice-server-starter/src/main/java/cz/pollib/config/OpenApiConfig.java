package cz.pollib.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("InvoiceWebApp OpenAPI Documentation")
                        .version("1.0")
                        .description("API for managing invoices"));
    }

    @Bean
    public GroupedOpenApi personApi() {
        return GroupedOpenApi
                .builder()
                .group("person")
                .displayName("Person API")
                .pathsToMatch(
                        "/api/identification/*/purchases",
                        "/api/identification/*/sales",
                        "/api/person",
                        "/api/person/**",
                        "/api/persons",
                        "/api/persons/**"
                        )
                .build();
    }

    @Bean
    public GroupedOpenApi invoiceApi() {
        return GroupedOpenApi
                .builder()
                .group("invoice")
                .pathsToMatch(
                        "/api/invoice",
                        "/api/invoice/**",
                        "/api/invoices",
                        "/api/invoices/**"
                )
                .build();
    }
}
