package interfaces;

import java.awt.event.KeyEvent;

public interface IKeyHandler {

	void keyTyped(KeyEvent k);
	void keyPressed(KeyEvent k);
	void keyReleased(KeyEvent k);
}
