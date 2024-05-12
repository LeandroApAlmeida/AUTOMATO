package afd;

public class Estado implements Comparable<Estado> {
    
    
    private String nome;
    
    private boolean terminal;

    
    public Estado(String nome, boolean terminal) {
        this.nome = nome;
        this.terminal = terminal;
    }
 
    
    public Estado(String nome) {
        this(nome, false);
    }

    
    public String getNome() {
        return nome;
    }

    
    public boolean isTerminal() {
        return terminal;
    }

    
    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
    }

    
    @Override
    public int compareTo(Estado estado) {
        return nome.compareTo(estado.nome);
    }

    
    @Override
    public boolean equals(Object estado) {
        if (estado != null) {
            if (estado instanceof String) {
                return ((String)estado).equals(nome);
            } else if (estado instanceof Estado) {
                return ((Estado)estado).nome.equals(nome);
            }
        } 
        return false;
    }

    
    @Override
    public String toString() {
        return nome;
    }
      
    
}