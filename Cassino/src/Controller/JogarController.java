package Controller;

import Conexao.JogarModel;
import Dados.Jogar;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class JogarController {
    public void createJogada(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("---- REGISTRAR JOGADA ----");
        System.out.print("ID Sessão: "); int idSessao = input.nextInt();
        System.out.print("ID Usuário: "); int idUser = input.nextInt();
        System.out.print("Valor Apostado: "); double valor = input.nextDouble();
        System.out.print("Retorno (Ganho): "); double retorno = input.nextDouble();
        input.nextLine();
        System.out.print("Data/Hora (YYYY-MM-DD HH:MM:SS): "); String data = input.nextLine();

        Jogar j = new Jogar(idSessao, idUser, valor, retorno, data);
        JogarModel.create(j, con);
        System.out.println("Jogada registrada!");
    }

    public void listarJogadas(Connection con) throws SQLException {
        for(Jogar j : JogarModel.listAll(con)) {
            System.out.println(j);
        }
    }
}