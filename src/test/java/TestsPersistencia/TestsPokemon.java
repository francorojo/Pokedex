package TestsPersistencia;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import Controllers.ControllerAplicacion;
import Dominio.Entrenador;
import Dominio.Evolucion;
import Dominio.Pokemon;
import Repositorios.RepositorioPokemonesMock;
import Repositorios.RepositorioPokemonesMySql;

public class TestsPokemon {
	
	@Test
	public void seEnumeranTodosLosPokemons(){
		
		//Como el test requiere verificar cantidad utilizo el RepositorioMock para no perjudicar a la base original.
        //Realizando una validacion sobre el funcionamiento de la aplicacion.
        //La informacion especifica sobre 1 pokemon, intercambiada con la base se valida luego con la base original.
				
		ControllerAplicacion controlador = new ControllerAplicacion(RepositorioPokemonesMock.getInstance());
		List<String> nombresDePrueba = new ArrayList<String>();
		nombresDePrueba.add("pokemonDePrueba1");
		nombresDePrueba.add("pokemonDePrueba2");
		nombresDePrueba.add("pokemonDePrueba3");
		nombresDePrueba.add("pokemonDePrueba4");
		nombresDePrueba.add("pokemonDePrueba5");	
		
		nombresDePrueba.forEach(nombre -> controlador.nuevoPokemon(nombre, "Fuego", "Bola de Fuego"));
		assertEquals(5, RepositorioPokemonesMock.getInstance().pokemones().size());
	}
	
	@Test
	public void seInsertaYSeRecuperaTodaLaInformacionDeUnPokemon(){
		Pokemon pokemon;
		
		ControllerAplicacion controlador = new ControllerAplicacion(RepositorioPokemonesMySql.getInstance());
 	    controlador.nuevoPokemon("PokemonDePrueba", "Fuego", "Bola de fuego");
 	    controlador.subirNivel("PokemonDePrueba");
 	    
 	    pokemon = RepositorioPokemonesMySql.getInstance().buscarPorNombre("PokemonDePrueba").get();
 	    assertEquals("PokemonDePrueba", pokemon.getNombre());
 	    assertTrue(pokemon.getTipos().contains("Fuego"));
 	    assertTrue(pokemon.getHabilidades().contains("Bola de fuego"));
 	    assertTrue(pokemon.getNivelActual().equals(1));
	    
 	    controlador.agregarEvolucion("EvolucionDePrueba", 1, "Fuego", "PokemonDePrueba");
 	    pokemon = RepositorioPokemonesMySql.getInstance().buscarPorNombre("PokemonDePrueba").get();
	    assertTrue(pokemon.getNivelActual().equals(0));
	    
	    RepositorioPokemonesMySql.getInstance().remover(pokemon);
	}
	
	@Test
	public void seActualizaUnPokemon(){
		//Ya que en el test anterior se verificaron cambios a un pokemon, voy a validar los caminos faltantes
		
		Pokemon pokemon;
		Pokemon pokemonNuevo;
		Optional<Pokemon> pokemonViejo;
		
		ControllerAplicacion controlador = new ControllerAplicacion(RepositorioPokemonesMySql.getInstance());
 	    controlador.nuevoPokemon("PokemonDePrueba", "Fuego", "Bola de fuego");
 	    controlador.subirNivel("PokemonDePrueba");
 	    
 	    //Quitar un tipo
 	    pokemon = RepositorioPokemonesMySql.getInstance().buscarPorNombre("PokemonDePrueba").get();
 	    assertTrue(pokemon.getTipos().contains("Fuego"));
	    controlador.eliminarTipo("Fuego", "PokemonDePrueba");
	    
	    pokemon = RepositorioPokemonesMySql.getInstance().buscarPorNombre("PokemonDePrueba").get();
	    assertFalse(pokemon.getTipos().contains("Fuego"));
	    
	    //CambiarNombre
	    controlador.modificarNombre("NuevoNombreDePrueba", "PokemonDePrueba");
	    pokemonNuevo = RepositorioPokemonesMySql.getInstance().buscarPorNombre("NuevoNombreDePrueba").get();
	    assertEquals("NuevoNombreDePrueba", pokemonNuevo.getNombre());
	    pokemonViejo = RepositorioPokemonesMySql.getInstance().buscarPorNombre("PokemonDePrueba");
	    assertFalse(pokemonViejo.isPresent());
	    
	    RepositorioPokemonesMySql.getInstance().remover(pokemonNuevo);
	}
}
