package Proyecto3EVALUACION.Proyecto3EVALUACION;

import java.net.URL;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import interfaces.IRankingAddController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import model.Partida;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

public class RankingAddController implements IRankingAddController, Initializable {
	
	private static final Logger LOG = Logger.getLogger(RankingAddController.class.getName());
	@FXML
	private TableView<Partida> table_jugadores;

	@FXML
	private TableColumn<Partida, Integer> col_id;

	@FXML
	private TableColumn<Partida, String> col_username;

	@FXML
	private TableColumn<Partida, String> col_tiempo;

	@FXML
	private TableColumn<Partida, String> col_fecha;

	@FXML
	private TextField txt_username;

	@FXML
	private TextField txt_fecha;

	@FXML
	private TextField txt_tiempo;

	@FXML
	private TextField txt_id;

	ObservableList<Partida> listM;
	ObservableList<Partida> dataList;

	int index = -1;

	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;



	/**
	 * Añade un jugador a la base de datos con los valores que se introducen en los
	 * text field 
	 */
	public void añadirJugador() {
		LOG.info("Se ha pulsado el boton añadir jugador");
		conn = mysqlConexion.getConexion();
		String sql = "INSERT INTO partida (tiempo,fecha,user)values(?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
//			pst.setString(1, txt_username.getText());
//			LOG.info("Se ha introducido el nombre de usuario");
			pst.setString(1, txt_tiempo.getText());
			LOG.info("Se ha introducido el tiempo record");
			pst.setString(2, txt_fecha.getText());
			LOG.info("Se ha introducido la fecha");
			pst.setString(3, txt_username.getText());
			LOG.info("Se ha introducido el nombre de usuario");
			pst.execute();
			

			JOptionPane.showMessageDialog(null, "Jugador añadido correctamente");
			LOG.info("El jugador ha sido introducido en la base de datos");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Introduce los valores correctos");
			LOG.warning("Valor incorrecto, escribe en formato AAAA-MM-DD, y no como "+txt_fecha.getText());
			
		}
	}
	/**
	 * Actualiza los atributos del usuario que hemos añadido a el table view, podemos actualizar
	 * el id, nombre de usuario, tiempo record y fecha en la que se jugo la partida
	 */
	public void editarJugador() {
		try {
			conn = mysqlConexion.getConexion();
			String value1 = txt_id.getText();
			String value2 = txt_username.getText();
			String value3 = txt_tiempo.getText();
			String value4 = txt_fecha.getText();
			String sql = "UPDATE partida SET partida_id= '" + value1 + "',user= '" + value2 + "',tiempo= '" + value3
					+ "',fecha= '" + value4 + ",' WHERE partida_id='" + value1 + "' ";
			pst = conn.prepareStatement(sql);
			pst.execute();
			//muestra una ventana emergente con el mensaje de que el jugador se ha actualizado
			JOptionPane.showMessageDialog(null, "Jugador Actualizado");
			LOG.info("Se ha actualizado el jugador con id: "+txt_id.getText());
		} catch (Exception e) {
			//muestra una ventana emergente con un mensaje de error de que el usuario no se ha podido
			//actualizar
			JOptionPane.showMessageDialog(null, "Error al actualizar el jugador");
			LOG.warning("Error al actualizar el jugador");
		}

	}

	@FXML

	/**
	 * Este metodo nos carga todos los datos del tableview del jugador que queramos, y asi podemos 
	 * eliminarlo o actualizar alguno de sus datos
	 */
	public void cargarDatos(MouseEvent event) {
		index = table_jugadores.getSelectionModel().getSelectedIndex();
		if (index <= -1) {

			return;
		}
		txt_id.setText(col_id.getCellData(index).toString());
		txt_username.setText(col_username.getCellData(index).toString());
		txt_tiempo.setText(col_tiempo.getCellData(index).toString());
		txt_fecha.setText(col_fecha.getCellData(index).toString());

	}

	public void eliminarJugador() {
		conn = mysqlConexion.getConexion();
		String sql = "DELETE FROM partida WHERE partida_id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, txt_id.getText());
			pst.execute();
			//muestra mensaje por pantalla para notificar que el jugador ha sido eliminado
			JOptionPane.showMessageDialog(null, "Jugador Eliminado");
			LOG.info("Se ha eliminado el jugador con id: "+txt_id.getText());
		} catch (Exception e) {
			//muestra mensaje por pantalla de que el jugador no se ha eliminado
			JOptionPane.showMessageDialog(null, "Error al eliminar el jugador");
		}

	}

	ObservableList<Partida> oblist = FXCollections.observableArrayList();

	/**
	 * El metodo initialize lo usamos implementando de Initialize, este metodo nos sirve para que cuando
	 * nos metamos a la pantalla de añadir jugador al ranking se carguen todos los datos que hay guardados
	 * en la base de datos haciendo un select
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			Connection con = mysqlConexion.getConexion();
			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM partida");

			while (rs.next()) {
				oblist.add(new Partida(rs.getInt("partida_id"), rs.getString("user"), rs.getString("tiempo"),
						rs.getString("fecha")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		col_id.setCellValueFactory(new PropertyValueFactory<Partida, Integer>("partida_id"));
		col_username.setCellValueFactory(new PropertyValueFactory<Partida, String>("user"));
		col_tiempo.setCellValueFactory(new PropertyValueFactory<Partida, String>("tiempo"));
		col_fecha.setCellValueFactory(new PropertyValueFactory<Partida, String>("fecha"));

		table_jugadores.setItems(oblist);
	}

	//Muestra menu Ranking
	public void mostrarMenuRanking(Event event) {
		try {

			Parent root = FXMLLoader.load(getClass().getResource("Ranking.fxml"));
			Scene scene = new Scene(root);
			Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			appStage.setScene(scene);
			appStage.toFront();
			appStage.show();

		} catch (Exception e) {
		}
	}

}
