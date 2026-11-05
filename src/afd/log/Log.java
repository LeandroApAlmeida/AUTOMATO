package afd.log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Classe que representa o log do processamento de uma palavra de entrada.
 */
public class Log implements Iterable<EntradaLog> {
    
    
    /**Lista com as entradas do log.*/
    private final List<EntradaLog> log = new ArrayList<>();
    
    /**Status de parada do Autômato Finito.*/
    private StatusParada status;
    
    
    /**
     * Adicionar uma entrada de log.
     * 
     * @param estadoInicial Estado inicial.
     * 
     * @param simboloLido Símbolo lido da fita.
     * 
     * @param novoEstado Novo estado.
     * 
     * @param parcelaNaoLida Parcela ainda não lida da fita.
     */
    public void adicionar(String estadoInicial, Character simboloLido, 
    String novoEstado, String parcelaNaoLida) {
        
        log.add (
            new EntradaLog(
                estadoInicial,
                simboloLido,
                novoEstado,
                parcelaNaoLida
            )
        );
        
    }
    
    
    /**
     * Obter a entrada de log no índice passado.
     * 
     * @param idx Índice da entrada de log.
     * 
     * @return Entrada de log.
     */
    public EntradaLog getEntradaLog(int idx) {
        
        return log.get(idx);
        
    }
    
    
    /**
     * Remover todas as entradas do log.
     */
    public void limpar() {
        
        log.clear();
        
    }

    
    /**
     * Definir o status de parada do autômato.
     * 
     * @param status Status de parada do autômato.
     */
    public void setStatusParada(StatusParada status) {
        
        this.status = status;
        
    }

    
    /**
     * Obter o status de parada do autômato.
     * 
     * @return Status de parada do autômato.
     */
    public StatusParada getStatusParada() {
        
        return status;
        
    }
   
    
    /**
     * Obter o número de entradas no log.
     * 
     * @return Número de entradas no log.
     */
    public int tamanho() {
        
        return log.size();
        
    }

    
    @Override
    public Iterator<EntradaLog> iterator() {
        
        return log.iterator();
        
    }
    
    
}