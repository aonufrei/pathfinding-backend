package com.aonufrei.pathfindingapp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Pathfinding Application API", description = "API to find shortest path between start point and target point"))
public class PathfindingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PathfindingAppApplication.class, args);
	}

}
