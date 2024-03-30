import java.util.Random;

public class JogoTabuleiro {

    private ListaDuplamenteLigadaCircular tabuleiro;
    private int quantidadeJogadores;
    private int jogadasRestantes;

    // Construtor
    public JogoTabuleiro(int quantidadeCasas, int quantidadeJogadores, int jogadasRestantes) {
        this.tabuleiro = new ListaDuplamenteLigadaCircular();
        this.quantidadeJogadores = quantidadeJogadores;
        this.jogadasRestantes = jogadasRestantes;
        inicializarTabuleiro(quantidadeCasas);
    }

    // Inicializa o tabuleiro com a quantidade de casas especificada
    private void inicializarTabuleiro(int quantidadeCasas) {
        for (int i = 1; i <= quantidadeCasas; i++) {
            Casa casa = new Casa(i);
            tabuleiro.insereUltimo(casa);
        }
        tabuleiro.getUltimo().setProximo(tabuleiro.getInicio()); // Conecta o último ao primeiro
        tabuleiro.getInicio().setAnterior(tabuleiro.getUltimo()); // Conecta o primeiro ao último
    }

    // Inicia o jogo
    public void iniciarJogo() {
        for (int i = 1; i <= jogadasRestantes; i++) {
            System.out.println("Rodada " + i + ":");
            realizarJogada();
            System.out.println();
        }
        encerrarJogo();
    }

    // Realiza uma jogada
    private void realizarJogada() {
        Random random = new Random();
        for (int jogador = 1; jogador <= quantidadeJogadores; jogador++) {
            int dado = random.nextInt(6) + 1; // Simula o lançamento de um dado
            System.out.println("Jogador " + jogador + " lançou o dado e obteve " + dado + ".");
            No posicaoAtual = tabuleiro.getInicio();
            for (int j = 1; j < dado; j++) {
                posicaoAtual = posicaoAtual.getProximo(); // Avança no tabuleiro de acordo com o resultado do dado
            }
            // Lógica para movimentar a peça no sentido horário ou anti-horário
            // Aqui deve-se implementar a interação com o jogador para permitir a escolha do movimento
            // A movimentação pode ser feita chamando métodos da lista duplamente ligada circular
            // Exemplo: tabuleiro.insereHorario(peca, posicaoAtual); ou tabuleiro.insereAntihorario(peca, posicaoAtual);
            // Após o movimento, deve-se atualizar o status da casa conforme as regras do jogo
        }
        System.out.println("Estado atual do tabuleiro:");
        imprimirTabuleiro();
    }

    // Imprime o estado atual do tabuleiro
    private void imprimirTabuleiro() {
        No atual = tabuleiro.getInicio();
        do {
            Casa casa = (Casa) atual;
            System.out.println("Casa " + casa.getNumero() + ": Status = " + casa.getStatus());
            atual = atual.getProximo();
        } while (atual != tabuleiro.getInicio());
    }

    // Encerra o jogo e determina o vencedor
    private void encerrarJogo() {
        // Implementar a lógica para determinar o vencedor
    }
}
