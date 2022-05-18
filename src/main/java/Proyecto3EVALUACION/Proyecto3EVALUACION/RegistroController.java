package Proyecto3EVALUACION.Proyecto3EVALUACION;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Usuario;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import DAO.UsuarioDAO;

public class RegistroController implements Initializable {

	private static final Logger LOG = Logger.getLogger(UsuarioDAO.class.getName());
	Connection conn = null;
	PreparedStatement pst = null;
	@FXML
	private TextField usernameTextField;
	@FXML
	private PasswordField enterPasswordField;
	@FXML
	private Label mensajeLoginLabel;
	@FXML
	private ProgressBar progressBar;
	// Para registrar usuarios con contraseña encriptada:
	// INSERT INTO cuentausuario VALUES ('','raul',MD5('tobi'));

	UsuarioDAO uDAO = new UsuarioDAO();

	@Override
	public void initialize(URL url,ResourceBundle res) {
		progressBar.setStyle("-fx-accent:green;");
	}
	public void registroButton(ActionEvent event) {
		// comprobamos si los campos no estan vacios
		if (usernameTextField.getText().isBlank() == false && enterPasswordField.getText().isBlank() == false) {
			registrar();
			LOG.info("Usuario Registrado");

		} else {
			mensajeLoginLabel.setText("Por favor introduce un nombre y una contraseña");
		}
	}

	public void registrar() {
		App m = new App();
		uDAO.registrar(mensajeLoginLabel, conn, pst, usernameTextField, enterPasswordField);

	}

	public void mostrarMenuLogearse(Event event) {
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
}
