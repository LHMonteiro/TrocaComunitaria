package br.com.economiacircular.plataforma.domain;
 
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;
 
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
    }

     @Test
    void deveInicializarSaldoCom100Creditos() {
        Usuario usuario = new Usuario();   
        usuario.inicializarSaldo();
        assertEquals(100, usuario.getSaldoCreditos());  
    }
 
    @Test
    void devePermitirCreditarSaldo() {
        Usuario usuario = new Usuario();    
        usuario.setSaldoCreditos(50);    
 
        usuario.setSaldoCreditos(usuario.getSaldoCreditos() + 30);   
 
        assertEquals(80, usuario.getSaldoCreditos());    
    }

    @Test
    void deveValidarSenhaCorreta(){
        Usuario usuario = new Usuario(); 
        usuario.setSenha("senha123");
        assertTrue(usuario.senhaCorreta("senha123"));

        assertFalse(usuario.senhaCorreta("errada"));
    }

    @Test
    void deveCreditarSaldo(){
        Usuario usuario = new Usuario();
        usuario.setSaldoCreditos(50);
        usuario.creditarSaldo(30);

        assertEquals(80, usuario.getSaldoCreditos());
    }


    @Test
    void deveLancarExcecaoQuandoSaldoInsuficiente(){
        Usuario usuario = new Usuario();
        usuario.setSaldoCreditos(20);

         IllegalArgumentException excecao = assertThrows(     
                IllegalArgumentException.class,   
                () -> usuario.debitarSaldo(50)    
        );
 
        assertEquals("Saldo insuficiente para realizar a troca", excecao.getMessage());  
    }


    @Test
    void deveLancarExcecaoAoCreditarValorNegativo(){
        Usuario usuario = new Usuario();
        usuario.setSaldoCreditos(100);
         
        
        assertThrows(IllegalArgumentException.class, () -> usuario.creditarSaldo(-10));    
    }


}