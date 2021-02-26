package ch.epfl.moocprog;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import ch.epfl.moocprog.utils.Time;
import ch.epfl.moocprog.utils.Utils;

public final class Environment implements FoodGeneratorEnvironmentView {

	private FoodGenerator foodGenrator;

	private List<Food> foods;

	public Environment() {
		super();
		this.foodGenrator = new FoodGenerator();
		this.foods = new LinkedList<>();
	}

	@Override
	public void addFood(Food food) {
		Utils.requireNonNull(food);
		this.foods.add(food);
		setFoodGenrator(foodGenrator);
	}

	public void update(Time dt) {
		this.foodGenrator.update(this, dt);
		this.foods.removeIf(food -> food.getQuantity() == 0.0);
	}

	public List<Double> getFoodQuantities() {
		return this.foods.stream().mapToDouble(Food::getQuantity).boxed().collect(Collectors.toList());
	}

	public FoodGenerator getFoodGenrator() {
		return foodGenrator;
	}

	public void setFoodGenrator(FoodGenerator foodGenrator) {
		this.foodGenrator = foodGenrator;
	}

}
