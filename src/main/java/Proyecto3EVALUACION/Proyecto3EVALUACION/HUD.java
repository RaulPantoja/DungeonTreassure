package Proyecto3EVALUACION.Proyecto3EVALUACION;
import java.awt.Graphics2D;

import java.awt.Font;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.logging.Logger;

import DAO.PartidaDAO;
import DAO.UsuarioDAO;
import interfaces.IHUD;
import model.objeto.*;

/**
 * Esta clase es la interfaz del juego, sirve para mostrar por pantalla toda la informacion del juego
 * como por ejemplo el tiempo que llevamos, las llaves que tenemos en posicion, un mensaje cada vez que abrimos
 * una puerta o un mensaje cuando no tenemos llaves para abrir puertas y el mensaje de cuando ganamos, tambien
 * muestra el tiempo que  hemos conseguido en la partida
 * @author RaulPP
 *
 */
public class HUD implements IHUD{

	private static final Logger LOG = Logger.getLogger(UsuarioDAO.class.getName());
	JuegoController gp;
	Font fuente;
	BufferedImage imagenLlave;
	BufferedImage imagenTiempo;
	public boolean mensajeOn = false;
	public String mensaje = "";
	int contadorMensajes = 0;
	public boolean juegoAcabado= false;
	
	public double tiempoJuego; 
	//queremos que el tiempo se muestre con este formato
	DecimalFormat decimal = new DecimalFormat ("#0.00");
	
	PartidaDAO pDAO = new PartidaDAO();
	
	public HUD(JuegoController gp) {
		this.gp = gp;
		fuente = new Font ("Arial", Font.PLAIN,40);
		Llave llave = new Llave();
		imagenLlave = llave.image;
		Tiempo tiempo = new Tiempo();
		imagenTiempo = tiempo.image;
	}
	
	public void mostrarMensaje(String text) {
		mensaje = text;
		mensajeOn = true;
	}
	public void draw(Graphics2D g2) {
		//comprobamos si hemos acabado el juego, y si es asi
		if(juegoAcabado == true) {
			//establecemos la fuente del texto
			g2.setFont(fuente);
			//establecemos el color del texto
			g2.setColor(Color.black);
			String text;
			int textLength;
			int x;
			int y;
			//escribimos el mensaje que queremos mostrar por pantalla
			text="Has encontrado el tesoro";
			//posicion donde va a salir el texto 
			textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 - (gp.tileSize*4);
			g2.drawString(text,x,y);
			
			text="Tiempo : " +  decimal.format(tiempoJuego);
			//posicion donde va a salir el texto 
			textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 + (gp.tileSize*4);
			g2.drawString(text,x,y);
			//esto sirve para para el hilo de ejecucion y acabar el juego
			gp.gameThread = null;
			LOG.info("El usuario ha acabado el juego con un tiempo de: "+tiempoJuego);
			Properties properties = new Properties();

			properties.setProperty("tiempoJuego", String.valueOf(tiempoJuego));
			try(FileWriter output = new FileWriter("tiempo.properties")){
			    properties.store(output, "");
			    
			} catch (IOException e) {
			    e.printStackTrace();
			}
			pDAO.añadirJugador();
			
		// si el juego todavia no lo hemos acabado: 
		}else {
			//establecemos fuente del texto
			g2.setFont(fuente);
			//color del texto
			g2.setColor(Color.black);
			//donde queramos que aparezca la imagen de la llave
			g2.drawImage(imagenLlave,gp.tileSize/2,gp.tileSize/2,gp.tileSize,gp.tileSize,null);
			//posicion del texto de cuantas llaves tenemos
			g2.drawString("x"+ gp.player.llaves,74,65);
			
			
			//TIEMPO 
			tiempoJuego += (double)1/60;
			//posicion de la imagen del reloj de arena
			g2.drawImage(imagenTiempo,610,37,gp.tileSize,gp.tileSize,null);
			//texto con el tiempo que llevamos con formato 00.00
			g2.drawString(""+decimal.format(tiempoJuego), 650,65);
			
			if(mensajeOn == true) {
				g2.setFont(g2.getFont().deriveFont(20F));
				g2.drawString(mensaje,gp.tileSize/2,gp.tileSize*5);
				contadorMensajes++;
				//el 120 significa los fps, por lo que si 1 segundo son 60 fps, el mensaje desaparece cuando han pasado 2 segundos
				if(contadorMensajes >120) {
					contadorMensajes = 0;
					mensajeOn = false;
				}
			}
		}
		
		
	}
}
