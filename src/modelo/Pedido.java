package modelo;

abstract public class Pedido {

    int idPedido;
    int idProduto;
    String quantidade_produto;
    String valorPedido;
    String nomeProduto;
    String estadoPedido;

    public Pedido() {

    }

    public Pedido(int idPedido, int idProduto, String quantidade_produto, String valorPedido, String nomeProduto, String estadoPedido) {
        this.idPedido = idPedido;
        this.idProduto = idProduto;
        this.quantidade_produto = quantidade_produto;
        this.valorPedido = valorPedido;
        this.nomeProduto = nomeProduto;
        this.estadoPedido = estadoPedido;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getQuantidade_produto() {
        return quantidade_produto;
    }

    public void setQuantidade_produto(String quantidade_produto) {
        this.quantidade_produto = quantidade_produto;
    }

    public String getValorPedido() {
        return valorPedido;
    }

    public void setValorPedido(String valorPedido) {
        this.valorPedido = valorPedido;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }
}
