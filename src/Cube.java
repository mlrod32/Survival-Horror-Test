import java.awt.*;

public class Cube extends PolygonModel3D
{


	  public Cube(double x, double y, double z, int A)
	  {
	      super(x, y, z, A);
	  }

	  public void shoot(Rect shell)
	  {
	     double unit_vx = Math.cos(A*Math.PI/180);
	     double unit_vy = Math.sin(A*Math.PI/180);

	   

	     shell.setLocation(x + (30) * unit_vx, y + (30  ) * unit_vy) ;

	     double vx = 10 * unit_vx;
	     double vy = 10 * unit_vy;

	     shell.setVelocity(vx, vy);
	  }


	  public int[][] struct_x()
	  {
	     final int[][] struct_x =
	     {
	        {50, 50, -50, -50},
	        {50, 50, -50, -50},
	        {50, 50, 50, 50},
	        {-50, -50, -50, -50},
	        
	     };

	     return struct_x;
	  }

	  public int[][] struct_y()
	  {
	     final int[][] struct_y =
	     {
	        {50, -50, -50, 50},
	        {50, -50, -50, 50},
	        {50, -50, -50, 50},
	        {50, -50, -50, 50},
	      
	     };

	     return struct_y;
	  }
	  
	  public int[][] struct_z()
	  {
	     final int[][] struct_z =
	     {
	        {50, 50, 50, 50},
	        {-50, -50, -50, -50},
	        {50, 50, -50, -50},
	        {50, 50, -50, -50},
	      
	     };

	     return struct_z;
	  }

}
