package Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.CassinoBean;
import Util.Conexao;

public class CassinoRepository {
    private Connection conexao;

    public CassinoRepository(Connection conexao) {
        this.conexao = conexao;
    }

    public void inserir(CassinoBean cassino) {
        String sql = "INSERT INTO public.cassino (cnpj, nome, cep, cidade, banca) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, cassino.getCnpj());
            ps.setString(2, cassino.getNome());
            ps.setString(3, cassino.getCep());
            ps.setString(4, cassino.getCidade());
            ps.setDouble(5, cassino.getBanca());
            ps.execute();
            System.out.println("SUCESSO: Cassino cadastrado!");
        } catch (SQLException e) {
            System.out.println("ERRO ao inserir: " + e.getMessage());
        }
    }

    public void remover(int idCassino) {
        String sql = "DELETE FROM public.cassino WHERE idcassino = ?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, idCassino);
            ps.execute();
            System.out.println("SUCESSO: Cassino removido!");
        } catch (SQLException e) {
            System.out.println("ERRO ao remover: " + e.getMessage());
        }
    }

    public List<CassinoBean> listarTodos() {
        List<CassinoBean> lista = new ArrayList<>();
        String sql = "SELECT * FROM public.cassino ORDER BY idcassino";
        try {
            ResultSet rs = conexao.createStatement().executeQuery(sql);
            while (rs.next()) {
                CassinoBean c = new CassinoBean();
                c.setIdCassino(rs.getInt("idcassino"));
                c.setNome(rs.getString("nome"));
                c.setCnpj(rs.getString("cnpj"));
                c.setCep(rs.getString("cep"));
                c.setCidade(rs.getString("cidade"));
                c.setBanca(rs.getDouble("banca"));
                lista.add(c);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public double calcularPatrimonioTotal() {
        String sql = "SELECT SUM(banca) as total FROM public.cassino";
        try {
            ResultSet rs = conexao.createStatement().executeQuery(sql);
            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return 0.0;
    }
}