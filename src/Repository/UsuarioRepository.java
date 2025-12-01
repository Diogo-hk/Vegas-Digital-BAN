package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.UsuarioBean;

public class UsuarioRepository {

    private Connection conexao;

    public UsuarioRepository(Connection conexao) {
        this.conexao = conexao;
    }

    // 1. Método de Login (SELECT)
    public UsuarioBean autenticar(String email, String senha) {
        String sql = "SELECT * FROM public.usuario WHERE email = ? AND senha = ?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                UsuarioBean usuario = new UsuarioBean();
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
            e.printStackTrace();
        }
        return null;
    }

    public boolean salvar(UsuarioBean usuario) {
        String sql = "INSERT INTO public.usuario (nomeusuario, cpf, email, senha, status, saldo) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, usuario.getNomeUsuario());
            ps.setString(2, usuario.getCpf());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getSenha());
            ps.setString(5, "ATIVO"); // Padrão
            ps.setDouble(6, 0.0);     // Saldo inicial

            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
            return false;
        }
    }
}