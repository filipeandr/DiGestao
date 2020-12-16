package modelo;

public class Mesa extends Pedido {

    int idMesa;
    String nomeMesa;

    public Mesa() {

    }

    public Mesa(int idMesa, String nomeMesa, int idPedido, int idProduto, String quantidade_produto, String valorPedido, String nomeProduto, String estadoPedido) {
        super(idPedido, idProduto, quantidade_produto, valorPedido, nomeProduto, estadoPedido);
        this.idMesa = idMesa;
        this.nomeMesa = nomeMesa;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public String getNomeMesa() {
        return nomeMesa;
    }

    public void setNomeMesa(String nomeMesa) {
        this.nomeMesa = nomeMesa;
    }
}
