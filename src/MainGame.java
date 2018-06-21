import java.applet.Applet;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;
import javax.swing.JPanel;

public class MainGame extends Applet implements Runnable, MouseMotionListener, MouseListener, KeyListener {

	String dir = System.getProperty("user.dir") + "\\Sprites\\";
	
	Image off_screen;
	Graphics off_g;
	
	Thread t;
	Image_Layer[] alley = new Image_Layer[2];
	
	Music horror;
	
//	   Animation anim1 = new Animation("Shiva_left_" , 6, 10);
//	   Animation anim2 = new Animation("Shiva_right_", 6, 10);
//	   Animation anim3 = new Animation("Shiva_up_"   , 6, 10);
//	   Animation anim4 = new Animation("Shiva_down_" , 6, 10);


	boolean lt_Pressed = false;
	boolean rt_Pressed = false;
	boolean up_Pressed = false;
	boolean dn_Pressed = false;
	boolean	sp_Pressed = false;
	
	boolean mouse_Inside = false;
	
	int mx;
	int my;
	
	boolean overlap = false;
	
	Rect r;
	Rect screne = new Rect(0,0,1000,350);
	
	Girl girl;
	Enemy enemy;
	Enemy enemy2;
	
//	JPanel panel = new JPanel();
	
//	Line line1 = new Line(300,10,600,600);
//	
//	Circle circle1 = new Circle(200,200, 30, 10);
//	
//	Cube cuby = new Cube(200,200,1000, 0);

	Objects box;
	
	Scene scene2; 
	Scene scene1; 
	Scene activeScene;
	
	
//	public static void main(String[] args) {
//
//        MainGame game = new MainGame();
//        JFrame frame = new JFrame("Group");
//        frame.setUndecorated(false);
//        frame.requestFocus();
//        frame.setSize(1000, 400);
//        frame.setLocationRelativeTo(null);
//        frame.add(game);
//        frame.setResizable(false);
//        frame.setVisible(true);
//        game.init();
//
//    }
	
	
	public void init()
	{
		
		//this.setLocation(200, 450);
		
		off_screen = this.createImage(1000, 350);
		off_g = off_screen.getGraphics();
		
		horror = new Music(dir + "horror_loop.wav");
		
		this.setSize(1000,350);
		
		alley[0] = new Image_Layer("Alleyway1.png", 0, 0, 3);
		alley[1] = new Image_Layer("Alleyway1.png", 1000, 0, 3);
		box = new Objects(540, 290, 1, "Box.png");
		girl = new Girl(400, 290, 2);
		enemy = new Enemy(450, 290,2);
		enemy2 = new Enemy(100,290, 2);
		girl.selected = true;
		
		
		requestFocus();
		addKeyListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
		
		off_screen = this.createImage(1000, 350);
		off_g = off_screen.getGraphics();
		
		
		
		t = new Thread(this);
		
		t.start();
	}
	
	
	
