package controlador;

import modelo.Dao.ProdutoDao;
import modelo.Produto;

public class ControladorProdutos {

    Produto p = new Produto();
    ProdutoDao pd = new ProdutoDao();

    public void dados(String desc, int tabela, String a, String b) {
        if (tabela == 1) {
            p.setCategoria(desc);
        }
        if (tabela == 2) {
            p.setSetor(desc);
        }

        pd.salvar(p, tabela, a, b);
    }

    public void editarCategoria(String desc, int id, int tabela, String a, String b, String c) {
        if (tabela == 1) {
            p.setCategoria(desc);
            p.setIdCategoria(id);
        }
        if (tabela == 2) {
            p.setSetor(desc);
            p.setIdSetor(id);
        }

        pd.editar(p, a, b, c, tabela);
    }

    public void excluirCategoria(int id, int tabela, String a, String b) {
        if (tabela == 1) {
            p.setIdCategoria(id);
        }
        if (tabela == 2) {
            p.setIdSetor(id);
        }
        if (tabela == 3) {
            p.setIdProduto(id);
        }

        pd.excluir(p, a, b, tabela);
    }

    public void dadosProdutos(String catPr, String preco, String setPp,
            String qtdMin, String unid, String det, String cod, String vm,
            String vb, String desc, String tipo, String cI) {
        p.setCategoriaProduto(catPr);
        p.setPreco(preco);
        p.setSetorPreparo(setPp);
        p.setQuantidadeMinima(qtdMin);
        p.setUnidade(unid);
        p.setDetalhes(det);
        p.setCodigo(cod);
        p.setPrecoMesa(vm);
        p.setPrecoBalcao(vb);
        p.setDescricao(desc);
        p.setTipo(tipo);
        p.setVisibilidade("1");
        p.setCaminhoImagem(cI);

        pd.salvarProduto(p);
    }

    public void setEditaProdutoCadastrado(String x, int id, int coluna, String a, String b, String c) {
        p.setIdProduto(id);
        switch (coluna) {
            case 1:
                p.setCategoriaProduto(x);
                break;
            case 2:
                p.setDetalhes(x);
                break;
            case 3:
                p.setCodigo(x);
                break;
            case 4:
                p.setDescricao(x);
                break;
            case 5:
                p.setPreco(x);
                break;
            case 6:
                p.setSetorPreparo(x);
                break;
            case 7:
                p.setQuantidadeMinima(x);
                break;
            case 8:
                p.setUnidade(x);
                break;
            case 9:
                p.setPrecoMesa(x);
                break;
            case 10:
                p.setPrecoBalcao(x);
                break;
            case 11:
                p.setTipo(x);
                break;
            case 12:
                p.setVisibilidade(x);
                break;
            case 13:
                p.setEstoque(x);
                break;
            case 14:
                p.setCaminhoImagem(x);
        }
        pd.editaProdutoCadastrado(p, a, b, c, coluna);
    }
}
