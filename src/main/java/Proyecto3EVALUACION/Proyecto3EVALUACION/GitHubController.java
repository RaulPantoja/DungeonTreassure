//package Proyecto3EVALUACION.Proyecto3EVALUACION;
//
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.layout.StackPane;
//import javafx.scene.web.WebEngine;
//import javafx.scene.web.WebView;
//import javafx.stage.Stage;
//
//public class GitHubController {
//
//	public void start(Stage primaryStage) {
//		try {
//			WebView webView = new WebView();
//
//			WebEngine webEngine = webView.getEngine();
//			webEngine.load("http://www.google.com");
//
//			StackPane root = new StackPane();
//			root.getChildren().add(webView);
//
//			Scene scene = new Scene(root, 300, 250);
//
//			primaryStage.setTitle("JavaFX Navegador Web");
//			primaryStage.setScene(scene);
//			primaryStage.show();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//}
