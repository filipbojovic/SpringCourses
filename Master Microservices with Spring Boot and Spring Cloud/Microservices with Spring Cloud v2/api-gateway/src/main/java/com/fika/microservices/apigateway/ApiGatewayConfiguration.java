package com.fika.microservices.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        // using builder we customize the route we want
        // redirect it to httpbin
        return builder.routes()
                .route(p -> p.path("/get") // if the request comes to /get
                        .filters(f -> f.addRequestHeader("MyHeader", "MyURI")
                                .addRequestParameter("Param", "MyValue")) // add headers
                        .uri("http://httpbin.org:80")) // define a function that def
                .route(p -> p.path("/currency-exchange/**")
                        .uri("lb://currency-exchange")) // 'lb' means to talk to eureka, find this service and load balance it
                .route(p -> p.path("/currency-conversion/**")
                        .uri("lb://currency-conversion"))
                .route(p -> p.path("/currency-conversion-feign/**")
                        .uri("lb://currency-conversion"))
                .route(p -> p.path("/currency-conversion-new/**") // if we want to redirect this url to the new url
                        .filters(f -> f.rewritePath(
                                "/currency-conversion-new/(?<segment>.*)", // string to replace.
                                "/currency-conversion-feign/${segment}" // string to replace with
                        ))
                        .uri("lb://currency-conversion"))
                .build();
    }
}
