package Controller;

import Conexao.JogosModel;
import Dados.Jogos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JogosController {
    public void createJogosController(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os seguintes dados para a criar um novo Jogo: ");
        System.out.print("idJogos: ");
        int idJogos = input.nextInt();
        System.out.print("taxa: ");
        int taxaRTP = input.nextInt();
        Jogos jg = new Jogos(idJogos, taxaRTP);
        JogosModel.create(jg, con);
        System.out.println("Jogo criado com sucesso!!");
    }
}
