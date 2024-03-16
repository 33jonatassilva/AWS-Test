

public enum ResultadoOperacao {
    INEXISTENTE("Ops... Elemento inexistente!"),
    BEMSUCEDIDA("Operação executada"),
    FORADAFAIXA("Ops... Valor fora da faixa permitida no vetor"),
    FORADASFRONTEIRAS("Ops... Índice fora dos limites do vetor"),
    PREVISTOOPERACAO("Ops... A posicao já contém o previsto pela operacao"
            + "('ocupada' em caso de inclusao ou 'vaga' em caso de remocao)."),
    VALOREXISTENTE("Ops... Valor já existente no vetor");

    public final String msg;

    private ResultadoOperacao(String msg) {
        this.msg = msg;
    }
}