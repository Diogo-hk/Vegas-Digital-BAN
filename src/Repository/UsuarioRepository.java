package Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.UsuarioBean;
import Util.Conexao;

public class UsuarioRepository {
    private Connection conexao;

    public UsuarioRepository(Connection conexao) {
        this.conexao = conexao;
    }

    public UsuarioBean autenticar(String email, String senha) {
        String sql = "SELECT * FROM public.usuario WHERE email = ? AND senha = ?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                UsuarioBean u = new UsuarioBean();
                u.setIdUsuario(rs.getInt("iduser"));
                u.setNomeUsuario(rs.getString("nomeusuario"));
                u.setCpf(rs.getString("cpf"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                u.setStatus(rs.getString("status"));
                u.setSaldo(rs.getDouble("saldo"));
                return u;
            }
        } catch (SQLException e)
        {
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
            ps.setString(5, "ATIVO");
            ps.setDouble(6, 0.0);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
            return false;
        }
    }

    public List<UsuarioBean> listarTodos() {
        List<UsuarioBean> lista = new ArrayList<>();
        String sql = "SELECT * FROM public.usuario ORDER BY iduser";
        try {
            ResultSet rs = conexao.createStatement().executeQuery(sql);
            while(rs.next()) {
                UsuarioBean u = new UsuarioBean();
                u.setIdUsuario(rs.getInt("iduser"));
                u.setNomeUsuario(rs.getString("nomeusuario"));
                u.setEmail(rs.getString("email"));
                u.setStatus(rs.getString("status"));
                u.setSaldo(rs.getDouble("saldo"));
                lista.add(u);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public void remover(int id) {
        String sql = "DELETE FROM public.usuario WHERE iduser = ?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("SUCESSO: Usuário removido.");
        } catch (SQLException e) {
            System.out.println("ERRO: Não foi possível remover (pode ter histórico): " + e.getMessage());
        }
    }
}