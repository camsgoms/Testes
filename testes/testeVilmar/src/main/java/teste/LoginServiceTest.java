package teste;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import servicos.LoginService; // Supondo que você tenha um LoginService para gerenciar o login

public class LoginServiceTest {

    private LoginService loginService;

    @BeforeEach
    public void setup() {
        loginService = new LoginService();
    }

    @Test
    public void testLoginComCampoEmailEmBranco() {
        // Dados do Teste
        String email = "";
        String senha = "senha_valida";

        // Simula a tentativa de login
        String resultado = loginService.login(email, senha);

        // Verifica o resultado esperado
        assertEquals("Preencha o campo email", resultado, "Mensagem de erro esperada para campo email em branco.");
    }

    @Test
    public void testLoginComCampoSenhaEmBranco() {
        // Dados do Teste
        String email = "Ana_valido@example.com";
        String senha = "";

        // Simula a tentativa de login
        String resultado = loginService.login(email, senha);

        // Verifica o resultado esperado
        assertEquals("Preencha o campo senha", resultado, "Mensagem de erro esperada para campo senha em branco.");
    }

    @Test
    public void testLoginComCredenciaisCorretas() {
        // Dados do Teste
        String email = "ana_valido@example.com";
        String senha = "senha_valida";

        // Simula a tentativa de login
        String resultado = loginService.login(email, senha);

        // Verifica o resultado esperado
        assertEquals("Login realizado com sucesso", resultado, "Mensagem esperada para login com credenciais corretas.");
    }

    @Test
    public void testLoginComEmailInvalido() {
        // Dados do Teste
        String email = "ana_invalido.com";
        String senha = "senha_valida";

        // Simula a tentativa de login
        String resultado = loginService.login(email, senha);

        // Verifica o resultado esperado
        assertEquals("Insira um email válido", resultado, "Mensagem de erro esperada para email inválido.");
    }

    @Test
    public void testLoginComSenhaMuitoCurta() {
        // Dados do Teste
        String email = "ana_valido@example.com";
        String senha = "123";

        // Simula a tentativa de login
        String resultado = loginService.login(email, senha);

        // Verifica o resultado esperado
        assertEquals("Senha muito curta", resultado, "Mensagem de erro esperada para senha muito curta.");
    }

    @Test
    public void testLoginComEmailNaoCadastrado() {
        // Dados do Teste
        String email = "ana_nao_cadastrado@example.com";
        String senha = "senha_valida";

        // Simula a tentativa de login
        String resultado = loginService.login(email, senha);

        // Verifica o resultado esperado
        assertEquals("Usuário não encontrado", resultado, "Mensagem de erro esperada para email não cadastrado.");
    }

    @Test
    public void testLoginComSenhaIncorreta() {
        // Dados do Teste
        String email = "ana_valido@example.com";
        String senha = "senha_incorreta";

        // Simula a tentativa de login
        String resultado = loginService.login(email, senha);

        // Verifica o resultado esperado
        assertEquals("Senha incorreta", resultado, "Mensagem de erro esperada para senha incorreta.");
    }
}
