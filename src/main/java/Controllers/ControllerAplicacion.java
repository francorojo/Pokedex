package Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import Dominio.Entrenador;
import Dominio.Evolucion;
import Dominio.Pokemon;
import Excepciones.EvolucionDeOtroTipo;
import Excepciones.NivelRequeridoNoAlcanzado;
import Repositorios.RepositorioEntrenadores;
import Repositorios.RepositorioPokemones;
import Repositorios.RepositorioPokemonesMySql;

public class ControllerAplicacion implements WithGlobalEntityManager, TransactionalOps{
	
	RepositorioPokemones repositorio;
	
	public ControllerAplicacion(RepositorioPokemones repositorio){
		this.repositorio = repositorio;
	}
	
	public void mostrarPokemonesDeLaBase() {
		if(!repositorio.pokemones().isEmpty())
			mostrarPokemones(repositorio.pokemones());
		else
			System.out.print("No hay pokemones en la base");
	}

	public void mostrarPokemon(String nombrePokemon) {
		Optional<Pokemon> pokemon = repositorio.buscarPorNombre(nombrePokemon);
		if(pokemon.isPresent())
			informacionCompletaPokemon(pokemon.get());
		else
			System.out.print("No se ha encontrado al pokemon " + nombrePokemon + " en la base de datos");
        
	}
	
	public void informacionCompletaPokemon(Pokemon pokemon) {
		List<String> tipos = new ArrayList<String>(pokemon.getTipos());
		List<String> habilidades = new ArrayList<String>(pokemon.getHabilidades());
		List<String> evoluciones = new ArrayList<String>(pokemon.getEvoluciones().stream().map(evolucion -> evolucion.getNombre()).collect(Collectors.toList()));
		
		limpiarConsola(); 
		System.out.print("---------------------------\n");
		System.out.print("| Informacion del pokemon |\n");
		System.out.print("---------------------------\n");
		System.out.print(" * Nombre: " + pokemon.getNombre() + "\n"); 
		System.out.print(" * Nivel Actual: " + pokemon.getNivelActual().toString() + "\n"); 
		System.out.print(" * Tipos del pokemon \n");
		tipos.forEach(tipo -> System.out.print("- " + tipo + " ")); 
		System.out.print("\n"); 
		System.out.print(" * Habilidades del pokemon \n");
		habilidades.forEach(habilidad -> System.out.print("- " + habilidad + " ")); 
		System.out.print("\n");
		System.out.print(" * Evoluciones del pokemon \n");
		evoluciones.forEach(evolucion -> System.out.print("- " + evolucion + " "));
		System.out.print("\n");
	}

	public void mostrarPokemonesEntrenador(String nombreEntrenador) {
		Optional<Entrenador> entrenador = RepositorioEntrenadores.getInstance().buscarPorNombre(nombreEntrenador);
		
		limpiarConsola();
		if(entrenador.isPresent()){
			System.out.print("Pokemones de " + nombreEntrenador+ "\n");
			mostrarPokemones(new ArrayList<>(entrenador.get().getPokemones()));
			System.out.print("\n");
		}else
			System.out.print("No se encuentra el entrenador " + nombreEntrenador+ "\n");
	}
	
	public void modificarNombre(String atributo, String nombrePokemonAEditar) {
		withTransaction(() -> {
		Pokemon pokemonAnterior = repositorio.buscarPorNombre(nombrePokemonAEditar).get();
		Pokemon pokemonNuevo = new Pokemon(atributo);
	    pokemonNuevo.setNivelActual(pokemonAnterior.getNivelActual());
	    pokemonNuevo.setTipos(pokemonAnterior.getTipos());
		pokemonNuevo.setHabilidades(pokemonAnterior.getHabilidades());
		pokemonNuevo.setEvoluciones(pokemonAnterior.getEvoluciones());
		repositorio.agregar(pokemonNuevo);
		repositorio.remover(pokemonAnterior);
		});
		limpiarConsola();
		mensajeSeHaModificadoConExito(nombrePokemonAEditar);
	}

