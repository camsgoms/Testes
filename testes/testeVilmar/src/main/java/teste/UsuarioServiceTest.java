package teste;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entidades.Usuario;
import servicos.UsuarioService;

public class UsuarioServiceTest {

    private UsuarioService usuarioService;

    @BeforeEach
    public void setup() {
        usuarioService = new UsuarioService();
    }

    @Test
    public void testCadastroUsuarioComNomeEmBranco() {
        Usuario usuario = new Usuario("", "Silva", "novo@exemplo.com", "Nenhuma", "SenhaValida1!", "81 99999-9999", "");
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.adicionarUsuario(usuario);
        });
        assertEquals("O nome não pode ser vazio", thrown.getMessage());
    }


    @Test
    public void testCadastroUsuarioComOutrosEmBranco() {
        Usuario usuario = new Usuario("Ana", "Silva", "novo@exemplo.com", "", "SenhaValida1!", "81 99999-9999", "");
        boolean resultado = usuarioService.adicionarUsuario(usuario);
        assertTrue(resultado, "Cadastro deve ser realizado mesmo quando o campo Outros está em branco.");
    }

    @Test
    public void testCadastroUsuarioComTelefoneEmBranco() {
        Usuario usuario = new Usuario("Ana", "Silva", "novo@exemplo.com", "Endereço", "SenhaValida1!", "", "");
        boolean resultado = usuarioService.adicionarUsuario(usuario);
        assertTrue(resultado, "Cadastro deve ser realizado mesmo quando o campo Telefone está em branco.");
    }

    @Test
    public void testCadastroUsuarioComEnderecoEmBranco() {
        Usuario usuario = new Usuario("Ana", "Silva", "novo@exemplo.com", "", "SenhaValida1!", "81 99999-9999", "");
        boolean resultado = usuarioService.adicionarUsuario(usuario);
        assertTrue(resultado, "Cadastro deve ser realizado mesmo quando o campo Endereço está em branco.");
    }

    @Test
    public void testCadastroUsuarioComEmailValido() {
        Usuario usuario = new Usuario("Ana", "Silva", "email_valido@exemplo.com", "Endereço", "SenhaValida1!", "81 99999-9999", "");
        boolean resultado = usuarioService.adicionarUsuario(usuario);
        assertTrue(resultado, "Cadastro deve ser realizado quando o e-mail é válido e não registrado.");
    }

    @Test
    public void testCadastroUsuarioComSenhaEmBranco() {
        Usuario usuario = new Usuario("Ana", "Silva", "novo@exemplo.com", "Nenhuma", "", "81 99999-9999", "");
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.adicionarUsuario(usuario);
        });
        assertEquals("A senha não pode ser vazia", thrown.getMessage());
    }

    @Test
    public void testCadastroUsuarioComSenhaApenasNumerica() {
        Usuario usuario = new Usuario("Ana", "Silva", "novo@exemplo.com", "Nenhuma", "9999", "81 99999-9999", "");
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.adicionarUsuario(usuario);
        });
        assertEquals("A senha deve conter pelo menos 8 caracteres, incluindo letras maiúsculas, minúsculas, números e símbolos", thrown.getMessage());
    }

    @Test
    public void testCadastroUsuarioComSenhaApenasAlfabetica() {
        Usuario usuario = new Usuario("Ana", "Silva", "novo@exemplo.com", "Nenhuma", "abcdef", "81 99999-9999", "");
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.adicionarUsuario(usuario);
        });
        assertEquals("A senha deve conter pelo menos 8 caracteres, incluindo letras maiúsculas, minúsculas, números e símbolos", thrown.getMessage());
    }

    @Test
    public void testCadastroUsuarioComSenhaMenorQueValida() {
        Usuario usuario = new Usuario("Ana", "Silva", "novo@exemplo.com", "Nenhuma", "ana", "81 99999-9999", "");
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.adicionarUsuario(usuario);
        });
        assertEquals("A senha deve conter pelo menos 8 caracteres, incluindo letras maiúsculas, minúsculas, números e símbolos", thrown.getMessage());
    }

    @Test
    public void testCadastroUsuarioComSenhaMaiorQueValida() {
        Usuario usuario = new Usuario("Ana", "Silva", "novo@exemplo.com", "Nenhuma", "aaaaaaaaaa", "81 99999-9999", "");
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.adicionarUsuario(usuario);
        });
        assertEquals("A senha deve conter pelo menos 8 caracteres, incluindo letras maiúsculas, minúsculas, números e símbolos", thrown.getMessage());
    }

    @Test
    public void testCadastroUsuarioComEmailJaRegistrado() {
        Usuario usuario = new Usuario("Ana", "Silva", "ana@exemplo.com", "Nenhuma", "SenhaValida1!", "81 99999-9999", "");
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.adicionarUsuario(usuario);
        });
        assertEquals("O e-mail já está em uso", thrown.getMessage());
    }

    @Test
    public void testCadastroUsuarioComEmailEmBranco() {
        Usuario usuario = new Usuario("Ana", "Silva", "", "Nenhuma", "SenhaValida1!", "81 99999-9999", "");
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.adicionarUsuario(usuario);
        });
        assertEquals("O e-mail não pode ser vazio", thrown.getMessage());
    }

    @Test
    public void testCadastroUsuarioComSenhaInvalida() {
        Usuario usuario = new Usuario("Ana", "Silva", "novo@exemplo.com", "Endereço", "123", "81 99999-9999", "");
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.adicionarUsuario(usuario);
        });
        assertEquals("A senha deve conter pelo menos 8 caracteres, incluindo letras maiúsculas, minúsculas, números e símbolos", thrown.getMessage());
    }

    @Test
    public void testCadastroUsuarioComSenhaSemLetras() {
        Usuario usuario = new Usuario("Ana", "Silva", "novo@exemplo.com", "Endereço", "123456", "81 99999-9999", "");
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.adicionarUsuario(usuario);
        });
        assertEquals("A senha deve conter pelo menos 8 caracteres, incluindo letras maiúsculas, minúsculas, números e símbolos", thrown.getMessage());
    }

    @Test
    public void testCadastroUsuarioComSenhaSemNumeros() {
        Usuario usuario = new Usuario("Ana", "Silva", "novo@exemplo.com", "Endereço", "abcdef", "81 99999-9999", "");
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.adicionarUsuario(usuario);
        });
        assertEquals("A senha deve conter pelo menos 8 caracteres, incluindo letras maiúsculas, minúsculas, números e símbolos", thrown.getMessage());
    }

    @Test
    public void testAtualizarSenhaValida() {
        usuarioService.atualizarSenha("ana@exemplo.com", "SenhaNova1!");
        Usuario usuario = usuarioService.getUsuarios().get("ana@exemplo.com");
        assertEquals("SenhaNova1!", usuario.getSenha());
    }

    @Test
    public void testAtualizarSenhaInvalida() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.atualizarSenha("ana@exemplo.com", "123");
        });
        assertEquals("A senha deve conter pelo menos 8 caracteres, incluindo letras maiúsculas, minúsculas, números e símbolos", thrown.getMessage());
    }

    @Test
    public void testAtualizarEmailValido() {
        usuarioService.atualizarEmail("ana@exemplo.com", "novo@exemplo.com");
        Usuario usuario = usuarioService.getUsuarios().get("novo@exemplo.com");
        assertNotNull(usuario);
    }

    @Test
    public void testAtualizarEmailInvalido() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.atualizarEmail("ana@exemplo.com", "novo@exemplo");
        });
        assertEquals("O e-mail é inválido", thrown.getMessage());
    }

    @Test
    public void testAtualizarTelefoneValido() {
        usuarioService.atualizarTelefone("ana@exemplo.com", "85 88888-8888");
        Usuario usuario = usuarioService.getUsuarios().get("ana@exemplo.com");
        assertEquals("85 88888-8888", usuario.getTelefone());
    }

    @Test
    public void testAtualizarEnderecoValido() {
        usuarioService.atualizarEndereco("ana@exemplo.com", "Novo Endereço");
        Usuario usuario = usuarioService.getUsuarios().get("ana@exemplo.com");
        assertEquals("Novo Endereço", usuario.getEndereco());
    }

    @Test
    public void testAtualizarNomeValido() {
        usuarioService.atualizarNome("ana@exemplo.com", "Ana Clara");
        Usuario usuario = usuarioService.getUsuarios().get("ana@exemplo.com");
        assertEquals("Ana Clara", usuario.getNome());
    }

    @Test
    public void testAtualizarFotoPerfilValida() {
        usuarioService.atualizarFotoPerfil("ana@exemplo.com", "foto-perfil.jpg");
        Usuario usuario = usuarioService.getUsuarios().get("ana@exemplo.com");
        assertEquals("foto-perfil.jpg", usuario.getFotoPerfil());
    }
}
