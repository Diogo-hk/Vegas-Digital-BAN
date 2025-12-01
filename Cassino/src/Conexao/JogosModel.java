package Conexao;

import Dados.Jogos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JogosModel {
    public static void create(Jogos a, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("INSERT INTO Jogos (idJogos, taxaRTP) VALUES (?,?)");
        st.setInt(1, a.getIdJogos());
        st.setInt(2, (int) a.getTaxaRTP());
        st.execute();
        st.close();
    }

    static HashSet listAll(Connection con) throws SQLException {
        Statement st;
        HashSet list = new HashSet();
        st = (Statement) con.createStatement();
        String sql = "SELECT nroa, capacidade, andar FROM ambulatorios";
        ResultSet result = st.executeQuery(sql);
        while(result.next()) {
            list.add(new Jogos(result.getInt(1), result.getInt(2)));
        }
        return list;
    }

}
