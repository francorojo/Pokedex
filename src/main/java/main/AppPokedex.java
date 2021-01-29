package main;

import java.util.Scanner;

public class AppPokedex {
    public static void main(String[] args) {
    
    String opcionElegida = "";
	
    System.out.println ("Bienvenido a Pokedex");
    System.out.println ("A continuacion podra ver las opciones que le ofrece la aplicacion");
    
    System.out.println ("1 - Enumerar todos los Pokémon para un usuario específico");
    System.out.println ("2 - Mostrar informacion de un pokemon especifico");
    System.out.println ("3 - Editar la informacion de un pokemon especifico"); 
    //(Nombre, agregar o quitar un Tipo, agregar un nueva evolución, y agregar aumentar nivel)
    System.out.println ("4 - Agregar un nuevo pokemon");
    System.out.println ("5 - Enumerar todos los Pokémon de la base de datos");

    Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
    opcionElegida = entradaEscaner.nextLine (); //Invocamos un método sobre un objeto Scanner

    switch(opcionElegida)
    {
       case "1" :
   	      System.out.println ("Opcion 1");
          break;
       
       case "2" :
          break;

       case "3" :
           break;
        
       case "4" :
           break;
           
       case "5" :
            break;
          
       default : 
    	    System.out.println ("La entrada recibida por teclado es: \"" + opcionElegida +"\"" + " y no se encuentra dentro de las opciones");
    }
    

    }
}
