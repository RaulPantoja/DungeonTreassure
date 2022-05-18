package interfaces;
import java.awt.Graphics2D;
public interface ITileManager {

	void getTileImage();
	void cargarMapa(String ruta);
	void draw(Graphics2D g2);
}
