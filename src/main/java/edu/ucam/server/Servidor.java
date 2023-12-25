package edu.ucam.server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import edu.ucam.GesClub;

@SuppressWarnings("unused")
public class Servidor {
	
	public void ejecutar() {
		try {
			//Lanza el registro (programa residente)
			LocateRegistry.createRegistry(1099);
			
			//Crea el objeto a distribuir.
			GesClubImpl gClub = new GesClubImpl();
			
			
			//Registrar el objeto distribuido en el registro con el nombre GesClubService
			Naming.rebind("rmi://localhost:1099/GesClubService", gClub);
						
			System.out.println("Servidor activo...");
		} catch (Exception e) {
			System.out.println("Error ! >> " + e);
		}
	}

	public static void main(String args[]) {
		(new Servidor()).ejecutar();;
	}
}
