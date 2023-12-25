package edu.ucam.server;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

import edu.ucam.GesClub;
import edu.ucam.Empleado;
import edu.ucam.Club;
import edu.ucam.Jugador;


@SuppressWarnings("unused")
public class GesClubImpl extends UnicastRemoteObject implements GesClub{

	private static final long serialVersionUID = 1L;
	private ArrayList<Empleado> empleados = new ArrayList<Empleado>();
	private ArrayList<Club> clubes = new ArrayList<Club>();
	private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	
	
	Empleado empleado = new Empleado("12345678T", "admin", "admin");
	Club club = new Club();
	Jugador jugador = new Jugador();
	
	public GesClubImpl() throws RemoteException 
	{ super(); }

	
	@Override
	public boolean loginUsuario (String login, String pass) throws RemoteException {
		this.empleados.add(empleado); // Usuario creado por defecto (admin admin)
		boolean aux = false;
		for(Empleado e: this.empleados) {
			String usuario = e.getNombre();
			String password = e.getPassword();
			if ((login.equalsIgnoreCase(usuario) == true) && (pass.equalsIgnoreCase(password) == true)){
				aux = true;}	
		
		}
		return aux;
	}	
		
	
	@Override
	public void addClub (Club club) throws RemoteException{ 
		clubes.add(club);
	}
	
	@Override
	public void updateClub (int index, Club club) throws RemoteException {
		clubes.set(index, club);
	}

	@Override
	public String getClub (String nombre) throws RemoteException {
		club = clubes.get(this.buscaClub(nombre));
		return club.getId() + ", " + club.getNombre();
	}

	@Override
	public void deleteClub (String nombre) throws RemoteException {
		clubes.remove(this.buscaClub(nombre));
	}	

	
	@Override
	public int buscaClub (String nombre) throws RemoteException {
		int pos = -1;
		for(Club club : this.clubes) {
			if (club.getNombre().equalsIgnoreCase(nombre)) {
				pos = clubes.indexOf(club);
			}
		}
		return pos;
	}
		
	@Override
	public int countClubes () throws RemoteException {
			
		return clubes.size();
	}

	@Override
	public String listClubes () throws RemoteException {
		String string = "";
		for(Club club : clubes) {
			string = string + "(" + clubes.indexOf(club) + ")" + club.getId() + ", " + club.getNombre() + " |-| ";
			
		}
		return string;
	}
	
	@Override
	public int buscaJugador (String id) throws RemoteException {
		int pos = -1;
		for(Jugador jugador : this.jugadores) {
			if (jugador.getId().equalsIgnoreCase(id)) {
				pos = jugadores.indexOf(jugador);
			}
		}	
		return pos;
	}
	
	@Override
	public void addJugador(Jugador jugador) throws RemoteException {
		jugadores.add(jugador);
	}	
	
	
	@Override
	public String getJugador (String id) throws RemoteException {
		jugador = jugadores.get(this.buscaJugador(id));
		return jugador.getId() + ", " + jugador.getNombre() + ", " + jugador.getClub();
	}


	@Override
	public void deleteJugador(String nombre) throws RemoteException {
		jugadores.remove(this.buscaJugador(nombre));
	}

	@Override
	public int countJugadores () throws RemoteException {
				
		return jugadores.size();
	}
	
	@Override
	public String listJugadores () throws RemoteException {
		String string = "";
		for(Jugador jugador : jugadores) {
			string = string + "(" + jugadores.indexOf(jugador) + ")" + jugador.getId() + ", " + jugador.getNombre() + ", " + jugador.getClub() + " |-| ";
			
		}
		return string;
	}
	
	@Override
	public int countJugadoresClub(String nombre) throws RemoteException {
		int aux = 0;
		for (Jugador jugador : jugadores)
			if (jugador.getClub().equalsIgnoreCase(nombre)) {
				aux++;
			}
		return aux;
	}
	
	public String listJugadoresClub (String nombre) throws RemoteException {
		String string = "";
		for(Jugador jugador : jugadores) {
			if (jugador.getClub().equalsIgnoreCase(nombre)) {
				string = string + "(" + jugadores.indexOf(jugador) + ")" + jugador.getId() + ", " + jugador.getNombre() + ", " + jugador.getClub() + " |-| ";
			}
		}
		return string;
	}
	
}






