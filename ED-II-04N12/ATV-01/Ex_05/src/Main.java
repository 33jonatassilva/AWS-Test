import java.util.Random;

public class Main {

    public static void main(String[] args) {
        // Configurações do simulador
        int tamanhoFila = 10; // Tamanho máximo da fila
        int numProcessadores = 4; // Número de processadores do servidor
        int ciclos = 1000; // Número total de ciclos de simulação
        int maxRequisicoesPorCiclo = 3; // Máximo de requisições que podem chegar em um ciclo

        // Inicializa a fila
        Fila fila = new Fila(tamanhoFila);

        // Contadores para estatísticas
        int totalRequisicoes = 0;
        int requisiçõesAtendidas = 0;
        int requisiçõesPerdidas = 0;

        // Simulação
        Random random = new Random();
        for (int ciclo = 0; ciclo < ciclos; ciclo++) {
            // Gera um número aleatório de requisições para o ciclo atual
            int numRequisicoesChegando = random.nextInt(maxRequisicoesPorCiclo + 1);

            // Insere novas requisições na fila
            for (int i = 0; i < numRequisicoesChegando; i++) {
                if (!fila.isFull()) {
                    fila.enqueue(1); // Simplesmente insere um valor qualquer na fila
                    totalRequisicoes++;
                } else {
                    requisiçõesPerdidas++;
                }
            }

            // Atende requisições com os processadores disponíveis
            for (int i = 0; i < numProcessadores; i++) {
                if (!fila.isEmpty()) {
                    fila.dequeue();
                    requisiçõesAtendidas++;
                }
            }
        }

        // Exibe estatísticas
        System.out.println("Total de requisições: " + totalRequisicoes);
        System.out.println("Requisições atendidas: " + requisiçõesAtendidas);
        System.out.println("Requisições perdidas: " + requisiçõesPerdidas);
        double probabilidadePerda = (double) requisiçõesPerdidas / totalRequisicoes;
        System.out.println("Probabilidade de perda de requisição: " + probabilidadePerda);
    }
}
