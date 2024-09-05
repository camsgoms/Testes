package entidades;

import java.util.List;

public class Comentario {

    private String comentario;
    private List<Foto> fotos;

    // Construtor padrão
    public Comentario() {
    }

    // Construtor com parâmetros
    public Comentario(String comentario, List<Foto> fotos) {
        this.comentario = comentario;
        this.fotos = fotos;
    }

    // Getters e Setters
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "comentario='" + comentario + '\'' +
                ", fotos=" + fotos +
                '}';
    }
}
