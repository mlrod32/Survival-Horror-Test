import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Image_Layer{
	Image image;
	static String dir = System.getProperty("user.dir") + "\\Sprites\\";

	int x;
	int y;
	int z;
	
	

	public Image_Layer(String file, int x, int y, int z) {
		image = Toolkit.getDefaultToolkit().getImage(dir+file);
		
		this.x = x;
		this.y = y;
		this.z = z;

	}

	public void moveLeftBy(int dx) {
		x -= dx;

	}

	public void moveRightBy(int dx) {
		x += dx;
	}

	public void draw(Graphics g) {
		//g.drawImage(image, x / z, y, null);
		g.drawImage(image, x/ z, y, 1200, 400, null);
	}
}
