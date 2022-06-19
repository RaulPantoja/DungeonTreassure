package Proyecto3EVALUACION.Proyecto3EVALUACION;

import java.net.URL;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import DAO.PartidaDAO;
import interfaces.IRankingController;
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

public class RankingController implements IRankingController, Initializable {

	
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

	ObservableList<Partida> oblist = FXCollections.observableArrayList();
	PartidaDAO pDAO = new PartidaDAO();
	//
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		List jugadores=pDAO.getPartidas();
		oblist.setAll(jugadores);
		
		col_pID.setCellValueFactory(new PropertyValueFactory<Partida,Integer>("partida_id"));
		col_tiempo.setCellValueFactory(new PropertyValueFactory<Partida, String>("tiempo"));
		col_fecha.setCellValueFactory(new PropertyValueFactory<Partida, String>("fecha"));
		
		col_username.setCellValueFactory(new PropertyValueFactory<Partida, String>("username"));
		col_uID.setCellValueFactory(new PropertyValueFactory<Partida, Integer>("user_id"));
		
		table_jugadores.setItems(oblist);
	}

	public void mostrarMenuRankingAdd(Event event) {
		try {

			Parent root = FXMLLoader.load(getClass().getResource("RankingAdd.fxml"));
			Scene scene = new Scene(root);
			Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			appStage.setScene(scene);
			appStage.toFront();
			appStage.show();

		} catch (Exception e) {
		}
	}

	public void mostrarMenuHistorial(Event event) {
		try {

			Parent root = FXMLLoader.load(getClass().getResource("Historial.fxml"));
			Scene scene = new Scene(root);
			Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			appStage.setScene(scene);
			appStage.toFront();
			appStage.show();

		} catch (Exception e) {
		}
	}
	public void mostrarMenuInicio(Event event) {
		try {

			Parent root = FXMLLoader.load(getClass().getResource("EscenaMain.fxml"));
			Scene scene = new Scene(root);
			Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			appStage.setScene(scene);
			appStage.toFront();
			appStage.show();

		} catch (Exception e) {
		}
	}

}
