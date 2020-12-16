package controlador;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import modelo.Dao.UsuarioDao;
import modelo.Usuario;
import modelo.dadosTemporarios.SalvarTemporario;
import validacao.TelasMensagem;

public class ControladorLogin {

    Usuario u = new Usuario();
    UsuarioDao ud = new UsuarioDao();
    SalvarTemporario st = new SalvarTemporario();

    public void dados() {
        try {
            for (int i = 1; i < 4; i++) {
                try (FileInputStream arquivo = new FileInputStream("src/modelo/dados"
                        + "Temporarios/arqCad" + Integer.toString(i) + ".txt")) {
                    InputStreamReader input = new InputStreamReader(arquivo);
                    BufferedReader br = new BufferedReader(input);

                    String linha;

                    do {
                        linha = br.readLine();

                        if (i == 1) {
                            if (linha != null) {
                                String[] dados = linha.split(";");

                                u.setNome(dados[0]);
                                u.setNomeEmpresa(dados[1]);
                                u.setEmail(dados[2]);
                                u.setCelular(dados[3]);
                                u.setSenha(dados[4]);
                            }
                        }

                        if (i == 2) {
                            if (linha != null) {
                                String[] dados = linha.split(";");

                                u.setNomeFantasia(dados[0]);
                                u.setCelularEmpresa(dados[1]);
                            }
                        }

                        if (i == 3) {
                            if (linha != null) {
                                String[] dados = linha.split(";");

                                u.setCidade(dados[0]);
                                u.setNumero(dados[1]);
                                u.setRua(dados[2]);
                                u.setCep(dados[3]);
                                u.setBairro(dados[4]);
                                u.setEstado(dados[5]);
                            }
                        }
                    } while (linha != null);
                    br.close();
                    input.close();
                }
            }

            ud.salvar(u);

        } catch (IOException e) {
            TelasMensagem.setErroMensagem("Erro ao tentar cadastrar os dados:\n"+e, "Erro no cadastro");
        }
        SalvarTemporario.excluirDados("src/modelo/dadosTemporarios/arqCad1.txt");
        SalvarTemporario.excluirDados("src/modelo/dadosTemporarios/arqCad2.txt");
        SalvarTemporario.excluirDados("src/modelo/dadosTemporarios/arqCad3.txt");
    }
}
