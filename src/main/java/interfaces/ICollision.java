package interfaces;

import model.Entity.Entity;

public interface ICollision {

	void checkTile(Entity entidad);
	int checkObject(Entity entidad,boolean jugador);
}
