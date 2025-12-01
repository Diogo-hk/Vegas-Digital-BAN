package Dados;

public class Jogos {
    private int idJogos;
    private float taxaRTP;

    public Jogos(int idJogos, float taxaRTP) {
        this.idJogos = idJogos;
        this.taxaRTP = taxaRTP;
    }

    public int getIdJogos() {
        return idJogos;
    }

    public void setIdJogos(int idJogos) {
        this.idJogos = idJogos;
    }

    public float getTaxaRTP() {
        return taxaRTP;
    }

    public void setTaxaRTP(float taxaRTP) {
        this.taxaRTP = taxaRTP;
    }

    @Override
    public String toString() {
        return "Jogos{" +
                "idJogos=" + idJogos +
                ", taxaRTP=" + taxaRTP +
                '}';
    }
}
