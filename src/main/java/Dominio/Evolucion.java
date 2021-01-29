package Dominio;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

@Entity
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

    public void agregartipo(String tipo){
        tipos.add(tipo);
    }
    
	public Integer nivelRequeridoParaEvolucion() {
		return nivelRequeridoParaEvolucion;
	}

	public List<String> getTipos() {
		return tipos;
	}
}
