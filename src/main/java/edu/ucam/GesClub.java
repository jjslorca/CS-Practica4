package edu.ucam;

import java.rmi.*;

@SuppressWarnings("unused")
public interface GesClub extends java.rmi.Remote {

	public boolean loginUsuario (String login, String pass) throws java.rmi.RemoteException;
	
	public void addClub (Club club) throws java.rmi.RemoteException;
	
	public void updateClub (int index, Club club) throws java.rmi.RemoteException;
	
	public String getClub (String nombre) throws java.rmi.RemoteException;
	
	public void deleteClub (String nombre) throws java.rmi.RemoteException;
	
	public int buscaClub (String nombre) throws java.rmi.RemoteException;
	
	public int buscaJugador (String id) throws java.rmi.RemoteException;
	
	public int countClubes () throws java.rmi.RemoteException;
	
	public String listClubes () throws java.rmi.RemoteException;
	
	public void addJugador (Jugador jugador) throws java.rmi.RemoteException;
	
	public String getJugador (String id) throws java.rmi.RemoteException;
	
	public void deleteJugador (String id) throws java.rmi.RemoteException;
			
	public int countJugadores () throws java.rmi.RemoteException;
	
	public String listJugadores() throws java.rmi.RemoteException;
	
	public int countJugadoresClub (String nombre) throws java.rmi.RemoteException;
	
	public String listJugadoresClub (String nombre) throws java.rmi.RemoteException;

}
