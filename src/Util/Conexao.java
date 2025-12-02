package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private Connection con;

    public Conexao() {
        String url = "jdbc:postgresql://localhost:5432/TrabalhoBAN";
        String user = "postgres";
        String senha = "diogo4545";

        try {
            Class.forName("org.postgresql.Driver");
            this.con = DriverManager.getConnection(url, user, senha);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("FATAL: Erro na Conexão com o Banco.");
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return con;
    }
}