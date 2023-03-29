public class Produto {
    private String nome;
    private Long id;
    private String fabricante;
    private double preco;
    private String codigoBarra;

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
