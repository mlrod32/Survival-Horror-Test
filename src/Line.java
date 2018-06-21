

import java.awt.*;

public class Line 
{
	 int x0;
	 int x1;
	 
	 int y0;
	 int y1;
	 
	 double xN;
	 double yN;
	 
	 public Line(int x0, int x1, int y0, int y1)
	 {
		 this.x0 = x0;
		 this.y0 = y0;
		 
		 this.x1 = x1;
		 this.y1 = y1;
		 
		 double dx = x1 - x0;
		 double dy = y1 - y0;
		 
		 double mag = Math.sqrt(dx * dx + dy * dy);
		 
		 double xu = dx / mag;
		 double yu = dy / mag;
		 
		 xN = -yu;
		 yN = xu;		 
		 
	 }
	 
	 public double distanceTo(double x, double y)
	 {
		 return xN*(x - x0) + yN*(y - y0);
	 }
	 
	 public void draw(Graphics g) 
	 {
		g.drawLine(x0, y0, x1, y1); 
	 }
	 
}
