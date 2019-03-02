package org.geometry.main;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Block extends Objects{
	
	Image img;

	public Block(double x, double y) {
		super(x, y);
		img = new ImageIcon(getClass().getResource("/res/Brick.png")).getImage();
	}

	@Override
	public void update() {
		hitBox.x = (int) x;
		hitBox.y = (int) y;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(img, (int)x, (int)y, null);
		//g.fillRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
	}

}
