package ch.epfl.moocprog;

import static ch.epfl.moocprog.app.Context.getConfig;
import static ch.epfl.moocprog.config.Config.WORLD_HEIGHT;
import static ch.epfl.moocprog.config.Config.WORLD_WIDTH;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ch.epfl.moocprog.utils.Vec2d;

public final class ToricPosition {

	private Vec2d vec2d;

	// Step 9
	public double toricDistance(ToricPosition position) {
		return entryVec2s(position).getValue();
	}

	// Step 8
	public Vec2d toricVector(ToricPosition position) {
		return entryVec2s(position).getKey().minus(this.toVec2d());
	}

	// Step 8.1
	public Entry<Vec2d, Double> entryVec2s(ToricPosition position) {
		Entry<Vec2d, Double> result = null;
		for (Entry<Vec2d, Double> entry : getDistanceToricPosition(position).entrySet()) {
			if (result == null || result.getValue() > entry.getValue()) {
				result = entry;
			}
		}
		return result;
	}

	// Step 8.1.1
	public Map<Vec2d, Double> getDistanceToricPosition(ToricPosition position) {
		Map<Vec2d, Double> result = new HashMap<>();
		List<Vec2d> vec2ds = new ArrayList<>();
		String INC = "INC";
		String DEC = "DEC";
		int WORLDHEIGHT = getConfig().getInt(WORLD_HEIGHT);
		int WORLDWIDTH = getConfig().getInt(WORLD_WIDTH);

		Vec2d trajet1 = position.toVec2d();
		Vec2d trajet2 = initVec2d(position, 0, WORLDHEIGHT, INC);
		Vec2d trajet3 = initVec2d(position, 0, -WORLDHEIGHT, DEC);
		Vec2d trajet4 = initVec2d(position, WORLDWIDTH, 0, INC);
		Vec2d trajet5 = initVec2d(position, -WORLDWIDTH, 0, DEC);
		Vec2d trajet6 = initVec2d(position, WORLDWIDTH, WORLDHEIGHT, INC);
		Vec2d trajet7 = initVec2d(position, -WORLDWIDTH, WORLDHEIGHT, INC);
		Vec2d trajet8 = initVec2d(position, WORLDWIDTH, -WORLDHEIGHT, INC);
		Vec2d trajet9 = initVec2d(position, -WORLDWIDTH, -WORLDHEIGHT, INC);

		vec2ds.add(trajet1);
		vec2ds.add(trajet2);
		vec2ds.add(trajet3);
		vec2ds.add(trajet4);
		vec2ds.add(trajet5);
		vec2ds.add(trajet6);
		vec2ds.add(trajet7);
		vec2ds.add(trajet8);
		vec2ds.add(trajet9);

		for (Vec2d vec2d : vec2ds) {
			result.put(vec2d, vec2d.distance(this.toVec2d()));
		}

		return result;
	}

	// Step 8.1.1.b
	private Vec2d initVec2d(ToricPosition position, int width, int height, String operation) {
		Vec2d vectorPosition = position.toVec2d();
		Vec2d vectorInit = new Vec2d(width, height);
		return operation.equals("INC") ? vectorPosition.add(vectorInit)
				: operation.equals("DEC") ? vectorPosition.add(vectorInit) : this.toVec2d();
	}

	// Step 7
	public Vec2d toVec2d() {
		return clampedPosition(this.vec2d.getX(), this.vec2d.getY());
	}

	// Step 6
	public ToricPosition add(Vec2d vec) {
		return new ToricPosition(this.vec2d.add(vec));
	}

	// Step 5
	public ToricPosition add(ToricPosition that) {
		return new ToricPosition(this.vec2d.add(that.getVec2d()));
	}

	// Step 4
	public ToricPosition() {
		super();
		this.vec2d = new Vec2d(0, 0);
	}

	// Step 3
	public ToricPosition(Vec2d vec2d) {
		super();
		this.vec2d = clampedPosition(vec2d.getX(), vec2d.getY());
	}

	// Step 2
	public ToricPosition(double x, double y) {
		super();
		this.vec2d = clampedPosition(x, y);
	}

	// Step 1
	private static Vec2d clampedPosition(double x, double y) {
		int heigth = getConfig().getInt(WORLD_HEIGHT);
		int width = getConfig().getInt(WORLD_WIDTH);
		x = x < 0 ? loopIfNegative(x, width) : x >= width ? loopIfExceedMax(x, width) : x;
		y = y < 0 ? loopIfNegative(y, heigth) : y >= heigth ? loopIfExceedMax(y, heigth) : y;
		return new Vec2d(x, y);

	}

	// Step 1.a
	private static double loopIfNegative(double value, int minPosition) {
		while (value < 0) {
			value += minPosition;
		}
		;
		return value;
	}

	// Step 1.b
	private static double loopIfExceedMax(double value, int maxPosition) {
		while (value >= maxPosition) {
			value -= maxPosition;
		}
		;
		return value;
	}

	public void setVec2d(Vec2d vec2d) {
		this.vec2d = vec2d;
	}

	public Vec2d getVec2d() {
		return vec2d;
	}

	@Override
	public String toString() {
		return vec2d.toString();
	}

}
