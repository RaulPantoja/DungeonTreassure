package model.Entity;




import java.io.FileInputStream;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import Proyecto3EVALUACION.Proyecto3EVALUACION.JuegoController;
import Proyecto3EVALUACION.Proyecto3EVALUACION.KeyHandler;

import java.awt.*;
public class Player extends Entity {
	private JuegoController gp;
	private KeyHandler keyH;

	public final int screenX;
	public final int screenY;
	public int llaves = 0;
	
	int counter2 = 0;
	public Player(JuegoController gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		//creamos un rectangulo como area del personaje para que sea solido y tenga colision,de esta forma no todo el 
		// personaje tiene colision y hace que sea mas facil pasar entre tiles o caminos
		solidArea = new Rectangle();
		solidArea.x=8;
		solidArea.y=16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width=32;
		solidArea.height=32;
		
		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {
		//Variables para establecer una posicion por defecto a nuestro personaje, es decir en el medio del mapa
		 worldX = gp.tileSize*23;
		 worldY = gp.tileSize*21;
		 speed = 4;
		 direccion="down";
	}
	//metodo para cargar las imagenes del personajeRa
	public void getPlayerImage() {
		try {
			this.up = ImageIO.read(new FileInputStream("res/player/upPlayer.png"));
			this.down =  ImageIO.read(new FileInputStream("res/player/downPlayer.png"));
			this.left =  ImageIO.read(new FileInputStream("res/player/leftPlayer.png"));
			this.right =  ImageIO.read(new FileInputStream("res/player/rightPlayer.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		if (keyH.upPressed==true || keyH.downPressed == true || keyH.leftPressed ==true || keyH.rightPressed==true) {
			if (keyH.upPressed == true) {
				direccion = "up";

			} else if (keyH.downPressed == true) {
				direccion = "down";

			} else if (keyH.leftPressed == true) {
				direccion = "left";
				
			} else if (keyH.rightPressed == true) {
				direccion = "right";
				
			}
			spriteCounter++;
			if(spriteCounter > 10) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
			
			//Comprobamos la colision de los TILE
			collisionOn = false;
			gp.checker.checkTile(this);
			
			//Comprobamos la colision de los OBJETOS
			int objIndex = gp.checker.checkObject(this,true);
			cogerObjeto(objIndex);
			//si la colision es falsa el jugador puede moverse
			if(collisionOn == false) {
				switch(direccion) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}
		}
		
	}
	/*
	 * Metodo para coger los objetos del suelo, como las llaves o abrir puertas
	 *  si tenemos suficientes llaves
	 */
	public void cogerObjeto(int i) {
		//Si el index es 999 significa que no hemos tocado ningun objeto, pero si no es 999 significa que hemos cogido el objeto
		if(i != 999) {
			String nombreObjeto = gp.obj[i].name;
			
			switch(nombreObjeto) {
			case "Llave":
				//cada vez que cogemos un objeto con el nombre Llave sumamos uno al contador de llaves y eliminamos el objeto que tocamos
				llaves++;
				gp.obj[i] = null;
				//mensaje que muestra cada vez que cogemos una llave
				gp.hud.mostrarMensaje("Has conseguido una llave");
				break;
			case "Puerta":
				//comprobamos que el jugador tiene llaves
				if(llaves > 0) {
					//si tiene llaves abrimos la puerta y la eliminamos
					gp.obj[i] = null;
					//y nos quitamos una llave del contador
					llaves--;
					gp.hud.mostrarMensaje("Has abierto la puerta");
				}else {
					gp.hud.mostrarMensaje("Necesitas una llave");
				}
				break;
			case "Cofre":
				gp.hud.juegoAcabado = true;
				break;
			}
		}
	}
	
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		//hacemos un switch para cambiar entre las imagenes en el caso de que pulsemos up,down,left o right
		switch(direccion) {
		case "up":
			if(spriteNum==1) {
				image = up;
			}if(spriteNum==2) {
				image = up;
			}
			
			break;
		case "down":
			if(spriteNum==1) {
				image = down;
			}if(spriteNum==2) {
				image = down;
			}
			
			break;
		case "left":
			if(spriteNum==1) {
				image = left;
			}if(spriteNum==2) {
				image = left;
			}
			
			break;
		case "right":
			if(spriteNum==1) {
				image = right;
			}if(spriteNum==2) {
				image = right;
			}
			image = right;
			break;
		
		}
		//dibujamos la imagen
		g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize, null);
	}
}
