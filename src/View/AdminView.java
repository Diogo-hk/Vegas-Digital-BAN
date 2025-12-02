package View;

import Controller.*;
import java.util.Scanner;

public class AdminView {
    private CassinoController cassinoController;
    private SessaoController sessaoController;
    private JogosController jogosController;
    private UsuarioController usuarioController;
    private JogarController jogarController;

    public AdminView() {
        this.cassinoController = new CassinoController();
        this.sessaoController = new SessaoController();
        this.jogosController = new JogosController();
        this.usuarioController = new UsuarioController();
        this.jogarController = new JogarController();
    }

    public void exibirMenuAdmin(String nome) {
        Scanner sc = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 9) {
            System.out.println("\n=== ADMIN: " + nome + " ===");
            System.out.println("1 - Cassinos");
            System.out.println("2 - Jogos");
            System.out.println("3 - Sessões");
            System.out.println("4 - Usuários");
            System.out.println("5 - Histórico/Apostas");
            System.out.println("6 - Relatório Financeiro");
            System.out.println("9 - Sair");
            System.out.print("Opção: ");

            opcao = sc.nextInt(); sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("1-Novo | 2-Remover | 3-Listar");
                    int c = sc.nextInt();
                    sc.nextLine();

                    if(c==1) {
                        System.out.print("Nome:");
                        String nomeCassino = sc.nextLine();

                        System.out.print("CNPJ: ");
                        String cnpj = sc.nextLine();

                        System.out.print("Cidade: ");
                        String cidade = sc.nextLine();

                        System.out.print("Banca: ");
                        double banca = sc.nextDouble();
                        cassinoController.cadastrar(nomeCassino, cnpj, cidade, banca);
                    } else if(c==2) {
                        cassinoController.listar();
                        System.out.print("ID: ");
                        cassinoController.remover(sc.nextInt());
                    } else cassinoController.listar();
                    break;
                case 2:
                    System.out.println("1-Novo | 2-Remover | 3-Listar");
                    int j = sc.nextInt(); sc.nextLine();
                    if(j==1) {
                        System.out.println("1-Roleta | 2-Poker | 3-Blackjack");
                        int escolhaJogo = sc.nextInt();

                        System.out.print("RTP: ");
                        double taxaRetorno = sc.nextDouble(); sc.nextLine();

                        System.out.print("Detalhe (Nome ou Qtd): ");
                        String detalheJogo = sc.nextLine();

                        int di = 0;

                        try {
                            di = Integer.parseInt(detalheJogo);
                        } catch(Exception e){}
                        jogosController.cadastrarComplexo(escolhaJogo, taxaRetorno, detalheJogo, di);

                    } else if(j==2) {
                        jogosController.listar();
                        System.out.print("ID: ");
                        jogosController.remover(sc.nextInt());
                    } else {
                        jogosController.listar();
                    }
                    break;

                case 3:
                    System.out.println("1-Abrir | 2-Fechar | 3-Listar");
                    int s = sc.nextInt();
                    sc.nextLine();
                    if(s==1) {
                        System.out.print("ID Cassino: ");
                        int idCassino = sc.nextInt();

                        System.out.print("ID Jogo: ");
                        int idJogo = sc.nextInt(); sc.nextLine();

                        System.out.print("Nome: ");
                        String nomeJogo = sc.nextLine();

                        sessaoController.abrirSessao(idCassino, idJogo, nomeJogo);
                    } else if(s==2) {
                        sessaoController.listar();
                        System.out.print("ID: ");

                        sessaoController.remover(sc.nextInt());
                    } else {
                        sessaoController.listar();
                    }
                    break;
                case 4:
                    System.out.println("1-Listar | 2-Banir");
                    int u = sc.nextInt();
                    if (u==1) {
                        usuarioController.listarUsuarios();
                    }
                    else if(u==2) {
                        usuarioController.listarUsuarios();
                        System.out.print("ID para Banir: ");
                        usuarioController.removerUsuario(sc.nextInt());
                    }
                    break;
                case 5:
                    System.out.println("1-Listar Tudo | 2-Limpar Histórico Sessão");
                    int h = sc.nextInt();
                    if(h==1) {
                        jogarController.listarHistorico();
                    }
                    else {
                        System.out.print("ID Sessão: ");
                        jogarController.apagarHistorico(sc.nextInt());
                    }
                    break;
                case 6:
                    cassinoController.exibirPatrimonio();
                    break;
            }
        }
    }
}