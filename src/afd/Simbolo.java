package afd;

public class Simbolo implements Comparable<Simbolo> {
    
    
    private final Character caractere;

    
    public Simbolo(Character caractere) {
        this.caractere = caractere;
    }

    
    public Character get() {
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