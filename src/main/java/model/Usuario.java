package model;

import java.util.ArrayList;

public class Usuario {

	public int user_id;
	public String username;
	public String password;
	ArrayList<Partida> historial;
	
	public Usuario(int user_id, String username, String password, ArrayList<Partida> historial) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.historial = historial;
	}

	public Usuario(int int1, String string) {
		this.user_id = int1;
		this.username = string;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Partida> getHistorial() {
		return historial;
	}

	public void setHistorial(ArrayList<Partida> historial) {
		this.historial = historial;
	}
	
	
}
