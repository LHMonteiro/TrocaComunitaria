package br.com.economiacircular.plataforma.domain;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
 
class PublicacaoTest {
 
    private Usuario dono;
    private Publicacao publicacao;
 
    @BeforeEach
    void setUp() {
        dono = new Usuario();

        dono.setId(1L);  

        dono.setNome("Carlos");   
 
        publicacao = new Publicacao();
        publicacao.setTitulo("Livro de Java");
        publicacao.setValorCreditos(30);
        publicacao.setDono(dono);    
    }
 
    @Test
    void deveIniciarComoDisponivel() {
        assertEquals(StatusPublicacao.DISPONIVEL, publicacao.getStatus());
    }

    @Test
    void deveReservarPublicacaoDisponivel() {
        publicacao.reservar();
        assertEquals(StatusPublicacao.RESERVADA, publicacao.getStatus());
    }

    @test 
    void deveLancarExcecaoAoCancelarSeNaoForDono() {
        assertThrows(IllegalArgumentException.class, () -> publicacao.cancelar(99L));
    }

    @Test
    void deveLancarExcecaoQuandoDonoTentaSolicitarSuaPublicacao() {
        assertThrows(IllegalArgumentException.class,
                () -> publicacao.validarSolicitante(dono));
    }

    @Test 
    void deveDisponibilizarPublicacaoAposRecusa(){
        
        publicacao.reservar();
        publicacao.disponibilizar();

        assertEquals(StatusPublicacao.DISPONIVEL, publicacao.getStatus());
    }
}