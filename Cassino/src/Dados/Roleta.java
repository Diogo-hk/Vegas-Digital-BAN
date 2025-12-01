package Dados;

public class Roleta extends Jogos{

    private tipoRoleta tipoRoleta;

    public Roleta(int idJogos, float taxaRTP, Dados.tipoRoleta tipoRoleta) {
        super(idJogos, taxaRTP);
        this.tipoRoleta = tipoRoleta;
    }

    public Dados.tipoRoleta getTipoRoleta() {
        return tipoRoleta;
    }

    public void setTipoRoleta(Dados.tipoRoleta tipoRoleta) {
        this.tipoRoleta = tipoRoleta;
    }
}
