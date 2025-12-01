package Conexao;

import Dados.Cassino;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

public class CassinoModel {

    public static void create(Cassino c, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("INSERT INTO Cassino (idCassino, CNPJ, nome, cep, cidade, banca) VALUES (?,?,?,?,?,?)");
        st.setInt(1, c.getIdCassino());
        st.setString(2, c.getCNPJ());
        st.setString(3, c.getNome());
        st.setString(4, c.getCep());
        st.setString(5, c.getCidade());
        st.setDouble(6, c.getBanca());
        st.execute();
        st.close();
    }

    public static HashSet listAll(Connection con) throws SQLException {
        Statement st;
        HashSet list = new HashSet();
        st = con.createStatement();
        String sql = "SELECT idCassino, CNPJ, nome, cep, cidade, banca FROM Cassino";
        ResultSet result = st.executeQuery(sql);
        while(result.next()) {
            Cassino c = new Cassino(
                    result.getInt(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getDouble(6)
            );
            list.add(c);
        }
        return list;
    }

    public static void delete(int idCassino, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("DELETE FROM Cassino WHERE idCassino = ?");
        st.setInt(1, idCassino);
        st.execute();
        st.close();
    }
}
