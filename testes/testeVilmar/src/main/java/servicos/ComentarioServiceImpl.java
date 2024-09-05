package servicos;

import entidades.Comentario;
import entidades.Foto;

public class ComentarioServiceImpl implements ComentarioService {

    private static final String FORMATO_FOTO_PERMITIDO = "jpg"; // Defina o formato permitido
    private static final int NUMERO_MAXIMO_FOTOS = 3; // Defina o número máximo de fotos permitido
    private static final long TAMANHO_MAXIMO_FOTO = 5 * 1024 * 1024; // 5MB, por exemplo

    @Override
    public boolean adicionarComentario(Comentario comentario) {
        if (!validarComentario(comentario)) {
            return false; // Comentário não pode ser adicionado se a validação falhar
        }
        if (!validarNumeroMaximoFotos(comentario)) {
            return false; // Falha se o número máximo de fotos for excedido
        }
        if (!validarFotosDuplicadas(comentario)) {
            return false; // Falha se houver fotos duplicadas
        }
        if (!validarTamanhoFoto(comentario)) {
            return false; // Falha se houver fotos com tamanho superior ao permitido
        }
        if (comentario.getFotos().stream().anyMatch(foto -> !validarFormatoFoto(foto.getNome()))) {
            return false; // Adiciona validação para formato de foto
        }
        // Lógica para adicionar o comentário (ex: salvar no banco de dados)
        return true; // Comentário adicionado com sucesso
    }

    @Override
    public boolean denunciarFoto(String fotoNome) {
        if (fotoNome == null || fotoNome.isEmpty()) {
            return false; // Falha ao denunciar uma foto sem nome
        }
        // Lógica para registrar a denúncia (ex: salvar no banco de dados ou marcar como denunciado)
        return true; // Denúncia registrada com sucesso
    }

    @Override
    public boolean validarComentario(Comentario comentario) {
        if (comentario.getComentario().isEmpty()) {
            return false; // Falha se o comentário estiver vazio
        }
        // Verifica se a foto é obrigatória e se está ausente
        boolean fotoObrigatoria = true; // Suponha que a foto seja obrigatória para o teste
        if (fotoObrigatoria && comentario.getFotos().isEmpty()) {
            return false; // Falha se a foto for obrigatória e não estiver anexada
        }
        return true; // Comentário válido
    }

    @Override
    public boolean validarFormatoFoto(String fotoNome) {
        if (fotoNome == null || fotoNome.isEmpty()) {
            return false; // Falha se o nome do arquivo estiver vazio
        }
        String extensao = fotoNome.substring(fotoNome.lastIndexOf(".") + 1);
        return FORMATO_FOTO_PERMITIDO.equals(extensao); // Verifica se a extensão é permitida
    }

    @Override
    public boolean validarNumeroMaximoFotos(Comentario comentario) {
        return comentario.getFotos().size() <= NUMERO_MAXIMO_FOTOS; // Verifica se o número de fotos não excede o máximo
    }

    @Override
    public boolean validarFotosDuplicadas(Comentario comentario) {
        if (comentario.getFotos() == null || comentario.getFotos().isEmpty()) {
            return true; // Nenhuma foto para verificar duplicidade
        }
        long count = comentario.getFotos().stream()
                               .map(foto -> foto.getNome())
                               .distinct()
                               .count();
        return count == comentario.getFotos().size(); // Verifica se todos os nomes de fotos são únicos
    }

    @Override
    public boolean validarTamanhoFoto(Comentario comentario) {
        return comentario.getFotos().stream()
                         .allMatch(foto -> foto.getTamanho() <= TAMANHO_MAXIMO_FOTO); // Verifica se todas as fotos estão dentro do limite de tamanho
    }
}
