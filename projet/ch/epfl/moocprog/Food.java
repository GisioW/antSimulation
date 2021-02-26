package ch.epfl.moocprog;

public final class Food {

	private ToricPosition position;

	private double quantity;

	public Food(ToricPosition position, double quantity) {
		super();
		this.position = position;
		this.quantity = quantity >= 0.0 ? quantity : 0.0;
	}

	public double takeQuantity(double numberTook) {
		if (numberTook < 0) {
			throw new IllegalArgumentException();
		}
		double result = 0.0;
		double takeResult = getQuantity() - numberTook;
		if (takeResult < 0) {
			result = getQuantity();
			setQuantity(0);
		} else {
			result = numberTook;
			setQuantity(takeResult);
		}
		return result;
	}

	public ToricPosition getPosition() {
		return position;
	}

	public void setPosition(ToricPosition position) {
		this.position = position;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return String.format("Position=%s\nQuantity=%s", position, quantity);
	}

}
