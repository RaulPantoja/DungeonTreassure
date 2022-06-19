package DAO;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import Proyecto3EVALUACION.Proyecto3EVALUACION.App;
import Proyecto3EVALUACION.Proyecto3EVALUACION.LoginController;
import Proyecto3EVALUACION.Proyecto3EVALUACION.mysqlConexion;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Partida;
import model.Usuario;

public class UsuarioDAO {
	private static final Logger LOG = Logger.getLogger(UsuarioDAO.class.getName());

	
	public Usuario usuarioActivo;
	/*
	 * Metodo para devolver lista de partidas de cada usuario en especifico
	 */
	
//	public List<Usuario> getAll(){
//		List<Partida> partidas = new ArrayList<Partida>();
//		Connection con = mysqlConexion.getConexion();
//		try {
//			ResultSet rs = con.createStatement().executeQuery(
//					"SELECT p.fecha,p.partida_id,p.tiempo,u.username,u.user_id FROM partida p, usuario u WHERE p.user_id=u.user_id ORDER BY p.tiempo  ");
//			while (rs.next()) {
//				Partida p = new Partida(0, null, 0, null);
//				Usuario u = new Usuario(0, null);
//				p.setFecha(rs.getString(1));
//				p.setPartida_id(rs.getInt(2));
//				p.setTiempo(rs.getDouble(3));
//				u.setUsername(rs.getString(4));
//				u.setUser_id(rs.getInt(5));
//				p.setU(u);
//				partidas.add(p);
//				
//				//System.out.println(partidas);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return partidas;
//		
//		
//	}
	
	
	

	
	public List<Partida> getPartida(Usuario u){
		List<Partida> historial = new ArrayList<Partida>();
		Connection con = mysqlConexion.getConexion();
		try {
			ResultSet rs = con.createStatement().executeQuery(
					"SELECT u.user_id,u.username,p.fecha,p.partida_id,p.tiempo FROM partida p, usuario u WHERE u.user_id="+u.getUser_id());
			while(rs.next()) {
				String usuarioID=rs.getString("u.user_id");
				if(usuarioID.equals(u.getUser_id())) {
					Partida p = new Partida(0, null, 0, 0);
					p.setFecha(rs.getString(3));
					p.setPartida_id(rs.getInt(4));
					p.setTiempo(rs.getDouble(5));
					u.setUsername(u.getUsername());
					u.setUser_id(u.getUser_id());
					p.setU(u);
					historial.add(p);
					System.out.println(historial);
				}else {
					
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return historial;
	}
	
	
	
	
	
	/**
	 * Este metodo nos registra un jugador en la base de datos
	 * 
	 * @param mensajeLoginLabel  Sirve para mostrar un texto si el registro se ha
	 *                           completado
	 * @param conn               la conexion para la base de datos
	 * @param pst                el prepare statement
	 * @param usernameTextField  el nombre de usuario que queramos
	 * @param enterPasswordField la contrasea que queramos usar
	 */
	public void registrar(Label mensajeLoginLabel, Connection conn, PreparedStatement pst, TextField usernameTextField,
			PasswordField enterPasswordField) {
		conn = mysqlConexion.getConexion();
		if (!usernameTextField.getText().equals("")&& !enterPasswordField.getText().equals("")) {
			// con MD5 antes de poner la contraseña lo que hacemos es encriptarla
			String sql = "INSERT INTO usuario(username,password) VALUES (?,MD5(?))";
			try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, usernameTextField.getText());
				pst.setString(2, enterPasswordField.getText());
				pst.execute();
				mensajeLoginLabel.setText("Te has registrado");
				LOG.info("Te has registrado");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}else {
			mensajeLoginLabel.setText("Valores vacios");
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
	 * Este metodo sirve para comprobar si en la base de datos hay una cuenta creada
	 * con el nombre de usuario y contraseña que se le pasa
	 * 
	 * @param mensajeLoginLabel  muestra mensaje si iniciamos sesion
	 * @param conn               para la conexion
	 * @param pst
	 * @param usernameTextField  campo de texto con el nombre de usuario
	 * @param enterPasswordField campo de texto con la contraseña
	 */

	public int validarLogin(Label mensajeLoginLabel, Connection conn, PreparedStatement pst,
			TextField usernameTextField, PasswordField enterPasswordField) {
		App m = new App();
		mysqlConexion.getConexion();
		Connection con = mysqlConexion.getConexion();

		String verificarLogin = "SELECT count(1) from usuario WHERE username = '" + usernameTextField.getText()
				+ "'AND password='" + getMD5(enterPasswordField.getText()) + "'";
		String query ="SELECT user_id FROM usuario WHERE username ='"+usernameTextField.getText()+"'"+" AND password ='"+getMD5(enterPasswordField.getText())+"'";
		
		
		
		try {
			
			Statement statement2 = mysqlConexion.getConexion().createStatement();
			// ejecutamos la consulta de verificar login
			ResultSet rs = statement2.executeQuery(query);
			Statement st = mysqlConexion.getConexion().createStatement();
			ResultSet consultaResult = st.executeQuery(verificarLogin);
			// comprobamos por todas las columnas y la consulta nos devuelve 1 y si nos
			// devuelve uno
			// significa que hay un usuario con ese nombre y contraseña por lo que pasamos a
			// cambiar la escena
			// al menu de inicio de la aplicacion
			while (consultaResult.next()) {
				if (consultaResult.getInt(1) == 1) {
					m.cambiarEscena("EscenaMain.fxml");
					LOG.info("Has iniciado sesion");
					if(rs.next()) {
						System.out.println(rs.getInt(1));
						return rs.getInt("user_id");
					}
					
				} else {
					LOG.info("Error al iniciar sesion");
					mensajeLoginLabel.setText("Inicio de sesion incorrecto, prueba otra vez");
					return 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		return 0;
		
	}
}
