package com.banco.strategy.impl;

import com.banco.strategy.Operacao;


public class OperacaoDividir implements Operacao {

    @Override
    public double executar(double valorA, double valorB) {
        if (valorB == 0) {
            throw new ArithmeticException("Divisão por zero não é permitida.");
        }
        return valorA / valorB;
    }

    @Override
    public String getNome() {
        return "Divisão (Cotas de Investimento)";
    }
}
