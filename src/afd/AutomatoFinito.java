package afd;

public class AutomatoFinito {
    
    
    private class FitaEntrada {

        private final Simbolo[] celulas;

        public FitaEntrada(String palavra) {
            celulas = new Simbolo[palavra.length()];          
            for (int idx = 0; idx < palavra.length(); idx++) {
                celulas[idx] = new Simbolo(palavra.charAt(idx));            
            }
        }

        public Simbolo getSimbolo(int celula) {
            return celulas[celula-1];
        }

        public int dimensao() {
            return celulas.length;
        }

    }
   
    
    private class UnidadeLeitura {

        private final FitaEntrada fitaEntrada;
        private int cabecaLeitura;

        public UnidadeLeitura(FitaEntrada fitaEntrada) {
            this.fitaEntrada = fitaEntrada;
            cabecaLeitura = 1;
        }

        public Simbolo getSimbolo() {
            if (existeProximo()) {
                return fitaEntrada.getSimbolo(cabecaLeitura++);
            } else {
                return null;
            }
        }

        public boolean existeProximo() {
            return cabecaLeitura <= fitaEntrada.dimensao();
        }

        public Simbolo[] parcelaNaoLidaFita() {
            if (cabecaLeitura <= fitaEntrada.dimensao()) {
                Simbolo[] pNaoLida = new Simbolo[1+(fitaEntrada.dimensao()-cabecaLeitura)];
                int i = 0;
                for (int idx = cabecaLeitura; idx <= fitaEntrada.dimensao(); idx++) {
                    pNaoLida[i] = fitaEntrada.getSimbolo(idx);   
                    i++;
                }
                return pNaoLida;
            } else {
                return new Simbolo[]{};
            }
        }

    }
    
    
    private class UnidadeControle {

        private void gerarHistorico(Estado estado1, Simbolo simbolo, Estado estado2,
        Simbolo[] parcelaNaoLidaFita) {
            StringBuilder pnlf = new StringBuilder(parcelaNaoLidaFita.length);
            for (Simbolo s : parcelaNaoLidaFita) pnlf.append(s.get()); 
            historico.adicionar(
                estado1.getNome(), 
                simbolo.get(), 
                estado2 != null ? estado2.getNome() : "",
                pnlf.toString()
            ); 
        }

        private Estado lerFita(Estado origem, UnidadeLeitura uLeitura) {       
            Simbolo simbolo = uLeitura.getSimbolo();
            try {            
                Estado destino = fTransicao.get(origem.getNome(), simbolo.get()); 
                gerarHistorico(origem, simbolo, destino, uLeitura.parcelaNaoLidaFita());           
                if (uLeitura.existeProximo()) {
                    return lerFita(destino, uLeitura);
                } else {
                    return destino;
                }        
            } catch (Exception ex) {
                gerarHistorico(origem, simbolo, null, uLeitura.parcelaNaoLidaFita());
                return null;
            }
        }

        public Estado processarEntrada(FitaEntrada fita) throws Exception{        
            UnidadeLeitura uLeitura = new UnidadeLeitura(fita);        
            historico.limpar(); 
            if (fTransicao.totalmenteDefinida()) {            
                Estado estado = estadoInicial;
                if (uLeitura.existeProximo()) { 
                    estado = lerFita(estado, uLeitura);
                    historico.setStatus(
                        estado != null ? 
                        StatusParada.NORMAL : 
                        StatusParada.TRANSICAO_INDEFINIDA
                    );
                } else {
                    historico.setStatus(StatusParada.PALAVRA_VAZIA);
                }
                return estado;            
            } else {
                throw new Exception(
                    "Função de transição ainda não definida"
                );
            } 
        }

    }


    private final Estados estados;

    private final Alfabeto alfabeto;

    private Estado estadoInicial;

    private final FuncaoTransicao fTransicao;

    private final Historico historico;

    
    public AutomatoFinito() {
        alfabeto = new Alfabeto();
        estados = new Estados();
        fTransicao = new FuncaoTransicao(estados, alfabeto);
        estadoInicial = null;
        historico = new Historico();
    }

    
    public Alfabeto getAlfabeto() {
        return alfabeto;
    }

    
    public Estados getEstados() {
        return estados;
    }

    
    public void setEstadoInicial(Estado estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    
    public Estado getEstadoInicial() {
        return estadoInicial;
    }
    
    
    public FuncaoTransicao getFuncaoTransicao() {
        return fTransicao;
    }
    
    
    public Historico getHistorico() {
        return historico;
    }

    
    public boolean aceita(String palavra) throws Exception {
        FitaEntrada fitaEntrada = new FitaEntrada(palavra);
        UnidadeControle unidadeControle = new UnidadeControle();
        Estado estadoFinal = unidadeControle.processarEntrada(fitaEntrada);
        return estadoFinal != null ? estadoFinal.isTerminal() : false;          
    }
 
    
}