package afd.gui;

import afd.automato.Simbolo;
import afd.automato.AutomatoFinito;
import afd.automato.Estado;
import afd.automato.Alfabeto;
import afd.automato.Estados;
import afd.log.ConversorLogHtml;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


/**
 * Tela do simulador de Autômato Finito Determinístico.
 */
public class TelaPrincipal extends javax.swing.JFrame {
    
    
    static {
        
        //Tradução das caixas de mensagens (JOptionPane).
        UIManager.put("OptionPane.yesButtonText", "Sim");
        UIManager.put("OptionPane.noButtonText", "Não");
        UIManager.put("OptionPane.cancelButtonText", "Cancelar");
        UIManager.put("OptionPane.okButtonText", "OK");
        
    }
    
    
    /**
     * Ponto de entrada do programa. Carrega a tela principal.
     * 
     * @param args argumentos (nenhum).
     */
    public static void main(String[] args) {
        
        java.awt.EventQueue.invokeLater(() -> {
            new TelaPrincipal().setVisible(true);
        });
        
    }
    
    
    /**
     * Modelo de Lista para um componente {@link JList}.
     */
    private class ModeloLista extends AbstractListModel {
        
        
        /**Itens da {@link JList}.*/
        String[] itens;

        
        /**
         * Constructor da classe.
         * 
         * @param lista Lista de itens da {@link JList}.
         */
        public ModeloLista(Iterable lista) {
            
            this.itens = toStringArray(lista);
        
        }
 
        
        @Override
        public int getSize() {
            
            return itens.length;
        
        }

        
        @Override
        public Object getElementAt(int i) {
            
            return itens[i];
        
        }
 
        
    }

    
    /**Separador de itens dos campos de edição na tela.*/
    private final char SEPARADOR = ',';
    
