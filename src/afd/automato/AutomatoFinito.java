package afd.automato;

import afd.log.Log;
import afd.log.StatusParada;


/**
 * Classe que representa um Autômato Finito Determinístico, ou simplesmente 
 * Autômato Finito. O Autômato Finito é representado como uma máquina com as 
 * seguintes partes constituintes:
 * 
 * <pre style="font-family: 'Courier New', monospace !important; 
 * font-weight: bold; margin-left: 40px; background-color: white !important;">
 * 
 * Fita
 * ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┐
 * │ a │ a │ b │ b │ c │ c │ a │ c │ b │ b │ a │
 * └───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┘
 *                       ▲
 *                       │  
 *                       │ Cabeça de
 *                       │ Leitura
 *                       │
 *            ┌──────────┴──────────┐
 *            │ Unidade de     q1   │
 *            │ Controle    q0 ↑  q2│
 *            │                q3   │
 *            └─────────────────────┘
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
 * <pre style="font-family: 'Courier New', monospace; font-weight: bold; 
 * margin-left: 40px; background-color: white !important;">
 *
 * 
 * ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┐  ┌────┐
 * │ a │ a │ b │ b │ c │ c │ a │ c │ b │ b │ a │  │ q0 │
 * └───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┘  └────┘
 *   ▲
 * ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┐  ┌────┐
 * │ ā │ a │ b │ b │ c │ c │ a │ c │ b │ b │ a │  │ q1 │
 * └───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┘  └────┘
 *       ▲
 * ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┐  ┌────┐
 * │ a │ ā │ b │ b │ c │ c │ a │ c │ b │ b │ a │  │ q3 │
 * └───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┘  └────┘
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
 * q<sub>0</sub>: Estado inicial (q<sub>0</sub> ∈ Q).
 * 
 * <br><br>
 * 
 * F: Conjunto de estados terminais (F ⊆ Q).
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
 * Podemos representar o autômato M<sub>1</sub> visualmente no grafo:
 * 
 * <br><br>
 * 
 * <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAy4AAAJECAYAAADwq3AjA
 * AAAAXNSR0IB2cksfwAAAARnQU1BAACxjwv8YQUAAAAgY0hSTQAAeiYAAICEAAD6AAAAgOgAAHUwAA
 * DqYAAAOpgAABdwnLpRPAAAAAZiS0dEAP8A/wD/oL2nkwAAAAlwSFlzAAASdAAAEnQB3mYfeAAAAAd
 * 0SU1FB+oLBQgVBuvAQf4AAB+8SURBVHja7d3rjqu4EgbQTpT3f2XOj6OMmAwXczGusteStjTTlzTY
 * xNSHDXlN0zT9AQAABPbWBAAAgOACAAAguAAAAL37aAIAanm9Xv/8t1sqAbjCjAsAj4cYABBcAAAAw
 * QUAAEBwAaBL7nEBQHABIDz3uAAguAAAAIILAACA4AIAACC4AAAAggsAAIDgAgAACC4AAACCCwAAgO
 * ACQHDTNGkEAAQXAABAcAGAS16vl0YAQHABAAAEFwAAAMEFAABAcAEAAAQXAAAAwQUAABBcAAAABBc
 * AAADBBQAAEFwAAAAEFwAAAMEFAAAQXAAAAAQXAABAcAEAABBcAAAABBcAAEBwAQAAEFwAAAAEFwAA
 * QHABAAAQXAAAAMEFAABAcAEAABBcAAAAwQUAAEBwAQAAEFwAAADBBQAAQHABAAAG9NEEABzxer3+p
 * mn61/9/rX1962tz898HgH+dQyZnCQAKA8sTnJYAWGKpGAACBQCCCwAAgOACQDfM6gAguAAgWAAguA
 * AAAAguAHTNbA4AggsAAgYAggsAAIDgAkDXzOIAILgAIGgAILgAAAAILgB0zewNAIILAAIHAIILAAC
 * A4AJA18zaACC4ACB4ACC4AAAACC4AdM1sDQCCCwACCACCCwAAgOACQNfM0gAguAAgiAAguAAAAAgu
 * AKRUOutidgYAwQUAABBcAAAABBcAumaZGACCCwCCCQCCCwAAgOACQNfMxgAguAAgoAAguAAAAAguA
 * HTld9bFLAwAggsAACC4AAAACC4AAACCCwAtub8FgDt8NAEAZ71er1t+RsgBQHAB4JGAUuvvCDMACC
 * 4ANA8rR7ZDiAEQXAAQVKq87jRNt7327+sIMgADnacmoz6AwHIxmLTeFqcyAMEFAIGlWUA4uo1OaQC
 * CCwADBpZop4jSbXdqAxBcABBYBBgABBcAFPl3759THYDgAoCC3v4CILgAcH8R3/PQL8AACC4AJC/c
 * RxryRw5vrdtb2wI1vDUBQD+Fo9Dy7/3d2udaH7gJgOACwIkifK+AHyHACC/tjj8AwQWAotDCdnjbm
 * 6kCQHABQGh5PMCcaUvOtyuA4AIweGBZK7RHXxomvAAILgAECS1ninLKwp3wAhDTRxMATxbYCmuhJV
 * qAWWrT1+ulPW86LrdmBgEOjTU+xwV4qoBRtNRrX+2pXTO877UvILgAKYsXw8/19tWGwkv28KKNgVL
 * ucQFSFjrairu556Vu23poBHD5XGjGBahRWB8pAg1D50KLdtPe0dvtyBPctC+wx4wL8CjFiSLaMSq0
 * AAgugMJPaKHSMWzJGIDgAnRS6FnDLhQKL9rKsQwILkBa30953/q0d/YLZIWe8DLaMQ8w5wMoAQWI0
 * AIA4ZlxAW4tuIUWoa83Zl3uaTMAwQVIUXC7B0YRKLwAcIWlYkCV0KLYFloYe0w4euw61oE9ZlwAxX
 * bgAEjs41qfOd4BwQVg+CJOABTKARBcAKBa6ES7AIILkLhg8dSx8vZyZT8uS8auHdtL44DjHSjh5ny
 * gWsHyLUb2irozN/ICecOL0AKcYcYFuGyt8DgywzLqFWuzLX0d8xxrH+0ICC5A6EJFscIoIdSYILQA
 * N46zk5EDeKCIK7kvYLThyGzLGMc5/24vbQSc5R4XoIqS4kQBA8YFgFKWigEo6riJ5WIAggsACJsAg
 * gsAT3JlHgAEF4B0XLkXSgEQXABA6AQQXAA4whV5ABBcANJxxV44BWCfz3EB4PbifB7Gvj/Xc0Cbpk
 * lYARBcAMYo9nvaj6Xv+9R0AK6wVAzgQdkL99frZWYBgCbMuAA8XPiPEMaEGwDuZsYFIFjRnyV0TdO
 * 0uv2WhAFwNzMuAIQNR5mDkHt6AO5lxgWAw2oU5FvLyzIsPRNSAAQXABo7ExyOFPIlrx89vLivB0Bw
 * AWCgUPS9d2bpHprI4cCMC+QYY6Jvq4sg69zjMjtQIp50rn5wW639mr+pjl5V/X5Q2+8HtpW+ztY+r
 * b3ele3d+p3f/fj+3HwbS7Z33iZb+7m3/n/p9ZZ+p+R1154UtfVBe0v7sdb3a/2y99pbRWPpYL+2/7
 * U+RNCHE14r/n/bL9O9I/odvBdrb+9IF01e0+CXiJxUgGiFeoax8siFhpJAXPK6Zy9AOKcAziN9sFQ
 * MgBCFvqVWAAguAFyS6V4TgDvHvN/77n7Hw7UlriUXY74/M//50t8dkXtcCk7Q35P00om79Gtrr7tW
 * CKzds7C3nWvbsrUNV/dt6V6SVm+4O/5+lH1YKxZLtutofx/d7yj3GGT7nIzRin3h5q/pWALUe++Wh
 * JfS9/9vSNr73ZHHVve4FN6UDDD6eHN0+48+GOLIxZ2obZdhG4G+ziXucZGuAYwvBdu/duXPbAsAdx
 * t+qZjHlAJcGzONoQA8YfgZFydcgOPhxTYAILgA0EV4ES4AuJOnigFwKbzc9Vks2Z4St7T9AAguAAQ
 * PMGd/V8EPQAlLxQAIL2O4sVQOQHBxYgEU5AO3h3EbYEzDLxVTNAC09btczLgMwBJLxQAeLtI53y7a
 * D0BwAYCw4WWaJqEFYHCeKgbQWPbHAO+Fke/Sr54/+8XyNgDBBYAOwot9BuAqS8UAFLUAILgAQM8sE
 * wMQXAAUvwCA4ALQiuVi+hYAwQUAHmGmDEBwAVAEAwCCC0BrlhTpUwAEF4CUzLroKwAEFwCoxmwLgO
 * ACMFSx60p+fPoIQHABgC4CKACCi5MN0P045Ip+XPoGQHABgC6CJwCCC8Awxa8r+/HoEwDBxUkIEF6
 * El3TnC7MtAIILAACA4PLlahkQfUwy69Ke2RYAwQWAk4Uz2h5AcAGgGVfy9REAgssuV9aAqIWx8SnG
 * OUFoARBcwhYLAJELabQ1gOACQDNrF1IU1O1Ci4tbAIJL+JMVgPAitAAguIQvEgCEF6EFAMEl/EkLQ
 * HgRWgAQXAAQXoQWAAQXgBELb7QdgOACwOO2ZgAU4PeFlmmazLYACC55iwIA4WWM0AKA4ALAA+FFgP
 * k71T5CC4DgAkCF8GL25XhoORMGAQgwhk+Dj9RLJzEnL0BBro0AWo9Nd+lljOsuuOh8wMnP2CWwAOr
 * W/sY6S8UCH2QAR09M7n0p31+hBUBwAaBxgNkr6EcILVfaCCDCeI3g4iADhJdOZ19K9ssYDjgfJB3j
 * e7w5v/bJ2EkP6G1MzD6ujbCPwNjjtLr17+/jUBFagDHGra0T4/x7mcY5gQVgHF0Gl2ma3EAPcHJs/
 * P2ZSIX/kbFdYAGM+32Ng2ZcAAY9iZWGgJazMWcuQgksQMax2UV3wUVqBdgZ046cLGuHmCsnbmM0gO
 * ACgABza8iosf0A9D02fnruLFNuAOdOctHHT2EFULuOx4wLALvBoPXJVFABQHBx0gQoHgOfDDDGXQD
 * j5zDBxZQbQLuT4db4K5QAqF0FF6kVwNgJQHfemgAAAPrQ80Wjt84DAAC1q+Di4AEAAAQXAACg9wvu
 * b50IAABqV8EFAADgIo9Dlna79PsMdH0JAKhbc3vrTAAAULsKLg4WAABAcBFGAAAY1yg1rhkXAAAQS
 * AQXiOr3Bn4AAOLyVDHpduiwMv9//Q4AqFvjeutcRgksezMsZmAAALWr4AJpAg4AAAHrtGnQGLdWoE
 * q1Y/avD6wEALLVNqPVK2ZcGMbWm/v3e2ZeAAAEl5SFLfoWAADBRSELAAAILiDMAgDZbD0ZteSpqT3
 * 5jHwQbH1NEQsAQIua9O7X6KWu/TgQxj4AAADIF1bO/r3MNezHAXDf3xNmAACIUKuWbkem+vWj86VZ
 * 4h5TAIC6Yqm2vOv1M9Wvn5EPgK0Ouvq3hBgAAGHlSj16x+8c2abo9etrSlBVP3kQZN4mtvtnq+0zT
 * 5sCAPnq1Va1xpHtjFYPhQ4uWQ6AbNs68uCx1O7uVwIAateAIWcwkoWYsMFlhINAcdyuD75tfyTgAA
 * Acrfmy1BQZ9iVccOnpAOhxf3pqd30BANSoMzLXE5Fr1zDBZYQC32fDtGvraZq0PwCgXk28n82Dy2j
 * FpBmYWP2grQEANWuOfW4WXEYv4F39BwBQs2mD4MFla+dHK9i9GQAA8tVpI9Zordvj8eDiANAuAABq
 * s/7apna7PBZcdL42AgBQk2mj0MFF5+dIsQAA6jB1WNQa/y20xLPWLq/X6/TnkwAAILQ8UbPuZYCQw
 * UVoyXMgAAAILUSuWastFZNYhT8AgMz1llrrejve2YZvnR8/xW4tHQMAQN0apW6tWa++dX7eA0F4AQ
 * BQt44SXt46P394AQBAvdV7eLktuAgt+VMsAMBWrTf/18P+qFvbt3mT4CK0CC8AwJhBZrQCmvM54Er
 * bv2t1vtDiTQgACDFZ6iW1a/zw8q7V+eRLsQAAPYcYoSVOzfp4cNH5wgsAQIYQo26NVbOeOSbeT2wY
 * OVIsAMATISbKRVZ1VMwweXtwcV9LfykWAOCpIv/JAKNu7eP4eet8BwMAQCu9PWaZY/XqkT5/a8K+B
 * wIAgEx1xRMhxgXfnN53HLQ6v48UCwAQLSxdrWXUQv3Uqx/NBwBA9ACzV/xeKZzJ4X3loNH5faVYAI
 * AsQaZkNkYN1Fe9+jlygAgtQLQT19bnGR0ZGKdp+ud78//eG3B/f+f3e1vj5JGfn3/v+9/zv720b0f
 * G6JLfnf/Mkf06W1CU9Mnv31nbxqWvl/z9rfb5fc3S/Vnq85LtPlscPFG4PfV34Op4r3ZN3tdTYQ/q
 * /Hxv4iMnYQCAbPYuoqhdc9erv941DhRivpG/B4fQAgBkrnG+/4SWfuvVJUVLxRS6AABEK3Kf+F3ie
 * Ot8KRYAeK7Q3quj9mYTjv7c1u8s/f/vf5/5O3e1X8nsCuME0t17XEy15ab/AIBaNUXrQlbt0+extd
 * Zfn6cPJgAAUF+yFWSWjom3pjEQAAC0qFGeWAamFuqnXv3spR3GSbEAAJlDhNq1b59IBxv1BglvZAC
 * gt6BCv5YutFsqBgDA7YElQmgRnPoKuZ+tlMNYKRYAQEggKjMuBhMAgFO1RKTPWXHRvT+/ffq5crAC
 * ACDA2E6e8JZYAQAQBoh+fFkqNjABFQCALIqWiknX/aRYYQUAIK61Wm1ej89/ZqQ6/TNKh/926vdnh
 * DIAgD6L/Z72wcXnzpaKvV6v1U6df2/+Mw4CAIC+ZLswrR4dKLhsBRYHBgAAGUNLlEdNR2mfT687ub
 * Y0DAAAstSw86/dUc9m/hDyz0gdLrz0dfACAGSvw1r8jaz3er9bNGDNztjqgLs657s0TRACAOAue7X
 * q0Vq25Ib/aPXs1j76HBdvCAAAOrP2lN2lWjDLxfjd4JKp0C3Z1qv7Y5YFAIBMoeU3sCwFmAw17qen
 * Tsn+dwAA4E49rbb56E6BBQBAvUZ07nEZJKECAKA+zVzrfrJ3XO2U7fNgAACIoqQWLQ0k2epaMy4AA
 * KSVeYVMpG3P0I5dBRezIQAA9FrL3lXrZv08wrcDAgAAxqhV1z7fRXB5gHtQAADopZb9rWe/syNXa9
 * y118i01O5TspPZ1g5+O2W+3QINAABRw8tvrXpn7Zo9sBQHl0xJtWaH90KbAAAj1DvZCvMaT8vNGFi
 * 22uDdQ/rqYdu1GQCAumytNtv63ih136fHDl9LamszMwAAEKmevWpe7/ZyofqjswEAoB89hpa/Px9A
 * CQBAci5al4WY7/9nWn0079uP7qT1G8lgAwDwXHhZ+1r0muxTurOKS2q9cQAAYK9OtFTsoMwBrmVoy
 * DYtCQD0XQSTr8b9rG24zu47xDyx/Y4hAID767e9GquXlVK/++EelwEDi7ACAIxY0KtFcysOLj3d5y
 * J4CCsAwBg1irqvn3rSjIuDQWABACC81ZvzpdO+ne3f7032QgsA0Et9Q46+PPRUMcXquIQVACBrDUM
 * fLBXzhvUmBwAgfK26OeNiuq1Pa/1qGRgA0GudQ/4+PPwBlIra/hKssAIA9Fz7qHP6YKmYN3WIbbl6
 * dcTjDiHH+2fpb/1+7Ts+Lf3c0tePjnl37etWu+216Vo7zC19JsVem6z9/NqYX/L9re068rkZWz/rg
 * 69BTVr0s1PBCL70gopE4QQAIBP1a456da2fzLgM/IYVbAAAyBBa/v4K73FZegFFbx8h5vsPAKC3Gk
 * f92k9o+fsz4+JgWPieNzUAtC26t87Fe/cLldYJW/doHXmttde7cv9c6e9eufeNhPXtdKCn3evSb4I
 * 98lq1BmkAgMh1EW375K3p2AsUtd/cBg8AAHYDz3SwapRa+0ywd/wdwQUAGLk+om5fHJ5x0cl8j4M7
 * PwsBAABuDS4Kzz4TbIQAAwBQq15Rv+avVd86nzsDjBADAEANbs6XYJuHGGEHAHiiPimpn4hbq751v
 * gOhdYhx3AAAwstYteoZb53vQGgdYsy4AADCy1i16pn67/DjkEs3SDHa7mDQ9gAAaqieQsvfX8V7XC
 * TX+geCNxwAgPp1hNByW3BZ2wCdn+dAAAAYifo1Tps/Glx0fv4DAQBA/aR+vUOtVUHvJzqfugcCAAD
 * CS8+h5fbgothu044CIwCA8BK1Vr3L+8mO1/lCCwCAYlyteupvTJUq361OVmxrRwCAUQtwteo51ZaK
 * +aR0oQUAIBvLxu4JeTVq1WozLgpwbQYAoBYbL7TU8sjN+Tr32EHwdHoFAKC8fh199qXVcrpHZlwkV
 * +0DAMSoKdQV6rOsx9T7yR2WXL0pAIC2Baa66976dYS2jBKEH51xcRVAWwAAbUOLWqNe2/bYlpFq1S
 * bBZeTOF1oAgCihRb2hhs20n82Cy6gBRmADAJ6oMUof66vuqBcSM7Zt5Fq1eXAZpZjv+QAHAHKEFuF
 * FfZd5P0IEl15DjLACALSoOwSXuHVflPbOtr0hg0vmxjy63QYKACBCraIeGaMOzP7whrDBJVMQ8AQP
 * ACBzLaUuaV8b3tkfdzyiOeIxET64XO2AGrt35WAwMAAA0Wom9UnMALPXX3e/fvh7caZkR+qdHVT6t
 * I0RDgQAYNwiWZ2SN8SMVKN+snX6vHGvHgA1DyADAAAQsY4a4ZPeMwSE1v2Q8lHNU0cVtgMAABjR0R
 * vuPVVsrDq2l/799NThTydZb3IAIENNRO4+84CFDoPL0Q7cOwi86QGA6I5eqLVULH9NO+qM2Xv0g2C
 * po9e+DgAQvagFwQUAgLRer5fZFmE1tY+uBwDoL6Qc/XkzN0RnxgUAILk7QofZGAQXAABChRyzK2Rk
 * qRgAQCeB5KvkqVPCC4ILAABhQgz9GXVZn6ViAACA4AIAANxn1Bk1wQUAABKxVAwAAAjPjAsAAEBQn
 * ioGwGl3LFfw9CMABBcAwgSUM68t1AAguADQLKyc3QZBBkBwAUBQSbWNQgyA4AKAsCLEACQaD0cYBw
 * UXAIGlm/0RYIARPTH2RRhnPQ4ZYLDA0vMHl/W+fwDzENHi77UcY824ADjJ3ebMlbga22YGBqA/ggu
 * A0FI9nBx9vbu2eZR138BYpmkacnZZcAEQVh4NKmf+5pX9cRM/YIwXXADo8IQWsbifb9PVECO8ANmZ
 * cQFg2NCSqZj/buvZfXX/C4DgAoDA8niAObv/Zl+A0cb8O//+3qxPjfHV45ABhJb0zu6LRycDI415T
 * 557ajyeXnABGCy0TNPU5UzD2f0SXoARzwUZWSoGMMiJapRlUWfugXHfC9DDuBfl7/5+YOVd29c8uD
 * y9Ng5AaBnnRH60ndz3AnD9vPI7/t41tjYNLiVr4wQYAIHl6on16OyL8w5APM3ucTkzhQ8gtAgtVwK
 * M8w5AzHE3bHD5PSF8b6ic/wNAaLn7JHqkXYQXoNdzxNUQ0upBKM2fKra24066AELLk+cdANoEozTB
 * BYD7CnJF+b3hpcbnEABwTpOb851YAcqUFs3G1XPnIqEEIE/9H3LGxRUuAKEl0onUOQmgvebB5RtS5
 * v8AhBahRXgBiDlOloyHNcbMpo9DdhIAEFqEFwBjbNjgsjXweyQygNAivADEtjUW1honP6130skXQF
 * EcKbyULoFw/gJGPzd9v/4dD2ufwz66AiBXaFEwA5Dl/HXnOavpzflOvgDGzaxtbHYMGHVcLLmto8a
 * tH02Dy5G1cU4QQM9KxjihRUAEaD0uzsfHpXBS8171cEvFBBQAhXSUNt87J7nXBXAueu4c9W69w0c+
 * w0WoAXpkbBMYAQgYXEpPBB6JDAgtimfBE4CmwWUrmPx+ff7/Tt7AaIx7+gCA/2t+j0vpCcGJA+iNK
 * /V99aXzFEBdb00AEJNCWF8AILgANGW2RZ8CILgApOcKvz4BQHABAAAEFwC27C0pcmU/rr2+sVwMQH
 * ABAAAEFwAiMNuSv4/MugAILgDpKWoBQHABgEeYGQMQXAAgPTNrAIILQLfFrKv4ACC4AAOGBFe98R7
 * wHgAEFwDFG0OLOkPmmAd69dEEwCgBpmXBaZnYmMfcU/0qqACCC0DHIUZYoKdADiC4AAxQ9AkxnDFN
 * U5PwILAAggvA4CFGgCFL2AYQXAAUhgLMTYX1bzs+ed+HwAIguAAMVSjWLrR7KOTXCut5EPz+t/Ais
 * AAILgAVi0fF9vnCWgEusABc5XNcAA4UkwpKagYQxxjAOjMuACeL0dFnYPbuZ7lSvI/StkIKQDkzLg
 * AXis6SK+SjF6elIWTelpna7GzIEloAjjHjAnBTiLlayGbd35J93vu8k8xF/JFtF1YABBcAISZZG22
 * 1TaZ26zmUAURiqRjAQyGG8iAAgHPLLzMuAAganRc4GfroyYde/LbJmTZa+52lZZQlSyv3tmH+/ZK2
 * Wvr5pffu0vfmX1/6ENnS1zkzlpRs951jl4tLycazyRnHQQso/C+Mm0duvl/a77VCyHkEoO9zyVGWi
 * gHQrGhf+11BAADBBQAeZGEDwD3c4/JnjSNQt0g1vuy3V+9PYvu9nwK1xZPvn9LtWLvH5Peel+/PbL
 * 3u1r0x89df24e1fdq6P2lte+8y0ofjRuUeF6C/ga1BYbh3k+zZ383Sznv7sHdzcubgcqV/nzhWnea
 * BXlgqBnTnqUJtmqZ//o1eOJpFuHYMAbDPUjGAgKEoS1vMA8vS0gyB5vhxpc0ABBcAYeUBJYW3teL7
 * x5sAAyC4AAgrN7bTmQJ7lPa9sp8lH/wHILgACCtUDi+cOz61NSC4AAgrTfSwbKpk+xXc97e1NgUEF
 * wBhBWFNiAEQXACEFRBiAAQXQBGnEfRPdVEDguMf6JEPoARQAAOA4AKAK+CCGgCCCwDdBLtMAW8vfA
 * mrAIILQOri3P4vt4VCHwDBBSCBEZZPTdM0REARwgAEFwAAQHABoCZX4vvg4QIAgguAghgAEFwAoGa
 * 4NKsGILgAKIwBQHAB4AmuyAuVAAguAApkhFIAwQUABa4wCYDgAoBCWRgFQHABUOgiRAIILgAomIVQ
 * AAQXgNEKXuFFeARAcAGA6uETAMEFYIjC11X/NvbaXWgBEFwAIHRoAUBwARiOWRehBQDBBUBBze1tb
 * JkYgOACMKTSQlh4EVoABBcAhBcAEFwAEF5iMtsCILgAUCm8ILQACC4AhA8vZl2EFgDBBYCuCm+EFg
 * DBBYAq3O8itAAguAB0F14EmHtDCwCCCwAVwoui/N5wZ7YFQHABQHh5PLTUam8ABBcALoQXAUZoARB
 * cAAgfXs4U7UKL0AIQcjyfjNAAinJtI7AABGfGBUDQ6W4fhRaADsd3My4A4waSnk4B2gCgb2ZcADpw
 * tvjuZQZGaAHo30cTAPQTXs4U8PPfyVLIe1oagOACwIDhJUuIEVgABBcA2AwJrYKMsAKA4ALQedio8
 * dq1A8zd+zDfXiEIQHABIKhv4X5X0b71OiWh5snw4OZ7AMEFgMEDTOtQIrAAjMXjkAE6sRYalu5V6b
 * Ww73nfAEZnxgVgUE/MwDy5HwAILgAMEGAyhRhhBUBwASChvcDxer2Kiv3IIUZYARBcAGA3KDwdZAQ
 * VAAQXgI6UBorSWZejQeJqoBFQABBcAKhO8ADgCR6HDAAACC4A1HN0mVb2Rx8DILgAAAAILgAAAIIL
 * wGDOLvuyXAwAwQUAAEBwAeAOZl0AEFwAEDwAQHABAAAEFwBCu2u2xawNAIILAACA4AIAAAguAIR09
 * /Iuy8UAEFwAAAAEF4CxmB0BYGQfTQAgEE3TpCEGCr36G8jIjAsAACC4AHBd7WVilqEBILgAAAAILg
 * AAgOACwBAsF9P+AJF5qhhAAt+nQM2fALb0NLArxa8nTbUPKU89/et77OwdL44JINTYORmVALovjA3
 * 1cdrlaLi8YxuvzuY4foAILBUDgKCh5Y7Q0Wq7Ae5mqRgANCr+12YyngoKWzMpwgoQjRkXAGhgKzTc
 * ee/Smb//1DYACC4AkDS02E6AZZaKAUCwEBB1dmPpSXYAggsAdK5VQDkbPoQWQHABAGEFgA3ucQEAo
 * UXoAsIz4wIADYv+1o9EBsjCjAsANAgt0zQ1u2ekNBT9/px7XADBBQAGIgAACC4AkJ5lYgD/5R4XAG
 * gQTJZmXc4Glvnvlc7mfH+n9B4bs0SA4AIAg4aXbyC4c4bl6IdElvxtoQWIwFIxAKhsq/Bfu3F/6ec
 * sIQMEFwCgWXhZ+rk7Zzl+X+vIE83MtgBRWCoGAA3CS8l9KTXDy9r2CCqA4AIAVAslV5eRCSxAdJaK
 * AYBABCC4AAB1uWkfGIGlYgDQATMtQO/MuACA0AIguAAAAAguAACA4AIAACC4AAAACC4AAIDgAgAAI
 * LgAAACCCwAAgOACAAAguAAAAIILAACA4AIAACC4AAAAggsAAIDgAgAACC4AAACCCwAAgOACAAAILg
 * AAAIILAACA4AIAAAguAAAAggsAACC4AAAACC4AAACCCwAbXq+XNtAGAKl9NAGAwh0AojPjAgAACC4
 * AAACCCwD/MU2TRgCgK+5xARBeACA8My4AAIDgAgAAILgAAACCCwAAgOACAAAguAAAAIILAABAZf8D
 * hEAyA6OM0gcAAAAASUVORK5CYII="
 * alt="Diagrama"
 * width="400"
 * style="margin-left: 40px;">
 * 
 * <br><br>
 * 
 * Observe no diagrama que ele utiliza os estados q<sub>1</sub> e q<sub>2</sub> 
 * para "memorizar" o símbolo anterior. O estado q<sub>1</sub> representa 
 * "símbolo anterior é a" e q<sub>2</sub> representa "símbolo anterior é b". Após
 * identificar dois <i>a</i> ou dois <i>b</i> consecutivos, o autômato assume o
 * estado q<sub>f</sub> (final), e a partir daí, qualquer símbolo lido da fita 
 * mantém a unidade de controle no estado q<sub>f</sub>, até o término da leitura
 * da fita.
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
 * <span style="text-decoration: underline;">δ</span>(q, ε) = q
 * 
 * <br>
 * 
 * <span style="text-decoration: underline;">δ</span>(q, aw) = 
 * <span style="text-decoration: underline;">δ</span>(δ(q, a), w)
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
 * ACEITA(M) = {w | δ(q<sub>0</sub>, w) ∈ F}
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

    /**Log do processamento da palavra de entrada.*/
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
         * @return Símbolo na célula.
         */
        public Simbolo getSimbolo(int celula) {
            
            return celulas[celula-1];
        
        }

        
        /**
         * Obter o número de células da fita.
         * 
         * @return Número de células da fita.
         */
        public int numeroCelulas() {
        
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
         * @param fita Referência para a fita.
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
            
            return celulaAtual > fita.numeroCelulas();
        
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
            
            if (celulaAtual <= fita.numeroCelulas()) {
                
                Simbolo[] parcNaoLida = new Simbolo[1+(fita.numeroCelulas()-celulaAtual)];
                
                int i = 0;
                
                for (int idx = celulaAtual; idx <= fita.numeroCelulas(); idx++) {
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
         * Adicionar a transição ao log do processamento da palavra de entrada.
         * 
         * @param estadoInicial Estado inicial.
         * 
         * @param simboloLido Símbolo lido da fita.
         * 
         * @param novoEstado Novo estado.
         * 
         * @param parcelaNaoLidaFita Parcela ainda não lida da Fita.
         */
        private void adicionarLog(Estado estadoInicial, Simbolo simboloLido, 
        Estado novoEstado, Simbolo[] parcelaNaoLidaFita) {
            
            StringBuilder sb = new StringBuilder(parcelaNaoLidaFita.length);
            
            for (Simbolo s : parcelaNaoLidaFita) sb.append(s.getCaractere()); 
            
            log.adicionar(
                estadoInicial.getNome(), 
                simboloLido.getCaractere(), 
                novoEstado != null ? novoEstado.getNome() : "",
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
     * Obter o log do processamento da palavra de entrada.
     * 
     * @return Log do processamento da palavra de entrada.
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