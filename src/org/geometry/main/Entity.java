package org.geometry.main;

public abstract class Entity extends Objects{
	
	protected double health;

	public Entity(double x, double y) {
		super(x, y);
		health = 100;
	}
	
	public void life() {
		if (health <= 0) {
			//death();
		}
	}
	
	public void death() {
		exists = false;
	}

}