    /**Instância do autômato finito.*/
    private final AutomatoFinito automatoFinito;

    
    /**
     * Constructor da classe.
     */
    public TelaPrincipal() {
        
        automatoFinito = new AutomatoFinito();
        
        initComponents();
        
        jtfAlfabeto.requestFocus();
        
        jtFuncaoTransicao.setRowSelectionAllowed(false);
        jtFuncaoTransicao.setColumnSelectionAllowed(false);
        jtFuncaoTransicao.getTableHeader().setReorderingAllowed(false);
        
        configurarMenus();
        
    }

    
    /**
     * Configurar os menus suspensos das listas de alfabeto e de estados.
     */
    private void configurarMenus() {
        
        boolean simbolosSelecionados = !jlAlfabeto.getSelectedValuesList().isEmpty();
        boolean estadosNTerminaisSelecionados = !jlEstadosNaoTerminais.getSelectedValuesList().isEmpty();
        boolean estadosTerminaisSelecionados = !jlEstadosTerminais.getSelectedValuesList().isEmpty();
        boolean existeEstados = automatoFinito.getEstados().tamanho() > 0;
        boolean existeAlfabeto = automatoFinito.getAlfabeto().tamanho() > 0;
        
        //Menu Alfabeto.
        
        jmiRemoverSimbolos.setEnabled(simbolosSelecionados);
        jmiLimparAlfabeto.setEnabled(existeAlfabeto);
        
        //Menu Estados Não-Terminais.
        
        jmiRemoverEstadosNaoTerminais.setEnabled(estadosNTerminaisSelecionados);
        jmiDefinirTerminal.setEnabled(estadosNTerminaisSelecionados);
        jbDefinirTerminais.setEnabled(estadosNTerminaisSelecionados);
        jmiLimparEstados.setEnabled(existeEstados);
        
        //Menu EstadosTerminais.
        
        jmiRemoverTerminais.setEnabled(estadosTerminaisSelecionados);
        jmiDefinirNaoTerminal.setEnabled(estadosTerminaisSelecionados);
        jbDefinirNaoTerminais.setEnabled(estadosTerminaisSelecionados);
        jmiLimparEstados2.setEnabled(existeEstados);
        
    }  
    
    
    /**
     * Converter uma lista de objetos em uma lista de String.
     * 
     * @param lista Lista de objetos.
     * 
     * @return Lista de String.
     */
    private String[] toStringArray(Iterable lista) {
        
        ArrayList<String> l = new ArrayList<>();
        
        for (Object item : lista) l.add(item.toString()); 
        
        String[] itens = new String[l.size()];
        
        for (int idx = 0; idx < l.size(); idx++) itens[idx] = l.get(idx);
        
        return itens;
    
    }

    
    /**
     * Listar o alfabeto na {@link JList} jlAlfabeto.
     */
    private void listarAlfabeto() {
        
        jlAlfabeto.removeAll();
        
        jlAlfabeto.setModel(new ModeloLista(automatoFinito.getAlfabeto()));
        
    }

    
    /**
     * Listar os estados não-terminais na {@link JList} jlEstadosNaoTerminais.
     */
    private void listarEstadosNaoTerminais() {
        
        jlEstadosNaoTerminais.removeAll();
        
        jlEstadosNaoTerminais.setModel(
            new ModeloLista(automatoFinito.getEstados().getNaoTerminais())
        );
    
    }

    
    /**
     * Listar os estados terminais na {@link JList} jlEstadosTerminais.
     */
    private void listarEstadosTerminais(){
        
        jlEstadosTerminais.removeAll();
        
        jlEstadosTerminais.setModel(
            new ModeloLista(automatoFinito.getEstados().getTerminais())
        );
        
    }

    
    /**
     * Preencher a {@link JComboBox} jcbEstadoInicial com os estados e selecionar
     * o estado inicial, conforme definido no autômato.
     */
    private void selecionarEstadoInicial() {
        
        jcbEstadoInicial.removeAllItems();
        
        jcbEstadoInicial.setModel(
            new DefaultComboBoxModel(
                toStringArray(automatoFinito.getEstados())
            )
        );
        
        if (automatoFinito.getEstadoInicial() != null) {
            jcbEstadoInicial.setSelectedItem(
                automatoFinito.getEstadoInicial().toString()
            );
        }
        
    }

    
    /**
     * Listar os estados terminais e não-terminais.
     */
    private void listarEstados() {
        
        listarEstadosNaoTerminais();
        
        listarEstadosTerminais();
    
    }

    
    /**
     * Gerar a tabela da função de transição para edição.
     */
    private void gerarTabelaFuncaoTransicao() {
        
        Alfabeto alfabeto = automatoFinito.getAlfabeto();
        
        final Estados estados = automatoFinito.getEstados();
        
        if (alfabeto.tamanho() <= 0 && estados.tamanho() <= 0) {
            
            jtFuncaoTransicao.setModel(
                new javax.swing.table.DefaultTableModel()
            );
            
            return;
            
        }        
        
        // Modelo de componente JTextField que vai ser exibido nas células não
        // Editáveis (células aonde está sendo exibidos os símbolos no topo e 
        // aonde estão sendo exibidos os estados na primeira coluna da tabela.
         
        class JTextFieldCelulasFixas extends JTextField {
            
            public JTextFieldCelulasFixas(String text) {
                
                super(text);
                
                setBorder(
                    javax.swing.BorderFactory.
                    createBevelBorder(javax.swing.border.BevelBorder.RAISED)
                );
                
                setBackground(new Color(249, 250, 253));                
                setFont(new java.awt.Font("Tahoma", 1, 12));                
                setHorizontalAlignment(SwingConstants.CENTER);
                
            }
            
        }
        
        // Modelo de componente JTextField que vai ser exibido nas células 
        // editáveis (todas as células em colunas que não a primeira).

        class JTextFieldCelulasEditaveis extends JTextField {
            
            public JTextFieldCelulasEditaveis(Object text) {
                
                super(text != null ? (String) text : null);
                
                setBorder(null);
                setForeground(Color.BLUE);
                setFont(new java.awt.Font("Tahoma", 1, 12));
                setHorizontalAlignment(SwingConstants.CENTER);
                setFocusable(true);
                
            }
            
        }
        
        // Modelo de componente JComboBox que vai ser usado para editar as 
        // células editáveis. Esta JComboBox traz listados todos os estados do
        // autômato.
         
        class JComboboxEstados extends JComboBox<String> {
            
            public JComboboxEstados() {
                
                setModel(new DefaultComboBoxModel(toStringArray(estados)));
                
            }
            
        }
        
        // Cria o vetor string com os títulos das colunas. A primeira posição do
        // vetor recebe um símbolo de "", porque esta coluna vai gerar a 
        // coluna dos estados fixos e não têm título.
         
        String[] tColunas = new String[alfabeto.tamanho() + 1];
        String[] itens = toStringArray(alfabeto);
        
        tColunas[0] = "";
        
        System.arraycopy(itens, 0, tColunas, 1, itens.length);
        
        // Cria o vetor boolean com o estado de editável de cada coluna (somente
        // a primeira coluna é não-editável).
        
        final boolean[] eColunas = new boolean[alfabeto.tamanho() + 1];
        
        eColunas[0] = false;
        
        for (int i = 1; i < eColunas.length; i++) eColunas[i] = true;
        
        // Cria a estrutura da JTable (Estados à esquerda na primeira coluna).
        
        Object[][] eJTable = new Object[estados.tamanho()][alfabeto.tamanho() + 1];
        
        for (int i = 0; i < estados.tamanho(); i++) {
            eJTable[i][0] = estados.get(i).getNome();
        }
        
        //Cria o modelo da JTable.
        
        jtFuncaoTransicao.setModel(
            new DefaultTableModel(eJTable,tColunas){
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return eColunas[columnIndex];
                }
            }
        );
        
        // Configura os componentes de edição e exibição de cada coluna da
        // JTable.
        
        for (int i = 0; i < jtFuncaoTransicao.getColumnModel().getColumnCount(); i++) {
            
            // Atribui a JComboBox como componente de edição da célula.
            
            jtFuncaoTransicao.getColumnModel().getColumn(i).setCellEditor(
                new DefaultCellEditor(new JComboboxEstados())
            );
            
            // Atribui o componente de exibição dos cabeçalhos das colunas 
            // (símbolos).
            
            jtFuncaoTransicao.getTableHeader().getColumnModel().getColumn(i).
            setHeaderRenderer(
                new TableCellRenderer() {
                    //Redefine o componente de exibição...
                    @Override
                    public Component getTableCellRendererComponent(
                        JTable table,
                        Object value,
                        boolean isSelected,
                        boolean hasFocus,
                        int row,
                        int column
                    ) {
                        return new JTextFieldCelulasFixas(value.toString());
                    }
                }
            );
            
            if (i == 0) {
                
                // Atribui o componente de exibição à primeira coluna que contém
                // os estados, um em cada linha. Esta coluna não é editável.
                
                jtFuncaoTransicao.getColumnModel().getColumn(0).setCellRenderer(
                    new TableCellRenderer() {
                        @Override
                        public Component getTableCellRendererComponent(
                            JTable table,
                            Object value,
                            boolean isSelected,
                            boolean hasFocus,
                            int row,
                            int column
                        ) {
                            return new JTextFieldCelulasFixas(value.toString());
                        }
                    }
                );
                
            } else {
                
                //Redefine o componente de exibição da célula.
                
                jtFuncaoTransicao.getColumnModel().getColumn(i).setCellRenderer(
                    new TableCellRenderer() {
                        @Override
                        public Component getTableCellRendererComponent(
                            JTable table,
                            Object value,
                            boolean isSelected,
                            boolean hasFocus,
                            int row,
                            int column
                        ) {
                            return new JTextFieldCelulasEditaveis(value);
                        }
                    }
                );
                
            }
            
        }
        
