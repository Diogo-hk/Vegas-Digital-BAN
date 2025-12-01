package Controller;

import Conexao.PagamentoSalarioModel;
import Dados.PagamentoSalario;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class PagamentoSalarioController {
    public void createPagamento(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("---- PAGAMENTO SALÁRIO ----");
        System.out.print("ID Pagamento: "); int id = input.nextInt();
        System.out.print("ID Cassino: "); int idCassino = input.nextInt();
        System.out.print("ID Funcionário: "); int idFunc = input.nextInt();
        System.out.print("Valor: "); double valor = input.nextDouble();
        input.nextLine();
        System.out.print("Data (YYYY-MM-DD): "); String data = input.nextLine();

        PagamentoSalario p = new PagamentoSalario(id, idCassino, idFunc, valor, data);
        PagamentoSalarioModel.create(p, con);
        System.out.println("Pagamento registrado!");
    }

    public void listarPagamentos(Connection con) throws SQLException {
        for(PagamentoSalario p : PagamentoSalarioModel.listAll(con)) {
            System.out.println(p);
        }
    }
}