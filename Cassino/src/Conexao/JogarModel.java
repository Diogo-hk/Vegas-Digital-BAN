package Conexao;

import Dados.Jogar;
import java.sql.*;
import java.util.HashSet;

public class JogarModel {
    public static void create(Jogar j, Connection con) throws SQLException {
        String sql = "INSERT INTO Jogar (idSessao, idUsuario, valorApostado, retorno, dataHora) VALUES (?,?,?,?,?)";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, j.getIdSessao());
        st.setInt(2, j.getIdUsuario());
        st.setDouble(3, j.getValorApostado());
        st.setDouble(4, j.getRetorno());
        st.setString(5, j.getDataHora()); // YYYY-MM-DD HH:MM:SS
        st.execute();
        st.close();
    }

    public static HashSet<Jogar> listAll(Connection con) throws SQLException {
        HashSet<Jogar> list = new HashSet<>();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT idSessao, idUsuario, valorApostado, retorno, dataHora FROM Jogar");
        while(rs.next()) {
            list.add(new Jogar(
                rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getDouble(4), rs.getString(5)
            ));
        }
        return list;
    }

    public static void delete(int idSessao, int idUsuario, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("DELETE FROM Jogar WHERE idSessao = ? AND idUsuario = ?");
        st.setInt(1, idSessao);
        st.setInt(2, idUsuario);
        st.execute();
        st.close();
    }
}
