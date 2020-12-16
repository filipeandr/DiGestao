package modelo.dadosTemporarios;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import validacao.TelasMensagem;

public class SalvarTemporario {

    public static void salvarDados(String dados, String caminho) {
        try {
            FileOutputStream arqUsuario = new FileOutputStream(caminho);
            PrintWriter pr = new PrintWriter(arqUsuario);
            pr.print(dados);
            pr.close();
            arqUsuario.close();
        } catch (IOException e) {
            TelasMensagem.setErroMensagem("Erro ao tentar salvar dados:\n"+e, "Salvar dados");
        }
    }

    public static void excluirDados(String caminho) {
        boolean efetivar = (new File(caminho)).delete();
    }
}
