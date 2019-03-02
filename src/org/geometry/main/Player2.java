package org.geometry.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Player2 extends Entity implements KeyListener{
	
	protected Color c;
	protected int[] speeds = new int[4];
	protected boolean canShoot = true;
	protected int pos = 0;
	Image arrow;
	Image arrowRight;
	Image arrowLeft;
	Image arrowDown;
	protected double sx = 1;
	protected int swords = 10;
	protected int shields = 10;
	protected boolean canSlash = true;
	int cd = 0;
	int cd2 = 0;
	int cd3 = 0;
	int speed = 5;
	protected boolean canDash = true;
	protected boolean dashing = false;
	protected Graphics2D g2d;
	int dashingTime = 0;
	int invince = 0;

	public Player2(double x, double y) {
		super(x, y);
		Random r = new Random();
		c = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
		GlobalVariables.frame.addKeyListener(this);
		speeds[0] = 0; //Up
		speeds[1] = 0; //Right
		speeds[2] = 0; //Down
		speeds[3] = 0; //Left
		
		arrow = new ImageIcon(getClass().getResource("/res/Arrow.png")).getImage();
		arrowRight = new ImageIcon(getClass().getResource("/res/ArrowRight.png")).getImage();
		arrowLeft = new ImageIcon(getClass().getResource("/res/ArrowLeft.png")).getImage();
		arrowDown = new ImageIcon(getClass().getResource("/res/ArrowDown.png")).getImage();
	}

	@Override
	public void update() {
		
		if (cd > 0)
			cd--;
		else if (cd <= 0)
			canShoot = true;
		
		if (cd2 > 0)
			cd2--;
		else if (cd2 <= 0)
			canSlash = true;
		
		if (cd3 > 0)
			cd3--;
		if (cd3 <= 0)
			canDash = true;
		
		if (dashing) {
			if (dashingTime > 0) {
				dashingTime--;
			} else if (dashingTime <= 0) {
				dashing = false;
				speed = 5;
			}
		}
		
		if (invince > 0)
			invince --;
		
		y -= speeds[0];
		y += speeds[2];
		x += speeds[1];
		x -= speeds[3];
		
		hitBox.x = (int) x;
		hitBox.y = (int) y;
		
		for (int i = 0; i < GlobalVariables.handler.objects.size(); i++) {
			if (hitBox.intersects(GlobalVariables.handler.objects.get(i).hitBox)) {
				if (GlobalVariables.handler.objects.get(i) instanceof Entity) {
					Entity a = (Entity) GlobalVariables.handler.objects.get(i);
					if (a instanceof Bullet) {
						health -= 10;
						a.death();
					}
					if (a instanceof Bullet2) {
						if (((Bullet2) a).betrayal) {
							System.out.println("BILLAL");
							health -= 5;
							a.death();
						}
					}
					if (a instanceof Slash && invince == 0) {
						if (((Slash) a).counter > 5 && (((Slash) a).counter) < 12) {
							health -= 20;
							invince = 10;
						}
					}
				}else if (GlobalVariables.handler.objects.get(i) instanceof Block) {
					x = xPrevious;
					y = yPrevious;
				}
			}
		}
		
		if (health <= 0) {
			exists = false;
		}
		
		xPrevious = x;
		yPrevious = y;
	}
	
	@Override
	public void death() {
		GlobalVariables.frame.removeKeyListener(this);
		exists = false;
	}

	@Override
	public void render(Graphics g) {
		g2d = (Graphics2D) g;
		g.setColor(c);
		g.fillRect((int)x, (int)y, 32, 32);
		g2d.setColor(c.darker());
		g2d.setStroke(new BasicStroke(3));
		g2d.drawRect((int)x, (int)y, 32, 32);
		//System.out.println(x + " " + y);
		g.setColor(Color.GREEN);
		g.fillRect((int)x - 35, (int)y - 32, (int)health, 10);
		
		if (pos == 0)  {
			g.drawImage(arrow, (int)x, (int)y - 42, (int) (32 * sx), (int) (32 * sx), null);
		} else if (pos == 1) {
			g.drawImage(arrowRight, (int)x + 42, (int)y, (int) (32 * sx), (int) (32 * sx), null);
		} else if (pos == 2) {
			g.drawImage(arrowDown, (int)x, (int)y + 42, (int) (32 * sx), (int) (32 * sx), null);
		} else if (pos == 3) {
			g.drawImage(arrowLeft, (int)x - 42, (int)y, (int) (32 * sx), (int) (32 * sx), null);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			pos = 0;
			speeds[0] = speed;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			pos = 2;
			speeds[2] = speed;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			pos = 3;
			speeds[3] = speed;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			pos = 1;
			speeds[1] = speed;
		}
		
		if (e.getKeyCode() == 91) {
			if (cd2 <= 0) {
				canSlash = false;
				cd2 = 50;
				GlobalVariables.handler.addObject(new Slash2(x, y, pos, this));
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_P) {
			if (cd3 <= 0) {
				cd3 = 500;
				canDash = false;
				dashing = true;
				dashingTime = 50;
				speed = 10;
			}
		}
		
		
		
		if (e.getKeyCode() == 93 && canShoot) {
			canShoot = false;
			cd = 30;
			if (pos == 0)
				GlobalVariables.handler.addObject(new Bullet2(x + 8, y, 0, -10));
			if (pos == 1)
				GlobalVariables.handler.addObject(new Bullet2(x, y + 8, 10, 0));
			if (pos == 2)
				GlobalVariables.handler.addObject(new Bullet2(x + 8, y, 0, 10));
			if (pos == 3)
				GlobalVariables.handler.addObject(new Bullet2(x, y + 8, -10, 0));
		}
		
		//JOptionPane.showMessageDialog(null, "vrejiomsa");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			speeds[0] = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			speeds[2] = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			speeds[3] = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			speeds[1] = 0;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			//canShoot = true;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_PERIOD) {
			canSlash = true;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
