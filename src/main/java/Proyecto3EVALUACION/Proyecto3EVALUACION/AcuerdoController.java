package Proyecto3EVALUACION.Proyecto3EVALUACION;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import DAO.UsuarioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class AcuerdoController implements Initializable {

	private static final Logger LOG = Logger.getLogger(UsuarioDAO.class.getName());
	App m = new App();
	@FXML 
	CheckBox checkBox;
	@FXML
	private Label mensajeLoginLabel;
	@FXML
	private ProgressBar progressBar;
	
	@Override
	public void initialize(URL arg0,ResourceBundle res) {
		progressBar.setStyle("-fx-accent:green;");
	}
	public void change(ActionEvent event) {
		if(checkBox.isSelected()) {
			try {
				m.cambiarEscena("Registro.fxml");
				LOG.info("El usuario ha aceptado los acuerdos de licencia");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			mensajeLoginLabel.setText("Debes aceptar el acuerdo de licencia");
		}
	}
}
