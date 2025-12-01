package View;

import Controller.CassinoController;
import Controller.SessaoController;
import java.util.Scanner;

public class AdminView {

    private CassinoController cassinoController;
    private SessaoController sessaoController;

    public AdminView() {
        this.cassinoController = new CassinoController();
        this.sessaoController = new SessaoController();
    }

    public void exibirMenuAdmin(String nomeAdmin) {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 9) {
            System.out.println("\n=== PAINEL ADMIN: " + nomeAdmin + " ===");
            System.out.println("1 - Inserir Cassino");
            System.out.println("2 - Remover Cassino");
            System.out.println("3 - Listar Cassinos");
            System.out.println("4 - Abrir Nova Sessão");
            System.out.println("5 - Relatório Financeiro (Soma Bancas)");
            System.out.println("9 - Sair");
            System.out.print("Opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpa buffer
            } else {
                scanner.nextLine();
                opcao = 0;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Nome: "); String nome = scanner.nextLine();
                    System.out.print("CNPJ: "); String cnpj = scanner.nextLine();
                    System.out.print("Cidade: "); String cid = scanner.nextLine();
                    System.out.print("Banca: "); double banca = scanner.nextDouble();
                    cassinoController.cadastrar(nome, cnpj, cid, banca);
                    break;
                case 2:
                    cassinoController.listar();
                    System.out.print("ID para remover: ");
                    int idRemove = scanner.nextInt();
                    cassinoController.remover(idRemove);
                    break;
                case 3:
                    cassinoController.listar();
                    break;
                case 4:
                    System.out.print("ID Cassino: "); int idC = scanner.nextInt();
                    System.out.print("ID Jogo: "); int idJ = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nome da Mesa: "); String mesa = scanner.nextLine();
                    sessaoController.abrirSessao(idC, idJ, mesa);
                    break;
                case 5:
                    cassinoController.exibirPatrimonio();
                    break;
                case 9:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Inválido!");
            }
        }
    }
}