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
            // Carrega o driver
            Class.forName("org.postgresql.Driver");
            this.con = DriverManager.getConnection(url, user, senha);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro na Conexão: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return con;
    }

    // Método para fechar conexão (opcional, mas boa prática)
    public void fecharConexao() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}