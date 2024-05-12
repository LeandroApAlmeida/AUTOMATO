
package afd;

public class Etapa {
    
    
    private final String estado1;
    
    private final String estado2;
    
    private final Character simbolo;
    
    private final String parcNaoLidaFita;

    
    public Etapa(String estado1, String estado2, Character simbolo,
    String parcelaNaoLidaFita) {
        this.estado1 = estado1;
        this.estado2 = estado2;
        this.simbolo = simbolo;
        this.parcNaoLidaFita = parcelaNaoLidaFita;
    }

    
    public String getEstado1() {
        return estado1;
    }

    
    public String getEstado2() {
        return estado2;
    }

    
    public String getParcelaNaoLidaFita() {
        return parcNaoLidaFita;
    }

    
    public Character getSimbolo() {
        return simbolo;
    }
 
    
}