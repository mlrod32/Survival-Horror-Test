

import java.awt.Graphics;

public class Circle 
{
	
	double x;
	double y;
	double r;
	
	int A;
	
	public Circle(double x, double y, double r, int A)
	{
		this.x = x;
		this.y = y;
		this.r = r;
		
		this.A = A;
	}
	
	public void draw(Graphics g)
	{
		g.drawOval((int)(x - r), (int)(y - r),(int) (2 * r),(int) (2 * r));
		
		double cosA = Lookup.cosA[A];
		double sinA = Lookup.sinA[A];
		
		g.drawLine((int)x, (int)y, (int)(x + r *cosA) , (int)(y + r* sinA)) ;
	}
	
	public void moveBy(int dx, int dy) {

		x += dx;
		y += dy;

	}

	// rotates object by delta A
	public void rotateBy(int dA) {

		A += dA;

		if (A >= 360)
			A -= 360;
		if (A < 0)
			A += 360;

	}

	public void rotateLeftBy(int dA) {

		A -= dA;
		if (A < 0)
			A += 360;

	}

	public void rotateRightBy(int dA) {

		A += dA;

		if (A >= 360)
			A -= 360;

	}
	
	public boolean overlaps(Rect rect) {
		return(rect.y < r)&&
				(rect.x < r);
	}
	
	
}
