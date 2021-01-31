package main;

import java.io.UnsupportedEncodingException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Dominio.Entrenador;
import Dominio.Evolucion;
import Dominio.Pokemon;

public class Bootstrap {
	
	static void run() throws UnsupportedEncodingException {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("db");
	EntityManager entityManager = factory.createEntityManager();
	entityManager.getTransaction().begin();
	
	//Creo Evoluciones
	Evolucion primeraEvolucionFuego = new Evolucion("PrimeraEvolucionFuego", 1);
	primeraEvolucionFuego.agregarTipo("Fuego");
	entityManager.persist(primeraEvolucionFuego);
	
	Evolucion segundaEvolucionFuego = new Evolucion("SegundaEvolucionFuego", 2);
	segundaEvolucionFuego.agregarTipo("Fuego");
	entityManager.persist(segundaEvolucionFuego);
	
	Evolucion terceraEvolucionFuego = new Evolucion("TerceraEvolucionFuego", 3);
	terceraEvolucionFuego.agregarTipo("Fuego");
	entityManager.persist(terceraEvolucionFuego);
	
	Evolucion primeraEvolucionAgua = new Evolucion("PrimeraEvolucionAgua", 1);
	primeraEvolucionAgua.agregarTipo("Agua");
	entityManager.persist(primeraEvolucionAgua);
	
	Evolucion segundaEvolucionAgua = new Evolucion("SegundaEvolucionAgua", 2);
	segundaEvolucionAgua.agregarTipo("Agua");
	entityManager.persist(segundaEvolucionAgua);
	
	//Creo Pokemones
	Pokemon pokemonFuego1 = new Pokemon("pokemonDeFuego1");
	pokemonFuego1.agregarHabilidad("Bola de fuego");
	pokemonFuego1.agregarTipo("Fuego");
	pokemonFuego1.aumentarNivel();
	pokemonFuego1.evolucionar(primeraEvolucionFuego);
	entityManager.persist(pokemonFuego1);
	
	Pokemon pokemonFuego2 = new Pokemon("pokemonDeFuego2");
	pokemonFuego2.agregarHabilidad("Super bola de fuego");
	pokemonFuego2.agregarTipo("Fuego");
	pokemonFuego2.aumentarNivel();
	pokemonFuego2.aumentarNivel();
	pokemonFuego2.evolucionar(segundaEvolucionFuego);
	entityManager.persist(pokemonFuego2);
	
	Pokemon pokemonFuego3 = new Pokemon("pokemonDeFuego3");
	pokemonFuego3.agregarHabilidad("Asteroide de fuego");
	pokemonFuego3.agregarTipo("Fuego");
	pokemonFuego3.aumentarNivel();
	pokemonFuego3.aumentarNivel();
	pokemonFuego3.aumentarNivel();
	pokemonFuego3.evolucionar(terceraEvolucionFuego);
	entityManager.persist(pokemonFuego3);
	
	Pokemon pokemonAgua1 = new Pokemon("pokemonDeAgua");
	pokemonAgua1.agregarHabilidad("Chorro de agua");
	pokemonAgua1.agregarTipo("Agua");
	pokemonAgua1.aumentarNivel();
	pokemonAgua1.evolucionar(primeraEvolucionAgua);
	entityManager.persist(pokemonAgua1);
	
	Pokemon pokemonAgua2 = new Pokemon("pokemonDeAgua2");
	pokemonAgua2.agregarHabilidad("Super chorro de agua");
	pokemonAgua2.agregarTipo("Agua");
	pokemonAgua2.aumentarNivel();
	pokemonAgua2.aumentarNivel();
	pokemonAgua2.evolucionar(segundaEvolucionAgua);
	entityManager.persist(pokemonAgua2);
	
	Pokemon pokemonAire1 = new Pokemon("pokemonDeAire1");
	pokemonAire1.agregarHabilidad("Tornado");
	pokemonAire1.agregarTipo("Aire");
	pokemonAire1.agregarTipo("Agua");
	pokemonAire1.aumentarNivel();
	pokemonAire1.evolucionar(primeraEvolucionAgua);
	entityManager.persist(pokemonAire1);
	
	Pokemon pokemonAire2 = new Pokemon("pokemonDeAire2");
	pokemonAire2.agregarHabilidad("Super chorro de agua");
	pokemonAire1.agregarTipo("Aire");
	pokemonAire2.agregarTipo("Agua");
	pokemonAire2.aumentarNivel();
	pokemonAire2.aumentarNivel();
	pokemonAire2.evolucionar(segundaEvolucionAgua);
	entityManager.persist(pokemonAire2);
	
	Pokemon pokemonAire3 = new Pokemon("pokemonDeAire3");
	pokemonAire3.agregarHabilidad("Asteroide de fuego");
	pokemonAire3.agregarTipo("Agua");
	pokemonAire3.agregarTipo("Fuego");
	pokemonAire3.aumentarNivel();
	pokemonAire3.aumentarNivel();
	pokemonAire3.aumentarNivel();
	pokemonAire3.evolucionar(terceraEvolucionFuego);
	entityManager.persist(pokemonAire3);	
	
	Pokemon pokemonTierra1 = new Pokemon("pokemonDeTierra1");
	pokemonTierra1.agregarHabilidad("Lluvia de barro");
	pokemonTierra1.agregarTipo("Tierra");
	pokemonTierra1.agregarTipo("Agua");
	pokemonTierra1.aumentarNivel();
	pokemonTierra1.evolucionar(primeraEvolucionAgua);
	entityManager.persist(pokemonTierra1);
	
	Pokemon pokemonTierra2 = new Pokemon("pokemonDeTierra2");
	pokemonTierra2.agregarHabilidad("Tsunami de barro");
	pokemonTierra2.agregarTipo("Tierra");
	pokemonTierra2.agregarTipo("Agua");
	pokemonTierra2.aumentarNivel();
	pokemonTierra2.aumentarNivel();
	pokemonTierra2.evolucionar(segundaEvolucionAgua);
	entityManager.persist(pokemonTierra2);
	
	//Creo Entrenadores
	Entrenador entrenadorFranco = new Entrenador("Franco");
	entrenadorFranco.agregarPokemon(pokemonFuego1);
	entrenadorFranco.agregarPokemon(pokemonFuego2);
	entrenadorFranco.agregarPokemon(pokemonAgua2);
	entrenadorFranco.agregarPokemon(pokemonTierra1);
	entrenadorFranco.agregarPokemon(pokemonAire3);
	entityManager.persist(entrenadorFranco);
	
	Entrenador entrenadorNahuel = new Entrenador("Nahuel");
	entrenadorNahuel.agregarPokemon(pokemonFuego3);
	entrenadorNahuel.agregarPokemon(pokemonAgua1);
	entrenadorNahuel.agregarPokemon(pokemonAire1);
	entrenadorNahuel.agregarPokemon(pokemonAire2);
	entrenadorNahuel.agregarPokemon(pokemonTierra2);
	entityManager.persist(entrenadorNahuel);
	
	entityManager.getTransaction().commit();
	entityManager.close();
	factory.close();
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		run();
	}
}
