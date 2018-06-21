import java.awt.Graphics;

public class Enemy extends EnemySprite
{
	static String dir = System.getProperty("user.dir") + "\\Sprites\\";
	static String[] action = {"up", "down", "left", "right"};
	
	boolean dead = false;
	
	int health = 2;
	
	int delay = 200;	
	int counter = 0;
	int idletime = 120;
	int cdTime = 120;
		
	boolean cooldown = false;
	boolean idle = false;
	
	int ix;
	
	double xn;
	double yn;
	
	//Rect sight = new Rect(x , y, 100,50);
	
	enum States {
		IDLE, PATROL, ATTACK, FOLLOW;
	}
	
	States currState = States.PATROL; 
	
	public void CheckStates(Girl girl) {
		switch(currState) {
		
//		case IDLE:
//			Idle();
		
		case PATROL:
			//System.out.println("Patrolling");
			//if(!sight.overlaps(girl))
			Patrol(girl);
			break;
		
		case ATTACK:
//			if(girl.c.overlaps(this)) {
				//System.out.println("Attacking");
//		    	//currState = States.ATTACK;
//		    }
			Cooldown();
			if(cooldown) {
				System.out.println("Attacking");
				Attack(girl);
			}
				break;
			
//			if(this.overlaps(girl.radius)) {
//				Attack(girl);
//			}
//			
		case FOLLOW:
			if(girl.overlaps(sight) && (girl.hidden == false))
				moveTowards(girl);
			break;
		}
		
	}
	
	public Enemy(int x, int y, int z)
	{
		super(x, y, z, dir + "Shiva_", action, 6, 20);
		ix = x;
		
		double dx = super.x - super.x + super.w;
		double dy = super.y - super.y + super.h;
		
		double mag = Math.sqrt((dx*dx) + (dy*dy));
		
		double xu = dx/mag;
		double yu = dy/mag;
		
		xn = yu;
		yn = -xu;
	}
	
	public void Attack(Girl girl) {
		girl.health -= 1;
		girl.sanity++;
	}
	
//	public void moveSight(int x)
//	{
//		sight.x += x;
//	}
	
	
	
	public void patrolLeft(int x)
	{
		this.moveLeftBy(-x);
		sight.x -= (sight.x+sight.w) - (this.x+this.w);
		moveSight(-x);
		moveRange(-x);
	
	}
	
	public void patrolRight(int x)
	{
		this.moveRightBy(x);
		sight.x = this.x;
		moveSight(x);
		moveRange(x);
	
	}
	
	public void Patrol(Girl girl)
	{
		//System.out.println("Delay = " + delay);
		//System.out.println("counter = " + counter);
		
		Idle();
		if((delay <= 200) &&(delay !=0) &&(idle))
		{
			//cooldown = true;
			delay--;
			patrolRight(1);
			if(delay == 0)
				idletime = 120;
		}
		else
		{
			//cooldown = false;
			//System.out.println("Going Idle");
			Idle();
			if(idle) {
				counter++;
				patrolLeft(1);
				if(counter == 200)
				{		
						delay = 200;
						counter = 0;
						idletime = 120;

				}
			}
		}
		
//		if(girl.overlaps(sight)) {
//	    	currState = States.FOLLOW;
//	    }
		
	}
	
	public double distanceTo(double x)
	{
		return xn * (super.x - super.x + super.w) + 
			   yn * (super.y - super.y + super.h);
	}
		
	
	public void moveTowards(Girl girl)
	{
		
		//System.out.println("Following Girl");
		  //double ux = Lookup.cosA[A];
	      //double uy = Lookup.sinA[A];

	      //double nx = -uy;
	      //double ny = ux;

	      double vx = girl.x - x;
	      //double vy = girl.y - y;

	      double d = girl.distanceTo(x);
	      
	      if(girl.x < x)
	      {
	    	  patrolLeft(1);
	    	  //this.moveLeftBy(-1);
	    	  //moveSight(-1);
	    	  if(this.overlaps(girl)){
	    		  //System.out.println("Overlaps Left");
	    		  currState = States.ATTACK;
	   			//this.moveBy(girl.w);
	   			
	    		//this.x = this.x+girl.w;
	    		//moving = false;
	    	  }
	    	  
	
	      }
	      else
	      {
	    	  patrolRight(1);
	    	  //this.moveRightBy(-1);
	    	  //moveSight(1);
	    	  if(this.overlaps(girl)) {
	    		  //System.out.println("Overlaps Right");
	    		  currState = States.ATTACK;
		   			//this.moveBy(-girl.w);
		    		//this.x = this.x-girl.w;
		    		//moving = false;
		    	  }
	    	 
	      }
	      
//	    if(girl.c.overlaps(this)) {
//	    	currState = States.ATTACK;
//	    }
	      

	}
	
	
	public void Idle()
	{
		//System.out.println("Idle Time =" + idletime);
		if(idletime == 0)
		{
			idle = true;
			

		}else
		{
			idle = false;
			idletime--;
			
		}
	}
	
	public void Active(Girl girl)
	{
		if(!dead)
		{
			
			
			//CheckStates(girl);
			if(!girl.overlaps(sight))
			{
				
				currState = States.PATROL;			
				
			}
			if(girl.overlaps(sight) && (girl.hidden == false))
			{
				
				currState = States.FOLLOW;
				
			} 
			
			if(girl.overlaps(this) &&( currState != States.PATROL))
			{
				currState = States.ATTACK;
			}
			
		}
	}
	
	public void Cooldown()
	{
		if(cdTime == 0)
		{
			cooldown = true;
			cdTime = 120;

		}else
		{
			cooldown = false;
			cdTime--;
		}
	}
	
	
}
 