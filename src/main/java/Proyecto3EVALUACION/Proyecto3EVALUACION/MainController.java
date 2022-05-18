package Proyecto3EVALUACION.Proyecto3EVALUACION;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import java.util.ResourceBundle;

import javax.swing.Action;


import interfaces.IMainController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.awt.*;
import javafx.event.*;

public class MainController implements IMainController {

	public void mostrarMenuGit(Event event) {
		try {

			Parent root = FXMLLoader.load(getClass().getResource("GitHub.fxml"));
			Scene scene = new Scene(root);
			Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			appStage.setScene(scene);
			appStage.toFront();
			appStage.show();
			appStage.centerOnScreen();

		} catch (Exception e) {
		}
	}

	public void mostrarMenuLogin(Event event) {
		try {

			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root);
			Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			appStage.setScene(scene);
			appStage.toFront();
			appStage.show();
			appStage.centerOnScreen();

		} catch (Exception e) {
		}
	}
	public void mostrarMenuRanking(Event event) {
		try {

			Parent root = FXMLLoader.load(getClass().getResource("Ranking.fxml"));
			Scene scene = new Scene(root);
			Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			appStage.setScene(scene);
			appStage.toFront();
			appStage.show();
			appStage.centerOnScreen();

		} catch (Exception e) {
		}
	}

	public void mostrarMenuPerfil(Event event) {
		try {

			Parent root = FXMLLoader.load(getClass().getResource("Perfil.fxml"));
			Scene scene = new Scene(root);
			Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			appStage.setScene(scene);
			appStage.toFront();
			appStage.show();

		} catch (Exception e) {
		}
	}

	public void mostrarMenuJuego(Event event) {
		try {

			Parent root = FXMLLoader.load(getClass().getResource("Juego.fxml"));
			Scene scene = new Scene(root);
			Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			appStage.setScene(scene);
			appStage.toFront();
			appStage.show();

		} catch (Exception e) {
		}
	}

	public void mostrarMenuTutorial(Event event) {
		try {

			Parent root = FXMLLoader.load(getClass().getResource("Tutorial.fxml"));
			Scene scene = new Scene(root);
			Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			appStage.setScene(scene);
			appStage.toFront();
			appStage.show();

		} catch (Exception e) {
		}
	}

	@FXML
	private Button btnMode;

	@FXML
	private AnchorPane panel;
	@FXML
	private ImageView imgMode;

	@FXML
	private Button exitButton;

	// Para el boton de cerrar sesion
	Stage stage;

	private boolean isLightMode = true;

	// Este es el metodo que se asocia al boton y que cuando lo pulsamos llama a los
	// metodos
	// de cambiar el css
	public void changeMode(ActionEvent event) {
		isLightMode = !isLightMode;

		if (isLightMode) {
			setLightMode();
		} else {
			setDarkMode();
		}
	}

	public void setLightMode() {
		// Eliminamos el archivo css del modo oscuro para añadir despues el del modo
		// claro
		panel.getStylesheets().remove("darkMode.css");
		panel.getStylesheets().add("lightMode.css");
		// Cambiamos el icono de el boton a el de la luna
		Image image = new Image(getClass().getResourceAsStream("/assets/moon.png"));
		imgMode.setImage(image);
	}

	public void setDarkMode() {
		// Eliminamos el archivo css del modo claro y añadimos el del modo oscuro
		panel.getStylesheets().remove("lightMode.css");
		panel.getStylesheets().add("darkMode.css");
		// Cambiamos el icono de la luna por el de el sol
		Image image = new Image(getClass().getResourceAsStream("/assets/sun.png"));
		imgMode.setImage(image);

	}

	public void exit(ActionEvent event) {
		// Creo una alerta para confirmar que el usuario quiere salir del programa
		Alert alerta = new Alert(AlertType.CONFIRMATION);
		// Titulo de la alerta
		alerta.setTitle("Cerrar sesion");
		// Cabecera de la alerta
		alerta.setHeaderText("Estas a punto de cerrar sesion");
		// Texto de la alerta
		alerta.setContentText("¿Seguro que quieres cerrar sesion?");

		// Si el usuario clicka el boton OK cerramos la ventana y mostramos un mensaje
		// por consola.
		if (alerta.showAndWait().get() == ButtonType.OK) {
			stage = (Stage) panel.getScene().getWindow();
			System.out.println("Has cerrado sesion");
			stage.close();
		}

	}

	@FXML
	private Hyperlink link;

	/**
	 * Metodo para abrir un link al pulsar el hyperlink
	 */
	@FXML
	void abrirLink(ActionEvent event) throws IOException, URISyntaxException{
		Desktop.getDesktop().browse(new URI("https://github.com/RaulPantoja/Proyecto3Evaluacion"));
	}

}
