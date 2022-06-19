package Proyecto3EVALUACION.Proyecto3EVALUACION;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileWriter;
import java.io.IOException;

import interfaces.IApp;

/**
 * JavaFX App
 */
public class App extends Application implements IApp {
	
	
	private static Logger Log = Logger.getLogger(App.class.getName());
	
	private static Stage stg;
	@Override
	public void start(Stage primaryStage) {
		try {
			stg= primaryStage;
			Parent root=FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene=new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setIconified(false);
			primaryStage.setResizable(false);
			primaryStage.centerOnScreen();
			//Titulo aplicacion
			primaryStage.setTitle("Encuentra el tesoro");
			//Icono aplicacion
			primaryStage.getIcons().add(new Image(getClass().getResource("/assets/iconoAPP.png").toExternalForm()));
			scene.getStylesheets().add("boton.css");
			
			primaryStage.setOnCloseRequest(evento -> {
				//Si no llamamos al metodo consume del evento aunque pulsemos el boton cancel
				//se nos cerrara la aplicacion
				evento.consume();
				exit(primaryStage);
			});
		} catch(Exception e) {
			e.printStackTrace();
			Log.warning("Error al iniciar la escena principal");
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
		Logger logger = Logger.getLogger("Mi Logger");
		logger.log(Level.INFO,"No se ha podido cargar el stylesheet");
		//System.out.println(System.getProperty("java.home"));
		
	}
	
	public void cambiarEscena(String fxml) throws IOException{
		Parent pane = FXMLLoader.load(getClass().getResource(fxml));
		stg.getScene().setRoot(pane);
	}
	
	//Funcion para poder cerrar una ventana y que nos cree una alerta
	public void exit(Stage stage) {
		//Creo una alerta para confirmar que el usuario quiere salir del programa
		Alert alerta = new Alert(AlertType.CONFIRMATION);
		alerta.setTitle("Salir");
		alerta.setHeaderText("Estas a punto de cerrar sesion");
		alerta.setContentText("¿Seguro que quieres cerrar sesion?");
		
		//Si el usuario clicka el boton OK cerramos la ventana y mostramos un mensaje por consola.
		if(alerta.showAndWait().get()==ButtonType.OK) {
		Properties properties = new Properties();
		Properties properties2 = new Properties();
		properties2.setProperty("tiempoJuego", String.valueOf(0));
		properties.setProperty("user_id", String.valueOf("0"));
		try(FileWriter output = new FileWriter("config.properties")){
		    properties.store(output, "");
		    
		    FileWriter output2 = new FileWriter("tiempo.properties");
		    properties2.store(output2, "");
		    stage.close();
		    System.out.println("Has cerrado sesion");
		    
		} catch (IOException e) {
		    e.printStackTrace();
		}
			
			
		//Si el usuario clicka el boton cancel o pulsa la cruz de cerrar la ventana no hacemos nada
		}else {
			
		}
		
	}
}
