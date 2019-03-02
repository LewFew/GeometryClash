package org.geometry.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Slash extends Entity{
	
	protected int pos;
	protected Image[] upSlash = new Image[5];
	protected Image[] downSlash = new Image[5];
	protected Image[] rightSlash = new Image[5];
	protected Image[] leftSlash = new Image[5];
	protected Objects user;
	protected int counter = 0;
	
	protected Image upSlash1;
	protected Image upSlash2;
	protected Image upSlash3;
	protected Image upSlash4;
	protected Image upSlash5;
	
	protected Image downSlash1;
	protected Image downSlash2;
	protected Image downSlash3;
	protected Image downSlash4;
	protected Image downSlash5;
	
	protected Image rightSlash1;
	protected Image rightSlash2;
	protected Image rightSlash3;
	protected Image rightSlash4;
	protected Image rightSlash5;
	
	protected Image leftSlash1;
	protected Image leftSlash2;
	protected Image leftSlash3;
	protected Image leftSlash4;
	protected Image leftSlash5;
	
	protected int framePrev = 0;

	public Slash(double x, double y, int pos, Objects user) {
		super(x, y);
		
		this.pos = pos;
		this.user = user;
		
		upSlash1 = new ImageIcon(getClass().getResource("/res/RedSlash1Up.png")).getImage();
		upSlash2 = new ImageIcon(getClass().getResource("/res/RedSlash2Up.png")).getImage();
		upSlash3 = new ImageIcon(getClass().getResource("/res/RedSlash3Up.png")).getImage();
		upSlash4 = new ImageIcon(getClass().getResource("/res/RedSlash4Up.png")).getImage();
		upSlash5 = new ImageIcon(getClass().getResource("/res/RedSlash5Up.png")).getImage();
		
		downSlash1 = new ImageIcon(getClass().getResource("/res/RedSlash1Down.png")).getImage();
		downSlash2 = new ImageIcon(getClass().getResource("/res/RedSlash2Down.png")).getImage();
		downSlash3 = new ImageIcon(getClass().getResource("/res/RedSlash3Down.png")).getImage();
		downSlash4 = new ImageIcon(getClass().getResource("/res/RedSlash4Down.png")).getImage();
		downSlash5 = new ImageIcon(getClass().getResource("/res/RedSlash5Down.png")).getImage();
		
		rightSlash1 = new ImageIcon(getClass().getResource("/res/RedSlash1Right.png")).getImage();
		rightSlash2 = new ImageIcon(getClass().getResource("/res/RedSlash2Right.png")).getImage();
		rightSlash3 = new ImageIcon(getClass().getResource("/res/RedSlash3Right.png")).getImage();
		rightSlash4 = new ImageIcon(getClass().getResource("/res/RedSlash4Right.png")).getImage();
		rightSlash5 = new ImageIcon(getClass().getResource("/res/RedSlash5Right.png")).getImage();
		
		leftSlash1 = new ImageIcon(getClass().getResource("/res/RedSlash1Left.png")).getImage();
		leftSlash2 = new ImageIcon(getClass().getResource("/res/RedSlash2Left.png")).getImage();
		leftSlash3 = new ImageIcon(getClass().getResource("/res/RedSlash3Left.png")).getImage();
		leftSlash4 = new ImageIcon(getClass().getResource("/res/RedSlash4Left.png")).getImage();
		leftSlash5 = new ImageIcon(getClass().getResource("/res/RedSlash5Left.png")).getImage();
		
		
		upSlash[0] = upSlash1;
		upSlash[1] = upSlash2;
		upSlash[2] = upSlash3;
		upSlash[3] = upSlash4;
		upSlash[4] = upSlash5;
		
		downSlash[0] = downSlash1;
		downSlash[1] = downSlash2;
		downSlash[2] = downSlash3;
		downSlash[3] = downSlash4;
		downSlash[4] = downSlash5;
		
		rightSlash[0] = rightSlash1;
		rightSlash[1] = rightSlash2;
		rightSlash[2] = rightSlash3;
		rightSlash[3] = rightSlash4;
		rightSlash[4] = rightSlash5;
		
		leftSlash[0] = leftSlash1;
		leftSlash[1] = leftSlash2;
		leftSlash[2] = leftSlash3;
		leftSlash[3] = leftSlash4;
		leftSlash[4] = leftSlash5;
		
		if (pos == 0) {
			hitBox.setBounds((int) (user.x - 188/2.5), (int) (user.y - 182/2.5), 180, (int) (182 / 2.5));
		}
		if (pos == 1) {
			hitBox.setBounds((int) ((user.x - 188/2.5) + 188 / 2), (int) (user.y - 182/2.5), (int) (188 / 2.5), 176);
		}
		if (pos == 2) {
			hitBox.setBounds((int) (user.x - 188/2.5), (int) ((user.y - 182/2.5) + 182 / 2), 180, (int) (182 / 2.5));
		}
		if (pos == 3) {
			hitBox.setBounds((int) (user.x - 188/2.5), (int) (user.y - 182/2.5), (int) (188 / 2.5), 176);
		}
	}

	@Override
	public void update() {
		x = user.x - 188/2.5;
		y = user.y - 182/2.5;
		
		if (pos == 0) {
			hitBox.x = (int) (user.x - 188/2.5);
			hitBox.y = (int) (int) (user.y - 182/2.5);
		}
		if (pos == 1) {
			hitBox.x = (int) ((user.x - 188/2.5) + 188 / 2);
			hitBox.y = (int) (user.y - 182/2.5);
		}
		if (pos == 2) {
			hitBox.x = (int) (user.x - 188/2.5);
			hitBox.y = (int) ((user.y - 182/2.5) + 182 / 2);
		}
		if (pos == 3) {
			hitBox.x = (int) (user.x - 188/2.5);
			hitBox.y = (int) (user.y - 182/2.5);
		}
		
		for (int i = 0; i < GlobalVariables.handler.objects.size(); i++) {
			if (hitBox.intersects(GlobalVariables.handler.objects.get(i).hitBox)) {
				if (GlobalVariables.handler.objects.get(i) instanceof Block && counter >= 5) {
					exists = false;
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		
		//g.setColor(Color.BLUE);
		//g.fillRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
		
		if (counter < 15) {
		
			if (pos == 0) {
				if (counter % 3 == 0 || counter == 0) {
					g.drawImage(upSlash[counter/3], (int) x, (int) y, null);
					framePrev = counter/3;
				} else {
					g.drawImage(upSlash[framePrev], (int) x, (int) y, null);
				}
			} else if (pos == 1) {
				if (counter % 3 == 0 || counter == 0) {
					g.drawImage(rightSlash[counter/3], (int) x, (int) y, null);
					framePrev = counter/3;
				} else {
					g.drawImage(rightSlash[framePrev], (int) x, (int) y, null);
				}
			} else if (pos == 2) {
				if (counter % 3 == 0 || counter == 0) {
					g.drawImage(downSlash[counter/3], (int) x, (int) y, null);
					framePrev = counter/3;
				} else {
					g.drawImage(downSlash[framePrev], (int) x, (int) y, null);
				}
			} else if (pos == 3) {
				if (counter % 3 == 0 || counter == 0) {
					g.drawImage(leftSlash[counter/3], (int) x, (int) y, null);
					framePrev = counter/3;
				} else {
					g.drawImage(leftSlash[framePrev], (int) x, (int) y, null);
				}
			}
		} else {
			exists = false;
		}
		counter++;
	} 

}
