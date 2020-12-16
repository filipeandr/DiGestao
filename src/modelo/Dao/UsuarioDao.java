package modelo.Dao;

import controlador.Conexao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.Usuario;
import validacao.TelasMensagem;
import view.TelaLogin01.TelaLogin01View;

public class UsuarioDao {

    Conexao conex = TelaLogin01View.conex;

    public void salvar(Usuario u) {
        try {
            PreparedStatement pst = conex.con.prepareStatement("insert into usuario "
                    + "(nome_empresa, celular_usuario, nome_fantasia, nome_usuario, "
                    + "email_usuario, senha_usuario, celular_empresa, cidade_empresa, "
                    + "numero_empresa, rua_empresa, cep_empresa, bairro_empresa, "
                    + "estado_empresa) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");

            pst.setString(1, u.getNomeEmpresa());
            pst.setString(2, u.getCelular());
            pst.setString(3, u.getNomeFantasia());
            pst.setString(4, u.getNome());
            pst.setString(5, u.getEmail());
            pst.setString(6, u.getSenha());
            pst.setString(7, u.getCelularEmpresa());
            pst.setString(8, u.getCidade());
            pst.setString(9, u.getNumero());
            pst.setString(10, u.getRua());
            pst.setString(11, u.getCep());
            pst.setString(12, u.getBairro());
            pst.setString(13, u.getEstado());

            pst.execute();

        } catch (SQLException ex) {
            TelasMensagem.setErroMensagem("Erro ao inserir dados no banco:\n" + ex, "Erro no banco de dados");
        }
    }

    public String ler(String a, String b, String email) {
        String pesquisa;
        conex.executaSql("select *from " + a + " where " + b + " = " + email);
        try {
            conex.rs.next();
            pesquisa = conex.rs.getString("senha_usuario");
            return pesquisa;
        } catch (SQLException ex) {
            return "";
        }
    }
}
