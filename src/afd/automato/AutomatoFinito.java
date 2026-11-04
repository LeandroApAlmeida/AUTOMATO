package afd.automato;

import afd.log.Log;
import afd.log.StatusParada;


/**
 * Classe que representa um Autômato Finito Determinístico, ou simplesmente 
 * Autômato Finito. O Autômato Finito é representado como uma máquina com as 
 * seguintes partes constituintes:
 * 
 * <br>
 * 
 * <pre style="font-family: 'Courier New', monospace !important; margin-left: 40px; background-color: white !important;">
 * 
 * Fita
 * ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┐
 * │ a │ a │ b │ b │ c │ c │ a │ c │ b │ a │
 * └───┴───┴───┴───┴───┴───┴───┴───┴───┴───┘
 *            ◤
 *              ╲    Cabeça de 
 *                ╲  Leitura
 *                  ╲ 
 *        ┌──────────────────────┐
 *        │                      │
 *        │ Unidade de      q1   │
 *        │ Controle     q0 ↑  q2│
 *        │                 q3   │
 *        │                      │
 *        └──────────────────────┘
 * </pre>
 * 
 * <br>
 * 
 * Onde:
 * 
 * <br><br>
 * 
 * <p style="margin-left: 40px;">
 * 
 * <b>Fita:</b> Dispositivo de entrada que contém a palavra a ser processada. É
 * composta de células, com um símbolo gravado em cada célula. A fita é finita à
 * esquerda e à direita e não é possível gravar sobre ela.
 * 
 * <br><br>
 *
 * <b>Cabeça de Leitura:</b> Cabeça móvel para leitura da fita, a qual acessa 
 * uma célula da fita a cada passo e movimenta-se exclusivamente para a direita.
 * 
 * <br><br>
 * 
 * <b>Unidade de Controle:</b> É a parte principal da máquina, constituída de um 
 * bloco de mecanismos internos que podem se apresentar em um momento específico
 * em algum dos seus estados internos.
 * 
 * </p>
 * 
 * <br>
 * 
 * Inicialmente a máquina está com a cabeça de leitura posicionada na célula mais
 * à esquerda da fita e a unidade de controle em um estado inicial pré-estabelecido.
 * A intervalos regulares, o autômato lê um símbolo da fita e entra em um
 * novo estado, que depende somente do estado atual e do símbolo lido. Após a 
 * leitura, a cabeça de leitura se movimenta uma célula para a direita da fita, 
 * posicionando-se de modo que, no próximo movimento ela leia o símbolo contido
 * na próxima célula. Esse processo é repetido continuamente: um símbolo é lido,
 * a cabeça de leitura move-se para a direita e muda o estado da unidade de 
 * controle.
 * 
 * <br>
 * 
 * <pre style="font-family: 'Courier New', monospace; margin-left: 40px; background-color: white !important;">
 *
 * ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┐  ┌────┐
 * │ a │ a │ b │ b │ c │ c │ a │ c │ b │ a │  │ q0 │
 * └───┴───┴───┴───┴───┴───┴───┴───┴───┴───┘  └────┘
 *   ▲
 * ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┐  ┌────┐
 * │ ā │ a │ b │ b │ c │ c │ a │ c │ b │ a │  │ q1 │
 * └───┴───┴───┴───┴───┴───┴───┴───┴───┴───┘  └────┘
 *       ▲
 * ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┐  ┌────┐
 * │ a │ ā │ b │ b │ c │ c │ a │ c │ b │ a │  │ q3 │ 
 * └───┴───┴───┴───┴───┴───┴───┴───┴───┴───┘  └────┘
 *           ▲
 * 
 * (...)
 * </pre>
 * 
 * <br>
 * 
 * Quando a cabeça de leitura alcança o final da fita o autômato pára, aceitando 
 * ou rejeitando a palavra contida nesta com base no estado a que foi conduzido
 * ao final do processo. Caso o estado pertença ao conjunto dos <b>estados 
 * terminais</b> a palavra de entrada é considerada <b>aceita</b>, do contrário
 * é considerada <b>rejeitada</b>.
 * 
 * <br><br>
 * 
 * Expressando essa descrição informal em seus princípios matemáticos básicos, 
 * um Autômato Finito Determinístico é uma quíntupla M = (Σ, Q, δ, q<sub>0</sub>,
 * F), onde:
 * 
 * <br><br>
 * 
 * <p style="margin-left: 40px;">
 * 
 * Σ: Alfabeto de símbolos de entrada.
 * 
 * <br><br>
 * 
 * Q: Conjunto finito de estados.
 * 
 * <br><br>
 * 
 * δ: Função programa ou função de de transição: (δ: Q × Σ → Q).
 * 
 * <br><br>
 * 
 * q<sub>0</sub>: Estado inicial, tal que q<sub>0</sub> ∈ Q.
 * 
 * <br><br>
 * 
 * F: Conjunto de estados terminais, tal que F ⊆ Q.
 * 
 * </p>
 * 
 * <br>
 * 
 * O processamento de um Autômato Finito Determinístico M, para uma palavra de 
 * entrada w, consiste na sucessiva aplicação da função de transição para cada
 * símbolo de w (da esquerda para a direita) até ocorrer uma condição de parada.
 * 
 * <br><br>
 * 
 * Considere a linguagem:
 * 
 * <br><br>
 * 
 * <p style="margin-left: 40px;">
 * L<sub>1</sub> = {w | w possui aa ou bb como subpalavra}
 * </p>
 * 
 * <br>
 * 
 * e o Autômato Finito:
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
 * onde δ<sub>1</sub> é representada na tabela abaixo:
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
 * M<sub>1</sub> reconhece a linguagem L<sub>1</sub>, desta forma: Após identificar 
 * dois <i>a</i> ou dois <i>b</i> consecutivos, o autômato assume o estado 
 * q<sub>f</sub> (final) e a partir daí, qualquer símbolo lido da fita mantém a 
 * unidade de controle no estado q<sub>f</sub>, até o término da leitura da fita.
 * 
 * <br><br>
 * 
 * Como já mencionado, a parada pode ser de duas maneiras: aceitando ou rejeitando
 * uma palavra de entrada w. As condições de parada são as seguintes:
 * 
 * <br><br>
 * 
 * <p style="margin-left: 40px;">
 * 
 * <b>a)</b> Após processar o último símbolo da fita, o autômato finito assume
 * um estado terminal: o autômato pára e a entrada w é aceita.
 * 
 * <br><br>
 * 
 * <b>b)</b> Após processar o último símbolo da fita, o autômato finito assume
 * um estado não-terminal: o autômato pára e a entrada w é rejeitada.
 * 
 * <br><br>
 * 
 * <b>c)</b> A função programa é indefinida para o argumento (estado corrente e
 * símbolo lido): a autômato pára e a palavra w é rejeitada.
 * 
 * </p>
 * 
 * <br>
 * 
 * Para definir formalmente o comportamento de um Autômato Finito, é necessário
 * estender a definição da função de transição usando como argumento um estado 
 * e uma palavra.
 * 
 * <br><br>
 * 
 * <h2>Função Programa Estendida</h2><br>
 * 
 * Seja M = (Σ, Q, δ, q<sub>0</sub>, F) um Autômato Finito Determinístico. A 
 * Função Programa Estendida, denotada por:
 * 
 * <br><br>
 * 
 * <p style="margin-left: 40px;">
 * <span style="text-decoration: underline;">δ</span>: Q × Σ* → Q
 * </p>
 * 
 * <br>
 * 
 * é a função programa δ: Q × Σ → Q estendida para palavras, e é definida como
 * segue:
 * 
 * <br><br>
 * 
 * <p style="margin-left: 40px;">
 * 
 * <span style="text-decoration: underline;">δ</span>(q,ε) = q
 * 
 * <br>
 * 
 * <span style="text-decoration: underline;">δ</span>(q,aw) = 
 * <span style="text-decoration: underline;">δ</span>(δ(q,a),w)
 * 
 * </p>
 * 
 * <br>
 * 
 * Considere o autômato M<sub>1</sub> definido no exemplo anterior. A Função 
 * Programa Estendida aplicada à palavra <i>abaa</i> a partir do estado inicial
 * q<sub>0</sub> é como segue:
 * 
 * <br><br>
 * 
 * <p style="margin-left: 40px;">
 * 
 * <span style="text-decoration: underline;">δ</span>(q<sub>0</sub>, abaa) =
 * 
 * <br>
 * 
 * <span style="text-decoration: underline;">δ</span>(δ(q<sub>0</sub>, a), baa) =
 * 
 * <br>
 * 
 * <span style="text-decoration: underline;">δ</span>(q<sub>1</sub>, baa) =
 * 
 * <br>
 * 
 * <span style="text-decoration: underline;">δ</span>(δ(q<sub>1</sub>, b), aa) =
 * 
 * <br>
 * 
 * <span style="text-decoration: underline;">δ</span>(q<sub>2</sub>, aa) =
 * 
 * <br>
 * 
 * <span style="text-decoration: underline;">δ</span>(δ(q<sub>2</sub>, a), a) =
 * 
 * <br>
 * 
 * <span style="text-decoration: underline;">δ</span>(q<sub>1</sub>, a) =
 * 
 * <br>
 * 
 * <span style="text-decoration: underline;">δ</span>(δ(q<sub>1</sub>, a), ε) =
 * 
 * <br>
 * 
 * <span style="text-decoration: underline;">δ</span>(q<sub>f</sub>, ε) = 
 * q<sub>f</sub>
 * 
 * </p>
 * 
 * <br> 
 * 
 * Neste caso, como a leitura do último símbolo da fita leva a máquina ao estado
 * q<sub>f</sub>, a palavra é aceita.
 * 
 * <br><br>
 * 
 * A linguagem aceita por um Autômato Finito Determinístico M = (Σ, Q, δ, 
 * q<sub>0</sub>, F), denotada por ACEITA(M) ou L(M), é o conjunto de todas as
 * palavras pertencentes a Σ* aceitas por M, ou seja:
 * 
 * <br><br>
 * 
 * <p style="margin-left: 40px;">
 * ACEITA(M) = {w | <span style="text-decoration: underline;">δ</span>(q<sub>0</sub>, 
 * w) ∈ F}
 * </p>
 * 
 * <br>
 * 
 * Analogamente, REJEITA(M) é o conjunto de todas as palavras pertencentes a Σ*
 * rejeitadas por M.
 * 
 * <br><br>
 * 
 * As seguintes afirmações são verdadeiras:
 * 
 * <br><br>
 * 
 * <p style="margin-left: 40px;">
 * 
 * ● ACEITA(M) ∩ REJEITA(M) = ∅
 * 
 * <br><br>
 * 
 * ● ACEITA(M) ∪ REJEITA(M) = Σ*
 * 
 * <br><br>
 * 
 * ● O complemento de ACEITA(M) é REJEITA(M)
 * 
 * <br><br>
 * 
 * ● O complemento de REJEITA(M) é ACEITA(M)
 * 
 * </p>
 * 
 * <br>
 * 
 * <b>Obs.:</b> As partes componentes da "máquina" estarão representadas como 
 * classes privadas internas à esta. Teremos uma classe para a Fita, para a 
 * Cabeça de Leitura e uma para a Unidade de Controle.
 */
