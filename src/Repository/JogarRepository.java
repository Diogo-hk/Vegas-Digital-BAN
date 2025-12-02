package Repository;

import java.sql.*;
import Util.Conexao;

public class JogarRepository {
    private Connection conexao;

    public JogarRepository(Connection conexao) {
        this.conexao = conexao;
    }

    public double buscarSaldo(int idUsuario) {
        String sql = "SELECT saldo FROM public.usuario WHERE iduser = ?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getDouble("saldo");
        } catch (SQLException e) { System.out.println("Erro saldo: " + e.getMessage()); }
        return 0.0;
    }

    public double buscarRTP(int idSessao) {
        String sql = "SELECT j.taxartp FROM public.sessao s JOIN public.jogos j ON s.fk_idjogos = j.idjogos WHERE s.idsessao = ?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, idSessao);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) return rs.getDouble("taxartp");
        } catch (SQLException e) { System.out.println("Erro RTP: " + e.getMessage()); }
        return 0.0;
    }

    public void registrarJogo(int idUsuario, int idSessao, double apostado, double retorno) {
        String hist = "INSERT INTO public.jogar (idsessao, idusuario, valorapostado, retorn, data_hora) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)";
        String up = "UPDATE public.usuario SET saldo = saldo - ? + ? WHERE iduser = ?";
        try {
            PreparedStatement ps = conexao.prepareStatement(hist);
            ps.setInt(1, idSessao); ps.setInt(2, idUsuario); ps.setDouble(3, apostado); ps.setDouble(4, retorno);
            ps.execute();

            PreparedStatement ps2 = conexao.prepareStatement(up);
            ps2.setDouble(1, apostado); ps2.setDouble(2, retorno); ps2.setInt(3, idUsuario);
            ps2.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void depositar(int idUsuario, double valor) {
        String sql = "UPDATE public.usuario SET saldo = saldo + ? WHERE iduser = ?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setDouble(1, valor); ps.setInt(2, idUsuario); ps.executeUpdate();
            System.out.println("Depósito OK.");
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void listarHistoricoGeral() {
        String sql = "SELECT * FROM public.jogar ORDER BY data_hora DESC LIMIT 10";
        try {
            ResultSet rs = conexao.createStatement().executeQuery(sql);
            System.out.println("\n--- ÚLTIMAS 10 APOSTAS ---");
            while(rs.next()) {
                System.out.println("Sessão: " + rs.getInt("idsessao") + " | User: " + rs.getInt("idusuario") + " | Apostou: " + rs.getDouble("valorapostado") + " | Ganhou: " + rs.getDouble("retorn"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void limparHistoricoSessao(int idSessao) {
        try {
            PreparedStatement ps = conexao.prepareStatement("DELETE FROM public.jogar WHERE idsessao = ?");
            ps.setInt(1, idSessao);
            int qtd = ps.executeUpdate();
            System.out.println("Registros removidos: " + qtd);
        } catch (SQLException e) { e.printStackTrace(); }
    }
}