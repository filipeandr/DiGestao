package modelo;

public abstract class Setor extends Categoria {

    int idSetor;
    String setor;

    public Setor() {

    }

    public Setor(int idSetor, String setor, int idCateoria, String categoria) {
        super(idCateoria, categoria);
        this.idSetor = idSetor;
        this.setor = setor;
    }

    public int getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(int idSetor) {
        this.idSetor = idSetor;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

}
