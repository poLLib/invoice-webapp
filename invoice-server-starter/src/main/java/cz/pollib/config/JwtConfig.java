package cz.pollib.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JwtConfig.JwtProperties.class)
public class JwtConfig {

    @ConfigurationProperties(prefix = "jwt")
    public record JwtProperties(
            long expiration
    ) {
    }
}