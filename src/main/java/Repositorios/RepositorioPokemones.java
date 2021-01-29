package Repositorios;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;
import java.util.Optional;
import Dominio.*;

public class RepositorioPokemones implements WithGlobalEntityManager {
	 private static final RepositorioPokemones INSTANCE = new RepositorioPokemones();
	 
	 private RepositorioPokemones(){}
	 
	 public static RepositorioPokemones getInstance() {
		 return INSTANCE;
	 }

	    @SuppressWarnings("unchecked")
	    public List<Pokemon> usuarios() {
	        return entityManager()
	                .createQuery("from Pokemon")
	                .getResultList();
	    }

	    public Optional<Pokemon> buscarPorNombre(String username) {
	        return entityManager().createQuery("from Pokemon").getResultList().stream().filter(pokemon -> ((Pokemon) pokemon).getNombre().equals(username)).findFirst();
	    }

	    public void agregar(Pokemon usuario) {
	        entityManager().persist(usuario);
	    }

	    public void actualizar(Pokemon usuario) {
	        entityManager().merge(usuario);
	    }
	 
}

