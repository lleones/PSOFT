public class Produto {
    private String nome;
    private String id;
    private String fabricante;

    public Produto(String nome, String id, String fabricante){
        this.nome = nome;
        this.id = id;
        this.fabricante = fabricante;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
