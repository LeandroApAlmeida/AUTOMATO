package afd.automato;


/**
 * Classe que representa um símbolo gravado em uma célula da fita.
 */
public class Simbolo implements Comparable<Simbolo> {
    
    
    /**Caractere do símbolo na tabela UTF-8.*/
    private final Character caractere;

    
    /**
     * Constructor da classe.
     * 
     * @param caractere Caractere do símbolo na tabela UTF-8.
     */
    public Simbolo(Character caractere) {
        
        this.caractere = caractere;
    
    }

    
    /**
     * Obter o caractere do símbolo na tabela UTF-8.
     * 
     * @return Caractere do símbolo na tabela UTF-8. 
     */
    public Character getCaractere() {
        
        return caractere;
    
    }

    
    @Override
    public int compareTo(Simbolo simbolo) {
        
        return caractere.compareTo(simbolo.caractere);
    
    }

    
    @Override
    public boolean equals(Object obj) {
        
        if (obj != null) {
        
            if (obj instanceof Character) {
                return ((Character)obj).equals(caractere);            
            } else if (obj instanceof Simbolo) {
                return ((Simbolo)obj).caractere.equals(caractere);
            }
        
        }
        
        return false;
    
    }

    
    @Override
    public String toString() {
        
        return new String(new char[]{caractere});
        
    }

    
}