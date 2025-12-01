import Conexao.Conexao;
import Controller.CassinoController;
import Controller.FuncionarioController;
import Controller.JogosController;
import Controller.MovimentacaoFinanceiraController;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Bem-vindo ao Sistema Vegas Digital!");
        Conexao c = new Conexao();
        Connection con = c.getConnection();
        int op = 0;
        do {
            op = menu();
            try {
                switch (op) {
                    case 1:
                        new JogosController().createJogosController(con);
                        break;
                    case 2:
                        new JogosController().listarJogos(con);
                        break;
                    case 3:
                        new CassinoController().createCassinoController(con);
                        break;
                    case 4:
                        new CassinoController().listarCassinos(con);
                        break;
                    case 5:
                        new FuncionarioController().createFuncionarioController(con);
                        break;
                    case 6:
                        new FuncionarioController().listarFuncionarios(con);
                        break;
                    case 7:
                        new MovimentacaoFinanceiraController().createMovimentacaoController(con);
                        break;
                    case 8:
                        new MovimentacaoFinanceiraController().listarMovimentacoes(con);
                        break;
                    default:
                        System.out.println("Saindo...");
                        break;
                }
            } catch (SQLException ex) {
                System.out.println("Erro na operação: " + ex.getMessage());
                // ex.printStackTrace(); // Descomente para debug se necessário
            }
        } while (op > 0 && op < 9);

        c.closeConnection(); // É boa prática fechar a conexão pelo objeto wrapper se disponível, ou con.close()
    }

    private static int menu() {
        System.out.println("");
        System.out.println("---- MENU PRINCIPAL ----");
        System.out.println("Informe o número da opção que desejas executar: ");
        System.out.println("1 - Inserir um novo Jogo");
        System.out.println("2 - Listar todos os Jogos");
        System.out.println("3 - Inserir um novo Cassino");
        System.out.println("4 - Listar todos os Cassinos");
        System.out.println("5 - Inserir um novo Funcionário");
        System.out.println("6 - Listar todos os Funcionários");
        System.out.println("7 - Inserir uma nova Movimentação Financeira");
        System.out.println("8 - Listar todas as Movimentações Financeiras");
        System.out.println("Digite qualquer outro valor para sair");
        System.out.print("Sua opção: ");
        Scanner input = new Scanner(System.in);
        // Tratamento simples para evitar erro se o usuário digitar texto
        if (input.hasNextInt()) {
            return input.nextInt();
        } else {
            return 0; // Retorna 0 para sair se não for número
        }
    }
}