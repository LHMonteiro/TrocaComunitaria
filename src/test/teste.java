package br.com.economiacircular.plataforma.domain;
 
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
 
class UsuarioTest {
 
    @Test
    void deveCriarUsuarioComDadosValidos() {
        Usuario usuario = new Usuario();
        usuario.setNome("Ana Silva");
        usuario.setEmail("ana@email.com");
        usuario.setSenha("senha123");
        usuario.setContato("81999999999");
        usuario.setSaldoCreditos(100);
 
        assertEquals("Ana Silva", usuario.getNome());
        assertEquals("ana@email.com", usuario.getEmail());
        assertEquals(100, usuario.getSaldoCreditos());
    }}