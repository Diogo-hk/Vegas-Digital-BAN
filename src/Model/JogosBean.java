package Model;

public class JogosBean {
    private int idJogos;
    private double taxaRTP;
    private String nome;

    public JogosBean() {
    }

    public int getIdJogos() { return idJogos; }
    public void setIdJogos(int idJogos) { this.idJogos = idJogos; }

    public double getTaxaRTP() { return taxaRTP; }
    public void setTaxaRTP(double taxaRTP) { this.taxaRTP = taxaRTP; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}