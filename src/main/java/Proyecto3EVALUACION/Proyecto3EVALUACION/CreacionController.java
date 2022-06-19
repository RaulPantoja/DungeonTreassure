package Proyecto3EVALUACION.Proyecto3EVALUACION;

import java.io.File;

import java.net.URL;
import java.util.ResourceBundle;

import interfaces.ICreacionController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CreacionController implements Initializable,ICreacionController{

	@FXML
	private MediaView mediaView1;
	
	@FXML
	private MediaView mediaView2;
	
	@FXML
	private MediaView mediaView3;
	
	@FXML
	private Button playButton, pauseButton;
	
	private File file1;
	private Media media1;
	private MediaPlayer mediaPlayer1;
	
	private File file2;
	private Media media2;
	private MediaPlayer mediaPlayer2;
	
	private File file3;
	private Media media3;
	private MediaPlayer mediaPlayer3;
	
	//Inizializa los videos con el archivo que le pasemos 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		file1 = new File("leftPersonaje.mp4");
		media1 = new Media(file1.toURI().toString());
		mediaPlayer1 = new MediaPlayer(media1);
		mediaView1.setMediaPlayer(mediaPlayer1);
		
		file2 = new File("cofreCreacion.mp4");
		media2 = new Media(file2.toURI().toString());
		mediaPlayer2 = new MediaPlayer(media2);
		mediaView2.setMediaPlayer(mediaPlayer2);
		
		file3 = new File("puertaCreacion.mp4");
		media3 = new Media(file3.toURI().toString());
		mediaPlayer3 = new MediaPlayer(media3);
		mediaView3.setMediaPlayer(mediaPlayer3);
		
	}
	//Metodo que le asignamos al boton INICIAR para darle play al video
	public void playMedia() {
		
		mediaPlayer1.play();
		mediaPlayer2.play();
		mediaPlayer3.play();
	}
	//Metodo que le asignamos al boton PAUSA para pausar el video
	public void pauseMedia() {
		
		mediaPlayer1.pause();
		mediaPlayer2.pause();
		mediaPlayer3.pause();
	}
	
	
	//Muestra menu Tutorial
	public void mostrarMenuTutorial(Event event) {
	    try {

	        Parent root = FXMLLoader.load(getClass().getResource("Tutorial.fxml"));
	        Scene scene = new Scene(root);
	        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        appStage.setScene(scene);
	        appStage.toFront();
	        appStage.show();

	    } catch (Exception e) {
	    }
	}
}
