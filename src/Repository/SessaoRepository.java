package Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.SessaoBean;
import Util.Conexao;

public class SessaoRepository {
    private Connection conexao;

    public SessaoRepository(Connection conexao) {
        this.conexao = conexao;
    }

    public void criarSessao(SessaoBean sessao) {
        String sql = "INSERT INTO public.sessao (fk_idjogos, fk_idcassino, nomesessao) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, sessao.getIdJogos());
            ps.setInt(2, sessao.getIdCassino());
            ps.setString(3, sessao.getNomeSessao());
            ps.execute();
            System.out.println("Sessão aberta!");
        } catch (SQLException e) {
            System.out.println("Erro ao criar sessão: " + e.getMessage());
        }
    }

    public List<SessaoBean> listarSessoes() {
        List<SessaoBean> lista = new ArrayList<>();
        String sql = "SELECT * FROM public.sessao";
        try {
            ResultSet rs = conexao.createStatement().executeQuery(sql);
            while(rs.next()){
                SessaoBean s = new SessaoBean();
                s.setIdSessao(rs.getInt("idsessao"));
                s.setNomeSessao(rs.getString("nomesessao"));
                s.setIdCassino(rs.getInt("fk_idcassino"));
                s.setIdJogos(rs.getInt("fk_idjogos"));
                lista.add(s);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public void remover(int id) {
        try {
            PreparedStatement ps = conexao.prepareStatement("DELETE FROM public.sessao WHERE idsessao = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("SUCESSO: Sessão fechada.");
        } catch (SQLException e) {
            System.out.println("ERRO ao remover sessão: " + e.getMessage());
        }
    }
}