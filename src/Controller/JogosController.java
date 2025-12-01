package Controller;

import java.util.List;
import Model.JogosBean;
import Repository.JogosRepository;
import Util.Conexao;

public class JogosController {
    private JogosRepository repository;

    public JogosController() {
        this.repository = new JogosRepository(new Conexao().getConnection());
    }

    public void cadastrar(int tipo, double rtp, String dadoTexto, int dadoInt) {
        // Tipo: 1=Roleta, 2=Poker, 3=Blackjack
        repository.cadastrarJogoEspecifico(rtp, tipo, dadoTexto, dadoInt);
    }

    public void listar() {
        List<JogosBean> lista = repository.listarTodos();
        System.out.println("\n--- JOGOS DISPONÍVEIS ---");
        if (lista.isEmpty()) {
            System.out.println("Nenhum jogo cadastrado.");
        } else {
            for (JogosBean j : lista) {
                System.out.println("ID: " + j.getIdJogos() + " | Jogo: " + j.getNome() + " | RTP: " + j.getTaxaRTP() + "%");
            }
        }
    }
}