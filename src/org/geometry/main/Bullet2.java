package org.geometry.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Bullet2 extends Entity{
	
	Image img1, img2, img3, img4;
	double velX, velY;
	float alpha = 1;
	Color c = new Color(100, 150, 255, 255);
	Color c2 = new Color(100, 150, 255, 255);
	int k = 0;
	int xTrail, yTrail;
	boolean betrayal = false;

	public Bullet2(double x, double y, double velX, double velY) {
		super(x, y);
		this.velX = velX;
		this.velY = velY;
		if (velX != 0) {
			hitBox.width = 32;
			hitBox.height = 16;
		} else if (velY != 0) {
			hitBox.width = 16;
			hitBox.height = 32;
		}
		img1 = new ImageIcon(getClass().getResource("/res/Sword.png")).getImage();
		img2 = new ImageIcon(getClass().getResource("/res/SwordRight.png")).getImage();
		img3 = new ImageIcon(getClass().getResource("/res/SwordDown.png")).getImage();
		img4 = new ImageIcon(getClass().getResource("/res/SwordLeft.png")).getImage();
	}

	@Override
	public void update() {
		health -= 0.1;
		x += velX;
		y += velY;
		
		hitBox.x = (int) x;
		hitBox.y = (int) y;
		
		for (int i = 0; i < GlobalVariables.handler.objects.size(); i++) {
			if (hitBox.intersects(GlobalVariables.handler.objects.get(i).hitBox)) {
				if (GlobalVariables.handler.objects.get(i) instanceof Block) {
					exists = false;
				}
				if (GlobalVariables.handler.objects.get(i) instanceof Slash && !betrayal) {
					velX *= -1;
					velY *= -1;
					betrayal = true;
				}
				if (GlobalVariables.handler.objects.get(i) instanceof Slash2 && betrayal) {
					velX *= -1;
					velY *= -1;
					betrayal = false;
				}
			}
		}
		
		if (health <= 0) {
			exists = false;
		}
	}

	@Override
	public void render(Graphics g) {
		
		//g.setColor(c);
		//g.fillOval((int)x, (int)y, 16, 16);
	//GlobalVariables.handler.addObject(new Particle1(x, y, c2, 16, 16, 0.01f));
		//g.fillRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
		
		if (velX > 0)
			g.drawImage(img2, (int)x, (int)y, null);
		if (velX < 0)
			g.drawImage(img4, (int)x, (int)y, null);
		if (velY > 0)
			g.drawImage(img3, (int)x, (int)y, null);
		if (velY < 0)
			g.drawImage(img1, (int)x, (int)y, null);
	}

}