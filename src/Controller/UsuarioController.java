package Controller;

import java.sql.Connection;
import java.util.List;
import Model.UsuarioBean;
import Repository.UsuarioRepository;
import Util.Conexao;

public class UsuarioController {
    private UsuarioRepository repository;
    private Connection conexao;

    public UsuarioController() {
        Conexao util = new Conexao();
        this.conexao = util.getConnection();
        this.repository = new UsuarioRepository(this.conexao);
    }

    public void entrarNoSistema(String email, String senha) {
        UsuarioBean usuario = repository.autenticar(email, senha);
        if (usuario == null) {
            System.out.println("ERRO: Login inválido!");
        } else {
            System.out.println("SUCESSO: Bem-vindo, " + usuario.getNomeUsuario());
            if ("ADMIN".equalsIgnoreCase(usuario.getStatus())) {
                new View.AdminView().exibirMenuAdmin(usuario.getNomeUsuario());
            } else {
                new View.UsuarioComumView().exibirUsuarioMenu(usuario);
            }
        }
    }

    public boolean cadastrarUsuario(String nome, String cpf, String email, String senha) {
        UsuarioBean novo = new UsuarioBean();
        novo.setNomeUsuario(nome); novo.setCpf(cpf); novo.setEmail(email); novo.setSenha(senha);
        return repository.salvar(novo);
    }

    public void listarUsuarios() {
        List<UsuarioBean> lista = repository.listarTodos();
        System.out.println("\n--- LISTA DE USUÁRIOS ---");
        for(UsuarioBean u : lista) {
            System.out.println("ID: " + u.getIdUsuario() + " | Nome: " + u.getNomeUsuario() + " | Status: " + u.getStatus());
        }
    }

    public void removerUsuario(int id) {
        repository.remover(id);
    }
}