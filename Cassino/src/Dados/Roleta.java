package Dados;

public class Roleta extends Jogos{

    private int tipoRoleta;

    public Roleta(int idJogos, float taxaRTP, int tipoRoleta) {
        super(idJogos, taxaRTP);
        this.tipoRoleta = tipoRoleta;
    }

    public int getTipoRoleta() {
        return tipoRoleta;
    }

}
