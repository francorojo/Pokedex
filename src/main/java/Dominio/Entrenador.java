package Dominio;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Entrenador {
	
	@Id
	@GeneratedValue
	Integer id_entrenador; //PK

	String nombre;

    @OneToMany
    @JoinColumn(name = "pokemones_usuarios")
    List<Pokemon> pokemones = new ArrayList<>();
    
    public Entrenador(String nombre) {
		this.nombre = nombre;
	}
    
    public Entrenador() {}

    public void agregarPokemon(Pokemon pokemon){
        pokemones.add(pokemon);
    }
    
    public String getNombre(){
        return nombre;
    }
    
}

