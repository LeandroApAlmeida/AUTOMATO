<h3>Simulador de Autômato Finito Determinístico</h3>

<br>

Neste projeto implemento um simulador de Autômato Finito Determinístico (AFD) usando Java Swing.

Um Autômato Finito Determinístico A é uma quíntupla, A = (Q, Σ, δ, q0, F) onde:

<br>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Σ: Conjunto finito de símbolos de entrada chamado Alfabeto.
  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Q: Conjunto finito de estados.
  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; δ: Função de transição (δ : Q × Σ → Q).
  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; q0: Estado inicial (q0 ∈ Q).
  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; F: Conjunto de estados terminais (F ⊆ Q).

<br>

Se w = a1a2 ... an é uma cadeia de símbolos sobre o alfabeto Σ, o autômato M aceita a cadeia w se e somente se existe uma sequência de estados, r0, r1, ..., rn, em Q com as seguintes condições:

<br>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; r0 = q0
  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ri+1 = δ(ri, ai+1), para i = 0, ..., n−1
  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; rn ∈ F.

<br>
  
A primeira condição afirma que a máquina se inicia no estado inicial q0. 

A segunda condição diz que, dado cada símbolo da entrada w, a máquina transita de estado em estado de acordo com a função de transição δ.

A terceira e última condição diz que a máquina aceita w se e somente se o último símbolo da entrada leva o autômato a parar em um estado f tal que f ∈ F. Caso contrário, diz-se que a máquina rejeita a entrada.

O conjunto de cadeias que M aceita é chamado Linguagem reconhecida por M e é simbolicamente representado por L(M).

<br>

![Tela 2](https://github.com/LeandroApAlmeida/AUTOMATO/assets/158072587/1b127fd0-dde3-4368-a2d6-a5bcf403ef84)

