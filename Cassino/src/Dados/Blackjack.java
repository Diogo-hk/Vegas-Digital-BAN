package Dados;

public class Blackjack extends Jogos{
    private int quantidadeBaralhos;

    public Blackjack(int idJogos, float taxaRTP, int quantidadeBaralhos) {
        super(idJogos, taxaRTP);
        this.quantidadeBaralhos = quantidadeBaralhos;
    }

    public int getQuantidadeBaralhos() {
        return quantidadeBaralhos;
    }

    public void setQuantidadeBaralhos(int quantidadeBaralhos) {
        this.quantidadeBaralhos = quantidadeBaralhos;
    }
}
