package Proyecto3EVALUACION.Proyecto3EVALUACION;

import interfaces.IObjetoPosicion;
import model.objeto.*;



public class ObjetosPosicion implements IObjetoPosicion {

	JuegoController gp;
	public ObjetosPosicion(JuegoController gp) {
		this.gp = gp;
	}
	//instanciamos la posicion que queremos que tengan los objetos en el mapa
	public void setObjeto() {
		gp.obj[0] = new Llave();
		gp.obj[0].worldX = 23 * gp.tileSize;
		gp.obj[0].worldY = 5 * gp.tileSize;
		
		gp.obj[1] = new Llave();
		gp.obj[1].worldX = 24 * gp.tileSize;
		gp.obj[1].worldY = 34 * gp.tileSize;
		
		gp.obj[2] = new Llave();
		gp.obj[2].worldX = 39 * gp.tileSize;
		gp.obj[2].worldY = 11 * gp.tileSize;
		
		gp.obj[3] = new Puerta();
		gp.obj[3].worldX = 15 * gp.tileSize;
		gp.obj[3].worldY = 24 * gp.tileSize;
		
		gp.obj[4] = new Puerta();
		gp.obj[4].worldX = 15 * gp.tileSize;
		gp.obj[4].worldY = 13 * gp.tileSize;
		
		gp.obj[5] = new Puerta();
		gp.obj[5].worldX = 12 * gp.tileSize;
		gp.obj[5].worldY = 28 * gp.tileSize;
		
		gp.obj[6] = new Cofre();
		gp.obj[6].worldX = 15 * gp.tileSize;
		gp.obj[6].worldY = 11 * gp.tileSize;
	}
}