        // O alfabeto tendo 9 ou mais símbolos força a JTable a criar uma Barra
        // de Rolagem na horizontal.
        
        if (alfabeto.tamanho() < 9) {
            jtFuncaoTransicao.setAutoResizeMode(
                javax.swing.JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS
            );
        } else {
            jtFuncaoTransicao.setAutoResizeMode(
                javax.swing.JTable.AUTO_RESIZE_OFF
            );
        }
        
        jtpResultado.setText("");
        
    }
    
    
    /**
     * Gerar a função de transição do Autômato Finito a partir dos dados na
     * {@link JTable} jtFuncaoTransicao.
     * 
     * @throws Exception Erro ao validar dados na {@link JTable}. 
     */
    private void gerarFuncaoTransicao() throws Exception {
        
        if (automatoFinito.getAlfabeto().tamanho() <= 0) {
            throw new Exception("Não há um alfabeto definido.");
        }        
        
        if (automatoFinito.getEstados().tamanho() <= 0) {
            throw new Exception("Não há um conjunto de estados definido.");
        }        
        
        automatoFinito.getFuncaoTransicao().limpar();        
        
        String estadoInicial;
        String novoEstado;
        char simboloLido;      
        
        for (int i = 0; i < jtFuncaoTransicao.getModel().getRowCount(); i++) {
        
            for (int j = 1; j < jtFuncaoTransicao.getModel().getColumnCount(); j++) {
                
                estadoInicial = (String)jtFuncaoTransicao.getModel().getValueAt(i, 0);
                simboloLido = jtFuncaoTransicao.getModel().getColumnName(j).charAt(0);
                novoEstado = (String)jtFuncaoTransicao.getModel().getValueAt(i, j);                                 
                
                automatoFinito.getFuncaoTransicao().setTransicao(
                    estadoInicial,
                    simboloLido,
                    novoEstado
                );                
            
            }
            
        }
        
    }
    
    
    /**
     * Inserir os símbolos digitados no Autômato Finito.
     */
    private void inserirSimbolos() {
        
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        
        //Recupera o texto contendo os símbolos a serem inseridos.
        
        String cadeia = jtfAlfabeto.getText();
        
        if (!cadeia.equals("")) { 
            
            jtfAlfabeto.setText(""); 
            
            boolean inseriu = false;
            
            //Lista de símbolos inválidos.            
            ArrayList<String> invalidos = new ArrayList<>();
            
            if (cadeia.length() == 1) {
                
                // Só tem um caractere digitado.
                
                if (!automatoFinito.getAlfabeto().inserir(new Simbolo(cadeia.charAt(0)))) {
                    invalidos.add(cadeia);
                }
                
            } else {
                
                // Múltiplos caracteres separados por vírgula.
                
                String[] simbolos = cadeia.split(
                    new String(new char[]{SEPARADOR})
                ); 
                
                for (String simbolo : simbolos) {
                    
                    inseriu = false;
                    
                    if (simbolo.length() == 1) {
                        inseriu = automatoFinito.getAlfabeto().inserir(
                            new Simbolo(simbolo.charAt(0))
                        );                   
                    }
                    
                    if (!inseriu) {
                        //Ocorre quando há ocorrências do tipo aa,sd,ab, etc.
                        if (!invalidos.contains(simbolo)) {
                            invalidos.add(simbolo);
                        }
                    }
                    
                }
                
            }
            
            gerarTabelaFuncaoTransicao();            
            listarAlfabeto();            
            configurarMenus();
            
            // Erro ocorrido na inserção dos símbolos. Exibe a mensagem de erro.
            
            if (!invalidos.isEmpty()) {
                
                StringBuilder s = new StringBuilder();
                
                s.append("As seguintes construções foram consideradas inválidas:\n\n");
                s.append("[");
                s.append(invalidos.get(0));
                
                for (int idx = 1; idx < invalidos.size(); idx++) {
                    s.append(", ");
                    s.append(invalidos.get(idx));                    
                }
                
                s.append("]");
                s.append("\n\n");
                s.append("Isso ocorreu por ter tentado inserir uma sequencia de caracteres,\n");
                s.append("como aa, ab, ca, por exemplo, em vez de um caracter simples ou por\n");
                s.append("ter tentado inserir um símbolo já inserido");
                
                JOptionPane.showMessageDialog(
                    this,
                    s.toString(),
                    "Erro ao inserir símbolos",
                    JOptionPane.WARNING_MESSAGE
                );
                
            }
            
        }
        
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        
    }

    
    /**
     * Inserir os estados digitados no Autômato Finito.
     */
    private void inserirEstados() {
        
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        
        String cadeia = jtfEstados.getText();
        
        if (!cadeia.equals("")) {
            
            // Lista de estados inválidos.
            ArrayList<String> invalidos = new ArrayList<>();
            
            jtfEstados.setText("");            
            
            String[] estados = cadeia.split(new String(new char[]{SEPARADOR}));           
            
            for (String estado : estados) {
                if (!automatoFinito.getEstados().inserir(new Estado(estado))) {                 
                    if (!invalidos.contains(estado)) {
                        invalidos.add(estado);
                    }    
                }    
            }
            
            gerarTabelaFuncaoTransicao();
            listarEstadosNaoTerminais();            
            selecionarEstadoInicial();            
            configurarMenus();
            
            // Erro ocorrido na inserção dos estados. Exibe a mensagem de erro.
            
            if (!invalidos.isEmpty()) {
                
                StringBuilder s = new StringBuilder();
                
                s.append("As seguintes construções foram consideradas inválidas:\n\n");
                s.append("[");
                s.append(invalidos.get(0));
                
                for (int idx = 1; idx < invalidos.size(); idx++) {
                    s.append(", ");
                    s.append(invalidos.get(idx));                    
                }
                
                s.append("]");
                s.append("\n\n");
                s.append("Isso ocorreu por ter tentado inserir um estado com a sintaxe\n");
                s.append("incorreta ou que já tenha sido inserido.");
                
                JOptionPane.showMessageDialog(
                    this,
                    s.toString(),
                    "Erro ao inserir estados",
                    JOptionPane.WARNING_MESSAGE
                );
                
            }
            
        }
        
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        
    }

    
    /**
     * Remover os símbolos selecionados do alfabeto.
     */
    private void removerSimbolos() {
        
        if (jlAlfabeto.getSelectedIndices().length > 0) {
            
            List<String> selecionados = jlAlfabeto.getSelectedValuesList();
            
            int opt = JOptionPane.showConfirmDialog(
                this,
                "Confirma a remoção dos símbolos selecionados?",
                "Atenção!",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            
            if (opt == JOptionPane.YES_OPTION) {
                
                this.setCursor(new Cursor(Cursor.WAIT_CURSOR));                
                
                for (String object : selecionados) {
                    automatoFinito.getAlfabeto().remover(
                        Character.valueOf(object.charAt(0))
                    );
                }                  
                
                listarAlfabeto();
                gerarTabelaFuncaoTransicao();
                configurarMenus();                
                
                this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                
            }
            
        }
        
    }
    
    
    /**
     * Remover os estados selecionados do conjunto de estados (Q).
     * 
     * @param jList Lista de estados.
     */
    private void removerEstados(JList<String> jList) {
        
        if (jList.getSelectedIndices().length > 0) {
            
            List<String> selecionados = jList.getSelectedValuesList();
            
            int opt = JOptionPane.showConfirmDialog(
                this,
                "Confirma a remoção dos estados selecionados?",
                "Atenção!",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            
            if (opt == JOptionPane.YES_OPTION) {
                
                this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                
                for (String estado : selecionados) automatoFinito.getEstados().remover(estado);                
                
                listarEstadosNaoTerminais();
                listarEstadosTerminais();
                selecionarEstadoInicial();
                gerarTabelaFuncaoTransicao();
                configurarMenus();
                
                this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                
            }
            
        }
        
    }

    
    /**
     * Remover estados não-terminais.
     */
    private void removerEstadosNaoTerminais() {
        
        removerEstados(jlEstadosNaoTerminais);
        
    }

    
    /**
     * Remover estados terminais.
     */
    private void removerEstadosTerminais() {
        
        removerEstados(jlEstadosTerminais);
        
    }

    
    /**
     * Remover todos os símbolos do alfabeto.
     */
    private void limparAlfabeto() {
        
        int opt = JOptionPane.showConfirmDialog(
            this,
            "Limpar o Alfabeto?",
            "Atenção!",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        ); 
        
        if (opt == JOptionPane.YES_OPTION) {
            
            this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            
            automatoFinito.getAlfabeto().limpar();
            
            listarAlfabeto();
            gerarTabelaFuncaoTransicao();
            configurarMenus();
            
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        
        }
    
    }

    
    /**
     * Remover todos os estados do conjunto de estados.
     */
    private void limparEstados() {
        
        int opt = JOptionPane.showConfirmDialog(this,
            "Limpar o Conjunto dos Estados?",
            "Atenção!",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (opt == JOptionPane.YES_OPTION) {
            
            this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            
            automatoFinito.getEstados().limpar();
            
            listarEstados();
            selecionarEstadoInicial();
            gerarTabelaFuncaoTransicao();
            configurarMenus();
            
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        
        }
    
    }   
    
    
    /**
     * Definir estados selecionados da JList como terminais ou não-terminais.
     * 
     * @param jList Lista de estados.
     * 
     * @param isTerminal Status de estado terminal.
     */
    private void definirEstados(JList<String> jList, boolean isTerminal) {
        
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));        
        
        List<String> selecionados = jList.getSelectedValuesList();        
        
        for (String estado : selecionados) {
            automatoFinito.getEstados().get(estado).setTerminal(isTerminal);
        }
        
        listarEstados();
        configurarMenus();
        
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));    
    
    }

    
    /**
     * Definir os estados selecionados na JList como terminais.
     */
    private void definirTerminais() {
        
        definirEstados(jlEstadosNaoTerminais, true);
    
    }

    
    /**
     * Definir os estados selecionados na JList como não-terminais.
     */
    private void definirNaoTerminais() {
        
        definirEstados(jlEstadosTerminais, false);        
    
    }

    
    /**
     * Definir o estado inicial do Autômato Finito com base no estado 
     * selecionado no {@link JComboBox} jcbEstadoInicial.
     */
    private void definirEstadoInicial() {
        
        if (jcbEstadoInicial.getSelectedIndex() >= 0) {
    
            String estado = (String) jcbEstadoInicial.getSelectedItem();
            
            automatoFinito.setEstadoInicial(
                automatoFinito.getEstados().get(estado)
            );
        
        }
    
    }

    
    /**
     * Verificar a palavra digitada no campo.
     */
    private void verificarPalavra() {
        
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        
        try {
        
            gerarFuncaoTransicao();
            
            definirEstadoInicial();
            
            String palavra = jtfPalavra.getText();
            
            boolean aceita = automatoFinito.aceita(palavra);
            
            ConversorLogHtml conversor = new ConversorLogHtml(
                automatoFinito,
                aceita
            );

            jtpResultado.setText(conversor.converterParaHtml());
        
        } catch (Exception ex) {
            
            JOptionPane.showMessageDialog(
                this,
                ex.getMessage(),
                "Erro ao gerar transições!",
                JOptionPane.ERROR_MESSAGE
            );
            
        } 
        
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));   
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jppmAlfabeto = new javax.swing.JPopupMenu();
        jmiRemoverSimbolos = new javax.swing.JMenuItem();
        js1 = new javax.swing.JPopupMenu.Separator();
        jmiLimparAlfabeto = new javax.swing.JMenuItem();
        jppmEstadosNaoTerminais = new javax.swing.JPopupMenu();
        jmiRemoverEstadosNaoTerminais = new javax.swing.JMenuItem();
        js2 = new javax.swing.JPopupMenu.Separator();
        jmiDefinirTerminal = new javax.swing.JMenuItem();
        js3 = new javax.swing.JPopupMenu.Separator();
        jmiLimparEstados = new javax.swing.JMenuItem();
        jppmEstadosTerminais = new javax.swing.JPopupMenu();
        jmiRemoverTerminais = new javax.swing.JMenuItem();
        js4 = new javax.swing.JPopupMenu.Separator();
        jmiDefinirNaoTerminal = new javax.swing.JMenuItem();
        js5 = new javax.swing.JPopupMenu.Separator();
        jmiLimparEstados2 = new javax.swing.JMenuItem();
        jppmCadeia = new javax.swing.JPopupMenu();
        jmiLimparCadeia = new javax.swing.JMenuItem();
        js6 = new javax.swing.JPopupMenu.Separator();
        jmiVerificarCadeia = new javax.swing.JMenuItem();
        jpAlfabeto = new javax.swing.JPanel();
        jtfAlfabeto = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jspAlfabeto = new javax.swing.JScrollPane();
        jlAlfabeto = new javax.swing.JList();
        jpEstados = new javax.swing.JPanel();
        jtfEstados = new javax.swing.JTextField();
        jspEstados = new javax.swing.JScrollPane();
        jlEstadosNaoTerminais = new javax.swing.JList();
        jbDefinirTerminais = new javax.swing.JButton();
        jbDefinirNaoTerminais = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jlEstadosTerminais = new javax.swing.JList();
        jcbEstadoInicial = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtFuncaoTransicao = new javax.swing.JTable();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtpResultado = new javax.swing.JEditorPane();
        jtfPalavra = new javax.swing.JTextField();
        jbVerificarPalavra = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        jmiRemoverSimbolos.setText("Remover");
        jmiRemoverSimbolos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRemoverSimbolosActionPerformed(evt);
            }
        });
        jppmAlfabeto.add(jmiRemoverSimbolos);
        jppmAlfabeto.add(js1);

        jmiLimparAlfabeto.setText("Limpar Alfabeto");
        jmiLimparAlfabeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiLimparAlfabetoActionPerformed(evt);
            }
        });
        jppmAlfabeto.add(jmiLimparAlfabeto);

        jmiRemoverEstadosNaoTerminais.setText("Remover");
        jmiRemoverEstadosNaoTerminais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRemoverEstadosNaoTerminaisActionPerformed(evt);
            }
        });
        jppmEstadosNaoTerminais.add(jmiRemoverEstadosNaoTerminais);
        jppmEstadosNaoTerminais.add(js2);

        jmiDefinirTerminal.setText("Definir como Terminal");
        jmiDefinirTerminal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiDefinirTerminalActionPerformed(evt);
            }
        });
        jppmEstadosNaoTerminais.add(jmiDefinirTerminal);
        jppmEstadosNaoTerminais.add(js3);

        jmiLimparEstados.setText("Limpar Conjunto dos Estados");
        jmiLimparEstados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiLimparEstadosActionPerformed(evt);
            }
        });
        jppmEstadosNaoTerminais.add(jmiLimparEstados);

        jmiRemoverTerminais.setText("Remover");
        jmiRemoverTerminais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRemoverTerminaisActionPerformed(evt);
            }
        });
        jppmEstadosTerminais.add(jmiRemoverTerminais);
        jppmEstadosTerminais.add(js4);

        jmiDefinirNaoTerminal.setText("Definir como Não-Terminal");
        jmiDefinirNaoTerminal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiDefinirNaoTerminalActionPerformed(evt);
            }
        });
        jppmEstadosTerminais.add(jmiDefinirNaoTerminal);
        jppmEstadosTerminais.add(js5);

        jmiLimparEstados2.setText("Limpar Conjunto dos Estados");
        jmiLimparEstados2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiLimparEstados2ActionPerformed(evt);
            }
        });
        jppmEstadosTerminais.add(jmiLimparEstados2);

        jmiLimparCadeia.setText("Limpar");
        jmiLimparCadeia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiLimparCadeiaActionPerformed(evt);
            }
        });
        jppmCadeia.add(jmiLimparCadeia);
        jppmCadeia.add(js6);

        jmiVerificarCadeia.setText("Verificar");
        jmiVerificarCadeia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiVerificarCadeiaActionPerformed(evt);
            }
        });
        jppmCadeia.add(jmiVerificarCadeia);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SIMULADOR DE AUTÔMATO FINITO DETERMINÍSTICO");
        setName("TelaPrincipal"); // NOI18N

        jpAlfabeto.setBorder(javax.swing.BorderFactory.createTitledBorder(" Alfabeto ( ∑ ) "));
        jpAlfabeto.setPreferredSize(new java.awt.Dimension(200, 200));

        jtfAlfabeto.setNextFocusableComponent(jlAlfabeto);
        jtfAlfabeto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfAlfabetoFocusLost(evt);
            }
        });
        jtfAlfabeto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfAlfabetoKeyPressed(evt);
            }
        });

        jLabel1.setText("Símbolos. (Ex.: a,b,c)");

        jlAlfabeto.setToolTipText("");
        jlAlfabeto.setComponentPopupMenu(jppmAlfabeto);
        jlAlfabeto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jlAlfabeto.setInheritsPopupMenu(true);
        jlAlfabeto.setNextFocusableComponent(jtfEstados);
        jlAlfabeto.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlAlfabetoValueChanged(evt);
            }
        });
        jlAlfabeto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jlAlfabetoKeyPressed(evt);
            }
        });
        jspAlfabeto.setViewportView(jlAlfabeto);

        javax.swing.GroupLayout jpAlfabetoLayout = new javax.swing.GroupLayout(jpAlfabeto);
        jpAlfabeto.setLayout(jpAlfabetoLayout);
        jpAlfabetoLayout.setHorizontalGroup(
            jpAlfabetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpAlfabetoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpAlfabetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jspAlfabeto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfAlfabeto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpAlfabetoLayout.setVerticalGroup(
            jpAlfabetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAlfabetoLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addGap(9, 9, 9)
                .addComponent(jtfAlfabeto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspAlfabeto)
                .addContainerGap())
        );

        jpEstados.setBorder(javax.swing.BorderFactory.createTitledBorder(" Estados ( Q ) "));

        jtfEstados.setNextFocusableComponent(jlEstadosNaoTerminais);
        jtfEstados.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfEstadosFocusLost(evt);
            }
        });
        jtfEstados.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfEstadosKeyPressed(evt);
            }
        });

        jlEstadosNaoTerminais.setComponentPopupMenu(jppmEstadosNaoTerminais);
        jlEstadosNaoTerminais.setNextFocusableComponent(jbDefinirTerminais);
        jlEstadosNaoTerminais.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlEstadosNaoTerminaisMouseClicked(evt);
            }
        });
        jlEstadosNaoTerminais.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jlEstadosNaoTerminaisKeyPressed(evt);
            }
        });
        jlEstadosNaoTerminais.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlEstadosNaoTerminaisValueChanged(evt);
            }
        });
        jspEstados.setViewportView(jlEstadosNaoTerminais);

        jbDefinirTerminais.setText(">");
        jbDefinirTerminais.setNextFocusableComponent(jcbEstadoInicial);
        jbDefinirTerminais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDefinirTerminaisActionPerformed(evt);
            }
        });

        jbDefinirNaoTerminais.setText("<");
        jbDefinirNaoTerminais.setNextFocusableComponent(jtFuncaoTransicao);
        jbDefinirNaoTerminais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDefinirNaoTerminaisActionPerformed(evt);
            }
        });

        jlEstadosTerminais.setComponentPopupMenu(jppmEstadosTerminais);
        jlEstadosTerminais.setNextFocusableComponent(jbDefinirNaoTerminais);
        jlEstadosTerminais.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlEstadosTerminaisMouseClicked(evt);
            }
        });
        jlEstadosTerminais.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlEstadosTerminaisValueChanged(evt);
            }
        });
        jlEstadosTerminais.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jlEstadosTerminaisKeyPressed(evt);
            }
        });
        jScrollPane5.setViewportView(jlEstadosTerminais);

        jcbEstadoInicial.setNextFocusableComponent(jlEstadosTerminais);
        jcbEstadoInicial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcbEstadoInicialFocusLost(evt);
            }
        });
        jcbEstadoInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbEstadoInicialActionPerformed(evt);
            }
        });

        jLabel5.setText("Estados Não-Terminais");

        jLabel6.setText("Estados Terminais ( F )");

        jLabel3.setText("Estados. (Ex.: q0,q1,q2,q3)");

        jLabel7.setText("Estado Inicial ( q0 )");

        javax.swing.GroupLayout jpEstadosLayout = new javax.swing.GroupLayout(jpEstados);
        jpEstados.setLayout(jpEstadosLayout);
        jpEstadosLayout.setHorizontalGroup(
            jpEstadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpEstadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpEstadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpEstadosLayout.createSequentialGroup()
                        .addGroup(jpEstadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpEstadosLayout.createSequentialGroup()
                        .addGroup(jpEstadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jspEstados, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jtfEstados))
                        .addGap(8, 8, 8)
                        .addGroup(jpEstadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbDefinirTerminais)
                            .addComponent(jbDefinirNaoTerminais))
                        .addGap(8, 8, 8)))
                .addGroup(jpEstadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbEstadoInicial, 0, 167, Short.MAX_VALUE)
                    .addGroup(jpEstadosLayout.createSequentialGroup()
                        .addGroup(jpEstadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addGap(0, 50, Short.MAX_VALUE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpEstadosLayout.setVerticalGroup(
            jpEstadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpEstadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpEstadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpEstadosLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(8, 8, 8)
                        .addComponent(jtfEstados, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpEstadosLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(8, 8, 8)
                        .addComponent(jcbEstadoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpEstadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpEstadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpEstadosLayout.createSequentialGroup()
                        .addComponent(jbDefinirTerminais)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbDefinirNaoTerminais)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jspEstados, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                    .addComponent(jScrollPane5))
                .addContainerGap())
        );

        jLabel4.setText("Função de Transição ( δ )");

        jScrollPane2.setAutoscrolls(true);

        jtFuncaoTransicao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jtFuncaoTransicao.setCellSelectionEnabled(true);
        jtFuncaoTransicao.setFillsViewportHeight(true);
        jtFuncaoTransicao.setFocusable(false);
        jtFuncaoTransicao.setNextFocusableComponent(jtfPalavra);
        jtFuncaoTransicao.setSelectionBackground(new java.awt.Color(255, 255, 51));
        jScrollPane2.setViewportView(jtFuncaoTransicao);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("  Palavra ( w )  "));

        jtpResultado.setEditable(false);
        jtpResultado.setContentType("text/html"); // NOI18N
        jtpResultado.setFocusable(false);
        jScrollPane1.setViewportView(jtpResultado);

        jtfPalavra.setComponentPopupMenu(jppmCadeia);
        jtfPalavra.setNextFocusableComponent(jbVerificarPalavra);
        jtfPalavra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfPalavraActionPerformed(evt);
            }
        });
        jtfPalavra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfPalavraKeyPressed(evt);
            }
        });

        jbVerificarPalavra.setText("Verificar");
        jbVerificarPalavra.setNextFocusableComponent(jtfAlfabeto);
        jbVerificarPalavra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbVerificarPalavraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jtfPalavra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbVerificarPalavra)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfPalavra, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbVerificarPalavra, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jpAlfabeto, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jpEstados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpAlfabeto, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                            .addComponent(jpEstados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(17, 17, 17)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jtfAlfabetoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfAlfabetoKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
            inserirSimbolos();
        }
    }//GEN-LAST:event_jtfAlfabetoKeyPressed

    private void jmiRemoverSimbolosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRemoverSimbolosActionPerformed
        removerSimbolos();
    }//GEN-LAST:event_jmiRemoverSimbolosActionPerformed

    private void jtfEstadosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfEstadosKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
            inserirEstados();
        }
    }//GEN-LAST:event_jtfEstadosKeyPressed

    private void jmiRemoverEstadosNaoTerminaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRemoverEstadosNaoTerminaisActionPerformed
        removerEstadosNaoTerminais();
    }//GEN-LAST:event_jmiRemoverEstadosNaoTerminaisActionPerformed

    private void jmiDefinirTerminalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiDefinirTerminalActionPerformed
        definirTerminais();
    }//GEN-LAST:event_jmiDefinirTerminalActionPerformed

    private void jbDefinirTerminaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDefinirTerminaisActionPerformed
        definirTerminais();
    }//GEN-LAST:event_jbDefinirTerminaisActionPerformed

    private void jmiRemoverTerminaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRemoverTerminaisActionPerformed
        removerEstadosTerminais();
    }//GEN-LAST:event_jmiRemoverTerminaisActionPerformed

    private void jmiDefinirNaoTerminalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiDefinirNaoTerminalActionPerformed
        definirNaoTerminais();
    }//GEN-LAST:event_jmiDefinirNaoTerminalActionPerformed

    private void jbDefinirNaoTerminaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDefinirNaoTerminaisActionPerformed
        definirNaoTerminais();
    }//GEN-LAST:event_jbDefinirNaoTerminaisActionPerformed

    private void jbVerificarPalavraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVerificarPalavraActionPerformed
        verificarPalavra();
    }//GEN-LAST:event_jbVerificarPalavraActionPerformed

    private void jtfAlfabetoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfAlfabetoFocusLost
        inserirSimbolos();
        if (jlAlfabeto.getModel().getSize() > 0) {
            jlAlfabeto.setSelectedIndex(0);
        }
    }//GEN-LAST:event_jtfAlfabetoFocusLost

    private void jtfEstadosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfEstadosFocusLost
        inserirEstados();
        if (jlEstadosNaoTerminais.getModel().getSize() > 0) {
            jlEstadosNaoTerminais.setSelectedIndex(0);
        }
    }//GEN-LAST:event_jtfEstadosFocusLost

    private void jtfPalavraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfPalavraActionPerformed
        verificarPalavra();
    }//GEN-LAST:event_jtfPalavraActionPerformed

    private void jlAlfabetoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jlAlfabetoKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_DELETE){
            removerSimbolos();
        }
    }//GEN-LAST:event_jlAlfabetoKeyPressed

    private void jlEstadosNaoTerminaisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jlEstadosNaoTerminaisKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_DELETE){
            removerEstadosNaoTerminais();
        } else if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            definirTerminais();
        }
    }//GEN-LAST:event_jlEstadosNaoTerminaisKeyPressed

    private void jlEstadosTerminaisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jlEstadosTerminaisKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_DELETE){
            removerEstadosTerminais();
        } else if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            definirNaoTerminais();
        }
    }//GEN-LAST:event_jlEstadosTerminaisKeyPressed

    private void jmiLimparAlfabetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiLimparAlfabetoActionPerformed
        limparAlfabeto();
    }//GEN-LAST:event_jmiLimparAlfabetoActionPerformed

    private void jmiLimparEstadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiLimparEstadosActionPerformed
        limparEstados();
    }//GEN-LAST:event_jmiLimparEstadosActionPerformed

    private void jmiLimparEstados2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiLimparEstados2ActionPerformed
        limparEstados();
    }//GEN-LAST:event_jmiLimparEstados2ActionPerformed

    private void jmiLimparCadeiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiLimparCadeiaActionPerformed
        jtfPalavra.setText("");
    }//GEN-LAST:event_jmiLimparCadeiaActionPerformed

    private void jmiVerificarCadeiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiVerificarCadeiaActionPerformed
        verificarPalavra();
    }//GEN-LAST:event_jmiVerificarCadeiaActionPerformed

    private void jtfPalavraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfPalavraKeyPressed
        //Usando a tecla ESC, limpa o campo de texto da cadeia.
        if (evt.getKeyCode() == 0x1B) {
            jtfPalavra.setText("");
        }
    }//GEN-LAST:event_jtfPalavraKeyPressed

    private void jlAlfabetoValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlAlfabetoValueChanged
        configurarMenus();
    }//GEN-LAST:event_jlAlfabetoValueChanged

    private void jlEstadosNaoTerminaisValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlEstadosNaoTerminaisValueChanged
        configurarMenus();
    }//GEN-LAST:event_jlEstadosNaoTerminaisValueChanged

    private void jlEstadosTerminaisValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlEstadosTerminaisValueChanged
        configurarMenus();
    }//GEN-LAST:event_jlEstadosTerminaisValueChanged

    private void jcbEstadoInicialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcbEstadoInicialFocusLost
        if (jlEstadosTerminais.getModel().getSize() > 0) {
            jlEstadosTerminais.setSelectedIndex(0);
        }
    }//GEN-LAST:event_jcbEstadoInicialFocusLost

    private void jcbEstadoInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbEstadoInicialActionPerformed
        definirEstadoInicial();
    }//GEN-LAST:event_jcbEstadoInicialActionPerformed

private void jlEstadosNaoTerminaisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlEstadosNaoTerminaisMouseClicked
    if (evt.getClickCount() == 2) {
        definirTerminais();        
    }
}//GEN-LAST:event_jlEstadosNaoTerminaisMouseClicked

private void jlEstadosTerminaisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlEstadosTerminaisMouseClicked
    if (evt.getClickCount() == 2) {
        definirNaoTerminais();        
    }
}//GEN-LAST:event_jlEstadosTerminaisMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JButton jbDefinirNaoTerminais;
    private javax.swing.JButton jbDefinirTerminais;
    private javax.swing.JButton jbVerificarPalavra;
    private javax.swing.JComboBox jcbEstadoInicial;
    private javax.swing.JList jlAlfabeto;
    private javax.swing.JList jlEstadosNaoTerminais;
    private javax.swing.JList jlEstadosTerminais;
    private javax.swing.JMenuItem jmiDefinirNaoTerminal;
    private javax.swing.JMenuItem jmiDefinirTerminal;
    private javax.swing.JMenuItem jmiLimparAlfabeto;
    private javax.swing.JMenuItem jmiLimparCadeia;
    private javax.swing.JMenuItem jmiLimparEstados;
    private javax.swing.JMenuItem jmiLimparEstados2;
    private javax.swing.JMenuItem jmiRemoverEstadosNaoTerminais;
    private javax.swing.JMenuItem jmiRemoverSimbolos;
    private javax.swing.JMenuItem jmiRemoverTerminais;
    private javax.swing.JMenuItem jmiVerificarCadeia;
    private javax.swing.JPanel jpAlfabeto;
    private javax.swing.JPanel jpEstados;
    private javax.swing.JPopupMenu jppmAlfabeto;
    private javax.swing.JPopupMenu jppmCadeia;
    private javax.swing.JPopupMenu jppmEstadosNaoTerminais;
    private javax.swing.JPopupMenu jppmEstadosTerminais;
    private javax.swing.JPopupMenu.Separator js1;
    private javax.swing.JPopupMenu.Separator js2;
    private javax.swing.JPopupMenu.Separator js3;
    private javax.swing.JPopupMenu.Separator js4;
    private javax.swing.JPopupMenu.Separator js5;
    private javax.swing.JPopupMenu.Separator js6;
    private javax.swing.JScrollPane jspAlfabeto;
    private javax.swing.JScrollPane jspEstados;
    private javax.swing.JTable jtFuncaoTransicao;
    private javax.swing.JTextField jtfAlfabeto;
    private javax.swing.JTextField jtfEstados;
    private javax.swing.JTextField jtfPalavra;
    private javax.swing.JEditorPane jtpResultado;
    // End of variables declaration//GEN-END:variables

}
