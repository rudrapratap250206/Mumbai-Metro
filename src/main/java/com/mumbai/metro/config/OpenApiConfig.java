package com.mumbai.metro.config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
    title = "Mumbai Metro and Public Transport API",
    version = "1.0",
    description = "API documentation for stations, metro lines, bus routes, stops, journeys and schedules."
))
public class OpenApiConfig {}
