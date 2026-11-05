package afd.log;


/**
 * Enum que representa o status de parada do Autômato Finito.
 * 
 * <br><br>
 * 
 * Os valores são os seguintes:
 * 
 * <br><br>
 * 
 * <ol>
 * <li>NORMAL: Parada normal. Leu a fita até o fim.</li>
 * <li>PALAVRA_VAZIA: Parada por palavra vazia (fita de tamanho 0).</li>
 * <li>TRANSICAO_INDEFINIDA: Parada por transição <b>Estado Inicial × Símbolo Lido
 * → Novo Estado</b> indefinida.</li>
 * </ol>
 */
public enum StatusParada {

    /**Parada normal. Leu a fita até o fim.*/
    NORMAL,
    
    /**Parada por palavra vazia (fita de tamanho 0).*/
    PALAVRA_VAZIA,
    
    /**Parada por transição <b>Estado Inicial × Símbolo Lido → Novo Estado</b> 
     * indefinida.
     */
    TRANSICAO_INDEFINIDA;
    
}