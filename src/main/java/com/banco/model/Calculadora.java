package com.banco.model;

import com.banco.strategy.Operacao;


public class Calculadora {

    private Operacao operacao;

    public Calculadora(Operacao operacao) {
        this.operacao = operacao;
    }


    public void setOperacao(Operacao operacao) {
        this.operacao = operacao;
    }

    public Operacao getOperacao() {
        return operacao;
    }


    public double calcular(double valorA, double valorB) {
        if (operacao == null) {
            throw new IllegalStateException("Nenhuma operação definida na calculadora.");
        }
        System.out.printf("[%s] %.2f op %.2f = %.2f%n",
                operacao.getNome(), valorA, valorB,
                operacao.executar(valorA, valorB));
        return operacao.executar(valorA, valorB);
    }
}
