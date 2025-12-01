package Dados;

public class Cassino {
    private int idCassino;
    private String CNPJ;
    private String nome;
    private String cep;
    private String cidade;
    private double banca;

    // Construtor
    public Cassino(int idCassino, String CNPJ, String nome, String cep, String cidade, double banca) {
        this.idCassino = idCassino;
        this.CNPJ = CNPJ;
        this.nome = nome;
        this.cep = cep;
        this.cidade = cidade;
        this.banca = banca;
    }

    // Getters e Setters
    public int getIdCassino() {
        return idCassino;
    }

    public void setIdCassino(int idCassino) {
        this.idCassino = idCassino;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public double getBanca() {
        return banca;
    }

    public void setBanca(double banca) {
        this.banca = banca;
    }

    @Override
    public String toString() {
        return "Cassino{" +
                "idCassino=" + idCassino +
                ", CNPJ='" + CNPJ + '\'' +
                ", nome='" + nome + '\'' +
                ", cep='" + cep + '\'' +
                ", cidade='" + cidade + '\'' +
                ", banca=" + banca +
                '}';
    }
}