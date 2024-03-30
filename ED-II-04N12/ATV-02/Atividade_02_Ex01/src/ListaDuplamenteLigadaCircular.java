

public class ListaDuplamenteLigadaCircular {

    private No inicio;
    public int qtd;



    public void _imprimeElemento(No e) { // Ex1
        System.out.println(e.getId());
    }

    private void _autoConex(No e) {
        e.setProximo(e);
        e.setAnterior(e);
    }

    private boolean _insereEmVazio(No e) {
        if (this.qtd > 0) {
            return false;
        }
        inicio = e;
        return true;
    }

    private No _removeUnico() {
        if (isEmpty() || qtd > 1) {
            return null;
        }
        No e = inicio;
        inicio = null;
        qtd = 0;
        return e;
    }

    public void insereInicio(No e) {
        if (isEmpty()) {
            _insereEmVazio(e);
        } else {
            e.setProximo(inicio);
            e.setAnterior(inicio.getAnterior());
            inicio.setAnterior(e);
            e.getAnterior().setProximo(e);
            inicio = e;
        }
        qtd++;
    }

    public void insereUltimo(No e) {
        if (isEmpty()) {
            _insereEmVazio(e);
        } else {
            e.setProximo(inicio);
            e.setAnterior(inicio.getAnterior());
            inicio.getAnterior().setProximo(e);
            inicio.setAnterior(e);
        }
        qtd++;
    }

    public No removeInicio() {
        if (isEmpty()) {
            return null;
        }
        if (qtd == 1) {
            return _removeUnico();
        }
        No e = inicio;
        inicio.getAnterior().setProximo(inicio.getProximo());
        inicio.getProximo().setAnterior(inicio.getAnterior());
        inicio = inicio.getProximo();
        e.setProximo(e);
        e.setAnterior(e);
        qtd--;
        return e;
    }

    /**
     * Remove um elemento do fim; decrementa a quantidade.
     *
     * @return
     */
    public No removeFim() {
        if (isEmpty()) {
            return null;
        }
        if (qtd == 1) {
            return _removeUnico();
        }
        No e = inicio.getAnterior();
        inicio.setAnterior(e.getAnterior());
        e.getAnterior().setProximo(inicio);
        e.setProximo(e);
        e.setAnterior(e);
        qtd--;
        return e;
    }

    public No getPosHorario(No e, int pos) {
        if (pos > qtd || pos <= 0) {
            return null;
        }
        int posAtu = 1;
        No eAux = e; // * criar ponteiro de deslocamento
        while (posAtu < pos) {
            eAux = eAux.getProximo();
            posAtu++;
        }
        return eAux;
    }

    public No getPosAntiHorario(No e, int pos) {
        if (pos > qtd || pos <= 0) {
            return null;
        }
        int posAtu = 1;
        No eAux = e;
        while (posAtu < pos) {
            eAux = eAux.getAnterior();
            posAtu++;
        }
        return eAux;
    }

    public boolean removeElemento(No e) {
        if (isEmpty() || e == null) {
            return false;
        }
        if (qtd == 1) {
            _removeUnico();
        } else {
            if (e == inicio) {
                inicio = e.getProximo();
            }
            e.getAnterior().setProximo((e.getProximo()));
            e.getProximo().setAnterior((e.getAnterior()));
            _autoConex(e);
            qtd--;
        }
        return true;
    }

    public boolean insereHorario(No eNovo, No eAtual) {
        if (eNovo == null) {
            return false;
        }
        if (qtd <= 1 || eAtual == inicio) {
            insereInicio(eNovo);
        } else if (eAtual == inicio.getAnterior()) {
            insereUltimo(eNovo);
        } else {
            eNovo.setProximo(eAtual);
            eNovo.setAnterior(eAtual.getAnterior());
            eAtual.getAnterior().setProximo(eNovo);
            eAtual.setAnterior(eNovo);
            qtd++;
        }
        return true;
    }

    public boolean insereAntihorario(No eNovo, No eAtual) {

        if (eNovo == null) {
            return false;
        }
        if (isEmpty()) {
            insereInicio(eNovo);
        } else {
            eNovo.setProximo(eAtual.getProximo());
            eNovo.setAnterior(eAtual);
            eAtual.getProximo().setAnterior(eNovo);
            eAtual.setProximo(eNovo);
            if (eAtual == inicio) { // * se o atual for o inicio, deslocando-se no anti-horario -> o novo passa a
                // ser o inicio.
                inicio = eNovo;
            }
            qtd++;
        }
        return true;
    }

