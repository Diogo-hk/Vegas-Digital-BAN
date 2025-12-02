package Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.JogosBean;
import Util.Conexao;

public class JogosRepository {
    private Connection conexao;

    public JogosRepository(Connection conexao) {
        this.conexao = conexao;
    }

    public void cadastrarJogoEspecifico(double rtp, int tipoJogo, String dadoTexto, int dadoInt) {
        String sqlPai = "INSERT INTO public.jogos (taxartp) VALUES (?)";
        try {
            PreparedStatement psPai = conexao.prepareStatement(sqlPai, Statement.RETURN_GENERATED_KEYS);
            psPai.setDouble(1, rtp);
            psPai.executeUpdate();

            ResultSet rs = psPai.getGeneratedKeys();
            int novoId = 0;
            if (rs.next()) {
                novoId = rs.getInt(1);
            }

            if (novoId > 0) {
                if (tipoJogo == 1) { // ROLETA
                    PreparedStatement psR = conexao.prepareStatement("INSERT INTO public.roleta (idjogos, tiporoleta) VALUES (?, ?)");
                    psR.setInt(1, novoId); psR.setString(2, dadoTexto); psR.execute();
                } else if (tipoJogo == 2) { // POKER
                    PreparedStatement psP = conexao.prepareStatement("INSERT INTO public.poker (idjogos, tipopoker) VALUES (?, ?)");
                    psP.setInt(1, novoId); psP.setString(2, dadoTexto); psP.execute();
                } else if (tipoJogo == 3) { // BLACKJACK
                    PreparedStatement psB = conexao.prepareStatement("INSERT INTO public.blackjack (idjogos, quantidadebaralhos) VALUES (?, ?)");
                    psB.setInt(1, novoId); psB.setInt(2, dadoInt); psB.execute();
                }
                System.out.println("SUCESSO: Jogo ID " + novoId + " cadastrado!");
            }
        } catch (SQLException e) {
            System.out.println("ERRO ao cadastrar: " + e.getMessage());
        }
    }

    public List<JogosBean> listarTodos() {
        List<JogosBean> lista = new ArrayList<>();
        String sql = "SELECT j.idjogos, j.taxartp, r.tiporoleta, p.tipopoker, b.quantidadebaralhos " +
                "FROM public.jogos j " +
                "LEFT JOIN public.roleta r ON j.idjogos = r.idjogos " +
                "LEFT JOIN public.poker p ON j.idjogos = p.idjogos " +
                "LEFT JOIN public.blackjack b ON j.idjogos = b.idjogos ORDER BY j.idjogos";
        try {
            ResultSet rs = conexao.createStatement().executeQuery(sql);
            while (rs.next()) {
                JogosBean j = new JogosBean();
                j.setIdJogos(rs.getInt("idjogos"));
                j.setTaxaRTP(rs.getDouble("taxartp"));

                String tRoleta = rs.getString("tiporoleta");
                String tPoker = rs.getString("tipopoker");
                int qtd = rs.getInt("quantidadebaralhos");
                boolean isBj = !rs.wasNull();

                if (tRoleta != null)
                {
                    j.setNome("Roleta (" + tRoleta + ")");
                    }
                else if (tPoker != null){
                    j.setNome("Poker (" + tPoker + ")");
                }
                else if (isBj) {
                    j.setNome("Blackjack (" + qtd + " baralhos)");
                }
                else j.setNome("Jogo Genérico");

                lista.add(j);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public void remover(int id) {
        try {
            // Tenta deletar direto se tiver cascade no banco, apaga os filhos. Se não, teria que apagar filhos antes
            PreparedStatement ps = conexao.prepareStatement("DELETE FROM public.jogos WHERE idjogos = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("SUCESSO: Jogo removido.");
        } catch (SQLException e) {
            System.out.println("ERRO ao remover (Delete filhos primeiro se não tiver Cascade): " + e.getMessage());
        }
    }
}