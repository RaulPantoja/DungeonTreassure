module Proyecto3EVALUACION.Proyecto3EVALUACION {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.base;
	requires java.sql;
	requires java.desktop;
	requires javafx.media;


	
    opens Proyecto3EVALUACION.Proyecto3EVALUACION to javafx.fxml;
    exports Proyecto3EVALUACION.Proyecto3EVALUACION;
    
    opens model to javafx.base;
    
}
