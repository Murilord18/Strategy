package com.banco.strategy;

/**
 * Interface Strategy - Define o contrato para todas as operações bancárias.
 * Equivalente à interface "Operacao" do diagrama de classe.
 */
public interface Operacao {

    /**
     * Executa a operação bancária sobre dois valores.
     *
     * @param valorA primeiro valor
     * @param valorB segundo valor
     * @return resultado da operação
     */
    double executar(double valorA, double valorB);

    /**
     * Retorna o nome descritivo da operação.
     *
     * @return nome da operação
     */
    String getNome();
}
