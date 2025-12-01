package Conexao;

import Dados.Sessao;
import java.sql.*;
import java.util.HashSet;

public class SessaoModel {
    public static void create(Sessao s, Connection con) throws SQLException {
        PreparedStatement st = con.prepareStatement("INSERT INTO Sessao (idSessao, idJogos, idCassino, nomeSessao) VALUES (?,?,?,?)");
        st.setInt(1, s.getIdSessao());
        st.setInt(2, s.getIdJogos());
        st.setInt(3, s.getIdCassino());
        st.setString(4, s.getNomeSessao());
        st.execute();
        st.close();
    }

    public static HashSet<Sessao> listAll(Connection con) throws SQLException {
        HashSet<Sessao> list = new HashSet<>();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT idSessao, idJogos, idCassino, nomeSessao FROM Sessao");
        while(rs.next()) {
            list.add(new Sessao(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4)));
        }
        return list;
    }
    public static void delete(int idSessao, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("DELETE FROM Sessao WHERE idSessao = ?");
        st.setInt(1, idSessao);
        st.execute();
        st.close();
    }
}
