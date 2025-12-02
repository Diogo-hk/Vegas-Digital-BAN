package Model;

public class SessaoBean {
    private int idSessao;
    private int idJogos;
    private int idCassino;
    private String nomeSessao;

    public SessaoBean() {}

    public int getIdSessao() { return idSessao; }
    public void setIdSessao(int idSessao) { this.idSessao = idSessao; }
    public int getIdJogos() { return idJogos; }
    public void setIdJogos(int idJogos) { this.idJogos = idJogos; }
    public int getIdCassino() { return idCassino; }
    public void setIdCassino(int idCassino) { this.idCassino = idCassino; }
    public String getNomeSessao() { return nomeSessao; }
    public void setNomeSessao(String nomeSessao) { this.nomeSessao = nomeSessao; }
}