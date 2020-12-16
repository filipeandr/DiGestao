package modelo.Dao;

import controlador.Conexao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Mesa;
import validacao.TelasMensagem;
import view.Inicial.InicioView;
import view.MontarPrato01.MontarPrato01View;
import view.MontarPrato02.MontarPrato02View;

public class MesaDao {

    Conexao conex = InicioView.conex;

    public void salvarMesa(Mesa m) {
        try {
            PreparedStatement pst = conex.con.prepareStatement("insert into mesa (nome_mesa, estado_mesa) values(?,?)");

            pst.setString(1, m.getNomeMesa());
            pst.setString(2, "1");

            pst.execute();
        } catch (SQLException ex) {
            TelasMensagem.setErroMensagem("Erro ao inserir mesa:\n" + ex, "Erro no banco de dados");
        }
    }

    public int quantidadeDados(String a, int x) {

        int count = 0;

        if (x == 1) {
            conex.executaSql("select count (1) from " + a);
        }
        if (x == 2) {
            conex.executaSql("select count (categoria) as quantidade from produto where categoria = '" + a + "'");
        }
        if (x == 3) {
            conex.executaSql("select count (estado_pedido) as quantidade from pedido where estado_pedido = '" + a + "'");
        }
        if (x == 4) {
            conex.executaSql("select count (tipo) as quantidade from produto where tipo =  '" + a + "'");
        }
        if (x == 5) {
            conex.executaSql("select count (nome_produto) as quantidade from pedido where nome_produto = '" + a + "'");
        }
        if (x == 6) {
            conex.executaSql("select count (setor) as quantidade from produto where setor = '" + a + "'");
        }

        try {
            conex.rs.next();
            count = conex.rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
            TelasMensagem.setErroMensagem("Erro ao executar método de contagem:\n" + ex, "Erro no banco de dados");
        }
        return count;
    }

