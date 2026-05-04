package com.banco.strategy.impl;

import com.banco.strategy.Operacao;


public class OperacaoSubtrair implements Operacao {

    @Override
    public double executar(double valorA, double valorB) {
        return valorA - valorB;
    }

    @Override
    public String getNome() {
        return "Subtração (Saque/Débito)";
    }
}
