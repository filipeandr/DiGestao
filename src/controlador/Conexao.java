package controlador;

import java.sql.*;
import java.util.ArrayList;
import validacao.TelasMensagem;

public class Conexao {

    public Statement stm;
    public ResultSet rs;
    private final String driver = "org.postgresql.Driver";
    private final String caminho = "jdbc:postgresql://localhost:5432/gerenciamentorestaurante";
    private final String usuario = "postgres";
    private final String senha = "postgre";
    public Connection con;

    public void conexao() {
        try {
            System.setProperty("jdbc.Drivers", driver);
            con = DriverManager.getConnection(caminho, usuario, senha);
            System.out.println("Conectou no banco...");
        } catch (SQLException ex) {
            TelasMensagem.setErroMensagem("Erro ao se conectar no banco de dados:\n" + ex, "Erro no banco de dados");
        }
    }

    public void executaSql(String sql) {
        try {
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
            TelasMensagem.setErroMensagem("Erro ao executar o SQL:\n" + ex, "Erro no banco de dados");
        }
    }

    public void desconecta() {
        try {
            con.close();
            System.out.println("Desconectou do banco...");
        } catch (SQLException ex) {
            TelasMensagem.setErroMensagem("Erro ao desconectar o banco de dados:\n" + ex, "Erro no banco de dados");
        }
    }

    public ArrayList retornaDados(String a, String b, int coluna) {
        ArrayList<String> dados = new ArrayList();

        executaSql("select *from" + a + " order by" + b);

        try {
            while (rs.next()) {
                if (coluna == 1) {
                    dados.add(rs.getString("categoria"));
                }
                if (coluna == 2) {
                    dados.add(rs.getString("setor"));
                }
                if (coluna == 3) {
                    dados.add(rs.getString("descricao"));
                }
                if (coluna == 4) {
                    dados.add(rs.getString("categoria"));
                }
                if (coluna == 5) {
                    dados.add(rs.getString("preco"));
                }
                if (coluna == 6) {
                    dados.add(rs.getString("estoque"));
                }
                if (coluna == 7) {
                    dados.add(rs.getString("nome_mesa"));
                }
                if (coluna == 8) {
                    dados.add(rs.getString("estado_mesa"));
                }
                if (coluna == 9) {
                    dados.add(rs.getString("quantidade_minima"));
                }
                if (coluna == 10) {
                    dados.add(rs.getString("caminho_imagem"));
                }
            }

        } catch (SQLException ex) {
            TelasMensagem.setErroMensagem("Erro ao retornar dados:\n" + ex, "Erro no banco de dados");
        }
        return dados;
    }

    public ArrayList retornaId(String a, String b, int coluna) {
        ArrayList<Integer> id = new ArrayList();

        executaSql("select *from" + a + " order by" + b);

        try {
            while (rs.next()) {
                if (coluna == 1) {
                    id.add(rs.getInt("id_categoria"));
                }
                if (coluna == 2) {
                    id.add(rs.getInt("id_setor"));
                }
                if (coluna == 3) {
                    id.add(rs.getInt("id_produto"));
                }
                if (coluna == 4) {
                    id.add(rs.getInt("id_mesa"));
                }
            }

        } catch (SQLException ex) {
            TelasMensagem.setErroMensagem("Erro ao retornar ID:\n" + ex, "Erro no banco de dados");
        }
        return id;
    }

    public boolean verificarDadoExistente(String dado, int quantidadeDados,
            ArrayList dados, boolean verifica) {
        String aux;

        for (int i = 0; i < quantidadeDados; i++) {
            aux = (String) dados.get(i);

            if (dado.equals(aux.toUpperCase())) {
                verifica = true;
                break;
            } else {
                verifica = false;
            }
        }
        return verifica;
    }
}
