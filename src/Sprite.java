import java.awt.Graphics;

public abstract class Sprite extends Rect 
{
	
	Animation [] animation;
	
	boolean moving = false;
	boolean selected = true;
	
	static final int UP    = 0;
	static final int DOWN  = 1;
	static final int LEFT  = 2;
	static final int RIGHT = 3;
	int z;
	
	int pose = LEFT;
	
	public Sprite(int x, int y, int z, String file, String[] action, int count, int duration)
	{
		super(x, y, 20, 50);
		this.z = z;
		//this.x = x;
		//this.y = y;
		
		animation = new Animation[action.length];
		
		for(int i = 0; i < action.length; i++) 
		{
			// array of animations
			animation[i] = new Animation(file + action[i] + "_", count, duration);
		}
	}
	
	public void moveUPBy(int dy)
	{
		y += dy;
		moving = true;
		pose = UP;
	}
	
	public void moveDownBy(int dy)
	{
		y += dy;
		moving = true;
		pose = DOWN;
	}
	
	public void moveLeftBy(int dx)
	{
		x += dx;
		moving = true;
		pose = LEFT;
	}
	
	public void moveRightBy(int dx)
	{
		x += dx;
		moving = true;
		pose = RIGHT;
	}
	
	public void draw(Graphics g) {
		
		if(moving) {
			g.drawImage(animation[pose].nextImage(), (int)x, (int)y, null);
		}
		else {
			g.drawImage(animation[pose].stillImage(), (int)x, (int)y, null);
		}
		

		moving = false;	
		
		/*/
		if(selected) {
			super.draw(g);
		}
		*/
	}
	
	
}
