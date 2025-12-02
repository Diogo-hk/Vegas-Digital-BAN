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

    public void cadastrarComplexo(int tipo, double rtp, String dadoTexto, int dadoInt) {
        repository.cadastrarJogoEspecifico(rtp, tipo, dadoTexto, dadoInt);
    }

    public void listar() {
        List<JogosBean> lista = repository.listarTodos();
        System.out.println("\n--- JOGOS ---");
        for (JogosBean j : lista) {
            System.out.println("ID: " + j.getIdJogos() + " | " + j.getNome() + " | RTP: " + j.getTaxaRTP() + "%");
        }
    }

    public void remover(int id) { repository.remover(id); }
}