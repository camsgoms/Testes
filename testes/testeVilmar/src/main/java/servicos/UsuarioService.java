package servicos;

import entidades.Usuario;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class UsuarioService {

    private Map<String, Usuario> usuarios = new HashMap<>();
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    private static final Pattern TELEFONE_PATTERN = Pattern.compile("^\\d{2} \\d{5}-\\d{4}$");
    private static final Pattern SENHA_PATTERN = Pattern.compile("(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}");

    public UsuarioService() {
        // Simula e-mail já registrado
        Usuario usuarioExistente = new Usuario("Ana", "Silva", "ana@exemplo.com", "Endereço", "SenhaValida1!", "81 99999-9999", "");
        usuarios.put(usuarioExistente.getEmail(), usuarioExistente);
    }

    public boolean adicionarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }

        // Validações para o nome
        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser vazio");
        }

        // Validações para o e-mail
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("O e-mail não pode ser vazio");
        }

        if (!EMAIL_PATTERN.matcher(usuario.getEmail()).matches()) {
            throw new IllegalArgumentException("O e-mail é inválido");
        }

        if (usuarios.containsKey(usuario.getEmail())) {
            throw new IllegalArgumentException("O e-mail já está em uso");
        }

        // Validações para senha
        if (usuario.getSenha() == null || usuario.getSenha().trim().isEmpty()) {
            throw new IllegalArgumentException("A senha não pode ser vazia");
        }

        if (!SENHA_PATTERN.matcher(usuario.getSenha()).matches()) {
            throw new IllegalArgumentException("A senha deve conter pelo menos 8 caracteres, incluindo letras maiúsculas, minúsculas, números e símbolos");
        }

        // Adiciona o usuário
        usuarios.put(usuario.getEmail(), usuario);
        return true;
    }

    public boolean atualizarSenha(String email, String novaSenha) {
        Usuario usuario = usuarios.get(email);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        // Valida a nova senha
        if (novaSenha == null || novaSenha.trim().isEmpty()) {
            throw new IllegalArgumentException("A senha não pode ser vazia");
        }

        if (!SENHA_PATTERN.matcher(novaSenha).matches()) {
            throw new IllegalArgumentException("A senha deve conter pelo menos 8 caracteres, incluindo letras maiúsculas, minúsculas, números e símbolos");
        }

        usuario.setSenha(novaSenha);
        return true;
    }

    public boolean atualizarFotoPerfil(String email, String novaFoto) {
        Usuario usuario = usuarios.get(email);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        // Atualiza a foto de perfil
        usuario.setFotoPerfil(novaFoto);
        return true;
    }

    public boolean atualizarEmail(String emailAntigo, String novoEmail) {
        Usuario usuario = usuarios.get(emailAntigo);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        // Valida o novo email
        if (novoEmail == null || novoEmail.trim().isEmpty()) {
            throw new IllegalArgumentException("O e-mail não pode ser vazio");
        }

        if (!EMAIL_PATTERN.matcher(novoEmail).matches()) {
            throw new IllegalArgumentException("O e-mail é inválido");
        }

        if (usuarios.containsKey(novoEmail)) {
            throw new IllegalArgumentException("O e-mail já está em uso");
        }

        // Atualiza o e-mail
        usuarios.remove(emailAntigo);
        usuario.setEmail(novoEmail);
        usuarios.put(novoEmail, usuario);
        return true;
    }

    public boolean atualizarTelefone(String email, String novoTelefone) {
        Usuario usuario = usuarios.get(email);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        // Valida o telefone
        if (novoTelefone == null || novoTelefone.trim().isEmpty()) {
            throw new IllegalArgumentException("O telefone não pode ser vazio");
        }

        if (!TELEFONE_PATTERN.matcher(novoTelefone).matches()) {
            throw new IllegalArgumentException("Telefone inválido");
        }

        // Atualiza o telefone
        usuario.setTelefone(novoTelefone);
        return true;
    }

    public boolean atualizarEndereco(String email, String novoEndereco) {
        Usuario usuario = usuarios.get(email);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        // Atualiza o endereço
        usuario.setEndereco(novoEndereco);
        return true;
    }

    public boolean atualizarNome(String email, String novoNome) {
        Usuario usuario = usuarios.get(email);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        // Atualiza o nome
        usuario.setNome(novoNome);
        return true;
    }

    public Map<String, Usuario> getUsuarios() {
        return usuarios;
    }
}
