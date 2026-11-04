package afd.automato;


/**
 * Classe que representa um estado do Autômato Finito Determinístico.
 */
public class Estado implements Comparable<Estado> {
    
    
    /**Nome do estado.*/
    private String nome;
    
    /**Status de estado terminal.*/
    private boolean terminal;

    
    /**
     * Constructor da classe.
     * 
     * @param nome Nome do estado.
     * 
     * @param terminal Status de estado terminal.
     */
    public Estado(String nome, boolean terminal) {
        
        this.nome = nome;
        
        this.terminal = terminal;
    
    }
 
    
    /**
     * Constructor da classe.
     * 
     * @param nome Nome do estado.
     */
    public Estado(String nome) {
        
        this(nome, false);
    
    }

    
    /**
     * Obter o nome do estado.
     * 
     * @return Nome do estado.
     */
    public String getNome() {
        
        return nome;
    
    }

    
    /**
     * Obter o status de estado terminal.
     * 
     * @return Status de estado terminal.
     */
    public boolean isTerminal() {
    
        return terminal;
    
    }

    
    /**
     * Definir o status de estado terminal.
     * 
     * @param terminal Status de estado terminal.
     */
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