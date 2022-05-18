package model.objeto;
import java.awt.image.BufferedImage;

import Proyecto3EVALUACION.Proyecto3EVALUACION.JuegoController;

import java.awt.*;

import java.awt.Graphics2D;

public class ObjetoPadre {

	
	//No podemos poner estos atributos protected porque los usan otras clases de fuera como por ejemplo
	//ObjetosPosicion, HUD o Collision
	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int worldX,worldY;
	public Rectangle solidArea = new Rectangle (0,0,48,48);
	public int solidAreaDefaultX = 0;
	public int solidAreaDefaultY = 0;
	
	
	//para dibujar las imagenes del objeto
	public void draw(Graphics2D g2, JuegoController gp) {
		int screenX = worldX-gp.player.worldX + gp.player.screenX;
		int screenY = worldY-gp.player.worldY + gp.player.screenY;
		
		if(worldX +gp.tileSize> gp.player.worldX - gp.player.screenX && worldX -gp.tileSize < gp.player.worldX + gp.player.screenX && worldY +gp.tileSize > gp.player.worldY - gp.player.screenY &&
		   worldY - gp.tileSize <gp.player.worldY + gp.player.screenY) {
			g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);
		}
	}
}
