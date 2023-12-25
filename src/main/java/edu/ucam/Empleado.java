package edu.ucam;

import java.io.Serializable;

@SuppressWarnings("unused")
public class Empleado implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String nombre;
	private String password;
	
	
	public Empleado(String id, String nombre, String password) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.password = password;
	}


	public Empleado () {
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


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
}