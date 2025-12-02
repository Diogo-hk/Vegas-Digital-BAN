package Controller;

import java.util.Random;
import Util.Conexao;
import Repository.JogarRepository;

public class JogarController {
    private JogarRepository repository;

    public JogarController() {
        this.repository = new JogarRepository(new Conexao().getConnection());
    }

    public double buscarSaldoFoda(int idUsuario){
        return repository.buscarSaldo(idUsuario);
    }

    public void realizaDeposito(int idUsuario, double valor){
        if(valor > 0) repository.depositar(idUsuario, valor);
        else System.out.println("Valor inválido.");
    }

    public void apostar(int idSessao, int idUsuario, double aposta, int numero) {
        double saldo = repository.buscarSaldo(idUsuario);
        if(saldo < aposta) { System.out.println("Sem saldo."); return; }

        Random r = new Random();
        int sorteado = r.nextInt(37);
        System.out.println("Número Sorteado: " + sorteado);

        double rtp = repository.buscarRTP(idSessao);
        double retorno = 0.0;

        if(numero == sorteado) {
            retorno = aposta * 37.0 * (rtp/100.0);
            System.out.println("GANHOU! R$ " + retorno);
        } else {
            System.out.println("Perdeu.");
        }
        repository.registrarJogo(idUsuario, idSessao, aposta, retorno);
    }

    public void listarHistorico() { repository.listarHistoricoGeral(); }
    public void apagarHistorico(int idSessao) { repository.limparHistoricoSessao(idSessao); }
}