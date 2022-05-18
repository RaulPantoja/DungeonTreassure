package model.objeto;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Llave extends ObjetoPadre{
	
	public Llave() {
		name="Llave";
		try {
			image = ImageIO.read(getClass().getResource("/objetos/llave.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
