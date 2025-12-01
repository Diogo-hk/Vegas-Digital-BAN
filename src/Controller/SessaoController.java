package Controller;

import Model.SessaoBean;
import Repository.SessaoRepository;
import Util.Conexao;

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
}