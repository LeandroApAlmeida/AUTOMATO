package afd.automato;

import java.util.Collections;
import java.util.List;


/**
 * Classe que representa o alfabeto de símbolos de entrada (Σ).
 */
public class Alfabeto extends Conjunto<Simbolo> {


    /**
     * Ordenar os símbolos do alfabeto em ordem ascendente.
     * 
     * @param lista Lista de símbolos do alfabeto.
     */
    @Override
    void ordenar(List<Simbolo> lista) {
        
        Collections.sort(lista);
    
    }
   
    
}