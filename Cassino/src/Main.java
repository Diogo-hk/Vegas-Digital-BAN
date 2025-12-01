import controller.LoginController; // Importe o controller
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoginController controller = new LoginController();

        System.out.println("=== SISTEMA DE CASSINO ===");
        System.out.print("Digite seu Email: ");
        String email = scanner.nextLine();

        System.out.print("Digite sua Senha: ");
        String password = scanner.nextLine();
        controller.entrarNoSistema(email, password);

        scanner.close();
    }
}