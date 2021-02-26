package ch.epfl.moocprog.tests;

import static ch.epfl.moocprog.app.Context.getConfig;
import static ch.epfl.moocprog.config.Config.FOOD_GENERATOR_DELAY;
import static ch.epfl.moocprog.config.Config.WORLD_HEIGHT;
import static ch.epfl.moocprog.config.Config.WORLD_WIDTH;

import java.io.File;

import ch.epfl.moocprog.Environment;
import ch.epfl.moocprog.Food;
import ch.epfl.moocprog.ToricPosition;
import ch.epfl.moocprog.app.ApplicationInitializer;
import ch.epfl.moocprog.config.ImmutableConfigManager;
import ch.epfl.moocprog.utils.Time;
import ch.epfl.moocprog.utils.Vec2d;

public class Main {

	public static void main(String[] args) {
		ApplicationInitializer.initializeApplication(new ImmutableConfigManager(new File("res/app.cfg")));
		int width = getConfig().getInt(WORLD_WIDTH);
		int height = getConfig().getInt(WORLD_HEIGHT);

		ToricPosition tp1 = new ToricPosition();
		ToricPosition tp2 = new ToricPosition(1.2, 2.3);
		ToricPosition tp3 = new ToricPosition(new Vec2d(4.5, 6.7));
		ToricPosition tp4 = tp3.add(tp2);
		ToricPosition tp5 = new ToricPosition(width, height);
		ToricPosition tp6 = new ToricPosition(width / 2, height / 2);
		ToricPosition tp7 = tp4.add(tp6.add(new Vec2d(width / 2, height / 2)));
		ToricPosition tp8 = new ToricPosition(3, 4);
		Vec2d v1 = tp2.toricVector(tp3);

		Food f1 = new Food(tp2, 4.7);
		Food f2 = new Food(tp3, 6.7);
		System.out.println();
		System.out.println("Some tests for Food");
		System.out.println(f1);
		System.out.println(
				"Initial : " + f1.getQuantity() + ", taken : " + f1.takeQuantity(5.0) + ", left : " + f1.getQuantity());
		System.out.println(
				"Initial : " + f2.getQuantity() + ", taken : " + f2.takeQuantity(2.0) + ", left : " + f2.getQuantity());

		final Time foodGenDelta = getConfig().getTime(FOOD_GENERATOR_DELAY);

		Environment env = new Environment();

		env.addFood(f1);
		env.addFood(f2);
		System.out.println();
		System.out.println("Some tests for Environment");
		System.out.println("Inital food quantities : " + env.getFoodQuantities());
		System.out.println(foodGenDelta.toString());
		env.update(foodGenDelta);
		System.out.println("After update : " + env.getFoodQuantities());

		System.out.println("############################");

	}
}
