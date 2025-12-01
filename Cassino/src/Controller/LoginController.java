package controller;

import java.sql.Connection;
import model.UsuarioBean;
import repository.UsuarioRepository;
import util.Conexao;

public class LoginController {

    private UsuarioRepository repository;
    private Connection conexao;

    //faz as conexoes
    public LoginController() {
        Conexao util = new Conexao();
        this.conexao = util.getConnection();
        this.repository = new UsuarioRepository(this.conexao);
    }

    public void entrarNoSistema(String email, String senha) {
        UsuarioBean usuario = repository.autenticar(email, senha);

        if (usuario == null) {
            System.out.println("Login falhou! Verifique email e senha.");
        } else {
            System.out.println("--------------------------------");
            System.out.println("LOGIN REALIZADO COM SUCESSO!");
            System.out.println("Bem-vindo(a), " + usuario.getNomeUsuario());
            System.out.println("Seu Saldo: R$ " + usuario.getSaldo());
            System.out.println("--------------------------------");

            if (usuario.getStatus() != null && usuario.getStatus().equalsIgnoreCase("ADMIN")) {
                System.out.println(">> Painel de Administrador Carregado.");
                // Aqui você chamaria: new AdminView().setVisible(true);
            } else {
                System.out.println(">> Painel de Jogador Carregado.");
                // Aqui você chamaria: new JogosView(usuario).setVisible(true);
            }
        }
    }
}