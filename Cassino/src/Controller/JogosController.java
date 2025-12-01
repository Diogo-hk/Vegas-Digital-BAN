package Controller;

import Conexao.JogosModel;
import Dados.Blackjack;
import Dados.Jogos;
import Dados.Poker;
import Dados.Roleta;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class JogosController {

    public void createJogosController(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("---- CRIAR NOVO JOGO ----");

        System.out.print("ID do Jogo: ");
        int idJogos = input.nextInt();
        System.out.print("Taxa RTP: ");
        float taxaRTP = input.nextFloat();
        input.nextLine(); 

        System.out.println("Selecione o tipo de jogo:");
        System.out.println("1 - Roleta");
        System.out.println("2 - Blackjack");
        System.out.println("3 - Poker");
        System.out.print("Opção: ");
        int opcao = input.nextInt();
        input.nextLine();

        Jogos novoJogo = null;

        try {
            switch (opcao) {
                case 1: 
                    System.out.print("Digite o tipo da Roleta: ");
                    String tipoRStr = input.nextLine().toUpperCase();
                    int tipoR = Integer.valueOf(tipoRStr);

                    novoJogo = new Roleta(idJogos, taxaRTP, tipoR);
                    break;

                case 2: 
                    System.out.print("Quantidade de Baralhos: ");
                    int qtdBaralhos = input.nextInt();

                    novoJogo = new Blackjack(idJogos, taxaRTP, qtdBaralhos);
                    break;

                case 3: // Poker
                    System.out.println("Tipos de Poker disponíveis: TEXAS, HOLDEM");
                    System.out.print("Digite o tipo de Poker: ");
                    String tipoPStr = input.nextLine().toUpperCase();

                    int tipoP = Integer.valueOf(tipoPStr);

                    novoJogo = new Poker(idJogos, taxaRTP, tipoP);
                    break;

                default:
                    System.out.println("Opção inválida!");
                    return;
            }

            if (novoJogo != null) {
                JogosModel.create(novoJogo, con);
                System.out.println("Jogo criado com sucesso!");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Erro: Tipo digitado inválido (verifique se digitou exatamente como nas opções).");
        } catch (SQLException e) {
            System.out.println("Erro ao salvar no banco: " + e.getMessage());
        }
    }

    public void listarJogos(Connection con) throws SQLException {
        HashSet all = JogosModel.listAll(con);
        Iterator<Jogos> it = all.iterator();
        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }
}

