package com.akazimour.GATEWAY;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

//	@Bean
//	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
//		return builder.routes()
//				.route(r->r.path("/api/companies/**")
//						.uri("http://localhost:8082"))
//				.route(r->r.path("/api/jobs/**")
//						.uri("http://localhost:8081"))
//				.route(r->r.path("/api/reviews/**")
//						.uri("http://localhost:8083"))
//				.build();
//	}
}
