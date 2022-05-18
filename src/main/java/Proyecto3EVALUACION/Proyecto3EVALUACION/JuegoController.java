package Proyecto3EVALUACION.Proyecto3EVALUACION;

import java.awt.Dimension;



import javax.swing.JPanel;

import Proyecto3EVALUACION.Proyecto3EVALUACION.Collision;
import interfaces.IJuegoController;
import model.Entity.*;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.objeto.*;
import tile.TileManager;

import java.awt.*;
//juegoController implementa Runnable para añadir Hilos, ya que no podemos heredar de Thread porque
// no podemos tener herencia multiple
public class JuegoController extends JPanel implements Runnable,IJuegoController {
	//muestra el menu inicio
	public void mostrarMenuInicio(Event event) {
	    try {

	        Parent root = FXMLLoader.load(getClass().getResource("/EscenaMain.fxml"));
	        Scene scene = new Scene(root);
	        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        appStage.setScene(scene);
	        appStage.toFront();
	        appStage.show();

	    } catch (Exception e) {
	    }
	}
	final int tamañoTileOriginal = 16; //tile de 16x16
	//escalamos para que se vea mas grande en pantalla 16x3=48 pixeles
	final int scale = 3;
	public final int tileSize = tamañoTileOriginal * scale; //tile de 48x48
	//aqui definimos que tamaño queremos que tenga nuestro mapa en pantalla
	public int maxScreenCol = 16; //16 tiles horizontales
	public int maxScreenRow = 12; //12 tiles verticales
	public int screenWidth = tileSize * maxScreenCol;//768 pixeles de izquierda a derecha
	public int screenHeight = tileSize * maxScreenRow;//576 pixeles de arriba a abajo

	//Establecemos los ajustes de nuestro mapa, de como queremos que sea de grande
	public final int maxWorldCol=50;
	public final int maxWorldRow=50;
	public final int worldWidth=tileSize*maxWorldCol;
	public final int worldHeight= tileSize*maxWorldRow;
	//FPS del juego
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	
	public Collision checker = new Collision(this);
	KeyHandler keyH = new KeyHandler();
	public HUD hud = new HUD(this);
	Thread gameThread;
	public ObjetosPosicion oPos = new ObjetosPosicion(this);
	public Player player = new Player(this,keyH);
	//preparamos diez espacios para objetos pero no nos hace falta tantos solo 3
	public ObjetoPadre obj[] = new ObjetoPadre[10];
	
	public JuegoController () {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setDoubleBuffered(true);//mejor rendimiento
		this.addKeyListener(keyH);
		//sin esto el personaje no puede moverse, en true da el potencial de ganar el focus a el componente
		this.setFocusable(true);
	}
	//Este metodo sirve para colocar los objetos que estan colocados en el metodo setObjeto
	public void setupGame() {
		oPos.setObjeto();
	}
	//metodo para asignarle al boton de iniciar juego que ejecuta el main del juego
	public void play() {
		MainJuego.main(null);
	}

	
	public void empezarJuegoThread() {
		//le pasamos el panel del juego, la escala, el tamaño del tile... etc a este Thread, para instanciar el Thread
		gameThread = new Thread(this);
		gameThread.start();// esto llama al metodo run()
	}
	

	
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while (gameThread != null ) {
			currentTime = System.nanoTime();
			
			delta +=(currentTime - lastTime) / drawInterval;
			timer +=(currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			if (timer>=1000000000) {
				drawCount = 0;
				timer = 0;
			}
		}
	}
	//En el panel del juego la X se aumenta si vamos hacia la derecha y la Y se aumenta si vamos hacia abajo
	public void update() {
		 player.update();
	}
	//metodo para dibujar en el panel
	public void paint(Graphics g) {
		 super.paint(g);
		 
		 Graphics2D g2 = (Graphics2D)g;
		 //TILE- ponemos el tile antes que el player para que se nos muestre primero el mapa y despues el jugador
		 tileM.draw(g2);
		 
		 //OBJETO
		 for(int i = 0; i <obj.length; i++) {
			 if(obj[i] != null) {
				 obj[i].draw(g2, this);
			 }
		 }
		 
		 //JUGADOR
		 player.draw(g2);
		 
		 
		 //HUD
		 hud.draw(g2);
		 //MOSTRAMOS TODO en la pantalla en el orden que lo hemos dibujado, primero los tiles, es
		 //decir creamos el mapa, despues los objetos el jugador y el HUD de esta forma no
		 //sobreponemos una cosa con otra.
		 g2.dispose();
	}
	
	
}
