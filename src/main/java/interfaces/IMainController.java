package interfaces;

import javafx.event.ActionEvent;
import javafx.event.Event;

public interface IMainController {

	void mostrarMenuJuego(Event evento);
	void mostrarMenuRanking(Event evento);
	void mostrarMenuPerfil(Event evento);
	void mostrarMenuTutorial(Event evento);
	void exit(ActionEvent evento);
	void changeMode(ActionEvent evento);
	void setLightMode();
	void setDarkMode();

}
