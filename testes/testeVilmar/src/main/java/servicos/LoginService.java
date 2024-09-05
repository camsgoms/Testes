package servicos;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class LoginService {

    // Simula um banco de dados de usuários
    private Map<String, String> usuarios = new HashMap<>();

    private static final int MIN_SENHA_LENGTH = 6; // Define o comprimento mínimo da senha

    public LoginService() {
        // Simula um usuário registrado
        usuarios.put("ana_valido@example.com", "senha_valida");
    }

    public String login(String email, String senha) {
        if (email == null || email.trim().isEmpty()) {
            return "Preencha o campo email";
        }
        if (senha == null || senha.trim().isEmpty()) {
            return "Preencha o campo senha";
        }
        if (!isEmailValido(email)) {
            return "Insira um email válido";
        }
        if (senha.length() < MIN_SENHA_LENGTH) {
            return "Senha muito curta";
        }
        if (!usuarios.containsKey(email)) {
            return "Usuário não encontrado";
        }
        if (!usuarios.get(email).equals(senha)) {
            return "Senha incorreta";
        }

        return "Login realizado com sucesso"; // Mensagem fictícia para login bem-sucedido
    }

    private boolean isEmailValido(String email) {
        // Regex simples para validação de email
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(regex, email);
    }
}
