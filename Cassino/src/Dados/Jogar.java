package Dados;

public class Jogar {
    private int idSessao;
    private int idUsuario;
    private double valorApostado;
    private double retorno;
    private String dataHora;

    public Jogar(int idSessao, int idUsuario, double valorApostado, double retorno, String dataHora) {
        this.idSessao = idSessao;
        this.idUsuario = idUsuario;
        this.valorApostado = valorApostado;
        this.retorno = retorno;
        this.dataHora = dataHora;
    }

    // Getters e Setters simplificados
    public int getIdSessao() { return idSessao; }
    public int getIdUsuario() { return idUsuario; }
    public double getValorApostado() { return valorApostado; }
    public double getRetorno() { return retorno; }
    public String getDataHora() { return dataHora; }

    @Override
    public String toString() {
        return "Jogar{Sessao=" + idSessao + ", User=" + idUsuario + ", Aposta=" + valorApostado + ", Retorno=" + retorno + "}";
    }
}