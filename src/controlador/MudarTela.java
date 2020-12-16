package controlador;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import view.Financeiro.FinanceiroView;
import view.FuncProdutos.FuncProdutosView;
import view.Inicial.InicioView;
import view.Mesas01.Mesas01View;
import view.Mesas02.Mesas02View;
import view.Mesas03.Mesas03View;
import view.MontarPrato01.MontarPrato01View;
import view.MontarPrato02.MontarPrato02View;
import view.Pagar.PagarView;
import view.Preparo01.Preparo01View;
import view.Preparo02.Preparo02View;
import view.SubCadastrar.SubCadastrarView;
import view.SubCadastrarSetor.SubCadastrarSetorView;
import view.SubCategorias.SubCategoriasView;
import view.SubProdutos.SubProdutosView;
import view.SubReposicao.SubReposicaoView;

import view.TelaLogin01.TelaLogin01View;
import view.TelaLogin02.TelaLogin02View;
import view.TelaLogin03.TelaLogin03View;
import view.TelaLogin04.TelaLogin04View;

public class MudarTela {

    public void iniciarProdutos(ActionEvent a) throws IOException {
        FuncProdutosView f = new FuncProdutosView();
        Stage b = (Stage) ((Node) a.getSource()).getScene().getWindow();
        f.iniciar(b);
    }

    public void iniciarFinanceiro(ActionEvent a) {
        FinanceiroView f = new FinanceiroView();
        Stage b = (Stage) ((Node) a.getSource()).getScene().getWindow();
        f.iniciar(b);
    }

    public void iniciarSubProdutos(ActionEvent a) throws IOException {
        SubProdutosView f = new SubProdutosView();
        Stage b = (Stage) ((Node) a.getSource()).getScene().getWindow();
        f.iniciar(b);
    }

    public void iniciarCategorias(ActionEvent a) throws IOException {
        SubCategoriasView f = new SubCategoriasView();
        Stage b = (Stage) ((Node) a.getSource()).getScene().getWindow();
        f.iniciar(b);
    }

    public void iniciarReposicao(ActionEvent a) throws IOException {
        SubReposicaoView f = new SubReposicaoView();
        Stage b = (Stage) ((Node) a.getSource()).getScene().getWindow();
        f.iniciar(b);
    }

    public void iniciarSetores(ActionEvent a) throws IOException {
        SubCadastrarSetorView f = new SubCadastrarSetorView();
        Stage b = (Stage) ((Node) a.getSource()).getScene().getWindow();
        f.iniciar(b);
    }

    public void iniciarCadastrarProdutos(ActionEvent a) throws IOException {
        SubCadastrarView f = new SubCadastrarView();
        Stage b = (Stage) ((Node) a.getSource()).getScene().getWindow();
        f.iniciar(b);
    }

    public void IniciarTelaInicial(ActionEvent a) throws IOException {
        InicioView f = new InicioView();
        Stage b = (Stage) ((Node) a.getSource()).getScene().getWindow();
        f.iniciar(b);
    }

    public void IniciarCadastro(ActionEvent a) throws IOException {
        TelaLogin02View f = new TelaLogin02View();
        Stage b = (Stage) ((Node) a.getSource()).getScene().getWindow();
        f.iniciar(b);
    }

    public void IniciarLogin(ActionEvent a) throws IOException {
        TelaLogin01View f = new TelaLogin01View();
        Stage b = (Stage) ((Node) a.getSource()).getScene().getWindow();
        f.iniciar(b);
    }

    public void IniciarCadastro2(ActionEvent a) throws IOException {
        TelaLogin03View f = new TelaLogin03View();
        Stage b = (Stage) ((Node) a.getSource()).getScene().getWindow();
        f.iniciar(b);
    }

    public void IniciarCadastro3(ActionEvent a) throws IOException {
        TelaLogin04View f = new TelaLogin04View();
        Stage b = (Stage) ((Node) a.getSource()).getScene().getWindow();
        f.iniciar(b);
    }

    public void iniciarMesas01(ActionEvent a) {
        Mesas01View mv = new Mesas01View();
        Stage b = (Stage) ((Node) a.getSource()).getScene().getWindow();
        mv.iniciar(b);
    }

    public void iniciarMesas02(ActionEvent a) {
        Mesas02View mv = new Mesas02View();
        Stage b = (Stage) ((Node) a.getSource()).getScene().getWindow();
        mv.iniciar(b);
    }

    public void iniciarMesas03(ActionEvent a) {
        Mesas03View mv = new Mesas03View();
        Stage b = (Stage) ((Node) a.getSource()).getScene().getWindow();
        mv.iniciar(b);
    }

    public void iniciarMontarPrato01(ActionEvent a) {
        MontarPrato01View mp = new MontarPrato01View();
        Stage b = (Stage) ((Node) a.getSource()).getScene().getWindow();
        mp.iniciar(b);
    }

    public void iniciarMontarPrato02(ActionEvent a) {
        MontarPrato02View mp = new MontarPrato02View();
        Stage b = (Stage) ((Node) a.getSource()).getScene().getWindow();
        mp.iniciar(b);
    }

    public void iniciarPreparo01View(ActionEvent a) {
        Preparo01View p = new Preparo01View();
        Stage b = (Stage) ((Node) a.getSource()).getScene().getWindow();
        p.iniciar(b);
    }

    public void iniciarPreparo02View(ActionEvent a) {
        Preparo02View p = new Preparo02View();
        Stage b = (Stage) ((Node) a.getSource()).getScene().getWindow();
        p.iniciar(b);
    }

    public void iniciaPagar(ActionEvent a) {
        PagarView p = new PagarView();
        Stage b = (Stage) ((Node) a.getSource()).getScene().getWindow();
        p.iniciar(b);
    }
}
