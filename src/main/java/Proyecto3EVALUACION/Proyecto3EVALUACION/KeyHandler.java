package Proyecto3EVALUACION.Proyecto3EVALUACION;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import interfaces.IKeyHandler;
//Siempre que presionamos una tecla en el teclado un objeto de la clase KeyEvent
//notifica a KeyListener, y la clase KeyEvent tiene metodos que pueden utilizarse para
// obtener informacion sobre el evento
public class KeyHandler implements KeyListener,IKeyHandler {

	public boolean upPressed, downPressed, leftPressed, rightPressed;
	@Override
	public void keyTyped(KeyEvent e) {

	}

	/**
	 * Este metodo sirve para comprobar el codigo de la tecla que pulsamos con el codigo de la tecla 
	 * que queremos hacer la comprobacion
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// este metodo devuelve un entero asociado a la tecla pulsada
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = true;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = true;
		}

	}

	/**
	 * Este metodo sirve para comprobar la tecla que soltamos, si el codigo de la tecla que dejamos de usar 
	 * es el mismo que el codigo de la tecla W lo que hace es poner el boolean upPressed en false
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = false;
		}
	}

}
