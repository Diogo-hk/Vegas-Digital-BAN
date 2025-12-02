import Controller.UsuarioController;
import View.CadastroView;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioController controller = new UsuarioController();
        int opcao = 9;
        System.out.println("=== CASSINO VEGAS ===");
        System.out.println("1 - Login");
        System.out.println("2 - Cadastrar");
        System.out.print("Opção: ");

        while (opcao != 0) {
            opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                System.out.print("Email: ");
                String email = scanner.nextLine();

                System.out.print("Senha: ");
                String senha = scanner.nextLine();

                controller.entrarNoSistema(email, senha);

            } else if (opcao == 2) {
                new CadastroView().exibirFormulario();
            } else if (opcao == 0) {
                break;
            }
             else {
                System.out.println("Opção inválida");
            }
        }
        scanner.close();
    }
}