package model.objeto;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Puerta extends ObjetoPadre {

	public Puerta() {
		name="Puerta";
		try {
			image = ImageIO.read(getClass().getResource("/objetos/puerta.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}
}
