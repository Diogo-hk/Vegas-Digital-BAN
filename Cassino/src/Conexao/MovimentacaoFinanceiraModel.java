package Conexao;

import Dados.MovimentacaoFinanceira;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

public class MovimentacaoFinanceiraModel {

    public static void create(MovimentacaoFinanceira m, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("INSERT INTO MovimentacaoFinanceira (idMovimentacao, idCassino, idUsuario, tipoTransacao, valor, dataHora, metodoPagamento) VALUES (?,?,?,?,?,?,?)");
        st.setInt(1, m.getIdMovimentacao());
        st.setInt(2, m.getIdCassino());
        st.setInt(3, m.getIdUsuario());
        st.setString(4, m.getTipoTransacao());
        st.setDouble(5, m.getValor());
        // Passando dataHora como String. O banco fará o cast se o formato for 'YYYY-MM-DD HH:MM:SS'
        st.setString(6, m.getDataHora());
        st.setString(7, m.getMetodoPagamento());
        st.execute();
        st.close();
    }

    public static HashSet listAll(Connection con) throws SQLException {
        Statement st;
        HashSet list = new HashSet();
        st = con.createStatement();
        // Nomes das colunas ajustados conforme o script SQL gerado (camelCase ou sanitizados)
        String sql = "SELECT idMovimentacao, idCassino, idUsuario, tipoTransacao, valor, dataHora, metodoPagamento FROM MovimentacaoFinanceira";
        ResultSet result = st.executeQuery(sql);
        while(result.next()) {
            MovimentacaoFinanceira m = new MovimentacaoFinanceira(
                    result.getInt(1),
                    result.getInt(2),
                    result.getInt(3),
                    result.getString(4),
                    result.getDouble(5),
                    result.getString(6), // Retorna a Data/Hora como String do banco
                    result.getString(7)
            );
            list.add(m);
        }
        return list;
    }

    public static void delete(int idMovimentacao, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("DELETE FROM MovimentacaoFinanceira WHERE idMovimentacao = ?");
        st.setInt(1, idMovimentacao);
        st.execute();
        st.close();
    }
}
