package View;

import Controller.UsuarioController;
import java.util.Scanner;

public class CadastroView {
    private UsuarioController controller;

    public CadastroView() {
        this.controller = new UsuarioController();
    }

    public void exibirFormulario() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n=== CADASTRO ===");

        System.out.println("Nome: ");
        String nome = sc.nextLine();

        System.out.println("CPF: ");
        String cpf = sc.nextLine();

        System.out.println("Email: ");
        String email = sc.nextLine();

        System.out.println("Senha: ");
        String senha = sc.nextLine();
        controller.cadastrarUsuario(nome, cpf, email, senha);
    }
}