package model.objeto;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Cofre extends ObjetoPadre {

	public Cofre() {
		name="Cofre";
		try {
			image = ImageIO.read(getClass().getResource("/objetos/Cofre.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
