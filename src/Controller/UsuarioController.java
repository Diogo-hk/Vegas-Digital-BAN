package Controller;

import java.sql.Connection;
import Model.UsuarioBean;
import Repository.UsuarioRepository;
import Util.Conexao;
import View.AdminView;

public class UsuarioController {

    private UsuarioRepository repository;
    private Connection conexao;

    public UsuarioController() {
        Conexao util = new Conexao();
        this.conexao = util.getConnection();
        this.repository = new UsuarioRepository(this.conexao);
    }

    // Lógica de Login
    public void entrarNoSistema(String email, String senha) {
        UsuarioBean usuario = repository.autenticar(email, senha);

        if (usuario == null) {
            System.out.println("ERRO: Login inválido!");
        } else {
            System.out.println("SUCESSO: Bem-vindo, " + usuario.getNomeUsuario());
            if ("ADMIN".equalsIgnoreCase(usuario.getStatus())) {
                System.out.println(">> Acessando Painel ADMIN...");
                new View.AdminView().exibirMenuAdmin(usuario.getNomeUsuario());
            } else {
                System.out.println(">> Acessando Jogos...");
            }
        }
    }

    public boolean cadastrarUsuario(String nome, String cpf, String email, String senha) {
        UsuarioBean novoUsuario = new UsuarioBean();
        novoUsuario.setNomeUsuario(nome);
        novoUsuario.setCpf(cpf);
        novoUsuario.setEmail(email);
        novoUsuario.setSenha(senha);

        if (repository.salvar(novoUsuario)) {
            System.out.println("CADASTRO REALIZADO! Agora faça login.");
            return true;
        } else {
            System.out.println("ERRO AO CADASTRAR. Tente novamente.");
            return false;
        }
    }
}