public class AutomatoFinito {
    
    
    /**Conjunto finito de estados (Q).*/
    private final Estados estados;

    /**Alfabeto de símbolos de entrada (Σ).*/
    private final Alfabeto alfabeto;

    /**Estado inicial (q<sub>0</sub>).*/
    private Estado estadoInicial;

    /**Função de transição (δ).*/
    private final FuncaoTransicao funcaoTransicao;

    /**Log do processamento da palavra de entrada pelo Autômato Finito.*/
    private final Log log;
    
    
    /**
     * Classe que representa a fita.
     */
    private class Fita {

        
        /**Células da fita.*/
        private final Simbolo[] celulas;

        
        /**
         * Constructor da classe.
         * 
         * @param palavra Palavra de entrada.
         */
        public Fita(String palavra) {
        
            celulas = new Simbolo[palavra.length()];          
            
            for (int idx = 0; idx < palavra.length(); idx++) {
                celulas[idx] = new Simbolo(palavra.charAt(idx));            
            }
            
        }

        
        /**
         * Obter o símbolo na célula no índice passado.
         * 
         * @param celula Índice da célula (iniciado em 1).
         * 
         * @return Símbolo na célula passada.
         */
        public Simbolo getSimbolo(int celula) {
            
            return celulas[celula-1];
        
        }

        
        /**
         * Obter a dimensão da fita (número de células).
         * 
         * @return Dimensão da fita (Número de células).
         */
        public int dimensao() {
        
            return celulas.length;
        
        }
        

    }
   
    
    /**
     * Classe que representa a cabeça de leitura da fita.
     */
    private class CabecaLeitura {

        
        /**Referencia para a fita.*/
        private final Fita fita;
        
