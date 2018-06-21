import java.awt.Graphics;

public class EnemySprite extends Rect {
Animation [] animation;
	
	boolean moving = false;
	boolean attacking = false;
	boolean idle = false;
	
	static final int UP    = 0;
	static final int DOWN  = 1;
	static final int LEFT  = 2;
	static final int RIGHT = 3;
	
	int pose = LEFT;
	
	int z;
	
	Rect sight = new Rect(x , y, 100, 50);
	Rect attackRange = new Rect(x - 40, y, 100, 50);
	
	public EnemySprite(int x, int y, int z, String file, String[] action, int count, int duration)
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
	
	public void moveRange(int x) {
		attackRange.moveBy(x);
	}
	
	public void moveSight(int x)
	{
		sight.x += x;
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
		idle = false;
		moving = true;
		pose = LEFT;
	}
	
	public void moveRightBy(int dx)
	{
		x += dx;
		idle = false;
		moving = true;
		pose = RIGHT;
	}
	
	
	
	public void Stop(Girl girl) {
		idle = true;
		if(idle)
		{
			System.out.println("stopping");
			//moving = false;
				moveBy(-girl.w);
				this.x = this.x + girl.w;
		}
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
