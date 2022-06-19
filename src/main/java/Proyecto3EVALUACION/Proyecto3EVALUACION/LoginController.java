package Proyecto3EVALUACION.Proyecto3EVALUACION;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

import DAO.UsuarioDAO;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class LoginController implements Initializable {

	
	@FXML
	private Button cancelButton;
	@FXML
	private Label mensajeLoginLabel;
	@FXML
	private ImageView ImageView;
	@FXML
	private ImageView lockImageView;
	@FXML
	TextField usernameTextField=new TextField("user");
	@FXML
	private PasswordField enterPasswordField;

	UsuarioDAO uDAO = new UsuarioDAO();

	String usuario=usernameTextField.getText();

	private int user_id;
	


	

	@Override
	public void initialize(URL url, ResourceBundle res) {
//		File lockFile = new File("file:/E:/eclipse/3Proyecto3EVALUACION/src/main/resources/assets/lock.png");
//		Image lockImage = new Image(lockFile.toURI().toString());
//		ImageView.setImage(lockImage);
//		
//		File file = new File("file:/E:/eclipse/3Proyecto3EVALUACION/src/main/resources/assets/mapa.png");
//		Image image = new Image(file.toURI().toString());
//		ImageView.setImage(image);
	}

	//Metodo asignado al boton logearse que lo que hace es comprobar si los campos estan vacios ejecutamos 
	//el metodo validarLogin() y si no mostramos un mensaje en el Label de que introduzca los campos
	public void loginButtonOnAction(ActionEvent event) {
		// comprobamos si los campos no estan vacios
		if (usernameTextField.getText().isBlank() == false && enterPasswordField.getText().isBlank() == false) {
		
			user_id=validarLogin();

			
			Properties properties = new Properties();

			properties.setProperty("user_id", String.valueOf(user_id));
			try(FileWriter output = new FileWriter("config.properties")){
			    properties.store(output, "");
			} catch (IOException e) {
			    e.printStackTrace();
			}
			
//			p.setProperty("user_id", String.valueOf(user_id));
			
		} else {
			mensajeLoginLabel.setText("Por favor introduce un nombre y una contraseña");
		}
	}
	
	

	

	//este metodo esta asignado al boton cancelar que cierra la aplicacion cuando se pulsa
	public void cancelButtonOnAction(ActionEvent event) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}

	public int validarLogin() {
		return uDAO.validarLogin(mensajeLoginLabel, null, null, usernameTextField, enterPasswordField);
		
	}

	public void crearCuenta() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Registro.fxml"));
			Stage registroStage = new Stage();
			registroStage.setScene(new Scene(root, 493, 638));
			registroStage.show();
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
	}

	public void mostrarMenuAcuerdo(Event event) {
		try {

			Parent root = FXMLLoader.load(getClass().getResource("Acuerdo.fxml"));
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
