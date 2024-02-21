package com.drone.docs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "DroneDispatch Application Programmable Interfaces",
                version = "1.0",
                description = "An application to provide api to the DroneDispatch. ",
                license = @License(name = ""),
                contact = @Contact(name = "", url = "", email = "")
        ),
        tags = {
                @Tag(name = "DroneDispatchAPIs", description = "APIs related to DroneDispatch Application")
        },
        servers = {
                @Server(
                        url = "${server.servlet.context-path}",
                        description = "Default server URL"
                )
        }
)
public class SpringdocConfig {
    private static final String packagesToScan = "com.drone.api";
    private static final String pathToActuator = "/actuator/**";
    public static final String pathToActuatorHealth = "/actuator/health";
    private static final String actuatorGroupName = "Actuator";
    private static final String droneDispatchApiGroupName = "DroneDispatchAPIs";


    @Bean
    @Profile("!local")
    public GroupedOpenApi CineVPSLogApi() {
        return GroupedOpenApi.builder()
                .group(droneDispatchApiGroupName)
                .packagesToScan(packagesToScan)
                .addOpenApiCustomizer(openApi -> {
                    // Add bearer authentication to the security schemes
                    openApi.getComponents().addSecuritySchemes("bearerAuth", new SecurityScheme()
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT"));
                    openApi.addSecurityItem(new SecurityRequirement().addList("bearerAuth", Arrays.asList("read access", "write access")));

                    // Add the bearer authentication to all operations
                    List<SecurityRequirement> security = new ArrayList<>();
                    security.add(new SecurityRequirement().addList("bearerAuth", Arrays.asList("read access", "write access")));
                    openApi.getPaths().values().forEach(pathItem -> {
                        pathItem.readOperations().forEach(operation -> {
                            operation.setSecurity(security);
                        });
                    });
                })
                .build();
    }

    @Bean
    @Profile("local")
    public GroupedOpenApi localApi() {
        return GroupedOpenApi.builder()
                .group(droneDispatchApiGroupName)
                .packagesToScan(packagesToScan)
                .build();
    }

    @Bean
    @Profile("!local")
    public GroupedOpenApi CineVPSLogActuator() {
        return GroupedOpenApi.builder()
                .group(actuatorGroupName)
                .pathsToMatch(pathToActuator)
                .addOpenApiCustomizer(openApi -> {
                    // Add bearer authentication to the security schemes
                    openApi.getComponents().addSecuritySchemes("bearerAuth", new SecurityScheme()
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT"));
                    openApi.addSecurityItem(new SecurityRequirement().addList("bearerAuth", Arrays.asList("read access", "write access")));

                    // Add the bearer authentication to all operations
                    List<SecurityRequirement> security = new ArrayList<>();
                    security.add(new SecurityRequirement().addList("bearerAuth", Arrays.asList("read access", "write access")));
                    openApi.getPaths().keySet().forEach(path -> {
                        if (!pathToActuatorHealth.equals(path)) {
                            PathItem pathItem = openApi.getPaths().get(path);
                            pathItem.readOperations().forEach(operation -> {
                                operation.setSecurity(security);
                            });
                        }
                    });
                })
                .build();
    }

    @Bean
    @Profile("local")
    public GroupedOpenApi localActuator() {
        return GroupedOpenApi.builder()
                .group(actuatorGroupName)
                .pathsToMatch(pathToActuator)
                .build();
    }

}
