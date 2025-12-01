package Controller;

import Conexao.CassinoModel;
import Dados.Cassino;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class CassinoController {
    public void createCassinoController(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os seguintes dados para criar um novo Cassino: ");

        System.out.print("idCassino: ");
        int idCassino = input.nextInt();
        input.nextLine(); // Consumir quebra de linha

        System.out.print("CNPJ: ");
        String cnpj = input.nextLine();

        System.out.print("Nome: ");
        String nome = input.nextLine();

        System.out.print("CEP: ");
        String cep = input.nextLine();

        System.out.print("Cidade: ");
        String cidade = input.nextLine();

        System.out.print("Banca Inicial: ");
        double banca = input.nextDouble();

        Cassino cassino = new Cassino(idCassino, cnpj, nome, cep, cidade, banca);
        CassinoModel.create(cassino, con);
        System.out.println("Cassino criado com sucesso!!");
    }

    public void listarCassinos(Connection con) throws SQLException {
        HashSet all = CassinoModel.listAll(con);
        Iterator<Cassino> it = all.iterator();
        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }
}