# puc-sistemas-operacionais-tp1

Este programa processa simula o processamento de uma fila de vendas de um cinema. 
A aplicação lê um conjunto de configurações do arquivo `config.txt`. 
As entradas são lidas a partir do arquivo `input.txt`. Ambos seguem o formado especificado na definição do tp. 

## Instruções

Basta abrir o projeto como projeto maven. Por enquanto não existem dependências. O projeto possui duas execuções distintas.

O arquivo `Main.java` apresenta uma execução sem existência de priorização. Ou seja, as entradas são processadas a medida que são recebidas pela aplicação (fifo).

O arquivo `Priority.java`, por sua vez, apresenta uma execução seguindo as regras de negócio apresentadas na definição do TP.
Para que as regras sejam respeitadas, separamos as entradas em três filas distintas (`club`,`half`e `regular`) e processamos os dados de acordo
com suas regras epecificas (club primeiro, half até 40% de lotação, etc..)

## Exemplo de execução
### Input
```
J07;17:00;CSP;T;R;7
J07;17:00;CSP;T;R;7
A10;17:00;CXX;D;C;2
C04;14:30;CSX;T;M;5
J07;17:00;CSX;T;R;7
```
### Output
```
Client 0	REGULAR	J7	17:00	confirmou
Client 1	REGULAR	J7	17:00	ocupado - mudou para D12 e confirmou
Client 2	CLUB	A10	17:00	desistiu
Client 3	HALF	C4	14:30	desistiu
Client 4	REGULAR	J7	17:00	ocupado - mudou para H13 mas desistiu

#########REPORT POR SESSÃO#########

Sessão : 17:00
Vendas: 2
Assentos: 600
Percentual vendido: 0.33%

Sessão : 20:30
Vendas: 0
Assentos: 600
Percentual vendido: 0.00%

Sessão : 14:30
Vendas: 0
Assentos: 600
Percentual vendido: 0.00%

#########REPORT GLOBAL#########

Tempo de simulação: 28
Vendas: 2
Assentos: 1800
Percentual vendido: 0.11%
```
