package Dados;

public class Poker extends Jogos{
    private int tipoPoker;

    public Poker(int idJogos, float taxaRTP, int tipoPoker) {
        super(idJogos, taxaRTP);
        this.tipoPoker = tipoPoker;
    }

    public int getTipoPoker() {
        return tipoPoker;
    }


}
