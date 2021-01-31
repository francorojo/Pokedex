package Repositorios;

import java.util.List;
import java.util.Optional;

import Dominio.Evolucion;
import Dominio.Pokemon;

public interface RepositorioPokemones {

	 public List<Pokemon> pokemones();
	 public Optional<Pokemon> buscarPorNombre(String nombre);
	 public void agregar(Pokemon pokemon);
	 public void remover(Pokemon pokemon);
	 public void agregarEvolucion(Evolucion evolucion);
	 public void actualizar(Pokemon pokemon);
}
