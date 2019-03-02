package org.geometry.main;

import java.awt.Graphics;
import java.util.ArrayList;

public class Bot {
	
	private Player2 p2;
	
	public Bot (Player2 p2) {
		this.p2 = p2;
	}
	
	public void update() {
		
		Player enemy;
		
		Bullet closestBullet = null;
		Slash closestSlash = null;
		
		double closestBulletDistance = Double.MAX_VALUE;
		double closestSlashDistance = Double.MAX_VALUE;
		
		for (int i = 0; i < Main.objects.size(); i++) {
			if (Main.objects.get(i) instanceof Player) {
				enemy = (Player) Main.objects.get(i);
			} else if (Main.objects.get(i) instanceof Bullet) {
				double distance = Math.sqrt(Math.pow(Main.objects.get(i).x - p2.x, 2) + Math.pow(Main.objects.get(i).y - p2.y, 2));
				if (distance < closestBulletDistance) {
					closestBullet = (Bullet)Main.objects.get(i);
					closestBulletDistance = distance;
				}
			} else if (Main.objects.get(i) instanceof Slash) {
				double distance = Math.sqrt(Math.pow(Main.objects.get(i).x - p2.x, 2) + Math.pow(Main.objects.get(i).y - p2.y, 2));
				if (distance < closestSlashDistance) {
					 closestSlash = (Slash) Main.objects.get(i);
					 closestSlashDistance = distance;
				}
			}
		}
		
		if (closestBullet != null) {
			int bulletDirection;
		}
		
		if (closestSlash != null) {
			
		}
		
	}
	
//	private int[] perpSpeed (int direction) {
//		
//		int[] speeds = new int[2];
//		
//		if (direction == 3) {
//			speeds[0] = 0;
//			speeds[1] = 1;
//		}
//	}
	
	private int getDirection(Bullet o) { //1 = up, 2 = top-right, 3 = right, 4 = down-right, 5 = down, 6 = down-left, 7 = left, 8 = up-left
		if (o.velX > 0 && o.velY == 0) {
			return 3;
		} else if (o.velX == 0 && o.velY < 0) {
			return 1;
		} else if (o.velX > 0 && o.velY < 0) {
			return 2;
		} else if (o.velX > 0 && o.velY > 0) {
			return 4;
		} else if (o.velX == 0 && o.velY > 0) {
			return 5;
		} else if (o.velX < 0 && o.velY > 0) {
			return 6;
		} else if (o.velX < 0 && o.velY == 0) {
			return 7;
		} else if (o.velX < 0 && o.velY < 0) {
			return 8;
		} else {
			return 0;
		}
	}

	public void render(Graphics g) {
		
	}
	
	

}
