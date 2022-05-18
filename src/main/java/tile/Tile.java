package tile;
import java.awt.image.BufferedImage;
public class Tile {

	public BufferedImage image;
	//este boolean despues lo usamos para ponerlo en la clase TileManager a cada Tile que queramos
	//que tenga colision
	public boolean colision = false;
}
