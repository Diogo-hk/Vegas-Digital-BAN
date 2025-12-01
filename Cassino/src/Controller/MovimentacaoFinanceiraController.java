package Controller;

import Conexao.MovimentacaoFinanceiraModel;
import Dados.MovimentacaoFinanceira;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class MovimentacaoFinanceiraController {
    public void createMovimentacaoController(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os dados da Movimentação Financeira: ");

        System.out.print("idMovimentacao: ");
        int idMovimentacao = input.nextInt();

        System.out.print("idCassino: ");
        int idCassino = input.nextInt();

        System.out.print("idUsuario: ");
        int idUsuario = input.nextInt();
        input.nextLine(); // Consumir quebra de linha

        System.out.print("Tipo Transação (Saque/Deposito): ");
        String tipoTransacao = input.nextLine();

        System.out.print("Valor: ");
        double valor = input.nextDouble();
        input.nextLine(); // Consumir quebra de linha

        System.out.print("Data/Hora (YYYY-MM-DD HH:MM:SS): ");
        String dataHora = input.nextLine();

        System.out.print("Método Pagamento: ");
        String metodoPagamento = input.nextLine();

        MovimentacaoFinanceira mov = new MovimentacaoFinanceira(idMovimentacao, idCassino, idUsuario, tipoTransacao, valor, dataHora, metodoPagamento);
        MovimentacaoFinanceiraModel.create(mov, con);
        System.out.println("Movimentação registrada com sucesso!!");
    }

    public void listarMovimentacoes(Connection con) throws SQLException {
        HashSet all = MovimentacaoFinanceiraModel.listAll(con);
        Iterator<MovimentacaoFinanceira> it = all.iterator();
        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    public void deleteMovimentacao(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("---- DELETAR MOVIMENTAÇÃO FINANCEIRA ----");
        System.out.print("Informe o ID da Movimentação que deseja deletar: ");
        int idMovimentacao = input.nextInt();

        MovimentacaoFinanceiraModel.delete(idMovimentacao, con);
        System.out.println("Movimentação deletada com sucesso!!");
    }
}
