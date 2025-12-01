package View;

import Controller.CassinoController;
import Controller.SessaoController;
import Controller.JogosController; // Importe o controller
import java.util.Scanner;

public class AdminView {

    private CassinoController cassinoController;
    private SessaoController sessaoController;
    private JogosController jogosController; // Novo atributo

    public AdminView() {
        this.cassinoController = new CassinoController();
        this.sessaoController = new SessaoController();
        this.jogosController = new JogosController(); // Instancia
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
            System.out.println("5 - Relatório Financeiro");
            System.out.println("6 - Gerenciar Jogos (Completo)"); // Novo Menu
            System.out.println("9 - Sair");
            System.out.print("Opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.nextLine();
                opcao = 0;
            }

            switch (opcao) {
                case 1:
                    // ... (código cassino igual) ...
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
                    // Agora listamos os jogos antes de pedir o ID
                    System.out.println("--- ESCOLHA O CASSINO ---");
                    cassinoController.listar();
                    System.out.print("ID Cassino: "); int idC = scanner.nextInt();

                    System.out.println("--- ESCOLHA O JOGO ---");
                    jogosController.listar(); // Lista jogos vindos do banco
                    System.out.print("ID Jogo: "); int idJ = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Nome da Mesa: "); String mesa = scanner.nextLine();
                    sessaoController.abrirSessao(idC, idJ, mesa);
                    break;
                case 5:
                    cassinoController.exibirPatrimonio();
                    break;

                case 6: // GERENCIAR JOGOS
                    System.out.println("\n1 - Cadastrar Novo Jogo | 2 - Listar Jogos");
                    int sub = scanner.nextInt();
                    scanner.nextLine();

                    if (sub == 1) {
                        System.out.println("QUAL O TIPO?");
                        System.out.println("1 - Roleta | 2 - Poker | 3 - Blackjack");
                        int tipo = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Taxa RTP (ex: 95,5): ");
                        double rtp = scanner.nextDouble();
                        scanner.nextLine();

                        String texto = "";
                        int numero = 0;

                        if (tipo == 1) {
                            System.out.print("Tipo da Roleta (ex: Europeia): ");
                            texto = scanner.nextLine();
                        } else if (tipo == 2) {
                            System.out.print("Tipo Poker (ex: Texas): ");
                            texto = scanner.nextLine();
                        } else if (tipo == 3) {
                            System.out.print("Qtd Baralhos (ex: 6): ");
                            numero = scanner.nextInt();
                        }

                        jogosController.cadastrar(tipo, rtp, texto, numero);
                    } else {
                        jogosController.listar();
                    }
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