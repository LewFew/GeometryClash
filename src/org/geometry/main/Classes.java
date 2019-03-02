package org.geometry.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class Classes extends Entity implements KeyListener{
	
	protected int allyID;
	protected int keySet;
	protected float speed = 5;
	protected boolean[] keysDown = new boolean[4];
	protected float[] speeds = new float[4];
	protected int coolDown1, coolDown2, coolDown3, coolDown4, invinceTimer;
	protected boolean invince;
	protected Rectangle hitBox;

	public Classes(double x, double y, int allyID, int keySet) {
		super(x, y);
		this.allyID = allyID;
		this.keySet = keySet;
		keysDown[0] = false;
		keysDown[1] = false;
		keysDown[2] = false;
		keysDown[3] = false;
		speeds[0] = 0;
		speeds[1] = 0;
		speeds[2] = 0;
		speeds[3] = 0;
	}
	
	@Override
	public void death() {
		GlobalVariables.frame.removeKeyListener(this);
	}
	
	@Override
	public void update() {
		x += (speeds[3] + speeds[1]);
		y += (speeds[0] + speeds[2]);
		
		if (coolDown1 > 0)
			coolDown1--;
		if (coolDown2 > 0)
			coolDown2--;
		if (coolDown3 > 0)
			coolDown3--;
		if (coolDown4 > 0)
			coolDown4--;
		
		collision();
	}
	
	public abstract void basicAttack();
	public abstract void offensive();
	public abstract void defensive();
	public abstract void special();
	
	public abstract void collision();
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (keySet == 0) {
			if (e.getKeyCode() == KeyEvent.VK_W) {
				keysDown[0] = true;
				speeds[0] = -speed;
			}
			if (e.getKeyCode() == KeyEvent.VK_A) {
				keysDown[3] = true;
				speeds[3] = -speed;
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				keysDown[2] = true;
				speeds[2] = speed;
			}
			if (e.getKeyCode() == KeyEvent.VK_D) {
				keysDown[1] = true;
				speeds[1] = speed;
			}
			if (e.getKeyCode() == KeyEvent.VK_C && coolDown1 <= 0) {
				basicAttack();
			}
			if (e.getKeyCode() == KeyEvent.VK_V && coolDown2 <= 0) {
				offensive();
			}
			if (e.getKeyCode() == KeyEvent.VK_B && coolDown3 <= 0) {
				defensive();
			}
			if (e.getKeyCode() == KeyEvent.VK_N && coolDown4 <= 0) {
				special();
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if (keySet == 0) {
			if (e.getKeyCode() == KeyEvent.VK_W) {
				keysDown[0] = false;
				speeds[0] = 0;
			}
			if (e.getKeyCode() == KeyEvent.VK_A) {
				keysDown[3] = false;
				speeds[3] = 0;
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				keysDown[2] = false;
				speeds[2] = 0;
			}
			if (e.getKeyCode() == KeyEvent.VK_D) {
				keysDown[1] = false;
				speeds[1] = 0;
			}
		}
	}

}
