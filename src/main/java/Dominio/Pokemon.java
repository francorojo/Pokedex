package Dominio;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import Excepciones.EvolucionDeOtroTipo;
import Excepciones.NivelRequeridoNoAlcanzado;

@Entity
@Table(name = "Pokemones")
public class Pokemon {

	@Id
	String nombre;
	
	@ElementCollection
	List<String> tipos = new ArrayList<>();

	@ElementCollection
	List<String> habilidades = new ArrayList<>();

	Integer nivelActual;
	
	@OneToMany
	@JoinColumn(name = "id_pokemon")
    List<Evolucion> evoluciones = new ArrayList<>();
	
    public Pokemon(String nombre) {
		this.nombre = nombre;
	    this.nivelActual = 0;
	}
    
    public Pokemon() {}

    //Getters
    public String getNombre(){
        return nombre;
    }
    
    public Integer getNivelActual(){
        return nivelActual;
    }
    
    public List<String> getTipos(){
        return tipos;
    }
    
    public List<String> getHabilidades(){
        return habilidades;
    }
    
    public List<Evolucion> getEvoluciones(){
        return evoluciones;
    }
    
    //Setters
    public void setNivelActual(Integer nivel) {
		this.nivelActual = nivel;
	}
	
	public void setTipos(List<String> tipos) {
		this.tipos = tipos;
	}
	
	public void setHabilidades(List<String> habilidades) {
		this.habilidades = habilidades;
	}
	
	public void setEvoluciones(List<Evolucion> evoluciones) {
		this.evoluciones = evoluciones;
	}

	//Comportamiento
	public void aumentarNivel(){
    	nivelActual = nivelActual + 1;
    }
    
    public void evolucionar(Evolucion evolucion){
    	if(nivelActual < evolucion.getNivelRequeridoParaEvolucion())
    		throw new NivelRequeridoNoAlcanzado("El pokemon aun no ha alcanzado el nivel requerido para esta evolucion");
    	if(tipos.stream().filter(nombre -> evolucion.getTipos().contains(nombre)).collect(Collectors.toList()).isEmpty())// Interseccion
    		throw new EvolucionDeOtroTipo("La evolucion elegida no pertenece a un tipo del pokemon");
        evoluciones.add(evolucion);
    	nivelActual = nivelActual - evolucion.getNivelRequeridoParaEvolucion();
    }
	
	public void agregarHabilidad(String habilidad){
        habilidades.add(habilidad);
    }
    
    public void agregarTipo(String tipo){
        tipos.add(tipo);
    }
    	
	public void eliminarTipo(String atributo) {
		this.tipos.remove(atributo);
	}

}