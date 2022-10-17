package com.aonufrei.pathfindingapp;

import com.aonufrei.pathfindingapp.pathfinding.algorithm.AStarAlgorithm;
import com.aonufrei.pathfindingapp.pathfinding.algorithm.PathFindingAlgorithm;
import com.aonufrei.pathfindingapp.pathfinding.algorithm.Point2dUtils;
import com.aonufrei.pathfindingapp.pathfinding.model.Point2d;
import com.aonufrei.pathfindingapp.service.ImageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class PathfindingAppApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PathfindingAppApplication.class, args);
	}

	@Autowired
	private ImageGenerator imageGenerator;

	@Override
	public void run(String... args) throws Exception {
		imageGenerator.createFramesAndSave();
	}
}
