package controlador;

import modelo.Dao.MesaDao;
import modelo.Mesa;

public class ControladorMesas {

    Mesa m = new Mesa();
    MesaDao md = new MesaDao();

    public void salvarMesa(String mesa) {
        m.setNomeMesa(mesa);

        md.salvarMesa(m);
    }

    public void salvarProduto(int idPr, int idMesaAtual, String nomeProduo,
            String quantidadeProduto, String valorPedido, String estado) {
        m.setIdProduto(idPr);
        m.setIdMesa(idMesaAtual);
        m.setNomeProduto(nomeProduo);
        m.setQuantidade_produto(quantidadeProduto);
        m.setValorPedido(valorPedido);
        m.setEstadoPedido(estado);

        md.salvarPedido(m);
    }

    public void editarEstadoPedido(String estado, int idMesa) {
        m.setEstadoPedido(estado);
        m.setIdMesa(idMesa);

        md.editaEstadoPedido(m);
    }

    public void montarPrato(int idPr, int idMesaAtual, String nomeProduo, String quantidadeProduto,
            String valorPedido, String estado) {
        m.setIdProduto(idPr);
        m.setIdMesa(idMesaAtual);
        m.setNomeProduto(nomeProduo);
        m.setQuantidade_produto(quantidadeProduto);
        m.setValorPedido(valorPedido);
        m.setEstadoPedido(estado);

        md.montarPrato(m);
    }
}
