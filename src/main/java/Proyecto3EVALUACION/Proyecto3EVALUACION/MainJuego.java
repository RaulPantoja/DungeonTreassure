package Proyecto3EVALUACION.Proyecto3EVALUACION;

import javax.swing.JFrame;

public class MainJuego {

	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setResizable(false);
		window.setTitle("¡ENCUENTRA EL TESORO!");
		JuegoController gamePanel = new JuegoController();
		window.add(gamePanel);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		//llamamos al metodo para colocar los objetos
		gamePanel.setupGame();
		gamePanel.empezarJuegoThread();
	}

}
