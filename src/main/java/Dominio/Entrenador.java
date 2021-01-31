package Dominio;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Entrenadores")
public class Entrenador {
	
	@Id	
	String nombre;

    @OneToMany
    @JoinColumn(name = "id_entrenador")
    List<Pokemon> pokemones = new ArrayList<>();
    
    public Entrenador(String nombre) {
		this.nombre = nombre;
	}
    
    public Entrenador() {}
    
    //Getters
    public String getNombre(){
        return nombre;
    }
    
    public List<Pokemon> getPokemones(){
        return pokemones;
    }
    
    //Comportamiento
    public void agregarPokemon(Pokemon pokemon){
        pokemones.add(pokemon);
    }
    
}