    /**
     * Insere um elemento em determinada posicao no sentido horario
     *
     * @param pos Posicao desejada para inserÃ§ao
     * @param e   Casa a inserir
     * @return String vazia em caso de sucesso ou mensagem de erro
     */
    /*
     * public String inserePosParaFim(int pos, Elemento e) { if (pos > qtd + 1 ||
     * pos <= 0 || e == null) { return
     * "ERRO: um ou mais parametros com problemas!!"; } if (pos == 1) {
     * insereInicio(e); } else { if (pos == qtd + 1) { insereUltimo(e); } else {
     * Elemento eursor = getPosDoInicio(pos - 1); e.setProximo(cursor.getProximo());
     * e.setAnterior(cursor); cursor.getProximo().setAnterior(e);
     * cursor.setProximo(e); qtd++; } } return "";
     *
     * }
     */
    /**
     * Remove um elemento de determinada posicao (sentido fim -> inicio).
     *
     * @param pos
     * @return
     */

    /*
     * public Elemento removePosParaInicio(int pos) { if (isEmpty() || pos > qtd + 1
     * || pos <= 0) { return null; } if (pos == 1) { return removeUltimo(); } else
     * if (pos == qtd) { return removeInicio(); } Elemento eursor =
     * getPosDoFim(pos); cursor.getAnterior().setProximo(cursor.getProximo());
     * cursor.getProximo().setAnterior(cursor.getAnterior());
     * cursor.setProximo(null); cursor.setAnterior(null); qtd--; return cursor; }
     */
    /**
     * Imprime os elementos da lista no sentido horario (utiliza o metodo
     * getProximo())
     */
    public void imprimeHorario() {
        if (isEmpty()) {
            System.out.println("A lista esta' vazia");
        } else {
            No e = getInicio();
            int posAtu = 1;
            while (posAtu <= getQtd()) {
                _imprimeElemento(e);
                e = e.getProximo();
                posAtu++;
            }
        }
    }

    public void imprimeAntiHorario() {
        if (isEmpty()) {
            System.out.println("A lista esta' vazia");
        } else {
            No e = getInicio();
            int posAtu = 1;
            while (posAtu <= getQtd()) {
                _imprimeElemento(e);
                e = e.getAnterior();
                posAtu++;
            }
        }
    }

    public No getInicio() {
        return inicio;
    }

    public No getUltimo() {
        if (isEmpty()) {
            return null;
        }
        return inicio.getAnterior();
    }

    public boolean isEmpty() {
        return inicio == null;
    }

    public int getQtd() {
        return qtd;
    }

    public void destroi() {
        inicio = null;
        qtd = 0;
    }


    public No buscaPorNumero(int numero) {
        if (isEmpty() || numero <= 0 || numero > qtd) {
            return null;
        }
        int contador = 1;
        No atual = inicio;
        while (contador < numero) {
            atual = atual.getProximo();
            contador++;
        }
        return atual;
    }

    // b. Variável para contabilizar a quantidade de elementos da lista
    public int getQuantidadeElementos() {
        return qtd;
    }

    // c. Método para localizar um elemento pelo seu número (basta a primeira ocorrência)
    public No localizarElemento(int numero) {
        No atual = inicio;
        while (atual != null) {
            if (atual.getId() == numero) {
                return atual;
            }
            atual = atual.getProximo();
        }
        return null;
    }

    // d. Método para inserir um elemento em qualquer posição da lista (o primeiro é o de número 1)
    public void inserirElemento(int posicao, No elemento) {
        if (posicao < 1 || posicao > qtd + 1) {
            System.out.println("Posição inválida.");
            return;
        }
        if (posicao == 1) {
            insereInicio(elemento);
        } else if (posicao == qtd + 1) {
            insereUltimo(elemento);
        } else {
            No atual = inicio;
            for (int i = 1; i < posicao; i++) {
                atual = atual.getProximo();
            }
            elemento.setProximo(atual);
            elemento.setAnterior(atual.getAnterior());
            atual.getAnterior().setProximo(elemento);
            atual.setAnterior(elemento);
            qtd++;
        }
    }

    // e. Método para remover um elemento de qualquer posição da lista
    public void removerElemento(int posicao) {
        if (posicao < 1 || posicao > qtd) {
            System.out.println("Posição inválida.");
            return;
        }
        if (posicao == 1) {
            removeInicio();
        } else if (posicao == qtd) {
            removeFim();
        } else {
            No atual = inicio;
            for (int i = 1; i < posicao; i++) {
                atual = atual.getProximo();
            }
            atual.getAnterior().setProximo(atual.getProximo());
            atual.getProximo().setAnterior(atual.getAnterior());
            atual.setProximo(null);
            atual.setAnterior(null);
            qtd--;
        }
    }


}

