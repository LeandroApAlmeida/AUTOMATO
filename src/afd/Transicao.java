package afd;

public class Transicao {
        
    
    private final Estado estado1;
    
    private final Simbolo simbolo;
    
    private Estado estado2;
    

    public Transicao(Estado estado1, Simbolo simbolo, Estado estado2) {
        this.estado1 = estado1;
        this.estado2 = estado2;
        this.simbolo = simbolo;
    }

    
    public Estado getEstado1() {
        return estado1;
    }

    
    public Estado getEstado2() {
        return estado2;
    }

    
    public Simbolo getSimbolo() {
        return simbolo;
    }

    
    public void setEstado2(Estado estado2) {
        this.estado2 = estado2;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Transicao) {
            Transicao t = (Transicao)obj;
            return 
            t.getEstado1().equals(estado1) &&
            t.getEstado2().equals(estado2) &&
            t.getSimbolo().equals(simbolo);
        }
        return false;
    }
    
    
}