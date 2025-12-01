package Conexao;

import Dados.Blackjack;
import Dados.Jogos;
import Dados.Poker;
import Dados.Roleta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.sql.Statement;
import java.sql.ResultSet;

public class JogosModel {

    public static void create(Jogos jogo, Connection con) throws SQLException {
        PreparedStatement st = null;

        try {
            // 1. Desativa o auto-commit para garantir transação (ambos inserts funcionam ou nenhum)
            con.setAutoCommit(false);

            // ---------------------------------------------------------
            // PASSO 1: Inserir na Superclasse (Tabela Jogos)
            // ---------------------------------------------------------
            String sqlJogos = "INSERT INTO Jogos (idJogos, taxaRTP) VALUES (?, ?)";
            st = con.prepareStatement(sqlJogos);
            st.setInt(1, jogo.getIdJogos());
            st.setFloat(2, jogo.getTaxaRTP());
            st.executeUpdate();
            st.close();

            // ---------------------------------------------------------
            // PASSO 2: Inserir na Subclasse correta
            // ---------------------------------------------------------
            if (jogo instanceof Roleta) {
                Roleta roleta = (Roleta) jogo;
                String sqlRoleta = "INSERT INTO Roleta (idJogos, tipoRoleta) VALUES (?, ?)";
                st = con.prepareStatement(sqlRoleta);
                st.setInt(1, roleta.getIdJogos());
                // Salvando o Enum como String no banco
                st.setString(2, roleta.getTipoRoleta().name());
                st.executeUpdate();

            } else if (jogo instanceof Blackjack) {
                Blackjack bj = (Blackjack) jogo;
                String sqlBj = "INSERT INTO Blackjack (idJogos, quantidadeBaralhos) VALUES (?, ?)";
                st = con.prepareStatement(sqlBj);
                st.setInt(1, bj.getIdJogos());
                st.setInt(2, bj.getQuantidadeBaralhos());
                st.executeUpdate();

            } else if (jogo instanceof Poker) {
                Poker poker = (Poker) jogo;
                String sqlPoker = "INSERT INTO Poker (idJogos, tipoPoker) VALUES (?, ?)";
                st = con.prepareStatement(sqlPoker);
                st.setInt(1, poker.getIdJogos());
                // O PDF definia tipoPoker como INT no banco.
                // Vamos salvar o ordinal (0 para TEXAS, 1 para HOLDEM) ou adaptar para String.
                // Abaixo salvo o ordinal (inteiro) conforme seu script SQL:
                st.setInt(2, poker.getTipoPoker().ordinal());
                st.executeUpdate();
            }

            // Confirma a transação
            con.commit();

        } catch (SQLException e) {
            // Se der erro, desfaz tudo
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw e; // Lança o erro para o Controller tratar
        } finally {
            // Restaura o auto-commit e fecha recursos
            if (con != null) con.setAutoCommit(true);
            if (st != null) st.close();
        }
    }

    // Método listAll (pode ser melhorado depois para fazer JOINs e trazer os dados específicos)
    public static HashSet listAll(Connection con) throws SQLException {
        Statement st;
        HashSet list = new HashSet();
        st = con.createStatement();
        String sql = "SELECT idJogos, taxaRTP FROM Jogos"; // Traz apenas dados genéricos por enquanto
        ResultSet result = st.executeQuery(sql);
        while(result.next()) {
            list.add(new Jogos(result.getInt(1), result.getInt(2)));
        }
        return list;
    }
}