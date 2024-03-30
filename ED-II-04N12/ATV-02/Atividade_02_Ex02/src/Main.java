//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Exemplo de inicialização do jogo com 10 casas, 2 jogadores e 10 rodadas
        JogoTabuleiro jogo = new JogoTabuleiro(10, 2, 10);
        jogo.iniciarJogo();
    }
}