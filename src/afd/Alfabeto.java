package afd;

import java.util.Collections;
import java.util.List;

public class Alfabeto extends Conjunto<Simbolo> {


    @Override
    void ordenar(List<Simbolo> lista) {
        Collections.sort(lista);
    }
   
    
}
