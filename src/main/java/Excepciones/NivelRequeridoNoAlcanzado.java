package Excepciones;

public class NivelRequeridoNoAlcanzado extends RuntimeException{
    public NivelRequeridoNoAlcanzado(String texto) {
        super(texto);
    }
}
