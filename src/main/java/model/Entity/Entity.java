package model.Entity;
import java.awt.image.BufferedImage;
import java.awt.*;
public abstract class Entity {

	
	public int worldX,worldY;
	public int speed;
	public BufferedImage  up,down,left,right;
	public String direccion;
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle solidArea;
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	
	public abstract void setDefaultValues();
	public abstract void getPlayerImage();
	public abstract void update();
	public abstract void cogerObjeto(int i);
	public abstract void draw(Graphics2D g2);
}
