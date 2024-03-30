class Casa extends No {
    private int numero;
    private int status; // 0 = livre, 1 = marcada, 2 = proprietária

    public Casa(int numero) {
        this.numero = numero;
        this.status = 0; // Inicialmente a casa está livre
    }

    public int getNumero() {
        return numero;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
