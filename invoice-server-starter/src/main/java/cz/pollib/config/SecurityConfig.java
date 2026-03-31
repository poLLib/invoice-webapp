package cz.pollib.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.pollib.constant.ErrorCode;
import cz.pollib.service.common.model.ErrorResponse;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.http.SessionCreationPolicy.*;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Profile("!test")
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(
            UserDetailsService userDetailsService,
            JwtAuthenticationFilter jwtAuthenticationFilter,
            PasswordEncoder passwordEncoder
                         ) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeHttpRequests ->
                                               authorizeHttpRequests
                                                       .requestMatchers(
                                                               "/api/user/login",
                                                               "/api/user/register"
                                                                       )
                                                       .permitAll()
                                                       .requestMatchers(
                                                               HttpMethod.DELETE,
                                                               "/api/**"
                                                                       )
                                                       .hasRole("ADMIN")
                                                       .requestMatchers("/api/**")
                                                       .authenticated()
                                                       .requestMatchers(
                                                               "/swagger-ui/**",
                                                               "/v3/api-docs/**",
                                                               "/api-docs/**"
                                                                       )
                                                       .permitAll()
                                      )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint(new ObjectMapper())))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                                )
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(ObjectMapper objectMapper) {
        return (request, response, authException) -> {
            String uri = (String) request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI);
            if (uri == null) {
                uri = request.getRequestURI();
            }

            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter()
                    .write(
                            objectMapper.writeValueAsString(
                                    new ErrorResponse(
                                            ErrorCode.UNAUTHORIZED,
                                            uri,
                                            authException.getMessage()
                                    )
                                                           )
                          );
        };
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowedMethods(List.of(
                "GET",
                "POST",
                "PUT",
                "DELETE",
                "PATCH"
                                               ));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(
                "/**",
                configuration
                                        );
        return source;
    }
}
