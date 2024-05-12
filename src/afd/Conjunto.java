package afd;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Conjunto <E> implements Iterable<E> {
    
    
    private final List<E> conjunto = new ArrayList<>();    
        
    
    private int indiceDe(Object o) {
        for (int idx = 0; idx < conjunto.size(); idx++) {
            E e = conjunto.get(idx);
            if (e.equals(o)) return idx;            
        }
        return -1;
    }
    
    
    public boolean inserir(E o) {
        if (!contem(o)) {
            conjunto.add(o);
            ordenar(conjunto);
            return true;
        } else {
            return false;
        }    
    }
    
    
    public E get(int idx) {
        return conjunto.get(idx);
    }
    
    
    public E get(Object o) {
        int idx = indiceDe(o);
        if (idx>=0) return get(idx);
        return null;
    }
    
    
    public boolean remover(Object o) {
        int idx = indiceDe(o);
        if (idx >= 0) remover(idx);
        return false;
    }
    
    
    public E remover(int idx) {
        return conjunto.remove(idx);
    }
    
    
    public void limpar() {
        conjunto.clear();
    }
    
    
    public int tamanho() {
        return conjunto.size();
    }

    
    @SuppressWarnings("")
    public boolean contem(Object o) {
        return conjunto.contains(o);
    }
    
    
    public Object[] toArray() {
        return conjunto.toArray();
    }
    
    
    @Override
    public Iterator<E> iterator() {
        return conjunto.iterator();
    }
    
    
    abstract void ordenar(List<E> lista);
    
    
}