package afd.log;

import afd.automato.Alfabeto;
import afd.automato.AutomatoFinito;
import afd.automato.Estado;
import afd.automato.Estados;
import afd.automato.FuncaoTransicao;
import afd.automato.Simbolo;


/**
 * Classe para conversão do log do processamento da palavra de entrada para o
 * formato HTML a ser exibido na tela.
 */
public class ConversorLogHtml {
    
    
    /**Instância do autômato finito.*/
    private final AutomatoFinito automatoFinito;
    
    /**Status de palavra aceita pelo Autômato Finito.*/
    private final boolean aceita;

    
    /**
     * Constructor da classe.
     * 
     * @param automatoFinito Autômato finito.
     * 
     * @param aceita Status de palavra aceita pelo Autômato Finito.
     */
    public ConversorLogHtml(AutomatoFinito automatoFinito, boolean aceita) {
        
        this.automatoFinito = automatoFinito;
        
        this.aceita = aceita;
    
    }
    
    
    /**
     * Converter o log de processamento da palavra de entrada para o formato
     * HTML.
     * 
     * @throws Exception Erro ao processar o log.
     */
    public String converterParaHtml() throws Exception {
        
        StringBuilder sb = new StringBuilder();
        
        sb.append("<!DOCTYPE html>");
        sb.append("<html lang=\"pt-BR\">");
        sb.append("<head>");
        sb.append("<meta charset=\"UTF-8\">");
        sb.append("<style>");
        sb.append("    body {");
        sb.append("        font-family: Arial, sans-serif;");
        sb.append("        font-size: 12px;");
        sb.append("    }");  
        sb.append("</style>"); 
        sb.append("</head>");
        sb.append("<body>");
                
        sb.append("<b>Alfabeto (&sum;):</b> ");
        sb.append(formatarAlfabeto(automatoFinito.getAlfabeto()));
        sb.append("<br>");
        
        sb.append("<b>Estados (Q):</b> ");
        sb.append(formatarEstados(automatoFinito.getEstados()));
        sb.append("<br>");
        
        sb.append("<b>Estados Terminais (F):</b> ");
        sb.append(formatarEstadosTerminais(automatoFinito.getEstados()));
        sb.append("<br>");
        
        sb.append("<b>Estado Inicial (q<sub>0</sub>):</b> ");
        sb.append(formatarEstadoInicial(automatoFinito.getEstadoInicial()));
        sb.append("<br>");
        
        sb.append("<b>Função de Transição (&delta;):</b>");
        sb.append("<br><br>");
        sb.append(
            formatarFuncaoTransicao(
                automatoFinito.getFuncaoTransicao(),
                automatoFinito.getAlfabeto(),
                automatoFinito.getEstados()
            )
        );
        sb.append("<br><br>");
        
        sb.append("<b>");
        sb.append("Função Programa Estendida (");
        sb.append("<i><span style=\"text-decoration: underline;\">&delta;</span></i>): ");
        sb.append("</b>");
        sb.append("<br>");
        sb.append("<br>");        
        sb.append(
            formatarFuncaoProgramaExtendida(
                automatoFinito.getLog(),
                automatoFinito.getEstadoInicial()
            )
        );        
        sb.append("<br>");
        sb.append("<br>");
        
        sb.append("<b>");
        
        if (aceita) {
            sb.append("<font size=\"+1\" color=\"green\">"); 
            sb.append("Palavra aceita pela gramática");
        } else {
            sb.append("<font size=\"+1\" color=\"red\">");
            sb.append("Palavra NÃO aceita pela gramática");
        }
        
        sb.append("</font>");
        sb.append("</b>");
        
        sb.append("<br>");
        sb.append("<br>");
        
        sb.append("</body>");
        sb.append("</html>");
        
        return sb.toString();
        
    }
    
    
    /**
     * Formatar os itens na notação de conjunto {item1, item2, ..., itemN}.
     * 
     * @param itens Itens a serem formatados.
     * 
     * @return Itens formatados na notação de conjunto.
     */
    private String notacaoConjunto(Object[] itens) {
        
        StringBuilder s = new StringBuilder();
        
        s.append("{");
        
        if (itens.length > 0 ) {
        
            s.append(itens[0]);
            
            for (int idx = 1; idx < itens.length; idx++) {
                s.append(", ");
                s.append(itens[idx].toString());
            }
            
        }
        
        s.append("}");
        
        return s.toString();
    
    }
      
    
    /**
     * Formatar o alfabeto na notação de conjunto.
     * 
     * @param alfabeto Alfabeto.
     * 
     * @return Alfabeto formatado na notação de conjunto. 
     */
    private String formatarAlfabeto(Alfabeto alfabeto) {
        
        return notacaoConjunto(alfabeto.toArray());
    
    }
    
    
    /**
     * Formatar o conjunto dos estados terminais na notação de conjunto.
     * 
     * @param estados Conjunto dos estados terminais.
     * 
     * @return Conjunto do estados terminais formatado na notação de conjunto.
     */
    private String formatarEstadosTerminais(Estados estados) {
        
        return notacaoConjunto(estados.getTerminais().toArray());
        
    }
    
    
    /**
     * Formatar o conjunto dos estados na notação de conjunto.
     * 
     * @param estados Conjunto dos estados.
     * 
     * @return Conjunto do estados formatado na notação de conjunto.
     */
    private String formatarEstados(Estados estados) {
        
        return notacaoConjunto(estados.toArray());
        
    }
       
    
    /**
     * Formatar o estado inicial.
     * 
     * @param estadoInicial Estado inicial.
     * 
     * @return Estado inicial formatado.
     */
    private String formatarEstadoInicial(Estado estadoInicial) {
        
        return estadoInicial.toString();
        
    }
    
    
    /**
     * Formatar a função de transição como uma tabela HTML.
     * 
     * @param funcaoTransicao Função de transição.
     * 
     * @param alfabeto Alfabeto.
     * 
     * @param estados Conjunto dos estados.
     * 
     * @return Tabela HTML que representa a função de transição.
     */
    private String formatarFuncaoTransicao(FuncaoTransicao funcaoTransicao, 
    Alfabeto alfabeto, Estados estados) {
        
        StringBuilder sb = new StringBuilder();        
        
        sb.append("<table style=\"text-align: left;\" ");
        sb.append("border=\"1\" ");
        sb.append("cellpadding=\"0\" ");
        sb.append("cellspacing=\"0\">");
        sb.append("<tbody>");  
        sb.append("<tr>");
        sb.append("<td style=\"width: 20px;\"></td>"); 
        
        for (Simbolo simbolo : alfabeto) {
            sb.append("<td style=\"text-align: center; width: 20px;\">");
            sb.append("<b>");
            sb.append(simbolo.getCaractere());
            sb.append("</b>");
            sb.append("</td>");            
        }
        
        for (int idx1 = 0; idx1 < estados.tamanho(); idx1++) {
            
            Estado estado = estados.get(idx1);
            
            sb.append("<tr style=\"text-align: center;\">");
            sb.append("<td>");
            sb.append("<b>");
            sb.append(estado.getNome());
            sb.append("</b>");
            sb.append("</td>");
            
            for (int idx2 = 0; idx2 < alfabeto.tamanho(); idx2++) {
                
                try {
                    
                    Simbolo simbolo = alfabeto.get(idx2); 
                    
                    Estado novoEstado = funcaoTransicao.getNovoEstado(
                        estado.getNome(),
                        simbolo.getCaractere()
                    );
                    
                    sb.append("<td style=\"text-align: center;\">");
                    sb.append(novoEstado);
                    sb.append("</td>");
                    
                } catch (Exception ex) {
                    //Sem tratamento: jamais vai lançar uma exceção aqui.
                }
                
            }
            
            sb.append("</tr>");
            
        }
        
        sb.append("</tr>");
        sb.append("</tbody>");
        sb.append("</table>");
        
        return sb.toString();
        
    }

    
    /**
     * Formatar a Função Programa Estendida em formato HTML.
     * 
     * @param log Log do processamento da palavra de entrada.
     * 
     * @param estadoInicial Estado inicial.
     * 
     * @return Função Programa Estendida em formato HTML.
     */
    private String formatarFuncaoProgramaExtendida(Log log, Estado estadoInicial) { 
        
        StringBuilder s = new StringBuilder();        
        
        if (!log.getStatusParada().equals(StatusParada.PALAVRA_VAZIA)) {
            
            for (EntradaLog el : log) {
                
                s.append("<span style=\"text-decoration: underline;\">&delta;</span>");
                s.append("(");
                s.append(el.getEstado1());
                s.append(", ");
                s.append(el.getSimbolo());
                s.append(el.getParcelaNaoLidaDaFita()); 
                s.append(") ="); 
                s.append("<br>");                
                s.append("<span style=\"text-decoration: underline;\">&delta;</span>");
                s.append("(");
                s.append("&delta;");
                s.append("(");
                s.append(el.getEstado1());
                s.append(", ");
                s.append(el.getSimbolo());
                s.append("), ");
                
                if (!el.getParcelaNaoLidaDaFita().isEmpty()) {
                    s.append(el.getParcelaNaoLidaDaFita());
                } else {
                    s.append("&epsilon;");
                }
                
                s.append(") = ");
                
                if (el != log.getEntradaLog(log.tamanho()-1)) {
                    
                    s.append("<br>");
                    
                } else {
                    
                    switch (log.getStatusParada()) {
                        
                        case TRANSICAO_INDEFINIDA: { 
                            s.append("[<i><b>ERRO:</b> Símbolo indefinido \"");
                            s.append(new String(new char[]{el.getSimbolo()}));
                            s.append("\"</i>]");
                        } break;
                        
                        default: {
                            s.append("<br>");                        
                            s.append("<span style=\"text-decoration: underline;\">&delta;</span>");
                            s.append("(");
                            s.append(el.getEstado2());
                            s.append(", &epsilon;) = ");
                            s.append("<font size=\"+1\"><b><i>");
                            s.append(el.getEstado2());  
                            s.append("</i></b></font>");
                        } break;
                        
                    }
                    
                }
                
            }
            
        } else {
            
            s.append("<span style=\"text-decoration: underline;\">&delta;</span>");
            s.append("(");
            s.append(estadoInicial.getNome());
            s.append(", &epsilon;) = ");
            s.append("<font size=\"+1\"><b><i>");
            s.append(estadoInicial.getNome());  
            s.append("</i></b></font>");
            
        }
        
        return s.toString();
        
    }
    
    
}