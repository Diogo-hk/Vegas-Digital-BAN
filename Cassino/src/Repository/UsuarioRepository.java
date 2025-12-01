package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.UsuarioBean; // <--- Faltava importar isso

public class UsuarioRepository {
    private Connection conexao;

    public UsuarioRepository(Connection conexao) {
        this.conexao = conexao;
    }

    //pega do banco e valida
    public Model.UsuarioBean autenticar(String email, String senha){
        String sql = "SELECT * FROM public.usuario WHERE email = ? AND senha = ?";

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, senha);

            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                Model.UsuarioBean usuario = new Model.UsuarioBean();
                usuario.setIdUsuario(rs.getInt("iduser"));
                usuario.setNomeUsuario(rs.getString("nomeusuario"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setStatus(rs.getString("status"));
                usuario.setSaldo(rs.getDouble("saldo"));

                return usuario;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao autenticar: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}