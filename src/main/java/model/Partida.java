package model;

public class Partida {

	int partida_id;
	String user,tiempo,fecha;
	public Partida(int partida_id,String user,String tiempo,String fecha) {
		super();
		this.partida_id = partida_id;
		this.user = user;
		this.tiempo = tiempo;
		this.fecha = fecha;
	}
	public int getPartida_id() {
		return partida_id;
	}
	public void setPartida_id(int partida_id) {
		this.partida_id = partida_id;
	}
	public String getUser() {
		return user;
	}
	public void setUsername(String user) {
		this.user = user;
	}
	public String getTiempo() {
		return tiempo;
	}
	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
}
