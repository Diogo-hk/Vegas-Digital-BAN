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
        PreparedStatement psPai = null;
        ResultSet rs = null;

        try {
            // Statement.RETURN_GENERATED_KEYS recupera o ID Criado
            psPai = conexao.prepareStatement(sqlPai, Statement.RETURN_GENERATED_KEYS);
            psPai.setDouble(1, rtp);
            psPai.executeUpdate();

            rs = psPai.getGeneratedKeys();
            int novoId = 0;
            if (rs.next()) {
                novoId = rs.getInt(1); // O ID novo (ex: 55)
            }

            // 3. INSERIR NA TABELA FILHA ESPECÍFICA
            if (novoId > 0) {
                switch (tipoJogo) {
                    case 1: // ROLETA
                        String sqlRoleta = "INSERT INTO public.roleta (idjogos, tiporoleta) VALUES (?, ?)";
                        PreparedStatement psR = conexao.prepareStatement(sqlRoleta);
                        psR.setInt(1, novoId);
                        psR.setString(2, dadoTexto);
                        psR.execute();
                        break;

                    case 2: // POKER
                        String sqlPoker = "INSERT INTO public.poker (idjogos, tipopoker) VALUES (?, ?)";
                        PreparedStatement psP = conexao.prepareStatement(sqlPoker);
                        psP.setInt(1, novoId);
                        psP.setString(2, dadoTexto);
                        psP.execute();
                        break;

                    case 3: // BLACKJACK
                        String sqlBJ = "INSERT INTO public.blackjack (idjogos, quantidadebaralhos) VALUES (?, ?)";
                        PreparedStatement psB = conexao.prepareStatement(sqlBJ);
                        psB.setInt(1, novoId);
                        psB.setInt(2, dadoInt);
                        psB.execute();
                        break;
                }
                System.out.println("SUCESSO: Jogo ID " + novoId + " cadastrado");
            }

        } catch (SQLException e) {
            System.out.println("ERRO ao cadastrar jogo: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public List<JogosBean> listarTodos() {
        List<JogosBean> lista = new ArrayList<>();

        String sql = "SELECT j.idjogos, j.taxartp, " +
                "r.tiporoleta, p.tipopoker, b.quantidadebaralhos " +
                "FROM public.jogos j " +
                "LEFT JOIN public.roleta r ON j.idjogos = r.idjogos " +
                "LEFT JOIN public.poker p ON j.idjogos = p.idjogos " +
                "LEFT JOIN public.blackjack b ON j.idjogos = b.idjogos " +
                "ORDER BY j.idjogos";

        try {
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                JogosBean j = new JogosBean();
                j.setIdJogos(rs.getInt("idjogos"));
                j.setTaxaRTP(rs.getDouble("taxartp"));

                String tRoleta = rs.getString("tiporoleta");
                String tPoker = rs.getString("tipopoker");
                int qtdBaralhos = rs.getInt("quantidadebaralhos");
                boolean isBlackjack = !rs.wasNull();

                if (tRoleta != null) {
                    j.setNome("Roleta (" + tRoleta + ")");
                } else if (tPoker != null) {
                    j.setNome("Poker (" + tPoker + ")");
                } else if (isBlackjack) {
                    j.setNome("Blackjack (" + qtdBaralhos + " baralhos)");
                } else {
                    j.setNome("Jogo Genérico");
                }

                lista.add(j);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}