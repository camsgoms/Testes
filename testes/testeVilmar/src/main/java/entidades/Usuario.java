package entidades;

public class Usuario {
    private String nome;
    private String sobrenome;
    private String email;
    private String endereco;
    private String senha;
    private String telefone;
    private String outros;
    private String fotoPerfil; // Adiciona o atributo fotoPerfil

    // Construtor com fotoPerfil
    public Usuario(String nome, String sobrenome, String email, String endereco, String senha, String telefone, String outros, String fotoPerfil) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.endereco = endereco;
        this.senha = senha;
        this.telefone = telefone;
        this.outros = outros;
        this.fotoPerfil = fotoPerfil; // Inicializa o atributo fotoPerfil
    }

    // Construtor sem fotoPerfil
    public Usuario(String nome, String sobrenome, String email, String endereco, String senha, String telefone, String outros) {
        this(nome, sobrenome, email, endereco, senha, telefone, outros, null); // Chama o construtor com fotoPerfil como null
    }

    // Getters e setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSobrenome() { return sobrenome; }
    public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getOutros() { return outros; }
    public void setOutros(String outros) { this.outros = outros; }

    public String getFotoPerfil() { return fotoPerfil; }
    public void setFotoPerfil(String fotoPerfil) { this.fotoPerfil = fotoPerfil; } // Adiciona o getter e setter para fotoPerfil
}
