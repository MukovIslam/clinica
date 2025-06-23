package com.clinic.apigateway;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {

    private final AuthenticationFilter authFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth", r -> r.path("/auth/**")
                        .uri("http://localhost:8081"))

                .route("appointments", r -> r.path("/appointments/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("http://localhost:8083"))

                .route("doctors", r -> r.path("/doctors/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("http://localhost:8082"))

                .route("patients", r -> r.path("/patients/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("http://localhost:8084"))

                .build();
    }
}