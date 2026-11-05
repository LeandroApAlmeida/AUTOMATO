package afd.automato;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Classe que representa a função de transição (δ) do Autômato Finito. A função
 * de transição é uma função:
 * 
 * <br><br>
 * 
 * <p style="margin-left: 40px;">δ: Q × Σ → Q</p>
 * 
 * <br>
 * 
 * Por exemplo, seja o Autômato Finito:
 * 
 * <br><br>
 * 
 * <p style="margin-left: 40px;">
 * 
 * M<sub>1</sub> = ({a,b}, {q<sub>0</sub>, q<sub>1</sub>, q<sub>2</sub>, 
 * q<sub>f</sub>}, δ<sub>1</sub>, q<sub>0</sub>, {q<sub>f</sub>})
 * 
 * </p>
 * 
 * <br>
 * 
 * A função de transição δ<sub>1</sub> é representada na tabela abaixo:
 * 
 * <br><br>
 * 
 * <table border="1" style="width: 500px;margin-left: 40px;">
 * 
 * <caption> </caption>
 * 
 * <tr>
 *   <th style="text-align: center;">δ<sub>1</sub></th>
 *   <th style="text-align: center;">a</th>
 *   <th style="text-align: center;">b</th>
 * </tr>
 * 
 * <tr>
 *   <td style="text-align: center; vertical-align: middle;">q<sub>0</sub></td>
 *   <td style="text-align: center; vertical-align: middle;">q<sub>1</sub></td>
 *   <td style="text-align: center; vertical-align: middle;">q<sub>2</sub></td>
 * </tr>
 * 
 * <tr>
 *  <td style="text-align: center; vertical-align: middle;">q<sub>1</sub></td>
 *  <td style="text-align: center; vertical-align: middle;">q<sub>f</sub></td>
 *  <td style="text-align: center; vertical-align: middle;">q<sub>2</sub></td>
 * </tr>
 * 
 * <tr>
 *  <td style="text-align: center; vertical-align: middle;">q<sub>2</sub></td>
 *  <td style="text-align: center; vertical-align: middle;">q<sub>1</sub></td>
 *  <td style="text-align: center; vertical-align: middle;">q<sub>f</sub></td>
 * </tr>
 * 
 * <tr>
 *  <td style="text-align: center; vertical-align: middle;">q<sub>f</sub></td>
 *  <td style="text-align: center; vertical-align: middle;">q<sub>f</sub></td>
 *  <td style="text-align: center; vertical-align: middle;">q<sub>f</sub></td>
 * </tr>
 * 
 * </table>
 * 
 * <br>
 * 
 * A classe representa esta tabela, onde cada transição <b>Estado Inicial × 
 * Símbolo Lido → Novo Estado</b> do autômato é definida. A unidade de controle
 * do Autômato Finito precisa da função de transição para saber para qual estado
 * mudar quando ler um símbolo da fita estando num estado <i>q<sub>i</sub></i>.
 */
public class FuncaoTransicao implements Iterable<Transicao> {
    

    /**Conjunto finito de estados (Q).*/
    private final Estados estados;

    /**Alfabeto de símbolos de entrada (Σ).*/
    private final Alfabeto alfabeto;

    /**Lista de transições <b>Estado Inicial × Símbolo Lido → Novo Estado</b>.*/
    private final List<Transicao> transicoes = new ArrayList<>();

    
    /**
     * Constructor da classe.
     * 
     * @param estados Conjunto finito de estados (Q).
     * 
     * @param alfabeto Alfabeto de símbolos de entrada (Σ).
     */
    public FuncaoTransicao(Estados estados, Alfabeto alfabeto) {
        
        this.estados = estados;
        
        this.alfabeto = alfabeto;
    
    }
    
    
    /**
     * Obter o índice da transição passada.
     * 
     * @param transicao Transição a obter o índice.
     * 
     * @return Índice da transição passada, ou -1, caso não esteja definida. 
     */
    private int indiceDe(Transicao transicao) {
        
        for (int idx = 0; idx < transicoes.size(); idx++) {
            
            Transicao tc = transicoes.get(idx);
            
            if (transicao.getEstadoInicial().equals(tc.getEstadoInicial()) && 
            transicao.getSimboloLido().equals(tc.getSimboloLido())) {
                
                return idx;
                
            }
            
        }
        
        return -1;        
    
    }

    
    /**
     * Definir uma transição <b>Estado Inicial × Símbolo Lido → Novo Estado</b>.
     * Caso a transição <b>Estado Inicial × Símbolo Lido</b> esteja definida,
     * apenas altera <b>Novo Estado</b>.
     * 
     * @param estadoInicial Estado inicial.
     * 
     * @param simboloLido Símbolo lido da fita.
     * 
     * @param novoEstado Novo estado.
     * 
     * @return Se true, a transição foi inserida, se false, não.
     */
    public boolean setTransicao(String estadoInicial, Character simboloLido, 
    String novoEstado) {              
        
        Estado inicial = estados.get(estadoInicial);
        Estado novo = estados.get(novoEstado);        
        Simbolo simbolo = alfabeto.get(simboloLido);        
        
        if (inicial!=null && novo!=null && simbolo!=null) {
            
            Transicao transicao = new Transicao(inicial, simbolo, novo);
            
            int indice = indiceDe(transicao);
            
            if (indice < 0) {
                transicoes.add(transicao);
            } else {
                transicoes.get(indice).setNovoEstado(novo);
            }
            
            return true;
        
        } else {
        
            return false;
        
        }
        
    }
    
    
    /**
     * Obter o <b>Novo Estado</b> da transição <b>Estado Inicial × Símbolo Lido
     * → Novo Estado</b>.
     * 
     * @param estadoInicial Estado inicial.
     * 
     * @param simboloLido Símbolo lido da fita.
     * 
     * @return Novo estado da transição.
     * 
     * @throws Exception Erro em caso da transição <b>Estado Inicial × 
     * Símbolo Lido</b> não estar definida.
     */
    public Estado getNovoEstado(String estadoInicial, Character simboloLido) 
    throws Exception {        
        
        Estado inicial = estados.get(estadoInicial);
        Simbolo simbolo = alfabeto.get(simboloLido);        
        
        if (inicial!=null && simbolo!=null) {
            
            for (Transicao transicao : transicoes) {
                
                if (transicao.getEstadoInicial().equals(inicial) && 
                transicao.getSimboloLido().equals(simbolo)) {
                    
                    return transicao.getNovoEstado();
                    
                }
                
            }
        
        }
        
        throw new Exception(
            "Transição não definida: " + estadoInicial + "->" + simboloLido
        ); 
        
    }
    
    
    /**
     * Remover todas as transições da função de transiçção.
     */
    public void limpar() {
        
        transicoes.clear();
        
    }
    
    
    /**
     * Verificar se a função de transição tem todas as transições de 
     * <b>Estado Inicial × Símbolo Lido → Novo Estado</b> definidas.
     * 
     * @return Se true, a função de transição está totalmente definida, se false,
     * falta definir transições <b>Estado Inicial × Símbolo Lido → Novo Estado</b>.
     */
    public boolean totalmenteDefinida() {
        
        return transicoes.size() == (estados.tamanho()*alfabeto.tamanho());
    
    }
    
    
    @Override
    public Iterator<Transicao> iterator() {
        
        return transicoes.iterator();
    
    }
    
    
}