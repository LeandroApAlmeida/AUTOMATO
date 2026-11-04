package afd.automato;

/**
 * Classe que representa uma transição <b>Estado × Símbolo → Estado</b> da função
 * de transição.
 */
public class Transicao {
        
    
    /**Estado inicial.*/
    private final Estado estado1;
    
    /**Símbolo lido da fita.*/
    private final Simbolo simbolo;
    
    /**Novo estado.*/
    private Estado estado2;
    

    /**
     * Constructor da classe.
     * 
     * @param estado1 Estado inicial.
     * 
     * @param simbolo Símbolo lido da fita.
     * 
     * @param estado2 Novo estado.
     */
    public Transicao(Estado estado1, Simbolo simbolo, Estado estado2) {
        
        this.estado1 = estado1;
        
        this.estado2 = estado2;
        
        this.simbolo = simbolo;
    
    }

    
    /**
     * Obter o estado inicial.
     * 
     * @return Estado inicial.
     */
    public Estado getEstado1() {
        
        return estado1;
        
    }

    
    /**
     * Obter o novo estado.
     * 
     * @return Novo estado.
     */
    public Estado getEstado2() {
        
        return estado2;
    
    }

    
    /**
     * Obter o símbolo lido da fita.
     * 
     * @return Símbolo lido da fita.
     */
    public Simbolo getSimbolo() {
        
        return simbolo;
        
    }

    
    /**
     * Definir o novo estado da transição.
     * 
     * @param estado2 Novo estado da transição.
     */
    public void setEstado2(Estado estado2) {
        
        this.estado2 = estado2;
        
    }

    
    @Override
    public boolean equals(Object obj) {
        
        if (obj instanceof Transicao) {
            
            Transicao t = (Transicao)obj;
            
            return t.getEstado1().equals(estado1) &&
            t.getEstado2().equals(estado2) &&
            t.getSimbolo().equals(simbolo);
            
        }
        
        return false;
        
    }
    
    
}