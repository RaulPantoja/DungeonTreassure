package Proyecto3EVALUACION.Proyecto3EVALUACION;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import DAO.PartidaDAO;
import DAO.UsuarioDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Partida;
import model.Usuario;

public class HistorialController implements Initializable {

	@FXML 
	public TableView<Partida> table_jugadores;
	
	@FXML
	public TableColumn<Partida,Integer> col_pID;
	@FXML
	public TableColumn<Partida, Integer> col_uID;

	@FXML
	public TableColumn<Partida, String> col_username;

	@FXML
	public TableColumn<Partida, String> col_tiempo;

	@FXML
	public TableColumn<Partida, String> col_fecha;
	
	@FXML
	private TextField partidas;
	ObservableList<Partida> oblist = FXCollections.observableArrayList();
	Usuario u = new Usuario(0, null, null, null);
	UsuarioDAO uDAO = new UsuarioDAO();
	PartidaDAO pDAO = new PartidaDAO();
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Properties properties = new Properties();
		String user_id="0";
		try(FileReader fileReader = new FileReader("config.properties")){
		    properties.load(fileReader);
		    user_id=properties.getProperty("user_id");
		} catch (IOException e) {
		    e.printStackTrace();
		}
		List historial = pDAO.getHistorial(Integer.valueOf(user_id));
		System.out.println();
		oblist.setAll(historial);
		col_pID.setCellValueFactory(new PropertyValueFactory<Partida,Integer>("partida_id"));
		col_tiempo.setCellValueFactory(new PropertyValueFactory<Partida, String>("tiempo"));
		col_fecha.setCellValueFactory(new PropertyValueFactory<Partida, String>("fecha"));
		
		col_username.setCellValueFactory(new PropertyValueFactory<Partida, String>("username"));
		col_uID.setCellValueFactory(new PropertyValueFactory<Partida, Integer>("user_id"));
		table_jugadores.setItems(oblist);
		contarPartidas();
	}
	
	
	public void contarPartidas() {
		int contador = table_jugadores.getItems().size();
		partidas.setText(" "+contador);
	}
	
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
