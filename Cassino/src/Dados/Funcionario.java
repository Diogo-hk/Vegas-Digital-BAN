package Dados;

public class Funcionario {
    private int idFunc;
    private int idSessao;
    private int idCassino;
    private String nome;
    private String cpf;

    // Construtor
    public Funcionario(int idFunc, int idSessao, int idCassino, String nome, String cpf) {
        this.idFunc = idFunc;
        this.idSessao = idSessao;
        this.idCassino = idCassino;
        this.nome = nome;
        this.cpf = cpf;
    }

    // Getters e Setters
    public int getIdFunc() {
        return idFunc;
    }

    public void setIdFunc(int idFunc) {
        this.idFunc = idFunc;
    }

    public int getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(int idSessao) {
        this.idSessao = idSessao;
    }

    public int getIdCassino() {
        return idCassino;
    }

    public void setIdCassino(int idCassino) {
        this.idCassino = idCassino;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "idFunc=" + idFunc +
                ", idSessao=" + idSessao +
                ", idCassino=" + idCassino +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}