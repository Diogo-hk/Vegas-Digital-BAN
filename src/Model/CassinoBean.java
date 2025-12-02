package Model;

public class CassinoBean {
    private int idCassino;
    private String cnpj;
    private String nome;
    private String cep;
    private String cidade;
    private Double banca;

    public CassinoBean() {}

    public int getIdCassino() { return idCassino; }
    public void setIdCassino(int idCassino) { this.idCassino = idCassino; }
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }
    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public Double getBanca() { return banca; }
    public void setBanca(Double banca) { this.banca = banca; }
}