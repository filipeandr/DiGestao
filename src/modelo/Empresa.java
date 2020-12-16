package modelo;

abstract public class Empresa {

    private String nomeEmpresa;
    private String estado;
    private String cidade;
    private String numero;
    private String rua;
    private String cep;
    private String bairro;
    private String referencia;
    private String tipo;
    private String nomeFantasia;
    private String celularEmpresa;

    public Empresa(String nomeEmpresa, String estado, String cidade, String numero, String rua, String cep, String bairro,
            String referencia, String tipo, String nomeFantasia, String celularEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
        this.estado = estado;
        this.cidade = cidade;
        this.numero = numero;
        this.rua = rua;
        this.cep = cep;
        this.bairro = bairro;
        this.referencia = referencia;
        this.tipo = tipo;
        this.nomeFantasia = nomeFantasia;
        this.celularEmpresa = celularEmpresa;
    }

    public Empresa() {

    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public String getEstado() {
        return estado;
    }

    public String getCidade() {
        return cidade;
    }

    public String getNumero() {
        return numero;
    }

    public String getRua() {
        return rua;
    }

    public String getCep() {
        return cep;
    }

    public String getBairro() {
        return bairro;
    }

    public String getReferencia() {
        return referencia;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCelularEmpresa() {
        return celularEmpresa;
    }

    public void setCelularEmpresa(String celularEmpresa) {
        this.celularEmpresa = celularEmpresa;
    }
}
