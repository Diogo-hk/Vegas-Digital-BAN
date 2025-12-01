package Dados;

public class MovimentacaoFinanceira {
    private int idMovimentacao;
    private int idCassino;
    private int idUsuario;
    private String tipoTransacao; // Saque ou Depósito
    private double valor;
    private String dataHora;
    private String metodoPagamento; // Cartão, Pix, etc.

    // Construtor
    public MovimentacaoFinanceira(int idMovimentacao, int idCassino, int idUsuario, String tipoTransacao, double valor, String dataHora, String metodoPagamento) {
        this.idMovimentacao = idMovimentacao;
        this.idCassino = idCassino;
        this.idUsuario = idUsuario;
        this.tipoTransacao = tipoTransacao;
        this.valor = valor;
        this.dataHora = dataHora;
        this.metodoPagamento = metodoPagamento;
    }

    // Getters e Setters
    public int getIdMovimentacao() {
        return idMovimentacao;
    }

    public void setIdMovimentacao(int idMovimentacao) {
        this.idMovimentacao = idMovimentacao;
    }

    public int getIdCassino() {
        return idCassino;
    }

    public void setIdCassino(int idCassino) {
        this.idCassino = idCassino;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(String tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    @Override
    public String toString() {
        return "MovimentacaoFinanceira{" +
                "idMovimentacao=" + idMovimentacao +
                ", idCassino=" + idCassino +
                ", idUsuario=" + idUsuario +
                ", tipoTransacao='" + tipoTransacao + '\'' +
                ", valor=" + valor +
                ", dataHora='" + dataHora + '\'' +
                ", metodoPagamento='" + metodoPagamento + '\'' +
                '}';
    }
}