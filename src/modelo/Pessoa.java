package modelo;

abstract public class Pessoa extends Empresa {

    private int id;
    private String nome;
    private String celular;
    private String email;

    public Pessoa() {

    }

    public Pessoa(int id, String nome, String celular, String email, String nomeEmpresa,
            String estado, String cidade, String numero, String rua, String cep,
            String bairro, String referencia, String tipo, String nomeFantasia,
            String celularEmpresa) {
        super(nomeEmpresa, estado, cidade, numero, rua, cep, bairro, referencia, tipo, nomeFantasia, celularEmpresa);
        this.id = id;
        this.nome = nome;
        this.celular = celular;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
