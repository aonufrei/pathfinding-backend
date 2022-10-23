package com.aonufrei.pathfindingapp.service;

import com.aonufrei.pathfindingapp.pathfinding.algorithm.AStarAlgorithm;
import com.aonufrei.pathfindingapp.pathfinding.algorithm.Point2dUtils;
import com.aonufrei.pathfindingapp.pathfinding.model.IterationChanges;
import com.aonufrei.pathfindingapp.pathfinding.model.Point2d;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Service
public class ImageGenerator {

	private static final int ROUTE_COLOR = rgb(92, 184, 228);
	private static final int PROCESSED_COLOR =  rgb(242, 242, 242);
	private static final int PROCESSING_COLOR = rgb(135, 88, 255);
	private static final int BACKGROUND_COLOR = rgb(24, 24, 24);

	private static final int WALL_COLOR = rgb(232, 15, 136);
	private static final int START_COLOR = rgb(255, 0, 0);
	private static final int FINISH_COLOR = rgb(0, 0, 255);

	private static final Point2dUtils point2dUtils = new Point2dUtils();
//	public void createFramesAndSave() {
//		AStarAlgorithm<Point2d> algorithm = new AStarAlgorithm<>();
//		algorithm.findShortestPath(new Point2d(0, 0), new Point2d(7, 3), );
//		List<IterationChanges<Point2d>> algorithmIterations = algorithm.getLastIterationChanges();
//
//		int width = 12, height = 7;
//
//		List<BufferedImage> frames = new LinkedList<>();
//		for (int i = 0; i < algorithmIterations.size(); i++) {
//			BufferedImage prevBufferedImage = null;
//			if (i != 0) {
//				prevBufferedImage = frames.get(i - 1);
//			}
//			frames.add(drawImage(width, height, algorithmIterations.get(i), prevBufferedImage));
//		}
//
//		frames.forEach(bi -> {
//			bi.setRGB(3, 4, START_COLOR);
//			bi.setRGB(10, 1, FINISH_COLOR);
//		});
//
//		frames.forEach(forEachWithIndex((i, bi) -> {
//			try {
//				File f = new File(String.format("D:\\frames\\frame_%d.png", i));
//				ImageIO.write(bi, "png", f);
//			}
//			catch (IOException e) {
//				System.out.println("Error: " + e);
//			}
//		}));
//	}

	private <T> Consumer<T> forEachWithIndex(BiConsumer<Long, T> consumer) {
		AtomicLong count = new AtomicLong(-1);
		return o -> consumer.accept(count.incrementAndGet(), o);

	}

	private BufferedImage drawImage(int width, int height, IterationChanges<Point2d> iterationChanges, BufferedImage prev) {
		BufferedImage frame = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		if (prev != null) {
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					frame.setRGB(x, y, prev.getRGB(x, y));
				}
			}
		} else {
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					if (point2dUtils.collidesObstacles(new Point2d(x - 3, (y - 4) * (-1)))) {
						frame.setRGB(x, y, WALL_COLOR);
						continue;
					}
					frame.setRGB(x, y, BACKGROUND_COLOR);
				}
			}
		}

		iterationChanges.getAreAlreadyProcessed().forEach(p -> frame.setRGB(p.getX() + 3, p.getY() * -1 + 4, PROCESSED_COLOR));
		iterationChanges.getAreProcessing().forEach(p -> frame.setRGB(p.getX() + 3, p.getY() * -1 + 4, PROCESSING_COLOR));
//		iterationChanges.getRoute().forEach((p -> frame.setRGB(p.getX() + 3, p.getY() * -1 + 4, ROUTE_COLOR)));
		return frame;
	}

	private static int rgb(int r, int g, int b) {
		return (255<<24) | (r<<16) | (g<<8) | b;
	}

}
