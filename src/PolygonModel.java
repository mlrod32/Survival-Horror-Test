
import java.awt.*;
public abstract class PolygonModel 
{
	double x;
	  double y;
	  int A;

	  static final int radius = 60;

	  final int[][] struct_x = struct_x();
	  final int[][] struct_y = struct_y();

	  public abstract int[][] struct_x();
	  public abstract int[][] struct_y();


	  public PolygonModel(double x, double y, int A)
	  {
	     this.x = x;
	     this.y = y;
	     this.A = A;
	  }
	  public void draw(Graphics g)
	  {
	     int[] xp = new int[4];
	     int[] yp = new int[4];

	     double sinA = Lookup.sinA[A];
	     double cosA = Lookup.cosA[A];

	     for(int poly = 0; poly < struct_x.length; poly++)
	     {
	        for(int vert = 0; vert < struct_x[poly].length; vert++)
	        {
	           xp[vert] = (int)(struct_x[poly][vert]*cosA - struct_y[poly][vert]*sinA + x);
	           yp[vert] = (int)(struct_x[poly][vert]*sinA + struct_y[poly][vert]*cosA + y);
	        }

	        g.drawPolygon(xp, yp, 4);
	     }
	  }

	  public boolean contains(int mx, int my)
	  {
	     double dist2 = (mx-x)*(mx-x) + (my-y)*(my-y);

	     return dist2  < radius * radius;
	  }

	  public void moveForwardBy(int distance)
	  {
	     x += distance * Math.cos(A*Math.PI/180);
	     y += distance * Math.sin(A*Math.PI/180);
	  }

	  public void moveBy(int dx, int dy)
	  {
	      x += dx;
	      y += dy;
	  }


	  public void rotateLeftBy(int dA)
	  {
	     A -= dA;

	     if(A < 0)     A += 360;
	  }

	  public void rotateRightBy(int dA)
	  {
	     A += dA;

	     if(A >= 360)  A-= 360;
	  }



}
