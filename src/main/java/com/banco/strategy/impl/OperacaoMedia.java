package com.banco.strategy.impl;

import com.banco.strategy.Operacao;


public class OperacaoMedia implements Operacao {

    @Override
    public double executar(double valorA, double valorB) {
        return (valorA + valorB) / 2.0;
    }

    @Override
    public String getNome() {
        return "Média (Rendimentos)";
    }
}
