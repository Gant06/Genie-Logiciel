package com.example.testing;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests fonctionnels de Calculatrice")
class CalculatorFunctionalTest {
    private Calculator calculator;
    @BeforeEach void setUp() { calculator = new Calculator(); }

    //Add
    @Test void testAddPositiveNumbers() {
        assertEquals(5, calculator.add(2, 3));
        assertEquals(100, calculator.add(45, 55));
    }
    @Test void testAddWithNegativeNumbers() {
        assertEquals(-5, calculator.add(-2, -3));
        assertEquals(-100, calculator.add(-45, -55));
        assertEquals(1, calculator.add(-2, 3));
        assertEquals(-45, calculator.add(-100, 55));
    }

    //Subtract
    @Test void testSubstractPositiveNumbers(){
        assertEquals(4, calculator.subtract(10, 6));
        assertEquals(55, calculator.subtract(100, 45));
    }
    @Test void testSubstractWithNegativeNumbers(){
        assertEquals(-5, calculator.subtract(10, 15));
        assertEquals(-3, calculator.subtract(-10, -7));
        assertEquals(23, calculator.subtract(14, -9));
        assertEquals(-6, calculator.subtract(-2, 4));
        assertEquals(5, calculator.subtract(-13, -18));
    }

    //Multiply
    @Test void testMutiplyPositiveNumbers(){
        assertEquals(32, calculator.multiply(8, 4));
        assertEquals(648, calculator.multiply(36, 18));
        assertEquals(0, calculator.multiply(999, 0));
    }
    @Test void testMultiplyWithNegativeNumbers(){
        assertEquals(-6, calculator.multiply(2, -3));
        assertEquals(-6, calculator.multiply(-2, 3));
        assertEquals(6, calculator.multiply(2, 3));
    }

    //Divide
    @Test void testDividePositiveNumbers(){
        assertEquals(7, calculator.divide(42, 6));
        assertEquals(36, calculator.divide(648, 18));
    }


}
