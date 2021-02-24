package ch.epfl.moocprog;

public final class Positionable {
	private ToricPosition position;

	public Positionable() {
		super();
		this.position = new ToricPosition(0.0, 0.0);
	}

	public Positionable(ToricPosition position) {
		super();
		this.position = position;
	}

	public ToricPosition getPosition() {
		return position;
	}

	protected final void setPosition(ToricPosition position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return position.toString();
	}

}
