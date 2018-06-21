import java.awt.*;

public abstract class PolygonModel3D 
{
	  double x;
	  double y;
	  double z;
	  int A;

	  static final int radius = 60;

	  final int[][] struct_x = struct_x();
	  final int[][] struct_y = struct_y();
	  final int[][] struct_z = struct_z();

	  public abstract int[][] struct_x();
	  public abstract int[][] struct_y();
	  public abstract int[][] struct_z();


	  public PolygonModel3D(double x, double y, double z, int A)
	  {
	     this.x = x;
	     this.y = y;
	     this.z = z;
	     this.A = A;
	  }
	  public void draw(Graphics g)
	  {
		 final int d = 500;
		 
		 final int xo = 500;
		 final int yo = 350;
		 
	     int[] xp = new int[4];
	     int[] yp = new int[4];

	     double sinA = Lookup.sinA[A];
	     double cosA = Lookup.cosA[A];
	     

	     for(int poly = 0; poly < struct_x.length; poly++)
	     {
	        for(int vert = 0; vert < struct_x[poly].length; vert++)
	        {
	        	//translate vertex
	        	
	           double xt = (struct_x[poly][vert] + x);
	   	       double yt = (struct_y[poly][vert] + y);
	   	       double zt = (struct_z[poly][vert] + z);
	   	       
	   	       //3d Perspective Transmormation
	           xp[vert] = d * (int)xt / (int)zt;
	           yp[vert] = d * (int)yt / (int)zt;
	           
	           //Translate the origin
		        xp[vert] += xo;
		        yp[vert] += yo;
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

	  public void moveBy(int dx, int dy, int dz)
	  {
	      x += dx;
	      y += dy;
	      z += dz;
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
