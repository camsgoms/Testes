package entidades;

public class Foto {
    private String nome;
    private int tamanho; // Tamanho em bytes
    private String descricao; // Novo atributo para a descrição

    // Construtor
    public Foto(String nome, int tamanho) {
        this.nome = nome;
        this.tamanho = tamanho;
        this.descricao = ""; // Inicializa a descrição como uma string vazia
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Foto{" +
                "nome='" + nome + '\'' +
                ", tamanho=" + tamanho +
                ", descricao='" + descricao + '\'' + // Inclui a descrição no toString
                '}';
    }
}
