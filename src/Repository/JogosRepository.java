package Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.JogosBean;
import Util.Conexao;

public class JogosRepository {
    private Connection conexao;
    public JogosRepository(Connection conexao) { this.conexao = conexao; }

    // Vamos criar apenas o Inserir e Listar rápido para usar na Sessão
    public void inserir(double rtp) {
        try {
            conexao.createStatement().execute("INSERT INTO public.jogos (taxartp) VALUES (" + rtp + ")");
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public List<JogosBean> listarTodos() {
        List<JogosBean> lista = new ArrayList<>();
        try {
            ResultSet rs = conexao.createStatement().executeQuery("SELECT * FROM public.jogos ORDER BY idjogos");
            while(rs.next()) {
                JogosBean j = new JogosBean();
                j.setIdJogos(rs.getInt("idjogos"));
                j.setTaxaRTP(rs.getDouble("taxartp"));
                lista.add(j);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
}