package Controller;

import Conexao.UsuarioModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;

public class UsuarioController {

    public void listarUsuariosRicos(Connection con) throws SQLException {
        System.out.println("---- USUÁRIOS COM SALDO ACIMA DA MÉDIA ----");
        HashSet<String> lista = UsuarioModel.listUsuariosAcimaDaMedia(con);
        
        if (lista.isEmpty()) {
            System.out.println("Nenhum usuário encontrado acima da média.");
        } else {
            for (String item : lista) {
                System.out.println(item);
            }
        }
    }
}
