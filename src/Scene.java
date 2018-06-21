import java.awt.*;
public class Scene {
	
	String dir = System.getProperty("user.dir") + "\\Sprites\\";
	
	Girl girl;
	Enemy enemies;
	Objects[] objects;
	Image_Layer background;
	int girlx; 
	int enemyx1;
	int enemyx2;
	int objectx1;
	int objectx2; 
	Scene transition;
	
	public Scene(Girl girl, Enemy enemy, Objects[] box, int girlx, int enemyx1, int enemyx2, int objectx1, int objectx2, Scene transition, Image_Layer background){
		
		this.girlx = girlx;
		this.enemyx1 = enemyx1;
		this.enemyx2 = enemyx2;
		this.objectx1 = objectx1;
		this.objectx2 = objectx2;
		this.transition = transition;
//		
		this.girl = girl;
		this.enemies = enemies;
		this.objects = box;
		this.background = background;
		
		this.girl = new Girl(girlx, 500, 2);
		this.enemies = new Enemy(enemyx1, 500,2);
		this.objects[0] = new Objects(objectx1, 295, 1, "Box.png");
		this.objects[1] = new Objects(objectx2, 295, 1, "Box.png");
		this.background= new Image_Layer("Alleyway1.png", 600, 248, 3);
		
//		background = new Image_Layer("Alleyway1.png", 600, 2900, 3);
//		objects = new Objects[](540, 295, 1, "Box.png");
//		girl = new Girl(400, 300, 2);
//		enemy = new Enemy(600, 300,2);
	}
	
	public void draw(Graphics g) {
		
		background.draw(g);
		enemies.draw(g);
		girl.draw(g);
		for(int i = 0; i < objects.length;i++) {
			objects[i].draw(g);
		}
	}
	
}
