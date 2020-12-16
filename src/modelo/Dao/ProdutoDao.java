package modelo.Dao;

import controlador.Conexao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Produto;
import validacao.TelasMensagem;
import view.Inicial.InicioView;

public class ProdutoDao {

    Conexao conex = InicioView.conex;

    public void salvar(Produto p, int tabela, String a, String b) {
        try {
            PreparedStatement pst = conex.con.prepareStatement("insert into " + a + "(" + b + ") values(?)");
            if (tabela == 1) {
                pst.setString(1, p.getCategoria());
            }
            if (tabela == 2) {
                pst.setString(1, p.getSetor());
            }

            pst.execute();
        } catch (SQLException ex) {
            TelasMensagem.setErroMensagem("Erro ao inserir dados no banco:\n" + ex, "Erro no banco de dados");

        }
    }

    public int quantidadeDados(String a) {
        int count = 0;

        conex.executaSql("select count (1) from " + a);

        try {
            conex.rs.next();
            count = conex.rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
            TelasMensagem.setErroMensagem("Erro ao executar método de contagem:\n" + ex, "Erro no banco de dados");
        }
        return count;
    }

    public void editar(Produto p, String a, String b, String c, int tabela) {
        try {
            PreparedStatement pst = conex.con.prepareStatement("update " + a + " set " + b + "=? where " + c + "=?");
            if (tabela == 1) {
                pst.setString(1, p.getCategoria());
                pst.setInt(2, p.getIdCategoria());
            }
            if (tabela == 2) {
                pst.setString(1, p.getSetor());
                pst.setInt(2, p.getIdSetor());
            }

            pst.execute();
        } catch (SQLException ex) {
            TelasMensagem.setErroMensagem("Erro ao editar dados no banco:\n" + ex, "Erro no banco de dados");
        }
    }

    public void excluir(Produto p, String a, String b, int tabela) {
        try {
            PreparedStatement pst = conex.con.prepareStatement("delete from " + a + " where " + b + "=?");
            if (tabela == 1) {
                pst.setInt(1, p.getIdCategoria());
            }
            if (tabela == 2) {
                pst.setInt(1, p.getIdSetor());
            }
            if (tabela == 3) {
                pst.setInt(1, p.getIdProduto());
            }

            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salvarProduto(Produto p) {
        try {
            PreparedStatement pst = conex.con.prepareStatement("insert into produto (categoria, preco, setor_preparo, quantidade_minima, "
                    + "unidade, detalhes, codigo, preco_mesa, preco_balcao, descricao, tipo, "
                    + "visibilidade, estoque, caminho_imagem) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            pst.setString(1, p.getCategoriaProduto());
            pst.setString(2, p.getPreco());
            pst.setString(3, p.getSetorPreparo());
            pst.setString(4, p.getQuantidadeMinima());
            pst.setString(5, p.getUnidade());
            pst.setString(6, p.getDetalhes());
            pst.setString(7, p.getCodigo());
            pst.setString(8, p.getPrecoMesa());
            pst.setString(9, p.getPrecoBalcao());
            pst.setString(10, p.getDescricao());
            pst.setString(11, p.getTipo());
            pst.setString(12, p.getVisibilidade());
            pst.setString(13, "0");
            pst.setString(14, p.getCaminhoImagem());

            pst.execute();
        } catch (SQLException e) {
            TelasMensagem.setErroMensagem("Erro ao inserir produto no banco:\n" + e, "Erro no banco de dados");
        }
    }

    public String pesquisar(String a, String b, int id, int coluna) {
        String pesquisa = new String();
        
        conex.executaSql("select *from " + a + " where " + b + " = " + id);
        try {
            conex.rs.next();
            switch (coluna) {
                case 1:
                    pesquisa = conex.rs.getString("unidade");
                    break;
                case 2:
                    pesquisa = conex.rs.getString("tipo");
                    break;
                case 3:
                    pesquisa = conex.rs.getString("setor_preparo");
                    break;
                case 4:
                    pesquisa = conex.rs.getString("categoria");
                    break;
                case 5:
                    pesquisa = conex.rs.getString("quantidade_minima");
                    break;
                case 6:
                    pesquisa = conex.rs.getString("descricao");
                    break;
                case 7:
                    pesquisa = conex.rs.getString("preco");
                    break;
                case 8:
                    pesquisa = conex.rs.getString("detalhes");
                    break;
                case 9:
                    pesquisa = conex.rs.getString("codigo");
                    break;
                case 10:
                    pesquisa = conex.rs.getString("preco_mesa");
                    break;
                case 11:
                    pesquisa = conex.rs.getString("preco_balcao");
                    break;
                case 12:
                    pesquisa = conex.rs.getString("caminho_imagem");
                    break;
            }
        } catch (SQLException ex) {
            TelasMensagem.setErroMensagem("Erro ao pesquisar o produto:\n" + ex, "Erro no banco de dados");
        }

        return pesquisa;
    }

    public void editaProdutoCadastrado(Produto p, String a, String b, String c, int coluna) {
        try {
            PreparedStatement pst = conex.con.prepareStatement("update " + a + " set " + b + "=? where " + c + "=?");
            pst.setInt(2, p.getIdProduto());
            switch (coluna) {
                case 1:
                    pst.setString(1, p.getCategoriaProduto());
                    break;
                case 2:
                    pst.setString(1, p.getDetalhes());
                    break;
                case 3:
                    pst.setString(1, p.getCodigo());
                    break;
                case 4:
                    pst.setString(1, p.getDescricao());
                    break;
                case 5:
                    pst.setString(1, p.getPreco());
                    break;
                case 6:
                    pst.setString(1, p.getSetorPreparo());
                    break;
                case 7:
                    pst.setString(1, p.getQuantidadeMinima());
                    break;
                case 8:
                    pst.setString(1, p.getUnidade());
                    break;
                case 9:
                    pst.setString(1, p.getPrecoMesa());
                    break;
                case 10:
                    pst.setString(1, p.getPrecoBalcao());
                    break;
                case 11:
                    pst.setString(1, p.getTipo());
                    break;
                case 12:
                    pst.setString(1, p.getVisibilidade());
                    break;
                case 13:
                    pst.setString(1, p.getEstoque());
                    break;
                case 14:
                    pst.setString(1, p.getCaminhoImagem());
                    break;
            }

            pst.execute();
        } catch (SQLException ex) {
            TelasMensagem.setErroMensagem("Erro ao editar dados no banco:\n" + ex, "Erro no banco de dados");
        }
    }
}
