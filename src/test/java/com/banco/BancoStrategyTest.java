package com.banco;

import com.banco.model.Calculadora;
import com.banco.model.ContaBancaria;
import com.banco.strategy.impl.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes do Sistema Bancário - Padrão Strategy")
public class BancoStrategyTest {

    private Calculadora calculadora;

    @BeforeEach
    void setUp() {
        calculadora = new Calculadora(new OperacaoSomar());
    }

    // ===================== OperacaoSomar =====================

    @Test
    @DisplayName("Deve somar corretamente dois valores (Depósito)")
    void testOperacaoSomar() {
        calculadora.setOperacao(new OperacaoSomar());
        double resultado = calculadora.calcular(1000.0, 250.0);
        assertEquals(1250.0, resultado, 0.001);
    }

    @Test
    @DisplayName("Deve retornar o nome correto da OperacaoSomar")
    void testNomeOperacaoSomar() {
        assertEquals("Soma (Depósito)", new OperacaoSomar().getNome());
    }

    @Test
    @DisplayName("Deve somar valores negativos corretamente")
    void testOperacaoSomarNegativos() {
        calculadora.setOperacao(new OperacaoSomar());
        double resultado = calculadora.calcular(-500.0, 300.0);
        assertEquals(-200.0, resultado, 0.001);
    }

    // ===================== OperacaoSubtrair =====================

    @Test
    @DisplayName("Deve subtrair corretamente dois valores (Saque)")
    void testOperacaoSubtrair() {
        calculadora.setOperacao(new OperacaoSubtrair());
        double resultado = calculadora.calcular(1000.0, 300.0);
        assertEquals(700.0, resultado, 0.001);
    }

    @Test
    @DisplayName("Deve retornar o nome correto da OperacaoSubtrair")
    void testNomeOperacaoSubtrair() {
        assertEquals("Subtração (Saque/Débito)", new OperacaoSubtrair().getNome());
    }

    @Test
    @DisplayName("Subtração resultando em saldo negativo (cheque especial)")
    void testOperacaoSubtrairResultadoNegativo() {
        calculadora.setOperacao(new OperacaoSubtrair());
        double resultado = calculadora.calcular(100.0, 500.0);
        assertEquals(-400.0, resultado, 0.001);
    }

    // ===================== OperacaoMultiplicar =====================

    @Test
    @DisplayName("Deve multiplicar corretamente dois valores (Juros)")
    void testOperacaoMultiplicar() {
        calculadora.setOperacao(new OperacaoMultiplicar());
        double resultado = calculadora.calcular(1000.0, 1.05);
        assertEquals(1050.0, resultado, 0.001);
    }

    @Test
    @DisplayName("Deve retornar o nome correto da OperacaoMultiplicar")
    void testNomeOperacaoMultiplicar() {
        assertEquals("Multiplicação (Juros Compostos)", new OperacaoMultiplicar().getNome());
    }

    @Test
    @DisplayName("Multiplicação por zero retorna zero")
    void testOperacaoMultiplicarPorZero() {
        calculadora.setOperacao(new OperacaoMultiplicar());
        double resultado = calculadora.calcular(999.0, 0.0);
        assertEquals(0.0, resultado, 0.001);
    }

    // ===================== OperacaoDividir =====================

    @Test
    @DisplayName("Deve dividir corretamente dois valores (Cotas)")
    void testOperacaoDividir() {
        calculadora.setOperacao(new OperacaoDividir());
        double resultado = calculadora.calcular(900.0, 3.0);
        assertEquals(300.0, resultado, 0.001);
    }

    @Test
    @DisplayName("Deve retornar o nome correto da OperacaoDividir")
    void testNomeOperacaoDividir() {
        assertEquals("Divisão (Cotas de Investimento)", new OperacaoDividir().getNome());
    }

    @Test
    @DisplayName("Divisão por zero deve lançar ArithmeticException")
    void testOperacaoDividirPorZero() {
        calculadora.setOperacao(new OperacaoDividir());
        assertThrows(ArithmeticException.class, () -> calculadora.calcular(500.0, 0.0));
    }

    // ===================== OperacaoMedia =====================

    @Test
    @DisplayName("Deve calcular a média corretamente (Rendimentos)")
    void testOperacaoMedia() {
        calculadora.setOperacao(new OperacaoMedia());
        double resultado = calculadora.calcular(1000.0, 1500.0);
        assertEquals(1250.0, resultado, 0.001);
    }

    @Test
    @DisplayName("Deve retornar o nome correto da OperacaoMedia")
    void testNomeOperacaoMedia() {
        assertEquals("Média (Rendimentos)", new OperacaoMedia().getNome());
    }

    @Test
    @DisplayName("Média de valores iguais retorna o mesmo valor")
    void testOperacaoMediaValoresIguais() {
        calculadora.setOperacao(new OperacaoMedia());
        double resultado = calculadora.calcular(800.0, 800.0);
        assertEquals(800.0, resultado, 0.001);
    }

    // ===================== ContaBancaria =====================

    @Test
    @DisplayName("Depósito deve aumentar o saldo da conta")
    void testContaBancariaDeposito() {
        ContaBancaria conta = new ContaBancaria("Maria", 1000.0, new OperacaoSomar());
        double novoSaldo = conta.aplicarOperacao(500.0);
        assertEquals(1500.0, novoSaldo, 0.001);
    }

    @Test
    @DisplayName("Saque deve diminuir o saldo da conta")
    void testContaBancariaSaque() {
        ContaBancaria conta = new ContaBancaria("José", 2000.0, new OperacaoSubtrair());
        double novoSaldo = conta.aplicarOperacao(400.0);
        assertEquals(1600.0, novoSaldo, 0.001);
    }

    @Test
    @DisplayName("Rendimento deve multiplicar o saldo da conta")
    void testContaBancariaRendimento() {
        ContaBancaria conta = new ContaBancaria("Ana", 1000.0, new OperacaoMultiplicar());
        double novoSaldo = conta.aplicarOperacao(1.1); // 10% de rendimento
        assertEquals(1100.0, novoSaldo, 0.001);
    }

    @Test
    @DisplayName("Deve trocar estratégia em tempo de execução")
    void testContaBancariaTrocaEstrategia() {
        ContaBancaria conta = new ContaBancaria("Carlos", 1000.0, new OperacaoSomar());
        conta.aplicarOperacao(500.0); // saldo: 1500
        assertEquals(1500.0, conta.getSaldo(), 0.001);

        conta.setOperacao(new OperacaoSubtrair());
        conta.aplicarOperacao(300.0); // saldo: 1200
        assertEquals(1200.0, conta.getSaldo(), 0.001);

        conta.setOperacao(new OperacaoMultiplicar());
        conta.aplicarOperacao(1.05); // saldo: 1260
        assertEquals(1260.0, conta.getSaldo(), 0.001);
    }

    @Test
    @DisplayName("Calculadora sem operação deve lançar IllegalStateException")
    void testCalculadoraSemOperacao() {
        Calculadora calc = new Calculadora(null);
        assertThrows(IllegalStateException.class, () -> calc.calcular(100, 200));
    }
}
