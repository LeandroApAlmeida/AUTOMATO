
package afd.log;


/**
 * Classe que representa uma entrada no log do processamento da palavra de 
 * entrada.
 */
public class EntradaLog {
    
    
    /**Estado inicial.*/
    private final String estado1;
    
    /**Novo estado.*/
    private final String estado2;
    
    /**Símbolo lido da fita.*/
    private final Character simbolo;
    
    /**Parcela ainda não lida da fita.*/
    private final String parcNaoLidaFita;

    
    /**
     * Constructor da classe.
     * 
     * @param estado1 Estado inicial.
     * 
     * @param estado2 Novo estado.
     * 
     * @param simbolo Símbolo lido da fita.
     * 
     * @param parcelaNaoLidaFita Parcela ainda não lida da fita.
     */
    public EntradaLog(String estado1, String estado2, Character simbolo,
    String parcelaNaoLidaFita) {
        
        this.estado1 = estado1;
        
        this.estado2 = estado2;
        
        this.simbolo = simbolo;
        
        this.parcNaoLidaFita = parcelaNaoLidaFita;
    
    }

    
    /**
     * Obter o estado inicial.
     * 
     * @return Estado inicial. 
     */
    public String getEstado1() {
        
        return estado1;
    
    }

    
    /**
     * Obter o estado final.
     * 
     * @return Estado final.
     */
    public String getEstado2() {
        
        return estado2;
        
    }
    
    
    /**
     * Obter o símbolo lido da fita.
     * 
     * @return Símbolo lido da fita.
     */
    public Character getSimbolo() {
        
        return simbolo;
        
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