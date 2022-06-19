package Proyecto3EVALUACION.Proyecto3EVALUACION;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
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
import model.Usuario;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

import DAO.PartidaDAO;

public class RankingAddController implements IRankingAddController, Initializable {
	
	private static final Logger LOG = Logger.getLogger(RankingAddController.class.getName());
	@FXML
	private TableView<Partida> table_jugadores;

	@FXML
	private TableColumn<Partida, Integer> col_partida_id;

	@FXML
	private TableColumn<Partida, String> col_username;

	@FXML
	private TableColumn<Partida, Double> col_tiempo;

	@FXML
	private TableColumn<Partida, String> col_fecha;

	@FXML
	private TextField txt_username;

	@FXML
	private TextField txt_fecha;

	@FXML
	private TextField txt_tiempo;

	@FXML
	private TextField txt_partida_id;

	ObservableList<Partida> listM;
	ObservableList<Partida> dataList;

	int index = -1;

	PartidaDAO pDAO = new PartidaDAO();
	
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	/**
	 * Actualiza los atributos del usuario que hemos añadido a el table view, podemos actualizar
	 * el id, nombre de usuario, tiempo record y fecha en la que se jugo la partida
	 */
	public void editarJugador() {
		Properties properties = new Properties();
		String user_id="0";
		try(FileReader fileReader = new FileReader("config.properties")){
		    properties.load(fileReader);
		    user_id=properties.getProperty("user_id");
		} catch (IOException e) {
		    e.printStackTrace();
		}
		conn = mysqlConexion.getConexion();
		String value4 = txt_fecha.getText();
		String value1 = txt_partida_id.getText();
		System.out.println(value4);
		System.out.println(value1);
		System.out.println(user_id);
			try {
				String sql = "UPDATE `partida` SET `fecha` = '"+value4+"' WHERE partida_id = "+value1+" AND user_id = "+user_id;
				pst = conn.prepareStatement(sql);
				pst.execute();
				
				//muestra una ventana emergente con el mensaje de que el jugador se ha actualizado
				JOptionPane.showMessageDialog(null, "Jugador Actualizado");
				LOG.info("Se ha actualizado la partida con id: "+txt_partida_id.getText());
			} catch (SQLException e) {
				//muestra una ventana emergente con un mensaje de error de que la partida no se ha podido
				//actualizar
				JOptionPane.showMessageDialog(null, "Error al actualizar el jugador");
				LOG.warning("Error al actualizar el jugador");
				e.printStackTrace();
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
		txt_partida_id.setText(col_partida_id.getCellData(index).toString());
		txt_username.setText(col_username.getCellData(index).toString());
		txt_tiempo.setText(col_tiempo.getCellData(index).toString());
		txt_fecha.setText(col_fecha.getCellData(index).toString());

	}

	public void eliminarJugador() {
		Properties properties = new Properties();
		String user_id="0";
		try(FileReader fileReader = new FileReader("config.properties")){
		    properties.load(fileReader);
		    user_id=properties.getProperty("user_id");
		} catch (IOException e) {
		    e.printStackTrace();
		}
		conn = mysqlConexion.getConexion();
		String sql = "DELETE FROM partida WHERE partida_id = ? AND user_id ="+user_id;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, txt_partida_id.getText());
			pst.execute();
			//muestra mensaje por pantalla para notificar que la partida ha sido eliminado
			JOptionPane.showMessageDialog(null, "Jugador Eliminado");
			LOG.info("Se ha eliminado la partida con id: "+txt_partida_id.getText());
		} catch (Exception e) {
			//muestra mensaje por pantalla de que la partida no se ha eliminado
			JOptionPane.showMessageDialog(null, "Error al eliminar la partida");
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
		Properties properties = new Properties();
		String user_id="0";
		try(FileReader fileReader = new FileReader("config.properties")){
		    properties.load(fileReader);
		    user_id=properties.getProperty("user_id");
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		List historial = pDAO.getHistorial(Integer.valueOf(user_id));
		oblist.setAll(historial);
		
		col_partida_id.setCellValueFactory(new PropertyValueFactory<Partida,Integer>("partida_id"));
		col_tiempo.setCellValueFactory(new PropertyValueFactory<Partida, Double>("tiempo"));
		col_fecha.setCellValueFactory(new PropertyValueFactory<Partida, String>("fecha"));
		
		col_username.setCellValueFactory(new PropertyValueFactory<Partida, String>("username"));
		
		
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
