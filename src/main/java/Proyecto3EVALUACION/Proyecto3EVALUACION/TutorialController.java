package Proyecto3EVALUACION.Proyecto3EVALUACION;

import java.net.URL;
import java.util.ResourceBundle;

import interfaces.ITutorialController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class TutorialController implements ITutorialController {
	

	
	
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
	
	public void mostrarMenuCreacion(Event event) {
	    try {

	        Parent root = FXMLLoader.load(getClass().getResource("Creacion.fxml"));
	        Scene scene = new Scene(root);
	        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        appStage.setScene(scene);
	        appStage.toFront();
	        appStage.show();

	    } catch (Exception e) {
	    }
	}

}
