package br.com.bank.service;


import br.com.bank.gateway.Bacen;
import br.com.bank.model.Banco;

public class BacenFake extends Bacen {

    private long idBanco = 0;

    @Override
    public long cadastrarBanco(Banco banco) {
        return idBanco++;
    }
}
