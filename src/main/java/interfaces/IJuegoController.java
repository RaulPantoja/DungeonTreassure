package interfaces;

import javafx.event.Event;
import java.awt.*;
public interface IJuegoController {

	void mostrarMenuInicio(Event event);
	void setupGame();
	void play();
	void empezarJuegoThread();
	void run();
	void update();
	void paint(Graphics g);
}
