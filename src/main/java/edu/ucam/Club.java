package edu.ucam;

import java.rmi.*;
import java.io.*;


@SuppressWarnings("unused")
public class Club implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String nombre;
		
	public Club (String id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Club() {
		// TODO Auto-generated constructor stub
	}

	public String getNombre() {
		return nombre;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}





