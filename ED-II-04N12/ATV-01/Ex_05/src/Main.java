import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        // Configurações do simulador

        System.out.println("Digite o tamanho da fila: ");
        int tamanhoFila = sc.nextInt(); // Tamanho máximo da fila

        System.out.println("Digite o número de processadores: ");
        int numProcessadores = sc.nextInt(); // Número de processadores do servidor

        System.out.println("Digite o número de ciclos a serem executados: ");
        int ciclos = sc.nextInt(); // Número total de ciclos de simulação

        System.out.println("Digite o número máximo de Requisições por Ciclo: ");
        int maxRequisicoesPorCiclo = sc.nextInt(); // Máximo de requisições que podem chegar em um ciclo

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
                totalRequisicoes++;
                if (!fila.isFull()) {
                    fila.enqueue(1); // Simplesmente insere um valor qualquer na fila
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
