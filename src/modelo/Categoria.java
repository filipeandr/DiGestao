package modelo;

abstract public class Categoria {

    int idCategoria;
    String categoria;

    public Categoria() {

    }

    public Categoria(int idCateoria, String categoria) {
        this.idCategoria = idCategoria;
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int id) {
        this.idCategoria = id;
    }
}
