package org.geometry.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Objects {
	
	protected double x, y;
	protected double xPrevious, yPrevious;
	protected double speed;
	protected int index;
	protected Rectangle hitBox;
	protected boolean exists = true;
	
	public Objects(double x, double y) {
		this.x = x;
		this.y = y;
		//Default Rectangle
		hitBox = new Rectangle();
		hitBox.setBounds((int)x, (int)y, 32, 32);
	}
	
	public abstract void update();
	public abstract void render(Graphics g);

}
