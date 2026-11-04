package afd.automato;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Classe que representa a função de transição (δ) do Autômato Finito. A função
 * de transição é uma função <b>δ: Q × Σ → Q</b>.
 * 
 * <br><br>
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
 * onde a função de transição δ<sub>1</sub> é representada na tabela:
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
 * Esta classe representa a tabela, onde cada transição <b>Estado × Símbolo → Estado</b>
 * do autômato é definida. A unidade de controle do Autômato Finito precisa da 
 * função de transição para saber para qual estado mudar quando ler um símbolo 
 * da fita estando num estado <i>q</i>.
 * 
 * <br><br>
 * 
 * Comparando com um computador, a função de transição seria o programa que
 * controla o autômato, por isso ela é também chamada de função programa.
 */
public class FuncaoTransicao implements Iterable<Transicao> {
    

    /**Conjunto finito de estados (Q).*/
    private final Estados estados;

    /**Alfabeto de símbolos de entrada (Σ).*/
    private final Alfabeto alfabeto;

    /**Lista de transições <b>Estado × Símbolo → Estado</b>.*/
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
            
            Transicao t = transicoes.get(idx);
            
            if (transicao.getEstado1().equals(t.getEstado1()) && 
            transicao.getSimbolo().equals(t.getSimbolo())) {
                
                return idx;
                
            }
            
        }
        
        return -1;        
    
    }

    
    /**
     * Definir uma transição <b>Estado × Símbolo → Estado</b>. Caso a transição
     * <b>Estado × Símbolo</b> esteja definida, apenas altera estado2.
     * 
     * @param estado1 Estado inicial.
     * 
     * @param simbolo Símbolo lido da fita.
     * 
     * @param estado2 Novo estado.
     * 
     * @return Se true, a transição foi inserida, se false, não.
     */
    public boolean setTransicao(String estado1, Character simbolo, String estado2) {              
        
        Estado e1 = estados.get(estado1);
        Estado e2 = estados.get(estado2);        
        Simbolo s = alfabeto.get(simbolo);        
        
        if (e1!=null && e2!=null && s!=null) {
            
            Transicao transicao = new Transicao(e1, s, e2);
            
            int indice = indiceDe(transicao);
            
            if (indice < 0) {
                transicoes.add(transicao);
            } else {
                transicoes.get(indice).setEstado2(e2);
            }
            
            return true;
        
        } else {
        
            return false;
        
        }
        
    }
    
    
    /**
     * Obter o novo estado da transição <b>Estado × Símbolo</b>.
     * 
     * @param estado Estado inicial.
     * 
     * @param simbolo Símbolo lido da fita.
     * 
     * @return Novo estado da transição.
     * 
     * @throws Exception Erro em caso da transição <b>Estado × Símbolo</b> não
     * estar definida.
     */
    public Estado getNovoEstado(String estado, Character simbolo) throws Exception {        
        
        Estado e = estados.get(estado);
        Simbolo s = alfabeto.get(simbolo);        
        
        if (e!=null && s!=null) {
            
            for (Transicao t : transicoes) {
                if (t.getEstado1().equals(e) && t.getSimbolo().equals(s)) {
                    return t.getEstado2();
                }            
            }
        
        }
        
        throw new Exception(
            "Transição não definida: " + estado + "->" + simbolo
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
     * <b>Estado × Símbolo → Estado</b> definidas.
     * 
     * @return Se true, a função de transição está totalmente definida, se false,
     * falta definir transições <b>Estado × Símbolo → Estado</b>.
     */
    public boolean totalmenteDefinida() {
        return transicoes.size() == (estados.tamanho()*alfabeto.tamanho());
    }
    
    
    @Override
    public Iterator<Transicao> iterator() {
        return transicoes.iterator();
    }
    
    
}