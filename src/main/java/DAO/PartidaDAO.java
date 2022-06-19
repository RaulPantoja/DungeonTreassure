package DAO;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import Proyecto3EVALUACION.Proyecto3EVALUACION.RankingAddController;
import Proyecto3EVALUACION.Proyecto3EVALUACION.mysqlConexion;
import javafx.scene.control.Label;
import model.Partida;
import model.Usuario;

public class PartidaDAO {

	private static final Logger LOG = Logger.getLogger(RankingAddController.class.getName());
	
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	
	public List<Partida> getPartidas() {
		List<Partida> partidas = new ArrayList<Partida>();
		Connection con = mysqlConexion.getConexion();
		try {
			ResultSet rs = con.createStatement().executeQuery(
					"SELECT p.fecha,p.partida_id,p.tiempo,u.username,u.user_id FROM partida p, usuario u WHERE p.user_id=u.user_id ORDER BY p.tiempo  ");
			while (rs.next()) {
				Partida p = new Partida(0, null, 0, null);
				Usuario u = new Usuario(0, null);
				p.setFecha(rs.getString(1));
				p.setPartida_id(rs.getInt(2));
				p.setTiempo(rs.getDouble(3));
				u.setUsername(rs.getString(4));
				u.setUser_id(rs.getInt(5));
				p.setU(u);
				partidas.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return partidas;

	}
	
	public List<Partida> getHistorial(int user_id) {
		List<Partida> partidas = new ArrayList<Partida>();
		Connection con = mysqlConexion.getConexion();
		try {
			ResultSet rs = con.createStatement().executeQuery(
					"SELECT p.partida_id,p.tiempo,p.fecha,p.user_id,u.username FROM partida p, usuario u WHERE p.user_id ='" +user_id+ " 'AND p.user_id=u.user_id ORDER BY p.fecha");
			while (rs.next()) {
				Partida p = new Partida(0, null, 0, null);
				Usuario u = new Usuario(0, null);
				p.setFecha(rs.getString(3));
				p.setPartida_id(rs.getInt(1));
				p.setTiempo(rs.getDouble(2));
				u.setUsername(rs.getString(5));
				u.setUser_id(rs.getInt(4));
				p.setU(u);
				partidas.add(p);
				
				System.out.println(partidas);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return partidas;
	}
	
	int index = -1;
	/**
	 * Añade una nueva marca a la tabla partidas, este metodo lo llamamos en la clase HUD, que cuando acabamos el juego llamamos a la funcion
	 * y nos lo inserta en la base de datos
	 */
	public void añadirJugador() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String date2 = dateFormat.format(date);
		Properties properties = new Properties();
		String user_id="0";
		try(FileReader fileReader = new FileReader("config.properties")){
		    properties.load(fileReader);
		    user_id=properties.getProperty("user_id");
		} catch (IOException e) {
		    e.printStackTrace();
		}
		String tiempoJuego="0";
		try(FileReader fileReader = new FileReader("tiempo.properties")){
		    properties.load(fileReader);
		    tiempoJuego=properties.getProperty("tiempoJuego");
		} catch (IOException e) {
		    e.printStackTrace();
		}
		double tiempo = Double.parseDouble(tiempoJuego);
		double redondeo = Math.round(tiempo*100.0)/100.0;
		Usuario u = new Usuario(index, null);
		LOG.info("Se ha pulsado el boton añadir jugador");
		conn = mysqlConexion.getConexion();
		String sql = "INSERT INTO `partida` (`tiempo`, `fecha`, `user_id`) VALUES (?, ?, ?);";
		try {
			
			System.out.println(redondeo);
			pst = conn.prepareStatement(sql);
			pst.setInt(3,Integer.valueOf(user_id));
			pst.setDouble(1,redondeo);
			LOG.info("Se ha introducido el tiempo record");
			pst.setString(2,date2);
			LOG.info("Se ha introducido la fecha");
			pst.execute();
			

			JOptionPane.showMessageDialog(null, "Jugador añadido correctamente");
			LOG.info("El jugador ha sido introducido en la base de datos");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Introduce los valores correctos");
			LOG.warning("Valor incorrecto, escribe en formato AAAA-MM-DD, y no como "+
					" o introduce un usuario existente,\n ya que el usuario ");
			System.out.println(user_id);
			System.out.println(redondeo);
			System.out.println(date2);
			
		}
	}
	
	public int contador() {
		int contador = 0;
		Properties properties = new Properties();
		String user_id="0";
		try(FileReader fileReader = new FileReader("config.properties")){
		    properties.load(fileReader);
		    user_id=properties.getProperty("user_id");
		} catch (IOException e) {
		    e.printStackTrace();
		}
			String query ="SELECT COUNT(*) FROM partida WHERE user_id='"+user_id+"'";
			Connection con = mysqlConexion.getConexion();
			try {
				ResultSet rs = con.createStatement().executeQuery(
						"SELECT p.partida_id,p.tiempo,p.fecha,p.user_id,u.username FROM partida p, usuario u WHERE p.user_id ='" +user_id+ " 'AND p.user_id=u.user_id ORDER BY p.fecha");
				while (rs.next()) {
					contador++;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return contador;
		}
	
}
