package Repositorios;

import java.util.List;
import java.util.Optional;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import Dominio.Entrenador;
import Dominio.Pokemon;

public class RepositorioEntrenadores implements WithGlobalEntityManager {
	 private static final RepositorioEntrenadores INSTANCE = new RepositorioEntrenadores();
	 
	 private RepositorioEntrenadores(){}
	 
	 public static RepositorioEntrenadores getInstance() {
		 return INSTANCE;
	 }

	    @SuppressWarnings("unchecked")
	    public List<Entrenador> usuarios() {
	        return entityManager()
	                .createQuery("from Entrenador")
	                .getResultList();
	    }

	    public Optional<Entrenador> buscarPorNombre(String nombre) {
	        return entityManager().createQuery("from Entrenador").getResultList().stream().filter(entrenador -> ((Entrenador) entrenador).getNombre().equals(nombre)).findFirst();
	    }

	    public void agregar(Entrenador entrenador) {
	        entityManager().persist(entrenador);
	    }
	    
	    public void agregarPokemon(Entrenador entrenador, Pokemon pokemon) {
	    	entrenador.agregarPokemon(pokemon);
	        entityManager().merge(entrenador);
	    }
}