        /**Célula atual apontada pela cabeça de leitura.*/
        private int celulaAtual;

        
        /**
         * Constructor da classe.
         * 
         * @param fita Referencia para a fita.
         */
        public CabecaLeitura(Fita fita) {
            
            this.fita = fita;
            
            celulaAtual = 1;
        
        }

        
        /**
         * Ler o símbolo na célula atual da fita.
         * 
         * @return Símbolo na célula atual da fita, ou null, caso tenha chegado
         * ao final da fita.
         */
        public Simbolo lerSimbolo() {
            
            if (!chegouAoFinalDaFita()) {
                return fita.getSimbolo(celulaAtual++);
            } else {
                return null;
            }
        
        }


        /**
         * Verificar se a cabeça de leitura chegou ao final da fita.
         * 
         * @return Se true, chegou ao final da fita, se false, não chegou.
         */
        public boolean chegouAoFinalDaFita() {
            
            return celulaAtual > fita.dimensao();
        
        }

        
        /**
         * Retornar a parcela ainda não lida da fita, sem alterar a posição da
         * cabeça de leitura. 
         * 
         * <br><br>
         * 
         * Por exemplo, se a célula atual é a 2, e a fita tem 10 símbolos, 
         * retorna os símbolos de 2 a 10.
         * 
         * @return Parcela ainda não lida da fita.
         */
        public Simbolo[] getParcelaNaoLidaDaFita() {
            
            if (celulaAtual <= fita.dimensao()) {
                
                Simbolo[] parcNaoLida = new Simbolo[1+(fita.dimensao()-celulaAtual)];
                
                int i = 0;
                
                for (int idx = celulaAtual; idx <= fita.dimensao(); idx++) {
                    parcNaoLida[i] = fita.getSimbolo(idx);   
                    i++;
                }
                
                return parcNaoLida;
            
            } else {
            
                return new Simbolo[]{};
            
            }
            
        }

    }
    
    
    /**
     * Classe que representa a unidade de controle.
     */
    private class UnidadeControle {


