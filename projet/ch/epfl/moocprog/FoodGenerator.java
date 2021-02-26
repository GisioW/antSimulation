package ch.epfl.moocprog;

import static ch.epfl.moocprog.app.Context.getConfig;
import static ch.epfl.moocprog.config.Config.FOOD_GENERATOR_DELAY;
import static ch.epfl.moocprog.config.Config.NEW_FOOD_QUANTITY_MAX;
import static ch.epfl.moocprog.config.Config.NEW_FOOD_QUANTITY_MIN;
import static ch.epfl.moocprog.config.Config.WORLD_HEIGHT;
import static ch.epfl.moocprog.config.Config.WORLD_WIDTH;

import ch.epfl.moocprog.random.NormalDistribution;
import ch.epfl.moocprog.random.UniformDistribution;
import ch.epfl.moocprog.utils.Time;

public final class FoodGenerator {
	private Time time;

	public FoodGenerator() {
		super();
		this.time = Time.ZERO;
	}

	public void update(FoodGeneratorEnvironmentView env, Time dt) {
		if (dt.equals(Time.ZERO)) {
			return;
		}
		getTime().plus(dt);
		Time generatorDelay = getConfig().getTime(FOOD_GENERATOR_DELAY);
		int mu = getConfig().getInt(WORLD_WIDTH);
		int sigma2 = getConfig().getInt(WORLD_HEIGHT);
		double minQuantity = getConfig().getDouble(NEW_FOOD_QUANTITY_MIN);
		double maxQuantity = getConfig().getDouble(NEW_FOOD_QUANTITY_MAX);
		double ranadomQuantity = UniformDistribution.getValue(minQuantity, maxQuantity);
		do {
			getTime().minus(dt);
			Food food = new Food(
					new ToricPosition(NormalDistribution.getValue(mu, sigma2), NormalDistribution.getValue(mu, sigma2)),
					ranadomQuantity);
			env.addFood(food);
		} while (getTime().compareTo(generatorDelay) >= 0);

	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

}
