package interfaces;

import java.net.URL;
import javafx.scene.input.MouseEvent;
import java.util.ResourceBundle;

import javafx.event.Event;

public interface IRankingAddController {

	void mostrarMenuRanking(Event event);
	void añadirJugador();
	void editarJugador();
	void cargarDatos(MouseEvent event);
	void eliminarJugador();
	void initialize(URL url,ResourceBundle rb);
}
