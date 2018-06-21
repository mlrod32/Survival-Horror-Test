import java.awt.Graphics;
import java.awt.*;
import java.awt.Toolkit;

public class StillSprite extends Rect {
	Image image;
	double z;
	static String dir = System.getProperty("user.dir") + "\\Sprites\\";

	public StillSprite(int x, int y, int z, String file){
		super(x, y, 50, 50);
		this.z = z;
		image = Toolkit.getDefaultToolkit().getImage(dir+file);
		
	}
	
	public void draw(Graphics g) {
		g.drawImage(image, (int)(x / z), (int)y, null);
	}
}
