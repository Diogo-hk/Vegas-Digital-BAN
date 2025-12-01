package Controller;

import Conexao.SessaoModel;
import Dados.Sessao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class SessaoController {
    public void createSessao(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("---- NOVA SESSÃO ----");
        System.out.print("ID Sessão: "); int id = input.nextInt();
        System.out.print("ID Jogo: "); int idJogo = input.nextInt();
        System.out.print("ID Cassino: "); int idCassino = input.nextInt();
        input.nextLine(); // limpar buffer
        System.out.print("Nome da Sessão: "); String nome = input.nextLine();

        Sessao s = new Sessao(id, idJogo, idCassino, nome);
        SessaoModel.create(s, con);
        System.out.println("Sessão criada com sucesso!");
    }

    public void listarSessao(Connection con) throws SQLException {
        for(Sessao s : SessaoModel.listAll(con)) {
            System.out.println(s);
        }
    }
}