package validacao;

public class ValidacoesInternas {

    public static boolean validarTamanho(String str, int min, int max) {
        return str.length() >= min && str.length() <= max;
    }

    public static boolean validarEmail(String str) {
        return str.matches("(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|"
                + "(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|"
                + "(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))")
                && str.length() > 0;
    }

    public static boolean validarCelular(String str) {
        return str.matches("[1-9]{2}9[1-9][0-9]{3}[0-9]{4}");
    }

    public static boolean validarConfSenha(String senha, String conf) {
        return senha.equals(conf) && validarTamanho(conf, 6, 99);
    }

    public static boolean validarPreco(String valor) {
        int cont = 0;
        for (int i = 0; i < valor.length(); i++) {
            char c = valor.charAt(i);
            if (c == 46) {
                cont++;
            }
        }
        return cont <= 1;
    }

    public static String formataPreco(String valor) {
        double dValor = Double.parseDouble(valor);
        valor = String.format("%.2f", dValor);
        return valor;
    }
}
