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
        c.setNome(nome);
        c.setCnpj(cnpj);
        c.setCep("00000-000"); // CEP fixo para simplificar (ou peça no scanner)
        c.setCidade(cidade);
        c.setBanca(banca);

        repository.inserir(c);
    }

    public void remover(int id) {
        repository.remover(id);
    }

    public void listar() {
        List<CassinoBean> lista = repository.listarTodos();
        System.out.println("\n--- LISTA DE CASSINOS ---");

        if (lista.isEmpty()) {
            System.out.println("Nenhum cassino cadastrado.");
        } else {
            for (CassinoBean c : lista) {
                System.out.println("ID: " + c.getIdCassino() +
                        " | Nome: " + c.getNome() +
                        " | Cidade: " + c.getCidade() +
                        " | Banca: R$ " + c.getBanca());
            }
        }
    }

    public void exibirPatrimonio() {
        double total = repository.calcularPatrimonioTotal();
        System.out.println("\n==========================================");
        System.out.println(" RELATÓRIO FINANCEIRO (Soma das Bancas)");
        System.out.println(" Total em Caixa: R$ " + total);
        System.out.println("==========================================");
    }
}