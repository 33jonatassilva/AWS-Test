

public class Vetor {

    private final int tamanho;
    private final int minimo;
    private final int maximo;
    private final int vaga;
    private final int repete; // 0 = não repete; 1 = pode repetir
    private int[] dados;

    public Vetor(int tamanho, int minimo, int maximo, int vaga, int repete) {
        this.tamanho = tamanho;
        this.minimo = minimo;
        this.maximo = maximo;
        this.vaga = vaga;
        this.repete = repete;

        this.dados = new int[this.tamanho];

        if (this.vaga != 0) {
            for (int i = 0; i < this.tamanho; i++) {
                this.dados[i] = this.vaga;
            }
        }
    }

    private boolean valorValido(int valor) {
        return valor >= this.minimo && valor <= this.maximo;
    }

    private boolean posicaoValida(int posicao) {
        return posicao >= 0 && posicao < this.tamanho;
    }

    public boolean podeRepetir() {
        return this.repete == 1;
    }

    public int[] localizar(int valor, int nPrimeiros) {

        int[] res = new int[this.tamanho];
        if (!this.valorValido(valor)) {
            return res;
        }

        if (this.repete == 0) {
            nPrimeiros = 1;
        }

        for (int i = 0; i < this.tamanho; i++) {
            if (this.dados[i] == valor) {
                res[0]++;
                res[res[0]] = i;
                if (res[0] == nPrimeiros) {
                    break;
                }
            }
        }

        return res;
    }

    public ResultadoOperacao armazenar(int valor, int posicao) {

        if (!this.valorValido(valor)) { // valor válido?
            return ResultadoOperacao.FORADAFAIXA;
        }

        if (!this.posicaoValida(posicao)) { // posição é válida?
            return ResultadoOperacao.FORADASFRONTEIRAS;
        }

        if (this.dados[posicao] != this.vaga) { // posicao está vaga?
            return ResultadoOperacao.PREVISTOOPERACAO;
        }

        if (!this.podeRepetir()) { // se não pode repetir, então verificar se o elemento existe
            int[] existe = this.localizar(valor, 0);
            if (existe[0] > 0) {
                return ResultadoOperacao.VALOREXISTENTE;
            }

        }

        this.dados[posicao] = valor; // ok até aqui, armazenar
        return ResultadoOperacao.BEMSUCEDIDA;
    }

    public ResultadoOperacao alterar(int valor, int posicao) {

        if (!this.valorValido(valor)) { // valor válido?
            return ResultadoOperacao.FORADAFAIXA;
        }

        if (!this.posicaoValida(posicao)) { // posicão válida?
            return ResultadoOperacao.FORADASFRONTEIRAS;
        }

        if (this.dados[posicao] == this.vaga) { // posição contém um valor válido?
            return ResultadoOperacao.PREVISTOOPERACAO;
        }

        if (!this.podeRepetir()) { // se não pode repetir, verificar se já existe no vetor
            int[] existe = this.localizar(valor, 0);
            if (existe[0] > 0) {
                return ResultadoOperacao.VALOREXISTENTE;
            }

        }

        this.dados[posicao] = valor; // tudo bem até aqui, armazenar
        return ResultadoOperacao.BEMSUCEDIDA;
    }

    public ResultadoOperacao excluir(int posicao) {

        if (!this.posicaoValida(posicao)) { // posição de exclusão é válida?
            return ResultadoOperacao.FORADAFAIXA;
        }

        if (this.dados[posicao] == this.vaga) { // posição contém um valor válido?
            return ResultadoOperacao.FORADASFRONTEIRAS;
        }

        this.dados[posicao] = this.vaga; // pode excluir

        return ResultadoOperacao.BEMSUCEDIDA;
    }

    // ToDo: retorno do tipo incorreto. verificar
    public int ler(int posicao) {
        if (!this.posicaoValida(posicao)) { // posição válida?
            return 2;
        }
        return this.dados[posicao];
    }

    public ResultadoOperacao armazenar1Vaga(int valor) {

        if (!this.valorValido(valor)) { // o valor é válido?
            return ResultadoOperacao.FORADAFAIXA;
        }

        for (int i = 0; i < this.tamanho; i++) {
            if (this.dados[i] == this.vaga) {
                return (this.armazenar(valor, i));
            }
        }
        return ResultadoOperacao.INEXISTENTE;
    }

    public ResultadoOperacao removerUltima() {

        for (int i = this.tamanho - 1; i >= 0; i--) {
            if (this.dados[i] != this.vaga) {
                return this.excluir(i);
            }
        }
        return ResultadoOperacao.INEXISTENTE;
    }

    public void limparVetor() {
        for (int i = 0; i < tamanho; i++) {
            this.dados[i] = this.vaga;
        }
    }


    public void ordenarBubbleSort() {
        for (int i = 0; i < this.tamanho - 1; i++) {
            for (int j = 0; j < this.tamanho - i - 1; j++) {
                if (this.dados[j] > this.dados[j + 1]) {
                    // Trocar os elementos dados[j] e dados[j+1]
                    int temp = this.dados[j];
                    this.dados[j] = this.dados[j + 1];
                    this.dados[j + 1] = temp;
                }
            }
        }
    }


    public void ordenarInsertionSort() {
        for (int i = 1; i < this.tamanho; ++i) {
            int chave = this.dados[i];
            int j = i - 1;

            while (j >= 0 && this.dados[j] > chave) {
                this.dados[j + 1] = this.dados[j];
                j = j - 1;
            }
            this.dados[j + 1] = chave;
        }
    }

    public void ordenarSelectionSort() {
        for (int i = 0; i < this.tamanho - 1; i++) {
            int indiceMenor = i;
            for (int j = i + 1; j < this.tamanho; j++) {
                if (this.dados[j] < this.dados[indiceMenor]) {
                    indiceMenor = j;
                }
            }
            int temp = this.dados[indiceMenor];
            this.dados[indiceMenor] = this.dados[i];
            this.dados[i] = temp;
        }
    }



    private int particionar(int inicio, int fim) {
        int pivo = this.dados[fim];
        int i = (inicio - 1);

        for (int j = inicio; j < fim; j++) {
            if (this.dados[j] <= pivo) {
                i++;

                int temp = this.dados[i];
                this.dados[i] = this.dados[j];
                this.dados[j] = temp;
            }
        }

        int temp = this.dados[i + 1];
        this.dados[i + 1] = this.dados[fim];
        this.dados[fim] = temp;

        return i + 1;
    }

    public void ordenarQuickSort(int inicio, int fim) {
        if (inicio < fim) {
            int pivoIndex = particionar(inicio, fim);

            ordenarQuickSort(inicio, pivoIndex - 1);
            ordenarQuickSort(pivoIndex + 1, fim);
        }
    }


    public int buscarBinaria(int valor) {
        int inicio = 0;
        int fim = this.tamanho - 1;

        while (inicio <= fim) {
            int meio = inicio + (fim - inicio) / 2;

            if (this.dados[meio] == valor) {
                return meio; // Valor encontrado, retorna o índice
            }

            if (this.dados[meio] < valor) {
                inicio = meio + 1; // Busca na metade direita do vetor
            } else {
                fim = meio - 1; // Busca na metade esquerda do vetor
            }
        }

        return -1; // Valor não encontrado
    }

}