    public ArrayList retornaDados(String a, String b, String aux, int coluna) {
        ArrayList<String> dados = new ArrayList();

        conex.executaSql("select *from" + a + " order by" + b);

        try {
            while (conex.rs.next()) {
                if (coluna < 4) {
                    if (conex.rs.getString("categoria").equals(aux) || conex.rs.getString("tipo").equals(aux)) {
                        switch (coluna) {
                            case 1:
                                dados.add(conex.rs.getString("descricao"));
                                break;
                            case 2:
                                dados.add(conex.rs.getString("preco"));
                                break;
                            case 3:
                                dados.add(conex.rs.getString("categoria"));
                                break;
                        }
                    }
                }
                if (coluna > 3) {
                    if (conex.rs.getString("estado_pedido").equals(aux)) {
                        switch (coluna) {
                            case 4:
                                dados.add(conex.rs.getString("nome_produto"));
                                break;
                            case 5:
                                dados.add(conex.rs.getString("quantidade_produto"));
                                break;
                            case 6:
                                dados.add(conex.rs.getString("valor_pedido"));
                                break;
                            case 7:
                                dados.add(conex.rs.getString("estado_pedido"));
                                break;
                            case 8:
                                dados.add(conex.rs.getString("id_mesa"));
                                break;
                            case 9:
                                dados.add(conex.rs.getString("categoria_pedido"));
                                break;
                            case 10:
                                if (conex.rs.getString("id_prato") == null) {
                                    dados.add("a");
                                } else {
                                    dados.add(conex.rs.getString("id_prato"));
                                }
                                break;
                            case 11:
                                dados.add(conex.rs.getString("id_pedido"));
                                break;
                            case 12:
                                dados.add(conex.rs.getString("data"));
                                break;
                            case 13:
                                dados.add(conex.rs.getString("hora"));
                                break;
                            case 14:
                                dados.add(conex.rs.getString("id_produto"));
                                break;
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            TelasMensagem.setErroMensagem("Erro ao retornar dados da mesa:\n" + ex, "Erro no banco de dados");
        }
        return dados;
    }

    public ArrayList retornaId(String a, String b, String aux) {
        ArrayList<Integer> id = new ArrayList();

        conex.executaSql("select *from" + a + " order by" + b);

        try {
            while (conex.rs.next()) {
                if (conex.rs.getString("categoria").equals(aux) || conex.rs.getString("tipo").equals(aux)) {
                    id.add(conex.rs.getInt("id_produto"));
                }
            }

        } catch (SQLException ex) {
            TelasMensagem.setErroMensagem("Erro ao retornar ID:\n" + ex, "Erro no banco de dados");
        }
        return id;
    }

    public void salvarPedido(Mesa m) {
        try {
            PreparedStatement pst = conex.con.prepareStatement("insert into pedido (id_mesa, "
                    + "id_produto, nome_produto, quantidade_produto, valor_pedido, estado_pedido) values(?,?,?,?,?,?)");

            pst.setInt(1, m.getIdMesa());
            pst.setInt(2, m.getIdProduto());
            pst.setString(3, m.getNomeProduto());
            pst.setString(4, m.getQuantidade_produto());
            pst.setString(5, m.getValorPedido());
            pst.setString(6, m.getEstadoPedido());

            pst.execute();
        } catch (SQLException ex) {
            TelasMensagem.setErroMensagem("Erro ao inserir dados no banco:\n" + ex, "Erro no banco de dados");
        }
    }

    public String pesquisarEstadoProduto(String a, String b, int id) {
        String pesquisa = new String();
        conex.executaSql("select *from " + a + " where " + b + " = " + id);

        try {
            while (conex.rs.next()) {
                if (conex.rs.getString("estado_pedido").equals("1")) {
                    pesquisa = conex.rs.getString("estado_pedido");
                }
            }
        } catch (SQLException ex) {
            TelasMensagem.setErroMensagem("Erro ao pesquisar estado do produto:\n" + ex, "Erro no banco de dados");
        }

        return pesquisa;
    }

    public void editaEstadoPedido(Mesa m) {
        try {
            PreparedStatement pst = conex.con.prepareStatement("update pedido set estado_pedido = ? where (id_mesa = ?) "
                    + "and (estado_pedido = '1')");

            pst.setString(1, m.getEstadoPedido());
            pst.setInt(2, m.getIdMesa());

            pst.execute();
        } catch (SQLException ex) {
            TelasMensagem.setErroMensagem("Erro editar estado pedido no banco:\n" + ex, "Erro no banco de dados");
        }
    }

    public void montarPrato(Mesa m) {
        try {
            if (MontarPrato01View.avancar <= 2) {
                PreparedStatement pst = conex.con.prepareStatement("insert into pedido (id_mesa, "
                        + "id_produto, nome_produto, quantidade_produto, valor_pedido, "
                        + "estado_pedido, categoria_pedido) values(?,?,?,?,?,?,?)");

                pst.setInt(1, m.getIdMesa());
                pst.setInt(2, m.getIdProduto());
                pst.setString(3, m.getNomeProduto());
                pst.setString(4, m.getQuantidade_produto());
                pst.setString(5, m.getValorPedido());
                pst.setString(6, m.getEstadoPedido());
                switch (MontarPrato01View.avancar) {
                    case 0:
                        pst.setString(7, "g");
                        break;
                    case 1:
                        pst.setString(7, "c");
                        break;
                    case 2:
                        pst.setString(7, "m");
                        break;
                }

                pst.execute();
            } else {
                System.out.println("passou");
                PreparedStatement pst = conex.con.prepareStatement("insert into pedido (id_mesa, "
                        + "id_produto, nome_produto, quantidade_produto, valor_pedido, "
                        + "estado_pedido, num_prato) values(?,?,?,?,?,?,?)");

                pst.setInt(1, m.getIdMesa());
                pst.setInt(2, m.getIdProduto());
                pst.setString(3, m.getNomeProduto());
                pst.setString(4, m.getQuantidade_produto());
                pst.setString(5, m.getValorPedido());
                pst.setString(6, m.getEstadoPedido());
                pst.setString(7, Integer.toString(MontarPrato02View.numPrato));

                pst.execute();
            }

        } catch (SQLException ex) {
            TelasMensagem.setErroMensagem("Erro ao inserir pedido:\n" + ex, "Erro no banco de dados");
        }
    }

    public void excluirItemPrato(String a, String b, int idProduto) {
        try {
            PreparedStatement pst = conex.con.prepareStatement("delete from " + a + " where " + b + "=? and id_prato is null");

            pst.setInt(1, idProduto);

            pst.execute();
        } catch (SQLException ex) {
            TelasMensagem.setErroMensagem("Erro ao excluir item:\n" + ex, "Erro no banco de dados");
        }
    }

    public ArrayList retornaTipoProduto(String a, String b, String aux) {
        ArrayList<String> dados = new ArrayList();
        conex.executaSql("select *from" + a + " order by" + b);

        try {
            while (conex.rs.next()) {
                if (conex.rs.getString("categoria").equals(aux)) {
                    dados.add(conex.rs.getString("tipo"));
                }
            }
        } catch (SQLException ex) {
            TelasMensagem.setErroMensagem("Erro ao retornar tipo:\n" + ex, "Erro no banco de dados");
        }
        return dados;
    }

    public ArrayList retornaSetor(String a, String b, String aux) {
        ArrayList<String> dados = new ArrayList();

        conex.executaSql("select *from" + a + " order by" + b);

        try {
            while (conex.rs.next()) {
                if (conex.rs.getString("setor").equals(aux)) {
                    dados.add(conex.rs.getString("setor"));
                    break;
                }
            }
        } catch (SQLException ex) {
            TelasMensagem.setErroMensagem("Erro ao retornar dados dos setores:\n" + ex, "Erro no banco de dados");
        }
        return dados;
    }
}
