package afd;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FuncaoTransicao implements Iterable<Transicao> {
    

    private final Estados estados;

    private final Alfabeto alfabeto;

    private final List<Transicao> trasicoes = new ArrayList<>();

    
    public FuncaoTransicao(Estados estados, Alfabeto alfabeto) {
        this.estados = estados;
        this.alfabeto = alfabeto;
    }
    
    
    private int indiceDe(Transicao transicao) {
        for (int idx = 0; idx < trasicoes.size(); idx++) {
            Transicao t = trasicoes.get(idx);
            if (transicao.getEstado1().equals(t.getEstado1()) && 
            transicao.getSimbolo().equals(t.getSimbolo())) {
                return idx;                
            }
        }
        return -1;        
    }

    
    public boolean set(String estado1, Character simbolo, String estado2) {              
        Estado e1 = estados.get(estado1);
        Estado e2 = estados.get(estado2);
        Simbolo s = alfabeto.get(simbolo);        
        if (e1!=null && e2!=null && s!=null) {
            Transicao transicao = new Transicao(e1, s, e2);
            int indice = indiceDe(transicao);
            if (indice < 0) {
                trasicoes.add(transicao);
            } else {
                trasicoes.get(indice).setEstado2(e2);
            }
            return true;
        } else {
            return false;
        }        
    }
    
    
    public Estado get(String estado, Character simbolo) throws Exception {        
        Estado e = estados.get(estado);
        Simbolo s = alfabeto.get(simbolo);        
        if (e!=null && s!=null) {
            for (Transicao t : trasicoes) {
                if (t.getEstado1().equals(e) && t.getSimbolo().equals(s)) {
                    return t.getEstado2();
                }            
            }
        }        
        throw new Exception(
            "Transição não definida: " + estado + "->" + simbolo
        );        
    }
    
    
    public void limpar() {
        trasicoes.clear();
    }
    
    
    public boolean totalmenteDefinida() {
        return trasicoes.size() == (estados.tamanho()*alfabeto.tamanho());
    }
    
    
    @Override
    public Iterator<Transicao> iterator() {
        return trasicoes.iterator();
    }
    
    
}