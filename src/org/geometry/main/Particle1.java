package org.geometry.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Particle1 extends Entity{
	
	Graphics2D g2d;
	float alpha = 1;
	Color c;
	int width, height;
	float life;

	public Particle1(double x, double y, Color c, int width, int height, float life) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.c = c;
		this.width = width;
		this.height = height;
		this.life = life;
	}

	@Override
	public void update() {
		if (alpha > life) {
			alpha -= life - 0.01f;
		} else {
			death();
		}
	}

	@Override
	public void render(Graphics g) {
		
		g2d = (Graphics2D) g;
		g2d.setComposite(setTransparency(alpha));
		
		g.setColor(c);
		g.fillOval((int)x, (int)y, width, height);
		
		g2d.setComposite(setTransparency(1));
	}
	
	private AlphaComposite setTransparency(float alpha) {
		int type = AlphaComposite.SRC;
		System.out.println(alpha);
		return (AlphaComposite.getInstance(type, alpha));
	}

}