	public void agregarTipo(String atributo, String nombrePokemonAEditar) {
		withTransaction(() -> {
		Optional<Pokemon> pokemon = repositorio.buscarPorNombre(nombrePokemonAEditar);
		pokemon.get().agregarTipo(atributo);
		repositorio.actualizar(pokemon.get());
		});
		limpiarConsola();
		mensajeSeHaModificadoConExito(nombrePokemonAEditar);
	}

	public void eliminarTipo(String atributo, String nombrePokemonAEditar) {
		withTransaction(() -> {
		Optional<Pokemon> pokemon = repositorio.buscarPorNombre(nombrePokemonAEditar);
		pokemon.get().eliminarTipo(atributo);
		repositorio.actualizar(pokemon.get());
		});
		limpiarConsola();
		mensajeSeHaModificadoConExito(nombrePokemonAEditar);	
	}

	public void subirNivel(String nombrePokemonAEditar) {
		withTransaction(() -> {
		Optional<Pokemon> pokemon = repositorio.buscarPorNombre(nombrePokemonAEditar);
		pokemon.get().aumentarNivel();
		repositorio.actualizar(pokemon.get());
		});
		limpiarConsola();
		mensajeSeHaModificadoConExito(nombrePokemonAEditar);
	}

	public void nuevoPokemon(String nombreNuevoPokemon, String tipoInicial, String habilidadInicial) {
		withTransaction(() -> {
		Pokemon pokemon = new Pokemon(nombreNuevoPokemon);
		pokemon.agregarHabilidad(habilidadInicial);
		pokemon.agregarTipo(tipoInicial);
		repositorio.agregar(pokemon);
		});
		limpiarConsola();
		System.out.print("Se ha agregado con exito al pokemon: " + nombreNuevoPokemon + "\n");
	}

	public void agregarEvolucion(String atributo, int nivel, String tipo, String nombrePokemonAEditar) {
		withTransaction(() -> {
		Optional<Pokemon> pokemon = repositorio.buscarPorNombre(nombrePokemonAEditar);
		Evolucion evolucion = new Evolucion(atributo, nivel);
		evolucion.agregarTipo(tipo);
		
		limpiarConsola();
		try{
			pokemon.get().evolucionar(evolucion);
			repositorio.agregarEvolucion(evolucion);
			repositorio.actualizar(pokemon.get());
			mensajeSeHaModificadoConExito(nombrePokemonAEditar);

		}catch(NivelRequeridoNoAlcanzado e){
			System.out.print("El pokemon " + nombrePokemonAEditar + "no ha alcanzado el nivel requerido para la evolucion \n");
			}		
		});
	}

	public void agregarHabilidad(String atributo, String nombrePokemonAEditar) {
		withTransaction(() -> {
		Optional<Pokemon> pokemon = repositorio.buscarPorNombre(nombrePokemonAEditar);
		pokemon.get().agregarHabilidad(atributo);
		repositorio.actualizar(pokemon.get());
		});
		limpiarConsola();
		mensajeSeHaModificadoConExito(nombrePokemonAEditar);		
	}
	
	static void limpiarConsola() {
		System.out.print("\n\n\n\n\n\n\n\n");
	}
	
	static void mostrarPokemones(List<Pokemon> pokemones) {
		limpiarConsola();
		System.out.print("Pokemones en pokedex: \n");
		pokemones.forEach(pokemon -> System.out.print("Nombre: " + pokemon.getNombre() + 
                					 " | Nivel actual: " + pokemon.getNivelActual().toString() + "\n"));
	}
	
	static void mensajeSeHaModificadoConExito(String nombrePokemonAEditar) {
		System.out.print("Se ha modificado con exito el pokemon: " + nombrePokemonAEditar + "\n");
	}

}
