package model;

public class Partida {

	public int partida_id;
	public String fecha;
	public double tiempo;
	public Usuario u;
	
	public Partida(int partida_id, String fecha, double tiempo, Usuario u) {
		super();
		this.partida_id = partida_id;
		this.fecha = fecha;
		this.tiempo = tiempo;
		this.u = u;
	}
	public Partida(int int1, String string, double double1, int int2) {
		// TODO Auto-generated constructor stub
	}
	public int getPartida_id() {
		return partida_id;
	}
	public void setPartida_id(int partida_id) {
		this.partida_id = partida_id;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public double getTiempo() {
		return tiempo;
	}
	public void setTiempo(double tiempo) {
		this.tiempo = tiempo;
	}
	public Usuario getU() {
		return u;
	}
	public void setU(Usuario u) {
		this.u = u;
	}
	public int getUser_id() {
		return u.getUser_id();
	}
	public String getUsername() {
		return u.getUsername();
	}
	@Override
	public String toString() {
		return "Partida con id= " + partida_id + ", en la fecha= " + fecha + ", tiene un tiempo de =" + tiempo + ", y el usuario es= " + u;
	}

	
}