        /**
         * Adicionar a transição ao log de processamento da palavra de entrada.
         * 
         * @param estado1 Estado atual do autômato finito.
         * 
         * @param simbolo Símbolo lido da fita.
         * 
         * @param estado2 Novo estado do autômato finito.
         * 
         * @param parcelaNaoLidaFita Parcela ainda não lida da Fita.
         */
        private void adicionarLog(Estado estado1, Simbolo simbolo, 
        Estado estado2, Simbolo[] parcelaNaoLidaFita) {
            
            StringBuilder sb = new StringBuilder(parcelaNaoLidaFita.length);
            
            for (Simbolo s : parcelaNaoLidaFita) sb.append(s.getCaractere()); 
            
            log.adicionar(
                estado1.getNome(), 
                simbolo.getCaractere(), 
                estado2 != null ? estado2.getNome() : "",
                sb.toString()
            );
            
        }

        
        /**
         * Lê um símbolo da fita e altera o estado atual do autômato finito
         * recursivamente, conforme a função de transição, até chegar ao final
         * da fita.
         * 
         * @param estadoAtual Estado atual do autômato.
         * 
         * @param cabecaLeitura Cabeça de leitura.
         * 
         * @return Estado do autômato após o término da leitura da fita.
         */
        private Estado lerFita(Estado estadoAtual, CabecaLeitura cabecaLeitura) {
            
            Simbolo simbolo = cabecaLeitura.lerSimbolo();
            
            try {            
            
                Estado novoEstado = funcaoTransicao.getNovoEstado(
                    estadoAtual.getNome(),
                    simbolo.getCaractere()
                ); 
                
                adicionarLog(
                    estadoAtual,
                    simbolo,
                    novoEstado,
                    cabecaLeitura.getParcelaNaoLidaDaFita()
                );           
                
                if (!cabecaLeitura.chegouAoFinalDaFita()) {
                    
                    return lerFita(novoEstado, cabecaLeitura);
                
                } else {
                
                    return novoEstado;
                
                }
                
            } catch (Exception ex) {
                
                adicionarLog(
                    estadoAtual,
                    simbolo,
                    null,
                    cabecaLeitura.getParcelaNaoLidaDaFita()
                );
                
                return null;
                
            }
            
        }

        
        /**
         * Processar a palavra de entrada gravada na fita.
         * 
         * @param fita Referência para a fita.
         * 
         * @return Estado do autômato após o término da leitura da fita.
         * 
         * @throws Exception Erro em caso de função de transição não definida. 
         */
        public Estado processarFita(Fita fita) throws Exception{
            
            CabecaLeitura cabecaLeitura = new CabecaLeitura(fita);        
            
            log.limpar(); 
            
            if (funcaoTransicao.totalmenteDefinida()) {
                
                Estado estado = estadoInicial;
                
                if (!cabecaLeitura.chegouAoFinalDaFita()) {
                    
                    estado = lerFita(estado, cabecaLeitura);
                    
                    log.setStatusParada(
                        estado != null ? 
                        StatusParada.NORMAL : 
                        StatusParada.TRANSICAO_INDEFINIDA
                    );
                    
                } else {
                    
                    log.setStatusParada(StatusParada.PALAVRA_VAZIA);
                
                }
                
                return estado;            
            
            } else {
            
                throw new Exception(
                    "Função de transição ainda não definida"
                );
            
            }
            
        }

        
    }

    
    /**
     * Constructor da classe.
     */
    public AutomatoFinito() {
        
        alfabeto = new Alfabeto();
        
        estados = new Estados();
        
        funcaoTransicao = new FuncaoTransicao(estados, alfabeto);
        
        estadoInicial = null;
        
        log = new Log();
    
    }


