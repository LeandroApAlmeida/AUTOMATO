
package afd.log;


/**
 * Classe que representa uma entrada no log do processamento da palavra de 
 * entrada.
 */
public class EntradaLog {
    
    
    /**Estado inicial.*/
    private final String estadoInicial;
    
    /**Símbolo lido da fita.*/
    private final Character simboloLido;
    
    /**Novo estado.*/
    private final String novoEstado;
    
    /**Parcela ainda não lida da fita.*/
    private final String parcNaoLidaFita;

    
    /**
     * Constructor da classe.
     * 
     * @param estadoInicial Estado inicial.
     * 
     * @param novoEstado Novo estado.
     * 
     * @param simboloLido Símbolo lido da fita.
     * 
     * @param parcelaNaoLidaFita Parcela ainda não lida da fita.
     */
    public EntradaLog(String estadoInicial, Character simboloLido, 
    String novoEstado, String parcelaNaoLidaFita) {
        
        this.estadoInicial = estadoInicial;
        
        this.novoEstado = novoEstado;
        
        this.simboloLido = simboloLido;
        
        this.parcNaoLidaFita = parcelaNaoLidaFita;
    
    }

    
    /**
     * Obter o estado inicial.
     * 
     * @return Estado inicial. 
     */
    public String getEstadoInicial() {
        
        return estadoInicial;
    
    }

    
    /**
     * Obter o estado final.
     * 
     * @return Estado final.
     */
    public String getNovoEstado() {
        
        return novoEstado;
        
    }
    
    
    /**
     * Obter o símbolo lido da fita.
     * 
     * @return Símbolo lido da fita.
     */
    public Character getSimboloLido() {
        
        return simboloLido;
        
    }

    
    /**
     * Obter a parcela ainda não lida da fita.
     * 
     * @return Parcela ainda não lida da fita.
     */
    public String getParcelaNaoLidaDaFita() {
        
        return parcNaoLidaFita;
        
    }
 
    
}