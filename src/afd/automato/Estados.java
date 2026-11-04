package afd.automato;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Classe que representa o conjunto finito de estados (Q) do Autômato Finito.
 */
public class Estados extends Conjunto<Estado> {

    
    /**
     * Inserir um novo estado. O nome do estado deve, obrigatoriamente, iniciar
     * com o caractere <b>q</b>, seguido de caracteres numéricos e/ou alfabéticos
     * (maiúsculos e minúsculos).
     * 
     * @param estado Estado a ser inserido.
     * 
     * @return Se true, o estado foi inserido, se false, não.
     */
    @Override
    public boolean inserir(Estado estado) {
        
        String nome = estado.getNome();
        
        if (nome.startsWith("q")) {
            
            if (nome.length() > 1) {
                
                String sufixo = nome.substring(1, nome.length());
                
                for (int idx = 0; idx < sufixo.length(); idx++) {
                    
                    char ch = sufixo.charAt(idx);
                    
                    boolean isInt =   (ch >= 48 && ch <= 57);
                    boolean isUpper = (ch >= 65 && ch <= 90);
                    boolean isLower = (ch >= 97 && ch <= 122);
                    
                    if (!isInt && !isUpper && !isLower) {
                        return false;
                    } 
                    
                }
                
                return super.inserir(estado);
            
            } else {
            
                return false;
            
            }
            
        }
        
        return false;
        
    }
      
    
    /**
     * Obter o conjunto dos estados terminais (F).
     * 
     * @return Conjunto dos estados terminais (F).
     */
    public List<Estado> getTerminais() {
        
        List<Estado> terminais = new ArrayList<>();
        
        for (int idx = 0; idx < tamanho(); idx++) {
            
            Estado estado = get(idx);
            
            if (estado.isTerminal()) {
                terminais.add(estado);
            }
        
        }
        
        return terminais;
    
    }
    
    
    /**
     * Obter o conjunto dos estados não-terminais.
     * 
     * @return Conjunto dos estados não-terminais.
     */
    public List<Estado> getNaoTerminais() {
        
        List<Estado> naoTerminais = new ArrayList<>();
        
        for (int idx = 0; idx < tamanho(); idx++) {
        
            Estado estado = get(idx);
            
            if (!estado.isTerminal()) {
                naoTerminais.add(estado);
            }
        
        }
        
        return naoTerminais;
    
    }
    
    
    /**
     * Ordenar os estados em ordem ascendente do nome do estado.
     * 
     * @param lista Lista de estados.
     */
    @Override
    void ordenar(List<Estado> lista) {
        
        Collections.sort(lista);

    }
    
    
}