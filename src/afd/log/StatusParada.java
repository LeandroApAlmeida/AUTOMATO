package afd.log;


/**
 * Enum que representa o status de parada do Autômato Finito.
 */
public enum StatusParada {

    /**Parada normal. Leu a fita até o fim.*/
    NORMAL,
    
    /**Parada por palavra vazia (fita de tamanho 0).*/
    PALAVRA_VAZIA,
    
    /**Parada por transição <b>Estado × Símbolo → Estado</b> indefinida.*/
    TRANSICAO_INDEFINIDA;
    
}