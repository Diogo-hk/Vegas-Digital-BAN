
package Conexao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

public class UsuarioModel {

    // Método usando Subconsulta e Agregação (AVG)
    public static HashSet<String> listUsuariosAcimaDaMedia(Connection con) throws SQLException {
        HashSet<String> list = new HashSet<>();
        Statement st = con.createStatement();
        
        // Query: Seleciona usuários onde o saldo é maior que a média de todos os saldos
        String sql = "SELECT nomeUsuario, Saldo FROM Usuario " +
                     "WHERE Saldo > (SELECT AVG(Saldo) FROM Usuario)";
                     
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()) {
            String linha = "Usuário: " + rs.getString("nomeUsuario") +
                           " | Saldo: R$ " + rs.getDouble("Saldo");
            list.add(linha);
        }
        return list;
    }
}
