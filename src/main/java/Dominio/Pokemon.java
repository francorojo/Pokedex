package Dominio;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import Excepciones.EvolucionDeOtroTipo;
import Excepciones.NivelRequeridoNoAlcanzado;

@Entity
public class Pokemon {
	@Id
	@GeneratedValue
	Integer id_pokemon; //PK
	
	@ElementCollection
	List<String> tipos = new ArrayList<>();

	@ElementCollection
	List<String> habilidades = new ArrayList<>();
	
	String nombre;
	Integer nivelActual;
	
	@OneToMany
    List<Evolucion> evoluciones = new ArrayList<>();
	
    public Pokemon(String nombre) {
		this.nombre = nombre;
	    this.nivelActual = 0;
	}
    
    public Pokemon() {}

    public void agregarhabilidad(String habilidad){
        habilidades.add(habilidad);
    }
    
    public void agregartipo(String tipo){
        tipos.add(tipo);
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public Integer getNivelActual(){
        return nivelActual;
    }
    
    public void aumentarNivel(){
    	nivelActual = nivelActual + 1;
    }
    
    public void evolucionar(Evolucion evolucion){
    	if(nivelActual < evolucion.nivelRequeridoParaEvolucion())
    		throw new NivelRequeridoNoAlcanzado("El pokemon aun no ha alcanzado el nivel requerido para esta evolucion");
    	if(tipos.retainAll(evolucion.getTipos()))
    		throw new EvolucionDeOtroTipo("La evolucion elegida no pertenece a un tipo del pokemon");
        evoluciones.add(evolucion);
    	nivelActual = nivelActual - evolucion.nivelRequeridoParaEvolucion();
    }

}

