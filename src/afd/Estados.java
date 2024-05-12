package afd;

import java.util.ArrayList;
import java.util.List;

public class Estados extends Conjunto<Estado> {

    
    @Override
    public boolean inserir(Estado estado) {
        String n = estado.getNome();
        if (n.startsWith("q")) {
            if (n.length() > 1) {
                n = n.substring(1, n.length());
                for (int idx = 0; idx < n.length(); idx++) {
                    if (n.charAt(idx) < 48 || n.charAt(idx) > 57) return false;               
                }
                return super.inserir(estado);
            } else {
                return false;
            }
        }
        return false;
    }

    
    @Override
    void ordenar(List<Estado> lista) {
        String n1 = lista.get(lista.size()-1).getNome();        
        int i1 = Integer.parseInt(n1.substring(1, n1.length()));        
        for (int idx = 0; idx < lista.size()-1; idx++) {
            String n2 = lista.get(idx).getNome();
            int i2 = Integer.parseInt(n2.substring(1, n2.length()));
            if (i1 < i2) {
                lista.add(idx, lista.get(lista.size()-1));
                lista.remove(lista.size()-1);
                break;                
            }  
        }
    }
      
    
    public List<Estado> getTerminais() {
        List<Estado> terminais = new ArrayList<>();
        for (int idx = 0; idx < tamanho(); idx++) {
            Estado estado = get(idx);
            if (estado.isTerminal()) terminais.add(estado);
        }
        return terminais;
    }
    
    
    public List<Estado> getNaoTerminais() {
        List<Estado> naoTerminais = new ArrayList<>();
        for (int idx = 0; idx < tamanho(); idx++) {
            Estado estado = get(idx);
            if (!estado.isTerminal()) naoTerminais.add(estado);
        }
        return naoTerminais;
    }
    
    
}