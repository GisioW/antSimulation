package ch.epfl.moocprog;

public abstract class Animal extends Positionable {
	
	private double direction;

	public Animal(ToricPosition toricPosition) {
		super(toricPosition);
		this.direction = direction;
	}
	
	public final double getDirection() {
		return direction;
	}


	public void setDirection(double direction) {
		this.direction = direction;
	}
	
	
	public abstract void accept(AnimalVisitor visitor, RenderingMedia s);

}
