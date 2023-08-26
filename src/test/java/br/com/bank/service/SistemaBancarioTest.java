package br.com.bank.service;

import br.com.bank.gateway.Bacen;
import br.com.bank.model.Banco;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.quality.Strictness;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SistemaBancarioTest {

   @InjectMocks
   private SistemaBancario sistemaBancario;
   @Mock
   private Bacen bacen;

   @Test
   public void deve_registrar_banco_com_bacen_fake() {
        sistemaBancario = new SistemaBancario(new BacenFake());
        Banco banco0 = new Banco("Zero");
        Banco banco1 = new Banco("Um");

        assertEquals(0, sistemaBancario.registrarBanco(banco0));
        assertEquals(1, sistemaBancario.registrarBanco(banco1));
   }

   @Test
   public void deve_registrar_banco_com_bacen_stub() {
        sistemaBancario = new SistemaBancario(new BacenStub());
        Banco banco0 = new Banco("Zero");

        assertEquals(0, sistemaBancario.registrarBanco(banco0));
   }

   @Test
   public void deve_registrar_banco_com_bacen_mock() {
        sistemaBancario = new SistemaBancario(bacen);
        Banco banco1 = new Banco("Um");
        Mockito.when(bacen.cadastrarBanco(banco1)).thenReturn(1L);

        assertEquals(1, sistemaBancario.registrarBanco(banco1));
   }

//     BancoNaoCadastradoException do tipo RuntimeException deve ser retornada. Use o assertThrows para isso.
   @Test
   public void deve_lancar_execao_quando_falhar_registrar_banco_com_bacen_mock() {
        sistemaBancario = new SistemaBancario(bacen);
        Banco banco2 = new Banco("Dois");
        Mockito.when(bacen.cadastrarBanco(banco2)).thenThrow(new BancoNaoCadastradoException());

        assertThrows(BancoNaoCadastradoException.class, () -> sistemaBancario.registrarBanco(banco2));
   }

   class BancoNaoCadastradoException extends RuntimeException {}
}