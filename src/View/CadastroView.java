package View;

import Controller.UsuarioController; // Veja o import correto agora
import java.util.Scanner;

public class CadastroView {

    private UsuarioController controller;

    public CadastroView() {
        this.controller = new UsuarioController();
    }

    public void exibirFormulario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== CADASTRO NOVO ===");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        controller.cadastrarUsuario(nome, cpf, email, senha);
    }
}