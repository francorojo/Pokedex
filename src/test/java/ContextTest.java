import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import Dominio.Entrenador;
import Dominio.Evolucion;
import Dominio.Pokemon;

public class ContextTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

	@Test
	public void contextUp() {
		assertNotNull(entityManager());

	}

	@Test
	public void contextUpWithTransaction() throws Exception {
		withTransaction(() -> {});
	}

	@Test
	public void pruebaPersistirUnJugador(){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("db");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		//Creo una Evolucion
		Evolucion evolucionPrincipiante = new Evolucion("PrimeraEvolucion", 1);
		evolucionPrincipiante.agregartipo("Fuego");
		entityManager.persist(evolucionPrincipiante);
		
		//Creo un Pokemon
		Pokemon pikachu = new Pokemon("Pikachu");
		pikachu.agregarhabilidad("Bola de fuego");
		pikachu.agregartipo("Fuego");
		pikachu.aumentarNivel();
		pikachu.evolucionar(evolucionPrincipiante);
		entityManager.persist(pikachu);
		
		//Creo un Entrenador
		Entrenador entrenadorFranco = new Entrenador("Franco");
		entrenadorFranco.agregarPokemon(pikachu);
		entityManager.persist(entrenadorFranco);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
	
	@Test
	public void pruebaActualizarPikachu(){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("db");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Pokemon pikachu = entityManager.find(Pokemon.class, 1);
		pikachu.aumentarNivel();
		entityManager.merge(pikachu);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}

}
