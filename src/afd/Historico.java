package afd;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Historico implements Iterable<Etapa> {
    
    
    private final List<Etapa> historico = new ArrayList<>();
    
    private StatusParada status;
    
    
    public void adicionar(String estado1, Character simbolo, String estado2,
    String parcelaNaoLida) {
        historico.add (
            new Etapa(
                estado1, 
                estado2, 
                simbolo, 
                parcelaNaoLida
            )
        );
    }
    
    
    public Etapa get(int idx) {
        return historico.get(idx);
    }
    
    
    public void limpar() {
        historico.clear();
    }

    
    public void setStatus(StatusParada status) {
        this.status = status;
    }

    
    public StatusParada getStatus() {
        return status;
    }
   
    
    public int tamanho() {
        return historico.size();
    }

    
    @Override
    public Iterator<Etapa> iterator() {
        return historico.iterator();
    }
    
    
}