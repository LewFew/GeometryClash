package org.geometry.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main implements KeyListener{
	
	Handler handler;
	static Graphics g;
	JFrame frame;
	JPanel panel;
	Canvas canvas;
	BufferStrategy bs;
	Graphics2D g2d;
	int[][] map;
	
	boolean running = true;
	
	public static ArrayList<Objects> objects;
	
    public static void main(String[] agrs) {
        new Main();
    }
    
    public Main(){
        handler = GlobalVariables.handler;
        frame = GlobalVariables.frame;
        panel = new JPanel();
        canvas = new Canvas();
        
        canvas.setBounds(0, 0, 1000, 1000);
        
        frame.setSize(1000, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(canvas);
        frame.addKeyListener(this);
        frame.setResizable(false);
        frame.setVisible(true);
        
        canvas.createBufferStrategy(3);
        bs = canvas.getBufferStrategy();
        g = bs.getDrawGraphics();
        g2d = (Graphics2D) bs.getDrawGraphics();
        
        startLocal();
        loop();
    }
    
    public void startLocal() {
    	
    	for (int i = 0; i < frame.getKeyListeners().length; i++) {
    		frame.removeKeyListener(frame.getKeyListeners()[i]);
    	}
    	
    	frame.addKeyListener(this);
    	
    	//String ans = JOptionPane.showInputDialog("Map?: ");
    	
    	//if (ans.equalsIgnoreCase("map1")) {
    		map = GlobalVariables.map1;
    	//}
    	
    	running = true;
        
        handler.addObject(new Player(100, 100));
        handler.addObject(new Player2(1000 - 100, 720 - 100));
        //handler.addObject(new Block(200, 100));
        
//        for (int i = 0; i < 40; i++) {
//        	handler.addObject(new Block(i * 32, 0));
//        }
//        for (int i = 0; i < 30; i++) {
//        	handler.addObject(new Block(0, i * 32));
//        }
//        for (int i = 0; i < 30; i++) {
//        	handler.addObject(new Block(1000 - 40, i * 32));
//        }
//        for (int i = 0; i < 40; i++) {
//        	handler.addObject(new Block(i * 32, 720 - 64));
//        }
        
        for (int i = 0; i < 22; i++) {
        	for (int n = 0; n < 31; n++) {
        		System.out.println(i + " " + n);
        		if (map[i][n] == 1) {
        			handler.addObject(new Block(n * 32, i * 32));
        		}
        	}
        }
        
        Random r = new Random();
        
        for (int i = 0; i < 6; i++) {
        	int x = r.nextInt(900);
        	int y = r.nextInt(600);
//        	if (Math.abs(800 - x) > 50 && Math.abs(600 - y) > 50
//        			&& Math.abs(100 - x) > 50 && Math.abs(100 - y) > 50) {
//        		handler.addObject(new Block(x, y));
//        	}
        }
    }
    
    public void loop(){
    	
        long oldTime = System.nanoTime();
        long newTime = oldTime;
        
        while (true) {
        	if (!running)
        		break;
            if(newTime - oldTime >= 1000000000/60) {
            	frame.requestFocus();
            	objects = handler.getObjects();
                handler.update();
                handler.render(g);
                bs.show();
                g.setColor(Color.white);
                g.fillRect(0, 0, 1000, 720);
                oldTime = newTime;
                //System.out.println("Hi");
            }
            newTime = System.nanoTime();
        }
    }

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_F8) {
			//running = false;
			GlobalVariables.handler.removeAll();
			startLocal();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}