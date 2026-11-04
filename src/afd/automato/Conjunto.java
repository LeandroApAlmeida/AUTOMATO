package afd.automato;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Classe que representa um conjunto genérico de objetos.
 * 
 * @param <E> Tipo do objeto.
 */
public abstract class Conjunto <E> implements Iterable<E> {
    
    
    /**Lista de objetos.*/
    private final List<E> conjunto = new ArrayList<>();    
        
    
    /**
     * Obter o índice do objeto passado.
     * 
     * @param o Objeto a obter o índice.
     * 
     * @return Índice do objeto.
     */
    private int indiceDe(Object o) {
        
        for (int idx = 0; idx < conjunto.size(); idx++) {
            
            E e = conjunto.get(idx);
            
            if (e.equals(o)) return idx;            
        
        }
        
        return -1;
    
    }
    
    
    /**
     * Inserir um novo objeto no conjunto.
     * 
     * @param o Objeto a ser inserido.
     * 
     * @return Se true, o objeto foi inserido, se false, não.
     */
    public boolean inserir(E o) {
        
        if (!contem(o)) {
            
            conjunto.add(o);
            
            ordenar(conjunto);
            
            return true;
        
        } else {
        
            return false;
        
        }  
        
    }
    
    
    /**
     * Obter o objeto no índice passado.
     * 
     * @param idx Índice do objeto.
     * 
     * @return Objeto no índice.
     */
    public E get(int idx) {
        
        return conjunto.get(idx);
    
    }
    
    
    /**
     * Obter o objeto passado.
     * 
     * @param o Objeto de referência.
     * 
     * @return Objeto relacionado.
     */
    public E get(Object o) {
        
        int idx = indiceDe(o);
        
        if (idx>=0) return get(idx);
        
        return null;
    
    }
    
    
    /**
     * Remover o objeto passado.
     * 
     * @param o Objeto a ser removido.
     * 
     * @return Se true, o objeto foi removido, se false, não.
     */
    public boolean remover(Object o) {
        
        int idx = indiceDe(o);
        
        if (idx >= 0) {
            
            remover(idx);
            
            return true;
        
        } else {
        
            return false;
        
        }
    
    }
    
    
    /**
     * Remover o objeto no índice passado.
     * 
     * @param idx Índice do objeto a ser removido.
     * 
     * @return Objeto removido.
     */
    public E remover(int idx) {
        
        return conjunto.remove(idx);
    
    }
    
    
    /**
     * Remover todos os objetos do conjunto.
     */
    public void limpar() {
        
        conjunto.clear();
    
    }
    
    
    /**
     * Obter o número de objetos do conjunto.
     * 
     * @return Número de objetos do conjunto.
     */
    public int tamanho() {
        
        return conjunto.size();
   
    }

    
    /**
     * Verificar se o conjunto contém o objeto passado.
     * 
     * @param o Objeto a ser verificado.
     * 
     * @return Se true, o conjunto contém o objeto, se false, não contém.
     */
    @SuppressWarnings("")
    public boolean contem(Object o) {
        
        return conjunto.contains(o);
    
    }
    
    
    /**
     * Converte a lista de objetos num array de objetos.
     * 
     * @return Array de objetos.
     */
    public Object[] toArray() {
        
        return conjunto.toArray();
    
    }
    
    
    @Override
    public Iterator<E> iterator() {
        
        return conjunto.iterator();
    
    }
    
    
    /**
     * Ordenar o conjunto de objetos conforme critério da classe herdeira.
     * 
     * @param lista Lista de objetos.
     */
    abstract void ordenar(List<E> lista);
    
    
}