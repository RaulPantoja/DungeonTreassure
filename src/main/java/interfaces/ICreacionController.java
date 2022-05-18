package interfaces;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;

public interface ICreacionController {
	void initialize(URL url,ResourceBundle res);
	void playMedia();
	void pauseMedia();
	void mostrarMenuTutorial(Event evento);
}
