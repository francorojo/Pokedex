package Repositorios;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.util.List;
import java.util.Optional;
import Dominio.*;

public class RepositorioPokemonesMySql implements WithGlobalEntityManager, RepositorioPokemones, TransactionalOps{
	 private static final RepositorioPokemonesMySql INSTANCE = new RepositorioPokemonesMySql();
	 
	 private RepositorioPokemonesMySql(){}
	 
	 public static RepositorioPokemonesMySql getInstance() {
		 return INSTANCE;
	 }

	    @SuppressWarnings("unchecked")
	    public List<Pokemon> pokemones() {
	        return entityManager()
	                .createQuery("from Pokemon")
	                .getResultList();
	    }

	    public Optional<Pokemon> buscarPorNombre(String nombre) {
	        return entityManager().createQuery("from Pokemon").getResultList().stream().filter(pokemon -> ((Pokemon) pokemon).getNombre().equals(nombre)).findFirst();
	    }

	    public void agregar(Pokemon pokemon) {
	        entityManager().persist(pokemon);
	    }

	    public void actualizar(Pokemon pokemon) {
	        entityManager().merge(pokemon);
	    }
	    
	    public void remover(Pokemon pokemon) {
	    	withTransaction(() -> {
	    		entityManager().remove(pokemon);
	    	});
	    }

		public void agregarEvolucion(Evolucion evolucion) {
			entityManager().persist(evolucion);
		}
	 
}

