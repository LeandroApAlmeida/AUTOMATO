package afd.automato;

/**
 * Classe que representa uma transição <b>Estado Inicial × Símbolo Lido → 
 * Novo Estado</b> da função de transição.
 */
public class Transicao {
        
    
    /**Estado inicial.*/
    private final Estado estadoInicial;
    
    /**Símbolo lido da fita.*/
    private final Simbolo simboloLido;
    
    /**Novo estado.*/
    private Estado novoEstado;
    

    /**
     * Constructor da classe.
     * 
     * @param estadoInicial Estado inicial.
     * 
     * @param simboloLido Símbolo lido da fita.
     * 
     * @param novoEstado Novo estado.
     */
    public Transicao(Estado estadoInicial, Simbolo simboloLido, 
    Estado novoEstado) {
        
        this.estadoInicial = estadoInicial;
        
        this.novoEstado = novoEstado;
        
        this.simboloLido = simboloLido;
    
    }

    
    /**
     * Obter o estado inicial.
     * 
     * @return Estado inicial.
     */
    public Estado getEstadoInicial() {
        
        return estadoInicial;
        
    }
    
    
    /**
     * Obter o símbolo lido da fita.
     * 
     * @return Símbolo lido da fita.
     */
    public Simbolo getSimboloLido() {
        
        return simboloLido;
        
    }

    
    /**
     * Obter o novo estado.
     * 
     * @return Novo estado.
     */
    public Estado getNovoEstado() {
        
        return novoEstado;
    
    }

    
    /**
     * Definir o novo estado da transição.
     * 
     * @param novoEstado Novo estado da transição.
     */
    public void setNovoEstado(Estado novoEstado) {
        
        this.novoEstado = novoEstado;
        
    }

    
    @Override
    public boolean equals(Object obj) {
        
        if (obj instanceof Transicao) {
            
            Transicao t = (Transicao)obj;
            
            return t.getEstadoInicial().equals(estadoInicial) &&
            t.getNovoEstado().equals(novoEstado) &&
            t.getSimboloLido().equals(simboloLido);
            
        }
        
        return false;
        
    }
    
    
}