package DAO;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import Proyecto3EVALUACION.Proyecto3EVALUACION.App;
import Proyecto3EVALUACION.Proyecto3EVALUACION.mysqlConexion;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class UsuarioDAO {
	private static final Logger LOG = Logger.getLogger(UsuarioDAO.class.getName());

	
	
	/**
	 * Este metodo nos registra un jugador en la base de datos
	 * @param mensajeLoginLabel Sirve para mostrar un texto si el registro se ha completado
	 * @param conn la conexion para la base de datos
	 * @param pst el prepare statement 
	 * @param usernameTextField el nombre de usuario que queramos
	 * @param enterPasswordField la contrasea que queramos usar 
	 */
	public void registrar(Label mensajeLoginLabel,Connection conn,PreparedStatement pst,TextField usernameTextField,PasswordField enterPasswordField) {
		conn = mysqlConexion.getConexion();
		//con MD5 antes de poner la contraseña lo que hacemos es encriptarla
		String sql = "INSERT INTO usuario(username,password) VALUES (?,MD5(?))";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, usernameTextField.getText());
			pst.setString(2, enterPasswordField.getText());
			pst.execute();
			mensajeLoginLabel.setText("Te has registrado");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	// encriptar contraseña con MD5
		public static String getMD5(String input) {
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] messageDigest = md.digest(input.getBytes());
				BigInteger number = new BigInteger(1, messageDigest);
				String hashtext = number.toString(16);

				while (hashtext.length() < 32) {
					hashtext = "0" + hashtext;
				}
				return hashtext;
			} catch (NoSuchAlgorithmException e) {
				throw new RuntimeException(e);
			}
		}
		/**
		 * Este metodo sirve para comprobar si en la base de datos hay una cuenta creada con el 
		 * nombre de usuario y contraseña que se le pasa
		 * @param mensajeLoginLabel muestra mensaje si iniciamos sesion
		 * @param conn para la conexion
		 * @param pst 
		 * @param usernameTextField campo de texto con el nombre de usuario
		 * @param enterPasswordField campo de texto con la contraseña 
		 */
		public void validarLogin(Label mensajeLoginLabel,Connection conn,PreparedStatement pst,TextField usernameTextField,PasswordField enterPasswordField) {
			App m = new App();
			mysqlConexion.getConexion();

//			getMD5(enterPasswordField.getText());
//			System.out.println(getMD5(enterPasswordField.getText()));
			String verificarLogin = "SELECT count(1) from usuario WHERE username = '" + usernameTextField.getText()
					+ "'AND password='" + getMD5(enterPasswordField.getText()) + "'";

			
			try {
				Statement statement = mysqlConexion.getConexion().createStatement();
				//ejecutamos la consulta de verificar login
				ResultSet consultaResult = statement.executeQuery(verificarLogin);
				//comprobamos por todas las columnas y la consulta nos devuelve 1 y si nos devuelve uno 
				//significa que hay un usuario con ese nombre y contraseña por lo que pasamos a cambiar la escena
				//al menu de inicio de la aplicacion
				while (consultaResult.next()) {
					if (consultaResult.getInt(1) == 1) {
						//*nunca se lleva a ver esto* mensajeLoginLabel.setText("Has iniciado sesion");
						m.cambiarEscena("/EscenaMain.fxml");
						LOG.info("Has iniciado sesion");
					} else {
						LOG.info("Error al iniciar sesion");
						mensajeLoginLabel.setText("Inicio de sesion incorrecto, prueba otra vez");
						
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				e.getCause();
			}
		}
}
