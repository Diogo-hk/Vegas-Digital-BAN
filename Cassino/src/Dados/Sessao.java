package Dados;

public class Sessao {
    private int idSessao;
    private int idJogos;
    private int idCassino;
    private String nomeSessao;

    public Sessao(int idSessao, int idJogos, int idCassino, String nomeSessao) {
        this.idSessao = idSessao;
        this.idJogos = idJogos;
        this.idCassino = idCassino;
        this.nomeSessao = nomeSessao;
    }

    // Getters e Setters
    public int getIdSessao() { return idSessao; }
    public void setIdSessao(int idSessao) { this.idSessao = idSessao; }
    public int getIdJogos() { return idJogos; }
    public void setIdJogos(int idJogos) { this.idJogos = idJogos; }
    public int getIdCassino() { return idCassino; }
    public void setIdCassino(int idCassino) { this.idCassino = idCassino; }
    public String getNomeSessao() { return nomeSessao; }
    public void setNomeSessao(String nomeSessao) { this.nomeSessao = nomeSessao; }

    @Override
    public String toString() {
        return "Sessao{id=" + idSessao + ", jogo=" + idJogos + ", cassino=" + idCassino + ", nome='" + nomeSessao + "'}";
    }
}