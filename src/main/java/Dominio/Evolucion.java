package Dominio;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Evoluciones")
public class Evolucion {

	@Id @GeneratedValue
	Integer id_evolucion; //PK

	@ElementCollection
	List<String> tipos = new ArrayList<>();
	
	String nombre;
    @Column(name = "nivel_requerido_para_evolucion")
	
    Integer nivelRequeridoParaEvolucion;

    public Evolucion(String nombre,Integer nivelRequeridoParaEvolucion) {
		this.nombre = nombre;
	    this.nivelRequeridoParaEvolucion = nivelRequeridoParaEvolucion;
	}
    
    public Evolucion() {}

    //Getters
    public String getNombre() {
		return nombre;
	}
    
    public List<String> getTipos() {
		return tipos;
	}

	public Integer getNivelRequeridoParaEvolucion() {
		return nivelRequeridoParaEvolucion;
	}

	//Comportamiento
    public void agregarTipo(String tipo){
        tipos.add(tipo);
    }
    

	
}
