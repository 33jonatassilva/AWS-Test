Cálculo de Pi com Threads em C

Este repositório contém duas implementações em C para o cálculo do valor de Pi usando múltiplas threads. As implementações visam explorar a paralelização do cálculo para melhorar o desempenho.

Implementações

Sem Mutex: Esta implementação divide o trabalho entre várias threads para calcular partes da série de Leibniz que estimam o valor de Pi. No entanto, não há sincronização adequada no acesso à variável pi, o que pode levar a resultados inconsistentes devido a condições de corrida.
Com Mutex: Esta implementação é semelhante à anterior, mas utiliza um Mutex para sincronizar o acesso à variável pi. Isso garante que apenas uma thread modifique pi de cada vez, resultando em um valor final mais preciso.

Compilação e Execução

Para compilar os programas, basta usar um compilador C, como o GCC. Por exemplo:


gcc -o pi_no_mutex pi_no_mutex.c -pthread
gcc -o pi_with_mutex pi_with_mutex.c -pthread

Para executar os programas compilados:

./pi_no_mutex
./pi_with_mutex

Resultados

Os resultados da execução de cada implementação podem variar dependendo do sistema e do número de threads usadas. No entanto, é esperado que a implementação com Mutex produza um valor mais próximo do valor real de Pi devido à sincronização adequada do acesso à variável compartilhada.

Diferenças nos Resultados

As diferenças nos valores de Pi calculados podem ser atribuídas ao fato de que a implementação sem Mutex pode produzir resultados inconsistentes devido à concorrência das threads modificando a variável pi simultaneamente. Isso pode levar a um valor final de pi impreciso. A solução com Mutex resolve esse problema, garantindo que apenas uma thread modifique pi de cada vez, resultando em um valor final mais preciso.

