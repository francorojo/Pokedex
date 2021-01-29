package main;

import java.io.UnsupportedEncodingException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Dominio.Evolucion;
import Dominio.Pokemon;

public class Bootstrap {
	
	static void run() throws UnsupportedEncodingException {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("db");
	EntityManager entityManager = factory.createEntityManager();
	entityManager.getTransaction().begin();
	
	//Creo Evoluciones
	Evolucion primeraEvolucionFuego = new Evolucion("PrimeraEvolucionFuego", 1);
	primeraEvolucionFuego.agregartipo("Fuego");
	entityManager.persist(primeraEvolucionFuego);
	
	Evolucion segundaEvolucionFuego = new Evolucion("SegundaEvolucionFuego", 2);
	segundaEvolucionFuego.agregartipo("Fuego");
	entityManager.persist(segundaEvolucionFuego);
	
	Evolucion terceraEvolucionFuego = new Evolucion("TerceraEvolucionFuego", 3);
	terceraEvolucionFuego.agregartipo("Fuego");
	entityManager.persist(terceraEvolucionFuego);
	
	Evolucion primeraEvolucionAgua = new Evolucion("PrimeraEvolucionAgua", 1);
	primeraEvolucionAgua.agregartipo("Agua");
	entityManager.persist(primeraEvolucionAgua);
	
	Evolucion segundaEvolucionAgua = new Evolucion("SegundaEvolucionAgua", 2);
	segundaEvolucionAgua.agregartipo("Agua");
	entityManager.persist(segundaEvolucionAgua);
	
	//Creo Pokemones
	Pokemon pokemon1 = new Pokemon("pokemonDeFuego1");
	pokemon1.agregarhabilidad("Bola de fuego");
	pokemon1.agregartipo("Fuego");
	pokemon1.aumentarNivel();
	pokemon1.evolucionar(primeraEvolucionFuego);
	entityManager.persist(pokemon1);
	
	Pokemon pokemon2 = new Pokemon("pokemonDeFuego2");
	pokemon2.agregarhabilidad("Super bola de fuego");
	pokemon2.agregartipo("Fuego");
	pokemon2.aumentarNivel();
	pokemon2.aumentarNivel();
	pokemon2.evolucionar(segundaEvolucionFuego);
	entityManager.persist(pokemon2);
	
	Pokemon pokemon3 = new Pokemon("pokemonDeFuego3");
	pokemon3.agregarhabilidad("Asteroide de fuego");
	pokemon3.agregartipo("Fuego");
	pokemon3.aumentarNivel();
	pokemon3.aumentarNivel();
	pokemon3.aumentarNivel();
	pokemon3.evolucionar(terceraEvolucionFuego);
	entityManager.persist(pokemon3);
	
	entityManager.getTransaction().commit();
	entityManager.close();
	factory.close();
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		run();
	}
}
