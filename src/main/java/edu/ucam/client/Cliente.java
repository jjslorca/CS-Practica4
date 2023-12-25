package edu.ucam.client;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.util.ArrayList;

import edu.ucam.GesClub;
import edu.ucam.Club;
import edu.ucam.Empleado;
import edu.ucam.Jugador;
import edu.ucam.server.GesClubImpl;

@SuppressWarnings("unused")
public class Cliente {
   
	public static void main(String[] args) {
		try {
			//Instancia de objeto distribuido con la url del proxy para localizar el objeto
			GesClub gesClub = (GesClub)Naming.lookup("rmi://localhost:1099/GesClubService");
		
		
			Scanner teclado = new Scanner(System.in);
		    boolean salir = false;
		    
		    Club club = new Club();
		    Jugador jugador = new Jugador();
		    
		    ArrayList<Club> clubes = new ArrayList<Club>();
	    	ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		    
		    String id = "";
		    String nombre = "";
		    String clubJugador = "";
		    
		    System.out.println("APLICACIÓN GESCLUB 2.0 (RMI)");
			System.out.println("Nombre de usuario: ");
	    	String login = teclado.next();
	    	System.out.println("Contraseña: ");
	    	String pass = teclado.next();
		
		    while (gesClub.loginUsuario(login, pass) == false) {
		    	System.out.println("Nombre de usuario y/o contraseña incorrectos");
				System.out.println("Nombre de usuario: ");
		    	login = teclado.next();
		    	System.out.println("Contraseña: ");
		    	pass = teclado.next();
		    }
		    System.out.println("Login Ok! >> Usuario: " + login);
		    
		    while (!salir) {
		    	
		       	verMenu();// Menu consola para hacer uso del objeto remoto
		        	
		        System.out.println("Escribe una opción: ");
		        int opcion = teclado.nextInt();
		        teclado.nextLine();
		 
		        switch (opcion) {
		           	case 1: //ADDCLUB
		           		System.out.println("Id Club: ");
		           		id = teclado.next();
		           		System.out.println("Nombre: ");
	               		nombre = teclado.next();
	               		teclado.nextLine();
	               		
		            	if (gesClub.buscaClub(nombre) == -1) {
		            		club.setId(id);
		            		club.setNombre(nombre);
		            		gesClub.addClub(club);
		            		System.out.println("\nClub añadido con exito");
		            		
		            	}else {
		            		System.out.println("\nClub duplicado");
		            	}
		            	System.out.println("\nPresione ENTER para volver al Menú...");
		        		teclado.nextLine();	
		            	break;
		            case 2: //UPDATECLUB 
						System.out.println("Introduzca nombre Club a modificar: ");
						nombre = teclado.next();
						int index = gesClub.buscaClub(nombre);
						if (index != -1) {
							System.out.println("Nombre a modificar: ");
							nombre = teclado.next();
							System.out.println("Id a modificar: ");
							id = teclado.next();
							teclado.nextLine();
							club.setId(id);
		            		club.setNombre(nombre);
							gesClub.updateClub(index, club);
							System.out.println("\nClub modificado con exito");
						}else{
							System.out.println("Club no encontrado");
						}	
						System.out.println("\nPresione ENTER para volver al Menú...");
		        		teclado.nextLine();
						break;
		            case 3: //GETCLUB
		            	System.out.println("Introduzca nombre Club a buscar: ");
						nombre = teclado.next();
						teclado.nextLine();
						
						if (gesClub.buscaClub(nombre) != -1) {
							System.out.println("Club localizado");
							System.out.println("ID        Nombre    ");
							System.out.println("====================");
							System.out.println(gesClub.getClub(nombre));
						}else {	
							System.out.println("Club no encontrado");
						}
						System.out.println("\nPresione ENTER para volver al Menú...");
		        		teclado.nextLine();
						break;
		                
		            case 4: //REMOVECLUB
		            	System.out.println("Introduzca nombre Club a eliminar: ");
						nombre = teclado.next();
						teclado.nextLine();
						if (gesClub.buscaClub(nombre) != -1) {
							System.out.println("Club localizado");
							System.out.println(gesClub.getClub(nombre));
							System.out.println("Desea eliminar el registro (S/N): ");
							String aux = teclado.nextLine();
							if (aux.equalsIgnoreCase("S") == true){
								gesClub.deleteClub(nombre);
								System.out.println("Registro Club eliminado");
							}else {
								System.out.println("Operación cancelada");}
		        		}else {
		        			System.out.println("Club no encontrado");
		        		}
						System.out.println("\nPresione ENTER para volver al Menú...");
		        		teclado.nextLine();
						break;
		            case 5: //LISTCLUBES
		            	if(gesClub.countClubes() != 0) {
		            		System.out.println("Listado de datos de clubes registrados:");
		            		System.out.println(gesClub.listClubes());
		            	}else {
		            		System.out.println("No existen clubes registrados");
		            	}
		            	System.out.println("\nPresione ENTER para volver al Menú...");
		        		teclado.nextLine();
		            	break;
		            case 6: //COUNTCLUBES
		            	System.out.println("Número total de clubes registrados: ");
		            	System.out.println(gesClub.countClubes());
		            	System.out.println("\nPresione ENTER para volver al Menú...");
		        		teclado.nextLine();
		            	break;
		                               
		            case 7: //ADDJUGADOR
		            	System.out.println("Id Jugador: ");
		           		id = teclado.next();
		           		teclado.nextLine();
		           		System.out.println("Nombre: ");
	               		nombre = teclado.next();
	               		teclado.nextLine();
	               		System.out.println("Club: ");
	               		clubJugador = teclado.next();
	               		teclado.nextLine();
	               			            	
		            	if ((gesClub.buscaJugador(id) == -1) && (gesClub.buscaClub(clubJugador) != -1)) {
		            		jugador.setId(id);
		            		jugador.setNombre(nombre);
		            		jugador.setClub(clubJugador);
		            		gesClub.addJugador(jugador);
		            		System.out.println("\nJugador añadido con exito");
		            		
		            	}else {
		            		System.out.println("\nJugador duplicado o el club no existe");
		            	}
		            	System.out.println("\nPresione ENTER para volver al Menú...");
		        		teclado.nextLine();	
		            	break;	
			                
			        case 8: //GETJUGADOR
			        	System.out.println("Introduzca ID del jugador a buscar: ");
						id = teclado.next();
						teclado.nextLine();
						if (gesClub.buscaJugador(id) != -1) {
							System.out.println("Jugador localizado");
							System.out.println("ID        Nombre     Club      ");
							System.out.println("===============================");

							System.out.println(gesClub.getJugador(id));
						}else {	
							System.out.println("Jugador no encontrado");
						}
						System.out.println("\nPresione ENTER para volver al Menú...");
		        		teclado.nextLine();
						break;		                                       
			        case 9: //REMOVEJUGADOR
			        	System.out.println("Introduzca ID Jugador a eliminar: ");
						id = teclado.next();
						teclado.nextLine();
						if (gesClub.buscaJugador(id) != -1) {
							System.out.println("Jugador localizado");
							System.out.println(gesClub.getJugador(id));
							System.out.println("Desea eliminar el registro (S/N): ");
							String aux = teclado.next();
							teclado.nextLine();
							if (aux.equalsIgnoreCase("S") == true){
								gesClub.deleteJugador(id);
								System.out.println("Registro jugador eliminado");
							}else {
								System.out.println("Operación cancelada");}
						}else {
		        			System.out.println("Jugador no encontrado");
		        		}
						System.out.println("\nPresione ENTER para volver al Menú...");
		        		teclado.nextLine();
						break;		
			                
			        case 10: //LISTJUGADORES
			        	if(gesClub.countJugadores() != 0) {
		            		System.out.println("Listado de datos de jugadores registrados:");
		            		System.out.println(gesClub.listJugadores());
		            	}else {
		            		System.out.println("No existen jugadores registrados");
		            	}
			        	System.out.println("\nPresione ENTER para volver al Menú...");
		        		teclado.nextLine();
		            	break;
			        case 11: //ADDJUGADOR2CLUB
			        	System.out.println("Introduzca ID del Jugador: ");
						id = teclado.next();
						teclado.nextLine();
						if (gesClub.buscaJugador(id) == -1) {
							System.out.println("Introduzca nombre del Jugador: ");
							nombre = teclado.next();
							teclado.nextLine();
							System.out.println("Introduzca nombre del Club: ");
							clubJugador = teclado.next();
							teclado.nextLine();
							if (gesClub.buscaClub(clubJugador) != -1) {
								jugador.setId(id);
			            		jugador.setNombre(nombre);
			            		jugador.setClub(clubJugador);
			            		gesClub.addJugador(jugador);
			            		System.out.println("\nJugador añadido con exito");
							}else {	
								System.out.println("El club no está registrado");}
						}else {	
							System.out.println("El jugador ya está registrado");
						}
						System.out.println("\nPresione ENTER para volver al Menú...");
		        		teclado.nextLine();
						break;
			        case 12: //REMOVEJUGFROMCLUB
			        	System.out.println("Introduzca ID del Jugador a eliminar: ");
						id = teclado.next();
						System.out.println("Introduzca nombre del Club al que pertenece: ");
						clubJugador = teclado.next();
						if ((gesClub.buscaJugador(id) != -1) && (gesClub.buscaClub(clubJugador) != -1)) {
							System.out.println("Desea eliminar el registro (S/N): ");
							String aux = teclado.next();
							if (aux.equalsIgnoreCase("S") == true){
								gesClub.deleteJugador(id);
								System.out.println("Registro jugador eliminado");
							}else {
								System.out.println("Operación cancelada");
							}
						}else {
							System.out.println("Jugador o Club no encontrado");
						}
						System.out.println("\nPresione ENTER para volver al Menú...");
		        		teclado.nextLine();
						break;	
			                         
			        case 13: //LISTJUGFROMCLUB
			        	System.out.println("Introduzca nombre del Club: ");
		            	nombre = teclado.next();
		            	teclado.nextLine();
		            	if ((gesClub.buscaClub(nombre) != -1) && (gesClub.countJugadoresClub(nombre) != 0)) {
		            		System.out.println("Número total de jugadores registrados para este Club: ");
			            	System.out.println(gesClub.countJugadoresClub(nombre));
		            		
		            		System.out.println("Listado de datos de jugadores registrados:");
		            		System.out.println(gesClub.listJugadoresClub(nombre));
		            	}else {
		            		System.out.println("Club no existe o bien no tiene jugadores registrados");
		            	}
		            	System.out.println("\nPresione ENTER para volver al Menú...");
		        		teclado.nextLine();
		            	break;     
			        case 14: //Salir
			        	salir = true;
			        	break;
		        }
		    }
		    teclado.close();
		    System.out.print("\033[H\033[2J");
		    System.out.println("Programa Finalizado");
		    System.exit(0);
		}catch (Exception e) {e.printStackTrace();}
   }		
		
	

