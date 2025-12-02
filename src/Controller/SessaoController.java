package Controller;

import Model.SessaoBean;
import Repository.SessaoRepository;
import Util.Conexao;
import java.util.List;

public class SessaoController {
    private SessaoRepository repository;

    public SessaoController() {
        this.repository = new SessaoRepository(new Conexao().getConnection());
    }

    public void abrirSessao(int idCassino, int idJogo, String nome) {
        SessaoBean s = new SessaoBean();
        s.setIdCassino(idCassino);
        s.setIdJogos(idJogo);
        s.setNomeSessao(nome);

        repository.criarSessao(s);
    }

    public void listar() {
        List<SessaoBean> lista = repository.listarSessoes();
        System.out.println("\n--- SESSÕES ---");
        for (SessaoBean s : lista) {
            System.out.println("ID: " + s.getIdSessao() + " | Mesa: " + s.getNomeSessao());
        }
    }

    public void remover(int id) { repository.remover(id); }
}