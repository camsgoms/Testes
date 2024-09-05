package servicos;


import entidades.Comentario;


public interface ComentarioService {
    boolean adicionarComentario(Comentario comentario);
    boolean denunciarFoto(String fotoNome);
    boolean validarComentario(Comentario comentario);
    boolean validarFormatoFoto(String fotoNome);
    boolean validarNumeroMaximoFotos(Comentario comentario);
    boolean validarFotosDuplicadas(Comentario comentario);
    boolean validarTamanhoFoto(Comentario comentario);
}

