package br.com.bank;

import org.junit.jupiter.api.Test;

import br.com.bank.model.Banco;
import br.com.bank.model.Conta;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

class BancoTest {

    @Test
    // @Disabled
    public void deve_adicionar_conta() {

        Banco banco = new Banco("LucasBank");
        Conta conta = new Conta("00100200344");

        assertDoesNotThrow(() -> banco.adicionarConta(conta));
        assertNotNull(banco.pesquisarContaDoCliente(conta.getCpf()));

    }

    @Test
    public void deve_pesquisar_conta_do_cliente() {

        Banco banco = new Banco("LucasBank");
        Conta conta = new Conta("00100200344");
        banco.adicionarConta(conta);

        assertNotNull(banco.pesquisarContaDoCliente(conta.getCpf()));
        assertNull(banco.pesquisarContaDoCliente("99988877766"));

    }

    @Test
    public void deve_listar_contas_de_alta_renda() {

        Banco banco = new Banco("LucasBank");
        Conta conta = new Conta("00100200344");
        banco.adicionarConta(conta);
        Conta contaAltaRenda = new Conta("11122233344");
        contaAltaRenda.setSaldo(10000);
        banco.adicionarConta(contaAltaRenda);
        List<Conta> expectedContasAltaRenda = new ArrayList<Conta>();
        expectedContasAltaRenda.add(contaAltaRenda);

        assertIterableEquals(expectedContasAltaRenda, banco.listarContasAltaRenda());

    }

}