	public void run() {
		while(true)
		{
				
					if(lt_Pressed == true)
					{
						girl.moveLeftBy(-1);
//						girl.moveRadius(-1);
//						girl.c.moveBy(-1, 0);
						girl.atkRange.x -= (girl.atkRange.x+girl.atkRange.w) - (girl.x+girl.w);
						girl.Hidden(box);
						if(girl.overlaps(enemy)) {
							girl.moveBy(enemy.w-enemy.w);
						}
						if(girl.overlaps(enemy2)) {
							girl.moveBy(enemy2.w-enemy2.w);
						}
//						for(int i  = 0; i < alley.length; i++) {
//							alley[i].moveRightBy(1);
//						}
						//off_screen = this.createImage(1000, 350-1);
					}
					if(rt_Pressed == true)
					{
						girl.moveRightBy(1);
						girl.atkRange.x = girl.x;
//						girl.moveRadius(1);
//						girl.c.moveBy(1, 0);
						girl.Hidden(box);
						if(girl.overlaps(enemy)) 
							girl.moveBy(-enemy.w-enemy.w);
						if(girl.overlaps(enemy2)) 
							girl.moveBy(-enemy2.w-enemy2.w);
//						for(int i  = 0; i < alley.length; i++) {
//							alley[i].moveLeftBy(1);;
//						}
						
						//off_screen = this.createImage(1000, 350+1);
					}
					if((sp_Pressed == true))
					{
						//girl.AtkCooldown();
						girl.Attack(enemy);
					}
					if((sp_Pressed == true))
					{
						//girl.AtkCooldown();
						girl.Attack(enemy2);
					}
					if(girl.x == 1000) {
						System.out.println("Victory");
					}
					
					if(girl.health == 0) {
						girl.x = -1000000;
						System.out.println("DEAD A F");
					}
					if(enemy.health == 0) {
						enemy.x = -1000000;
						//System.out.println("DEAD A F");
					}if(enemy2.health == 0) {
						enemy2.x = -1000000;
						//System.out.println("DEAD A F");
					}
			
			enemy.CheckStates(girl);
			enemy2.CheckStates(girl);
			
			enemy.Active(girl);
			enemy2.Active(girl);
				
			girl.Insanity(enemy);
			
			repaint();
			
			try
			{
				t.sleep(15);
				
			}catch (Exception e)
			{
				
			}
		}
		
	}
	
	public void update(Graphics g)
	{
		off_g.clearRect(0, 0, 1000, 350);
		
		paint(off_g);
		
		g.drawImage(off_screen, 0, 0, null);
	}

	public void paint(Graphics g)
	{
		//Graphics2D g2d = (Graphics2D) g;
		
		//g2d.translate(girl.x, girl.y);
		//activeScene.draw(g);
		for(int i = 0; i < alley.length; i++) {
			alley[i].draw(g);
		}
		
		girl.draw(g);
		
		//girl.c.draw(g);
		enemy.sight.draw(g);
		//enemy.attackRange.draw(g);
		girl.radius.draw(g);
		girl.atkRange.draw(g);
		box.draw(g);
		enemy.draw(g);
		enemy2.draw(g);


	}
	
	public void mouseDragged(MouseEvent e) 
	{
		int mx = e.getX();
		int my = e.getY();
		
		int dx = mx - this.mx;
		int dy = my - this.my;
		
		r.resizeBy(dx,dy);
		
		this.mx = mx;
		this.my = my;	
		
		
	}
	
	public void mouseMoved(MouseEvent e) 
	{
		mx = e.getX();
		my = e.getY();
		
		
		if(girl.contains(mx, my))
			mouse_Inside = true;
		else
			mouse_Inside = false;
		
	}
	
	public void mousePressed(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();
		
		r = new Rect(mx, my, 0, 0);
		
		
	}
	 
	public void mouseReleased(MouseEvent e) 
	{
		r = new Rect(-100, 0, 0, 0);
		
	}
	
	public void keyPressed(KeyEvent e) 
	{
		int code = e.getKeyCode();
		if (code == e.VK_LEFT)
			lt_Pressed = true;
		if (code == e.VK_RIGHT)
			rt_Pressed = true;
		if (code == e.VK_UP)
			up_Pressed = true;
		if (code == e.VK_DOWN)
			dn_Pressed = true;
		if (code == e.VK_SPACE) {
//			System.out.println("Space Pressed");
			sp_Pressed = true;
		}
		
	}

	public void keyReleased(KeyEvent e) 
	{
		int code = e.getKeyCode();
		if (code == e.VK_LEFT)
			lt_Pressed = false;
		if (code == e.VK_RIGHT)
			rt_Pressed = false;
		if (code == e.VK_UP)
			up_Pressed = false;
		if (code == e.VK_DOWN)
			dn_Pressed = false;
		if(code == e.VK_SPACE)
			sp_Pressed = false;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}	

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
