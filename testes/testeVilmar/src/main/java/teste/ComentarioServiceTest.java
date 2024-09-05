package teste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import entidades.Comentario;
import entidades.Foto;
import servicos.ComentarioServiceImpl;

public class ComentarioServiceTest {

    @InjectMocks
    private ComentarioServiceImpl comentarioService;

    @Mock
    private Comentario comentarioMock;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAdicionarComentarioComFotoTamanhoExcedido() {
        // Configura o mock para retornar um comentário com uma foto grande
        Foto fotoGrande = new Foto("foto_grande.jpg", 6 * 1024 * 1024); // 6MB

        when(comentarioMock.getComentario()).thenReturn("Comentário com foto de tamanho excedido");
        when(comentarioMock.getFotos()).thenReturn(Arrays.asList(fotoGrande));

        // Simula a tentativa de adicionar o comentário
        boolean resultado = comentarioService.adicionarComentario(comentarioMock);

        // Verifica o resultado esperado
        assertFalse(resultado, "O sistema deve exibir uma mensagem de erro ao tentar anexar uma foto com tamanho superior ao limite permitido.");
    }

    @Test
    public void testAdicionarComentarioComFotoFormatoInvalido() {
        // Configura o mock para retornar um comentário com uma foto de formato inválido
        Foto fotoInvalida = new Foto("foto_invalida.bmp", 2 * 1024 * 1024); // 2MB

        when(comentarioMock.getComentario()).thenReturn("Comentário com foto de formato inválido");
        when(comentarioMock.getFotos()).thenReturn(Arrays.asList(fotoInvalida));

        // Simula a tentativa de adicionar o comentário
        boolean resultado = comentarioService.adicionarComentario(comentarioMock);

        // Verifica o resultado esperado
        assertFalse(resultado, "O sistema deve exibir uma mensagem de erro ao tentar anexar uma foto com formato inválido.");
    }

    @Test
    public void testAdicionarComentarioComNumeroMaximoFotos() {
        // Configura o mock para retornar um comentário com o número máximo de fotos
        Foto foto1 = new Foto("foto1.jpg", 2 * 1024 * 1024); // 2MB
        Foto foto2 = new Foto("foto2.jpg", 2 * 1024 * 1024); // 2MB
        Foto foto3 = new Foto("foto3.jpg", 2 * 1024 * 1024); // 2MB

        when(comentarioMock.getComentario()).thenReturn("Comentário com número máximo de fotos");
        when(comentarioMock.getFotos()).thenReturn(Arrays.asList(foto1, foto2, foto3));

        // Simula a tentativa de adicionar o comentário
        boolean resultado = comentarioService.adicionarComentario(comentarioMock);

        // Verifica o resultado esperado
        assertTrue(resultado, "O sistema deve permitir adicionar o comentário com o número máximo de fotos permitido.");
    }

    @Test
    public void testAdicionarComentarioComFotosDuplicadas() {
        // Configura o mock para retornar um comentário com fotos duplicadas
        Foto fotoDuplicada = new Foto("foto_duplicada.jpg", 2 * 1024 * 1024); // 2MB

        when(comentarioMock.getComentario()).thenReturn("Comentário com fotos duplicadas");
        when(comentarioMock.getFotos()).thenReturn(Arrays.asList(fotoDuplicada, fotoDuplicada));

        // Simula a tentativa de adicionar o comentário
        boolean resultado = comentarioService.adicionarComentario(comentarioMock);

        // Verifica o resultado esperado
        assertFalse(resultado, "O sistema deve exibir uma mensagem de erro ao tentar anexar fotos duplicadas.");
    }

    @Test
    public void testAdicionarComentarioSemFotoObrigatória() {
        // Configura o mock para retornar um comentário sem fotos
        when(comentarioMock.getComentario()).thenReturn("Comentário sem foto obrigatória");
        when(comentarioMock.getFotos()).thenReturn(Arrays.asList()); // Sem fotos

        // Simula a tentativa de adicionar o comentário
        boolean resultado = comentarioService.adicionarComentario(comentarioMock);

        // Verifica o resultado esperado
        assertFalse(resultado, "O sistema deve exibir uma mensagem de erro ao tentar adicionar um comentário sem foto obrigatória.");
    }

    @Test
    public void testDenunciarFoto() {
        // Simula a denúncia de uma foto
        String nomeFoto = "foto_inadequada.jpg";

        // Simula a tentativa de denunciar a foto
        boolean resultado = comentarioService.denunciarFoto(nomeFoto);

        // Verifica o resultado esperado
        assertTrue(resultado, "O sistema deve permitir denunciar uma foto.");
    }

    @Test
    public void testVisualizarDescricaoFoto() {
        // Configura o mock para retornar um comentário com uma foto com descrição
        Foto foto = new Foto("entrada_principal.jpg", 2 * 1024 * 1024); // 2MB
        foto.setDescricao("Foto da entrada principal");

        when(comentarioMock.getComentario()).thenReturn("Comentário com a foto da entrada principal");
        when(comentarioMock.getFotos()).thenReturn(Arrays.asList(foto));

        // Simula a adição do comentário
        comentarioService.adicionarComentario(comentarioMock);

        // Verifica a descrição da foto
        assertEquals("Foto da entrada principal", foto.getDescricao(), "O texto da descrição deve ser 'Foto da entrada principal'.");
    }

    @Test
    public void testAdicionarComentarioSemTexto() {
        // Configura o mock para retornar um comentário vazio
        Comentario comentario = new Comentario();
        comentario.setComentario(""); // Comentário vazio

        // Criar uma foto válida para o teste
        Foto foto = new Foto("foto_valid.jpg", 1 * 1024 * 1024); // 1MB

        comentario.setFotos(Arrays.asList(foto)); // Anexa a foto

        // Simula a tentativa de adicionar o comentário
        boolean resultado = comentarioService.adicionarComentario(comentario);

        // Verifica o resultado esperado
        assertFalse(resultado, "O sistema deve exibir uma mensagem de erro ao tentar adicionar um comentário sem texto.");
    }

    @Test
    public void testAdicionarComentarioComFotoInvalida() {
        // Configura o mock para retornar um comentário com uma foto inválida
        Foto fotoInvalida = new Foto("foto_invalida.txt", 2 * 1024 * 1024); // 2MB

        when(comentarioMock.getComentario()).thenReturn("Comentário com foto inválida");
        when(comentarioMock.getFotos()).thenReturn(Arrays.asList(fotoInvalida));

        // Simula a tentativa de adicionar o comentário
        boolean resultado = comentarioService.adicionarComentario(comentarioMock);

        // Verifica o resultado esperado
        assertFalse(resultado, "O sistema deve exibir uma mensagem de erro ao tentar anexar uma foto com formato inválido.");
    }

}
