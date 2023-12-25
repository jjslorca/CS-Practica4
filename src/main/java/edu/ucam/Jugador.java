package edu.ucam;

import java.io.Serializable;

@SuppressWarnings("unused")
public class Jugador implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String nombre;
	private String club;
	
	
	public Jugador(String id, String nombre, String club) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.club = club;
	}

	public Jugador() {
		// TODO Auto-generated constructor stub
	} 

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setClub(String club) {
		this.club = club;
	}
	
	public String getClub() {
		return club;
	}
}