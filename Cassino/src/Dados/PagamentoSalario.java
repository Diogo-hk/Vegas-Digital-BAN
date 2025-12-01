package Dados;

public class PagamentoSalario {
    private int idPag;
    private int idCassino;
    private int idFunc;
    private double valor;
    private String dataPagamento;

    public PagamentoSalario(int idPag, int idCassino, int idFunc, double valor, String dataPagamento) {
        this.idPag = idPag;
        this.idCassino = idCassino;
        this.idFunc = idFunc;
        this.valor = valor;
        this.dataPagamento = dataPagamento;
    }

    // Getters
    public int getIdPag() { return idPag; }
    public int getIdCassino() { return idCassino; }
    public int getIdFunc() { return idFunc; }
    public double getValor() { return valor; }
    public String getDataPagamento() { return dataPagamento; }

    @Override
    public String toString() {
        return "Pagamento{id=" + idPag + ", Func=" + idFunc + ", Valor=" + valor + ", Data='" + dataPagamento + "'}";
    }
}