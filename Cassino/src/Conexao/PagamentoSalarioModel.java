package Conexao;

import Dados.PagamentoSalario;
import java.sql.*;
import java.util.HashSet;

public class PagamentoSalarioModel {
    public static void create(PagamentoSalario p, Connection con) throws SQLException {
        String sql = "INSERT INTO PagamentoSalario (idPag, idCassino, idFunc, valor, dataPagamento) VALUES (?,?,?,?,?)";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, p.getIdPag());
        st.setInt(2, p.getIdCassino());
        st.setInt(3, p.getIdFunc());
        st.setDouble(4, p.getValor());
        Date dataPagamento = Date.valueOf(p.getDataPagamento());
        st.setDate(5, dataPagamento);
        st.execute();
        st.close();
    }

    public static HashSet<PagamentoSalario> listAll(Connection con) throws SQLException {
        HashSet<PagamentoSalario> list = new HashSet<>();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT idPag, idCassino, idFunc, valor, dataPagamento FROM PagamentoSalario");
        while(rs.next()) {
            list.add(new PagamentoSalario(
                rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDouble(4), rs.getString(5)
            ));
        }
        return list;
    }

    public static void delete(int idPag, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("DELETE FROM PagamentoSalario WHERE idPag = ?");
        st.setInt(1, idPag);
        st.execute();
        st.close();
    }
}
