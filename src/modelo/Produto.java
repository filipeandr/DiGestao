package modelo;

public class Produto extends Setor {

    int idProduto;
    String quantidadeMinima;
    String unidade;
    String preco;
    String precoMesa;
    String precoBalcao;
    String descricao;
    String categoriaProduto;
    String setorPreparo;
    String detalhes;
    String codigo;
    String tipo;
    String visibilidade;
    String estoque;
    private String caminhoImagem;

    public Produto() {

    }

    public Produto(int idProduto, String quantidadeMinima, String unidade, String preco, String precoMesa,
            String precoBalcao, String descricao, String categoriaProduto, String setorPreparo,
            String detalhes, String codigo, String tipo, String visibilidade, String estoque, String caminhoImagem) {
        this.idProduto = idProduto;
        this.quantidadeMinima = quantidadeMinima;
        this.unidade = unidade;
        this.preco = preco;
        this.precoMesa = precoMesa;
        this.precoBalcao = precoBalcao;
        this.descricao = descricao;
        this.categoriaProduto = categoriaProduto;
        this.setorPreparo = setorPreparo;
        this.detalhes = detalhes;
        this.codigo = codigo;
        this.tipo = tipo;
        this.visibilidade = visibilidade;
        this.estoque = estoque;
        this.caminhoImagem = caminhoImagem;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(String quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getPrecoMesa() {
        return precoMesa;
    }

    public void setPrecoMesa(String precoMesa) {
        this.precoMesa = precoMesa;
    }

    public String getPrecoBalcao() {
        return precoBalcao;
    }

    public void setPrecoBalcao(String precoBalcao) {
        this.precoBalcao = precoBalcao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoriaProduto() {
        return categoriaProduto;
    }

    public void setCategoriaProduto(String categoriaProduto) {
        this.categoriaProduto = categoriaProduto;
    }

    public String getSetorPreparo() {
        return setorPreparo;
    }

    public void setSetorPreparo(String setorPreparo) {
        this.setorPreparo = setorPreparo;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getVisibilidade() {
        return visibilidade;
    }

    public void setVisibilidade(String visibilidade) {
        this.visibilidade = visibilidade;
    }

    public String getEstoque() {
        return estoque;
    }

    public void setEstoque(String estoque) {
        this.estoque = estoque;
    }
}
