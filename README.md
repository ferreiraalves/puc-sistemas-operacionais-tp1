# puc-sistemas-operacionais-tp1

Este programa processa simula o processamento de uma fila de vendas de um cinema. 
A aplicação lê um conjunto de configurações do arquivo `config.txt`. 
As entradas são lidas a partir do arquivo `input.txt`. Ambos seguem o formado especificado na definição do tp. 

## Instruções

Basta abrir como projeto maven. Por enquanto não existem dependências.

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
REGULAR	J7	17:00	confirmou
REGULAR	J7	17:00	ocupado - mudou para D6 e confirmou
CLUB	A10	17:00	desistiu
HALF	C4	14:30	desistiu
REGULAR	J7	17:00	ocupado - mudou para A9 mas desistiu
```
