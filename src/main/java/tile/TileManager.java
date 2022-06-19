package tile;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.*;
import javax.imageio.ImageIO;

import Proyecto3EVALUACION.Proyecto3EVALUACION.JuegoController;
import utils.utils;
	
public class TileManager {

	JuegoController gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public TileManager(JuegoController gp) {
		this.gp = gp;
		//esto significa que vamos a tener al menos 10 tipos diferentes de Tiles(de cuadrado con fotos)
		tile = new Tile[10];
		mapTileNum = new int [gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		//usamos el metodo para cargar el mapa y la ruta del mapa con los tiles que queremos cargar
		cargarMapa("/mapas/mapaMundo.txt");
	}
	//el tile funciona como un array, si en la posicion 0 queremos una imagen
	//le pasamos la ruta de la imagen en la posicion 0
	public void getTileImage() {
		tile[0] = new Tile();
		tile[0].image = utils.cargarImagen("res/tiles/suelo.png");
		
		tile[1] = new Tile();
		tile[1].image = utils.cargarImagen("res/tiles/pared.png");
		tile[1].colision = true;
		
		tile[2] = new Tile();
		tile[2].image = utils.cargarImagen("res/tiles/lava.png");
		tile[2].colision = true;
		
		tile[3] = new Tile(); 
		tile[3].image = utils.cargarImagen("res/tiles/caminoArriba.png");
		
		tile[4] = new Tile();
		tile[4].image = utils.cargarImagen("res/tiles/caminoArribaDerecha.png");
		
		tile[5] = new Tile();
		tile[5].image = utils.cargarImagen("res/tiles/caminoAbajoDerecha.png");
		
		tile[6] = new Tile();
		tile[6].image = utils.cargarImagen("res/tiles/caminoDerecha.png");
		
		
		tile[7] = new Tile();
		tile[7].image = utils.cargarImagen("res/tiles/caminoArribaIzquierda.png");
		
		tile[8] = new Tile();
		tile[8].image = utils.cargarImagen("res/tiles/caminoAbajoIzquierda.png");
		
		
		tile[9] = new Tile();
		tile[9].image = utils.cargarImagen("res/tiles/caminoMedio.png");
	}
	//metodo para importar el mapa y leerlo
	//lee el archivo map01 linea por linea y divide cada numero y lo almacena en un mapa de tiles y asi
	//con todas las lineas, hasta que el bucle acabe
	public void cargarMapa(String ruta) {
		try {
			//importamos el mapa
			InputStream is = getClass().getResourceAsStream(ruta);
			//leemos el mapa y abrimos el flujo
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				//lee la linea de texto donde tenemos nuestros tiles con numeros
				String line = br.readLine();
				while(col<gp.maxWorldCol) {
					//el split separa el string con un espacio
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			//cerramos el flujo de datos
			br.close();
		}catch(Exception e) {
			
		}
	}
	public void draw(Graphics2D g2) {
		int col = 0;
		int row = 0;
		
		
		while(col <gp.maxWorldCol && row < gp.maxWorldRow) {
			int tileNum = mapTileNum[col][row];
			int worldX = col*gp.tileSize;
			int worldY = row*gp.tileSize;
			int screenX = worldX-gp.player.worldX + gp.player.screenX;
			int screenY = worldY-gp.player.worldY + gp.player.screenY;
			//esto sirve para dibujar los TILES dependiendo de hacia donde se mueva el jugador
			if(worldX +gp.tileSize> gp.player.worldX - gp.player.screenX && worldX -gp.tileSize < gp.player.worldX + gp.player.screenX && worldY +gp.tileSize > gp.player.worldY - gp.player.screenY &&
			   worldY - gp.tileSize <gp.player.worldY + gp.player.screenY) {
				g2.drawImage(tile[tileNum].image,screenX,screenY,gp.tileSize,gp.tileSize,null);
			}
			
			col++;
		
			//cuando el col del if llegue a el maxScreenCol que es 16 vamos a resetear la columna y la X y aumentar la fila y la Y;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
		
		
	}
}
