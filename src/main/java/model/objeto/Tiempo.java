package model.objeto;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Tiempo extends ObjetoPadre{
	
	public Tiempo() {
		name="Tiempo";
		try {
			image = ImageIO.read(getClass().getResource("/objetos/relojArena3232.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
