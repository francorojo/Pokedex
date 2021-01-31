package Repositorios;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Dominio.*;

public class RepositorioPokemonesMock implements RepositorioPokemones{
	private static final List<Pokemon> listaEnMemoriaPokemons = new ArrayList<>();
	private static final List<Evolucion> listaEnMemoriaEvoluciones = new ArrayList<>();
	private static final RepositorioPokemonesMock INSTANCE = new RepositorioPokemonesMock();
		 
	private RepositorioPokemonesMock(){}
		
		 public static RepositorioPokemonesMock getInstance() {
			 return INSTANCE;
		 }


		 public List<Pokemon> pokemones() {
		     return listaEnMemoriaPokemons;
		 }

		 public Optional<Pokemon> buscarPorNombre(String nombre) {
		     return listaEnMemoriaPokemons.stream().filter(pokemon -> ((Pokemon) pokemon).getNombre().equals(nombre)).findFirst();
		 }

		    public void agregar(Pokemon pokemon) {
		    	listaEnMemoriaPokemons.add(pokemon);
		    }
		    
		    public void remover(Pokemon pokemon) {
		    	listaEnMemoriaPokemons.remove(pokemon);
		    }
		    
		    public void actualizar(Pokemon pokemon) {
		    	//Se actualiza automaticamente al estar en memoria
		    }

			public void agregarEvolucion(Evolucion evolucion) {
				listaEnMemoriaEvoluciones.add(evolucion);
			}

}
