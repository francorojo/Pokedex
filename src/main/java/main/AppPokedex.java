package main;

import java.util.Scanner;

import Controllers.ControllerAplicacion;
import Repositorios.RepositorioPokemonesMySql;

public class AppPokedex {
    
	public static void main(String[] args) {
    ControllerAplicacion controlador = new ControllerAplicacion(RepositorioPokemonesMySql.getInstance());
    String eleccionDeVerElMenu = "1";

    inicio();
    
    while(eleccionDeVerElMenu.equals("1")){
	    String opcionElegida = "";
	    Scanner entradaEscaner = new Scanner (System.in);
	    
	    menu();
	    opcionElegida = entradaEscaner.nextLine ();
	
	    switch(opcionElegida){
	       case "1" :
	    	  String nombreEntrenador = "";
	   		
	   	      System.out.println ("Ingrese el nombre del entrenador");
	   	      nombreEntrenador = entradaEscaner.nextLine ();        
	   	      controlador.mostrarPokemonesEntrenador(nombreEntrenador);
	       break;
	       
	       case "2" :
	    	  String nombrePokemon = "";
	    		
	    	  System.out.println ("Ingrese el nombre del pokemon deseado");
	    	  nombrePokemon = entradaEscaner.nextLine ();
	    	  controlador.mostrarPokemon(nombrePokemon);
	       break;
	
	       case "3" :
	    	   String nombrePokemonAEditar = "";
	    	   String respuesta = "";
	    	   String atributo = "";
	
	    	   System.out.println ("Ingrese el nombre del pokemon deseado");
	    	   nombrePokemonAEditar = entradaEscaner.nextLine ();
	    	   controlador.mostrarPokemon(nombrePokemonAEditar);
	    	   menuEditar();
	    	   respuesta = entradaEscaner.nextLine ();
	    	   
	    	   switch(respuesta){
	    	       case "1" :
	    	    	   System.out.println ("Ingrese el nuevo nombre");
	    	    	   atributo = entradaEscaner.nextLine ();
	    	    	   controlador.modificarNombre(atributo, nombrePokemonAEditar);
	    	       break;
	    	    	
	    	       case "2" :
	    	    	   System.out.println ("Ingrese el nuevo tipo del pokemon");
	    	    	   atributo = entradaEscaner.nextLine ();
	    	    	   controlador.agregarTipo(atributo, nombrePokemonAEditar);
	       	       break;
	       	    	
	    	       case "3" :
	    	    	   System.out.println ("Ingrese el tipo a eliminar");
	    	    	   atributo = entradaEscaner.nextLine ();
	    	    	   controlador.eliminarTipo(atributo, nombrePokemonAEditar);
	       	       break;
	       	    	
	    	       case "4" :
	    	    	   String nivelRequerido = "";
	    	    	   String tipo = "";
	    	    	   
	    	    	   System.out.println ("Ingrese nombre de la nueva evolucion");
	    	    	   atributo = entradaEscaner.nextLine();
	    	    	   System.out.println ("Ingrese nivel requerido");
	    	    	   nivelRequerido = entradaEscaner.nextLine();
	    	    	   System.out.println ("Ingrese el tipo de la evolucion");
	    	    	   tipo = entradaEscaner.nextLine();
	    	    	   
	    	    	   controlador.agregarEvolucion(atributo, Integer.parseInt(nivelRequerido), tipo, nombrePokemonAEditar);   
	       	       break;
	       	    	
	    	       case "5" :
	    	    	   System.out.println ("Ingrese nueva habilidad");
	    	    	   atributo = entradaEscaner.nextLine();
	    	    	   controlador.agregarHabilidad(atributo, nombrePokemonAEditar);
	    	       break;
	    	    	
	    	       case "6" :
	    	    	   controlador.subirNivel(nombrePokemonAEditar);
	    	    	   System.out.println ("Nivel actualizado");
	    	       break;
	       	    	
	    	       default:
	    	    	   entradaInvalida(opcionElegida);
	    	   }
	       break;
	        
	       case "4" :
	    	   String nombreNuevoPokemon = "";
	    	   String tipoInicial = "";
	    	   String habilidadInicial = "";
	    	   
	    	   System.out.println ("Ingrese el nombre del pokemon");
	    	   nombreNuevoPokemon = entradaEscaner.nextLine();
	    	   System.out.println ("Ingrese el tipo inicial del pokemon");
	    	   tipoInicial = entradaEscaner.nextLine();
	    	   System.out.println ("Ingrese la habilidad inicial del pokemon");
	    	   habilidadInicial = entradaEscaner.nextLine();
	    	   controlador.nuevoPokemon(nombreNuevoPokemon, tipoInicial, habilidadInicial);
	       break;
	           
	       case "5" :
	    	   controlador.mostrarPokemonesDeLaBase();
	       break;
	          
	       default : 
	    	    entradaInvalida(opcionElegida);
      }
	    
    menuFinal();
    eleccionDeVerElMenu = entradaEscaner.nextLine();
    limpiarConsola();   
    }
    
    System.out.println ("Gracias por usar Pokedex");    
    }
	
	static void inicio() {
		System.out.println ("---------------------------------------------------------------------");
	    System.out.println ("| Bienvenido a Pokedex                                              |");
	    System.out.println ("| A continuacion podra ver las opciones que le ofrece la aplicacion |");
	    System.out.println ("---------------------------------------------------------------------");
	}
	
	static void menu() {
		System.out.println ("---------------------------------------------------------------------");
	    System.out.println ("| 1 - Enumerar todos los Pokémon para un entrenador específico      |");
	    System.out.println ("| 2 - Mostrar informacion completa de un pokemon especifico         |");
	    System.out.println ("| 3 - Editar la informacion de un pokemon especifico                |"); 
	    System.out.println ("| 4 - Agregar un nuevo pokemon                                      |");
	    System.out.println ("| 5 - Enumerar todos los Pokémon de la base de datos                |");
	    System.out.println ("---------------------------------------------------------------------"); 
	}
	
	static void menuEditar() {
		System.out.println ("Ingrese que atributo desea modificar");
 	   System.out.println ("1 - Cambiar nombre");
 	   System.out.println ("2 - Agregar tipo");
 	   System.out.println ("3 - Quitar tipo"); 
 	   System.out.println ("4 - Agregar Evolucion"); 
 	   System.out.println ("5 - Agregar Habilidad"); 
 	   System.out.println ("6 - Aumentar nivel");
	}
	
	static void menuFinal() {
		System.out.println ("----------------------------");
	    System.out.println ("| 1 - Volver a ver el menu |");
	    System.out.println ("| 2 - Salir de Pokedex     |");
	    System.out.println ("----------------------------");
	}
	
	static void entradaInvalida(String opcionElegida) {
	    System.out.println ("La entrada recibida por teclado es: \"" + opcionElegida +"\"" + " y no se encuentra dentro de las opciones");		
	}
	
	static void limpiarConsola() {
		System.out.print("\n\n\n\n\n\n\n\n");
	}
	
}
