package br.ce.wcaquino.servicos;

import br.ce.wcaquino.exceptions.NaoPodeDividirPorZero;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculadoraTests {

    Calculadora calc;

    @Before
    public void setup() {
        calc = new Calculadora();
    }

    @Test
    public void deveSomarDoisValores() {

        // Cenário
        int a = 5;
        int b = 3;
        // Action
        int resultado = calc.somar(a,b);

        // Verify
        Assert.assertEquals(8, resultado);

    }

    @Test
    public void deveSubtrairDoisValores() {
        // Cenário
        int a = 8;
        int b = 5;

        // Action
        int resultado = calc.subtrair(a, b);
        // Verificação
        Assert.assertEquals(3, resultado);

    }

    @Test(expected = NaoPodeDividirPorZero.class)
    public void deveLancarExceptionSeDividirPorZero() throws NaoPodeDividirPorZero {
        int a = 10;
        int b = 0;

        calc.divide(a,b);

    }

}
