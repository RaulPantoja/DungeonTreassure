package utils;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.event.Event;
import javafx.scene.image.Image;

public class utils {

	public static BufferedImage cargarImagen(String url) {
		BufferedImage image = null;
			try {
				image =ImageIO.read(new FileInputStream(url));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return image;
		
	}
	
//	public static void cambiarEscena(Event event) {
//		
//	}
}