    /**
     * Obter o alfabeto de símbolos de entrada (Σ).
     * 
     * @return Alfabeto de símbolos de entrada (Σ).
     */
    public Alfabeto getAlfabeto() {
        
        return alfabeto;
    
    }

    
    /**
     * Obter o conjunto finito de estados (Q).
     * 
     * @return Conjunto finito de estados (Q).
     */
    public Estados getEstados() {
        
        return estados;
    
    }

    
    /**
     * Definir o estado inicial (q0).
     * 
     * @param estadoInicial Estado inicial (q0).
     */
    public void setEstadoInicial(Estado estadoInicial) {
    
        this.estadoInicial = estadoInicial;
    
    }

    
    /**
     * Obter o estado inicial (q0).
     * 
     * @return Estado inicial (q0).
     */
    public Estado getEstadoInicial() {
        
        return estadoInicial;
    
    }
    
    
    /**
     * Obter a função de transicao (δ).
     * 
     * @return Função de transicao (δ). 
     */
    public FuncaoTransicao getFuncaoTransicao() {
    
        return funcaoTransicao;
    
    }
    
    
    /**
     * Obter o log de processamento da palavra de entrada.
     * 
     * @return Log de processamento da palavra de entrada.
     */
    public Log getLog() {
        
        return log;
    
    }

    
    /**
     * Verificar se a palavra de entrada é aceita pelo Autômato Finito.
     * 
     * @param palavra Palavra de entrada.
     * 
     * @return Se true, a palavra de entrada é aceita, se false, é rejeitada.
     * 
     * @throws Exception Erro se a função de transição não estiver definida.
     */
    public boolean aceita(String palavra) throws Exception {
        
        Fita fita = new Fita(palavra);
        
        UnidadeControle unidadeControle = new UnidadeControle();
        
        Estado estadoFinal = unidadeControle.processarFita(fita);
        
        return estadoFinal != null ? estadoFinal.isTerminal() : false;          
    
    }
 
    
}