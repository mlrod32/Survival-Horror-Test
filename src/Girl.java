import javax.swing.*;
public class Girl extends Sprite
{
	static String dir = System.getProperty("user.dir") + "\\Sprites\\";
	static String[] action = {"up", "down", "left", "right", "action"};
	
	int health = 10;
	double sanity = 0;
	double heartRate = 0;
	int bullets = 6;
	int maxBullets = 6;
	int atkcd = 10;	
	int delay = 120;
	int reloadtime = 100;
	
	Rect radius = new Rect(x-40, y, 100, 50);
	Rect atkRange = new Rect(x, y, 100, 50);
	
	Circle c = new Circle(x+12, y+25, 24, 0);
	
	boolean inRadius = false;
	boolean dead     = false;
	boolean cooldown = false;
	boolean hidden = false;
	boolean atkcooldown = true;
	boolean reloadcooldown = false;
	
	double xn;
	double yn;
	
	public Girl(int x, int y, int z)
	{
		super(x, y, z, dir + "Shiva_", action, 6, 20);
		
		double dx = super.x - super.x + super.w;
		double dy = super.y - super.y + super.h;
		
		double mag = Math.sqrt((dx*dx) + (dy*dy));
		
		double xu = dx/mag;
		double yu = dy/mag;
		
		xn = -yu;
		yn = xu;
	}
	
	public void Hidden(Objects object) {
		if(this.overlaps(object)) {
			hidden = true;
		}else
			hidden = false;
	}
	
	public void moveLeftBy(int dx)
	{
		x += dx;
		moving = true;
		pose = LEFT;
		moveRadius(dx);
		c.moveBy(dx, 0);
	}
	
	public void moveRightBy(int dx)
	{
		x += dx;
		moving = true;
		pose = RIGHT;
		moveRadius(dx);
		c.moveBy(dx, 0);
	}
	
	public void moveBy(double dx)
	{
		x += dx;
		//y += dy;
		moveRadius((int)dx);
		c.moveBy((int)dx, 0);
		
	}
		
	public void Attack(Enemy enemy) {
		//System.out.println("Girl Attacking");
		if(maxBullets > 0) {
			AtkCooldown();
			if(atkcooldown) {
				bullets--;
				maxBullets--;
				System.out.println("Girl Attacking");
				System.out.println("Bullets " + bullets);
				if (enemy.overlaps(atkRange)) {
				enemy.health -= 1;
				}
				if(bullets <= 0) {
					RLCooldown();
					if(reloadcooldown) {
						bullets = 6;
						//maxBullets -= 6;
					}
				
				}
			}
		}
		
		
	}
	
	public void AtkCooldown()
	{
		if(atkcd == 0)
		{
			atkcooldown = true;
			atkcd = 10;

		}else
		{
			atkcooldown = false;
			atkcd--;
		}
	}
	
	public void RLCooldown()
	{
		if(atkcd == 0)
		{
			reloadcooldown = true;
			reloadtime = 60;

		}else
		{
			reloadcooldown = false;
			reloadtime--;
		}
	}
	
	public void moveRadius(int x)
	{
		 radius.x += x;
	}
	
	public double distanceTo(double x)
	{
		return xn * (super.x - super.x + super.w) + 
			   yn * (super.y - super.y + super.h);
	}
	
	public void Insanity(Enemy enemy)
	{	Cooldown();
		if(cooldown)
		{
			
			if(radius.overlaps(enemy) )
			{
				inRadius = true;
				sanity++;
				System.out.println("Sanity = " + sanity);
				
				if(sanity >= 10)
				{
					sanity = 10;
					heartRate++;
					System.out.println("Heart Rate = " + heartRate);
				}
				
				if(heartRate >= 10)
				{
					heartRate = 10;
					health--;
					System.out.println("Health = " + health);
				}
				
				if(health <= 0) 
				{
					dead = true;
					health = 0;
				}
			}else
			{
				inRadius = false;
				sanity --;				
				if(sanity != 10)
				{
					heartRate--;
					System.out.println("Heart Rate = " + heartRate);
				}
				
				if(sanity <= 0)
				{
					sanity = 0;
				}
				
				if(heartRate <= 0)
				{
					heartRate = 0;
				}
				System.out.println("Sanity = " + sanity);
			}
			
		}
	}
	
	public void Cooldown()
	{
		if(delay == 0)
		{
			cooldown = true;
			delay = 120;

		}else
		{
			cooldown = false;
			delay--;
		}
	}
	

	
}
