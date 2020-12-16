package modelo;

public class Usuario extends Pessoa {

    private String senha;
    private String confSenha;

    public Usuario() {

    }

    public Usuario(String senha, String confSenha, int id, String nome, String celular, String email,
            String nomeEmpresa, String estado, String cidade, String numero,
            String rua, String cep, String bairro, String referencia,
            String tipo, String nomeFantasia, String celularEmpresa) {
        super(id, nome, celular, email, nomeEmpresa, estado, cidade, numero,
                rua, cep, bairro, referencia, tipo, nomeFantasia, celularEmpresa);
        this.senha = senha;
        this.confSenha = confSenha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfSenha() {
        return confSenha;
    }

    public void setConfSenha(String confSenha) {
        this.confSenha = confSenha;
    }

}
