package com.banco.model;

import com.banco.strategy.Operacao;


public class ContaBancaria {

    private String titular;
    private double saldo;
    private Operacao operacao;

    public ContaBancaria(String titular, double saldoInicial, Operacao operacao) {
        this.titular = titular;
        this.saldo = saldoInicial;
        this.operacao = operacao;
    }

    /**
     * Aplica a operação configurada ao saldo atual com o valor informado.
     */
    public double aplicarOperacao(double valor) {
        saldo = operacao.executar(saldo, valor);
        return saldo;
    }


    public void setOperacao(Operacao operacao) {
        this.operacao = operacao;
    }

    public Operacao getOperacao() {
        return operacao;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    @Override
    public String toString() {
        return String.format("ContaBancaria{titular='%s', saldo=%.2f, operacao='%s'}",
                titular, saldo, operacao.getNome());
    }
}
