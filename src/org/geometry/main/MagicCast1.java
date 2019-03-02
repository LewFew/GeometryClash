package org.geometry.main;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class MagicCast1 extends Entity{
	
	Image magicCast1;
	Image magicCast2;
	Image magicCast3;
	Image magicCast4;
	Image magicCast5;
	
	Image[] images = new Image[5];
	
	int c = 0;
	int pos;

	public MagicCast1(double x, double y, int pos) {
		super(x, y);
		
		magicCast1 = new ImageIcon(getClass().getResource("/res/MagicCast1.png")).getImage();
		magicCast2 = new ImageIcon(getClass().getResource("/res/MagicCast2.png")).getImage();
		magicCast3 = new ImageIcon(getClass().getResource("/res/MagicCast3.png")).getImage();
		magicCast4 = new ImageIcon(getClass().getResource("/res/MagicCast4.png")).getImage();
		magicCast5 = new ImageIcon(getClass().getResource("/res/MagicCast5.png")).getImage();
		
		images[0] = magicCast1;
		images[1] = magicCast2;
		images[2] = magicCast3;
		images[3] = magicCast4;
		images[4] = magicCast5;
		
		this.pos = pos;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		if (c < 15) {
			if (pos == 0) {
				if (c % 3 == 0 || c == 0) {
				
				}
			}
			if (pos == 1) {
				if (c % 3 == 0 || c == 0) {
					g.drawImage(images[c/3], (int)x, (int)y, null);
				}
			}
		} else if (c >= 15) {
			death();
		}
		c++;
	}
	
}