	private static void verMenu () {
	
		System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.println("\n");
		System.out.println("Menú opciones GesClub (RMI)");
		System.out.println("======================================");
	 	System.out.println(" (1) Añadir nuevo Club");				//ADDCLUB
	    System.out.println(" (2) Modificar Club");					//UPDATECLUB
	    System.out.println(" (3) Buscar Club");						//GETCLUB
	    System.out.println(" (4) Eliminar Club");					//REMOVECLUB
	    System.out.println(" (5) Listado de clubes");				//LISTCLUBES
	    System.out.println(" (6) Total de clubes registrados");		//COUNTCLUBES
	    System.out.println(" (7) Añadir Jugador");					//ADDJUGADOR
	    System.out.println(" (8) Solicitud datos de un jugador");	//GETJUGADOR
	    System.out.println(" (9) Eliminar un jugador");				//REMOVEJUGADOR
	    System.out.println("(10) Listado de jugadores existentes");	//LISTJUGADORES
	    System.out.println("(11) Añadir jugador a un club");		//ADDJUGADOR2CLUB
	    System.out.println("(12) Eliminar jugador de un club");		//REMOVEJUGFROMCLUB
	    System.out.println("(13) Listado jugadores de un club");	//LISTJUGFROMCLUB
	    System.out.println("(14) Salir");							//EXIT
	}		
		
}		
	