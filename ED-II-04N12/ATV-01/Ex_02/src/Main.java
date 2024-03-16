import java.util.Random;
import java.util.Stack;

public class Main {
    static Stack<Integer> pilha1 = new Stack<>();
    static Stack<Integer> pilha2 = new Stack<>();
    static Stack<Integer> pilha3 = new Stack<>();

    public static void main(String[] args) {
        inicializarPilhas();
        jogarQuebraCabeca();
        mostrarResultado();
    }

    static void inicializarPilhas() {
        Random random = new Random();

        System.out.print("Escolha o tamanho da Pilha: ");
        int tam = Integer.parseInt(System.console().readLine());

        for (int i = 0; i < tam; i++) {
            pilha1.push(random.nextInt(100));
            pilha2.push(random.nextInt(100));
        }
    }

    static void jogarQuebraCabeca() {
        int n = pilha1.size();
        while (!verificarFimJogo(n)) {

            System.out.println("\nPilha 1: " + pilha1);
            System.out.println("Pilha 2: " + pilha2);
            System.out.println("Pilha 3: " + pilha3);

            int pilhaOrigem = escolherPilha("Qual pilha será retirado o número (1 ou 2 ou 3): ");
            int pilhaDestino = escolherPilha("Qual pilha será inserido o número (1 ou 2 ou 3): ");
            if (!movimentoValido(pilhaOrigem, pilhaDestino, n)) {
                System.out.println("Movimento inválido! Tente novamente.");
                continue;
            }
            realizarMovimento(pilhaOrigem, pilhaDestino);
        }
    }

    static int escolherPilha(String mensagem) {
        System.out.print(mensagem);
        return Integer.parseInt(System.console().readLine());
    }

    static boolean verificarFimJogo(int n) {
        if (pilha1.isEmpty()) {
            int MaiorA = 0;
            int MenorA = 0;
            return false;
        }
        if (pilha2.isEmpty()) {
            int MaiorB = 0;
            int MenorB = 0;
            return false;
        }

        int MaiorA = pilha1.peek();
        int MenorA = pilha1.peek();
        int MaiorB = pilha2.peek();
        int MenorB = pilha2.peek();

        for (int elemento : pilha1) {
            if (elemento > MaiorA) {
                MaiorA = elemento;
            }
            if (elemento < MenorA) {
                MenorA = elemento;
            }
        }
        for (int elemento : pilha2) {
            if (elemento > MaiorB) {
                MaiorB = elemento;
            }
            if (elemento < MenorB) {
                MenorB = elemento;
            }
        }
        if((MaiorA < MenorB || MaiorB < MenorA) && pilha1.size()== n && pilha2.size() == n)return true;
        else{
            return false;
        }
    }

    static boolean movimentoValido(int origem, int destino, int tam) {
        if (destino == 1 && pilha1.size()>=tam){
            return false;
        }
        if (destino == 2 && pilha2.size()>=tam){
            return false;
        }
        if (destino == 3 && pilha3.size()>=tam){
            return false;
        }
        else {
            return (origem == 1 || origem == 2 || origem == 3) && (destino == 1 || destino == 2 || destino == 3) && origem != destino && pilha1.size()<=tam && pilha2.size()<=tam && pilha3.size()<=tam;
        }
    }

    static void realizarMovimento(int origem, int destino) {
        if (origem == 1){
            if(destino == 2){
                pilha2.push(pilha1.pop());
            }
            else{
                pilha3.push(pilha1.pop());
            }
        }
        if (origem == 2) {
            if(destino == 1){
                pilha1.push(pilha2.pop());
            }
            else{
                pilha3.push(pilha2.pop());
            }
        }
        if (origem == 3) {
            if(destino == 1){
                pilha1.push(pilha3.pop());
            }
            else{
                pilha2.push(pilha3.pop());
            }
        }
    }
    static void mostrarResultado() {

        System.out.println("\n ** PARABENS **\n");
        System.out.println("Pilha 1: " + pilha1);
        System.out.println("Pilha 2: " + pilha2);
    }
}