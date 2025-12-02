package Controller;

import java.util.List;
import Model.CassinoBean;
import Repository.CassinoRepository;
import Util.Conexao;

public class CassinoController {
    private CassinoRepository repository;

    public CassinoController() {
        this.repository = new CassinoRepository(new Conexao().getConnection());
    }

    public void cadastrar(String nome, String cnpj, String cidade, double banca) {
        CassinoBean c = new CassinoBean();
        c.setNome(nome); c.setCnpj(cnpj); c.setCep("00000"); c.setCidade(cidade); c.setBanca(banca);
        repository.inserir(c);
    }

    public void remover(int id) {
        repository.remover(id);
    }

    public void listar() {
        List<CassinoBean> lista = repository.listarTodos();
        System.out.println("\n--- LISTA DE CASSINOS ---");
        for (CassinoBean c : lista) {
            System.out.println("ID: " + c.getIdCassino() + " | Nome: " + c.getNome() + " | Banca: " + c.getBanca());
        }
    }

    public void exibirPatrimonio() {
        System.out.println(">>> TOTAL GLOBAL: R$ " + repository.calcularPatrimonioTotal());
    }
}