package Proyecto3EVALUACION.Proyecto3EVALUACION;

import java.net.URL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

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

public class RankingController implements IRankingController, Initializable {

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

	ObservableList<Partida> oblist = FXCollections.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		try {
			Connection con = mysqlConexion.getConexion();
			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM partida ORDER BY tiempo ");

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

	public void mostrarMenuInicio(Event event) {
		try {

			Parent root = FXMLLoader.load(getClass().getResource("/EscenaMain.fxml"));
			Scene scene = new Scene(root);
			Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			appStage.setScene(scene);
			appStage.toFront();
			appStage.show();

		} catch (Exception e) {
		}
	}

}
