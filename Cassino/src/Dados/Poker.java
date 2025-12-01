package Dados;

public class Poker extends Jogos{
    private tipoPoker tipoPoker;

    public Poker(int idJogos, float taxaRTP, Dados.tipoPoker tipoPoker) {
        super(idJogos, taxaRTP);
        this.tipoPoker = tipoPoker;
    }

    public Dados.tipoPoker getTipoPoker() {
        return tipoPoker;
    }

    public void setTipoPoker(Dados.tipoPoker tipoPoker) {
        this.tipoPoker = tipoPoker;
    }
}
