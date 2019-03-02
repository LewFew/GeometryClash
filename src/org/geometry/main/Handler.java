package org.geometry.main;

import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.LinkedList;

public class Handler {
	
	ArrayList<Objects> objects = new ArrayList<Objects>();
	int idToGive = 0;
	
	Graphics g;
	
	public Handler(Graphics g) {
		this.g = g;
	}
	
	public void update() {
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i).exists) {
				if (objects.get(i) instanceof Entity)
					((Entity) objects.get(i)).life();
				objects.get(i).update();
			} else {
				objects.remove(i);
			}
		}
	}
	
	public ArrayList<Objects> getObjects() {
		return objects;
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).render(g);
		}
	}
	
	public void addObject(Objects obj) {
		objects.add(obj);
	}
	
	public void removeObject(int n) {
		for (int i = 0; i < objects.size(); i++) {
			if (n == objects.get(i).index) {
				objects.remove(i);
				//idToGive--;
			}
		}
	}
	
	public void removeAll() {
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i) instanceof Player || objects.get(i) instanceof Player2) {
				GlobalVariables.frame.removeKeyListener((KeyListener) objects.get(i));
			}
		}
		objects.removeAll(objects);
	}

}
