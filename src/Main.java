import Controller.UsuarioController;
import View.CadastroView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        // Inicia o sistema
        System.out.println("Iniciando Sistema do Cassino...");
        UsuarioController controller = new UsuarioController();

        do {

            System.out.println("Escolha uma opção:");
            System.out.println("(1) - Login");
            System.out.println("(2) - Cadastrar-se");
            opcao = scanner.nextInt();

            if(opcao == 1){
                System.out.println("----- TELA DE LOGIN -----");
                System.out.print("Digite seu Email: ");
                scanner.nextLine();
                String email = scanner.nextLine();

                System.out.print("Digite sua Senha: ");
                String senha = scanner.nextLine();

                // Tenta logar
                controller.entrarNoSistema(email, senha);
            }

            else if(opcao == 2){
                new CadastroView().exibirFormulario();
            }

        } while (opcao != 0);
        scanner.close();
    }
}