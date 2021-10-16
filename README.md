# puc-sistemas-operacionais-tp1

Este programa processa simula o processamento de uma fila de vendas de um cinema.
A aplicação lê um conjunto de configurações do arquivo `config.txt`.
As entradas são lidas a partir do arquivo `input.txt`. Ambos seguem o formato especificado na definição do tp.

## Instruções

Basta abrir o projeto como projeto maven. Por enquanto não existem dependências. O projeto possui duas execuções distintas.

O arquivo `Main.java` apresenta uma execução sem existência de priorização. Ou seja, as entradas são processadas à medida que são recebidas pela aplicação (fifo).

O arquivo `Priority.java`, por sua vez, apresenta uma execução seguindo as regras de negócio apresentadas na definição do TP.
Para que as regras sejam respeitadas, separamos as entradas em três filas distintas (`club`,`half`e `regular`) e processamos os dados de acordo
com suas regras específicas (club primeiro, half até 40% de lotação, etc..)

Além disso existe um arquivo `GenerateTestFile.java`, capaz de gerar um arquivo de entrada com valores aleatórios para testar o sistema.
O número de linhas geradas pode ser configurada a partir de uma variável interna. Valores de geração aleatória também podem ser alterados.

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
Client 0 REGULAR J7 17:00 confirmou
Client 1 REGULAR J7 17:00 ocupado - mudou para D12 e confirmou
Client 2 CLUB A10 17:00 desistiu
Client 3 HALF C4 14:30 desistiu
Client 4 REGULAR J7 17:00 ocupado - mudou para H13 mas desistiu

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

## Análise
Dado um mesmo conjunto de entradas, totalizando 1000 linhas geradas aleatoriamente, as execuções do sistema apresentaram os seguintes relatórios globais:

### Fifo
```
#########REPORT GLOBAL#########

Tempo de simulação: 4903
Vendas: 546
Assentos: 600
Percentual vendido: 91.00%
```

### Prioridade
```
#########REPORT GLOBAL#########

Tempo de simulação: 4903
Vendas: 532
Assentos: 600
Percentual vendido: 88.67%
```

Portanto , inicialmente podemos considerar o execução fifo como melhor, já que foi capaz de vender mais ingressos.
No entanto, ao considerarmos as regras de negócio apresentadas podemos fazer uma análise distinta a partir do processamento de logs.
Neste caso a expressão regular abaixo foi utilizada:

```regex
(CLUB|HALF).*(ocupado - desistiu|ocupado - não encontrou)
```

Ou seja, estamos contabilizando quantos clientes CLUB e MEIA ENTRADA que desistiram ou não conseguiram encontrar lugar devido a lotação.
No caso da execução fifo, tivemos 60 (39 dos quais são CLUB) casos em que isto ocorreu dentre os 317 clientes que se encaixam nestas categorias.

Já na execução por prioridade, tivemos apenas 33 desses casos. No entanto, a maior parte das ocorrências ocorreram para casos de meia entrada acima do limite de 40%.
Ao considerarmos apenas os clientes CLUB, apenas 8 não conseguiram assistir a sua sessão, sendo que todos desistiram após a primeira consulta.
Ou seja, todos os clientes CLUB que procuraram novos lugares foram capazes de encontrar um disponível.

Portanto, podemos considerar que este tipo de execução consegue respeitar a devida prioridade dos casos do CLUB e MEIA ENTRADA.

## Conclusão

Apesar de gerar uma venda maior de ingressos, o modelo Fifo não é ideal no caso apresentado pois poderia gerar uma insatisfação muito grande em clientes prioritários. Para a demanda apresentada no trabalho,
nosso grupo sugere a utilização do sistema de prioridades pois ele é capaz de respeitar as regras de prioridade dos clientes CLUB e MEIA ENTRADA.