package View;

import Controller.JogarController;
import Controller.SessaoController;
import Model.UsuarioBean;
import java.util.Scanner;

public class UsuarioComumView {
    private SessaoController sessaoController;
    private JogarController jogarController;

    public UsuarioComumView() {
        this.sessaoController = new SessaoController();
        this.jogarController = new JogarController();
    }

    public void exibirUsuarioMenu(UsuarioBean usuario) {
        int opcao = 1;
        Scanner sc = new Scanner(System.in);

        while (opcao != 0) {
            double saldo = jogarController.buscarSaldoFoda(usuario.getIdUsuario());
            System.out.println("\n=== USUÁRIO: " + usuario.getNomeUsuario() + " ===");
            System.out.println("DINHEIRO: R$ " + saldo);
            System.out.println("1 - Depositar");
            System.out.println("2 - Jogar (Entrar Sessão)");
            System.out.println("3 - Ver Mesas");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = sc.nextInt();

            if (opcao == 1) {
                System.out.print("Valor: ");
                jogarController.realizaDeposito(usuario.getIdUsuario(), sc.nextDouble());
            }
            else if (opcao == 2) {
                sessaoController.listar();
                System.out.print("ID Sessão: ");
                int sessao = sc.nextInt();
                System.out.print("Aposta: ");
                double aposta = sc.nextDouble();
                System.out.print("Número (0-36): ");
                int num = sc.nextInt(); sc.nextLine();
                jogarController.apostar(sessao, usuario.getIdUsuario(), aposta, num);
            }
            else if(opcao == 3) {
                sessaoController.listar();
            }
        }
    }
}