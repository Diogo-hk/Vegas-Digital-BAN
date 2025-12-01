package Conexao;

import Dados.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

public class FuncionarioModel {

    public static void create(Funcionario f, Connection con) throws SQLException {
        PreparedStatement st;
        // Observação: idSessao e idCassino são FKs e devem existir nas tabelas respectivas
        st = con.prepareStatement("INSERT INTO Funcionario (idFunc, idSessao, idCassino, nome, cpf) VALUES (?,?,?,?,?)");
        st.setInt(1, f.getIdFunc());
        st.setInt(2, f.getIdSessao());
        st.setInt(3, f.getIdCassino());
        st.setString(4, f.getNome());
        st.setString(5, f.getCpf());
        st.execute();
        st.close();
    }

    public static HashSet listAll(Connection con) throws SQLException {
        Statement st;
        HashSet list = new HashSet();
        st = con.createStatement();
        String sql = "SELECT idFunc, idSessao, idCassino, nome, cpf FROM Funcionario";
        ResultSet result = st.executeQuery(sql);
        while(result.next()) {
            Funcionario f = new Funcionario(
                    result.getInt(1),
                    result.getInt(2),
                    result.getInt(3),
                    result.getString(4),
                    result.getString(5)
            );
            list.add(f);
        }
        return list;
    }
}