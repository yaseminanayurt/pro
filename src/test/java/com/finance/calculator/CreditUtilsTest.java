package com.finance.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.closeTo;

import static org.junit.Assert.*;

/**
 * Created by eanayas on 12.11.2017.
 */
public class CreditUtilsTest {

    @Test
    public void calculateTotalPayment() throws Exception {

        double amount = 30.88;

        CreditUtils creditUtils = new CreditUtils();
        double result = creditUtils.calculateTotalPayment(36,amount);
        assertNotEquals(result,1100.68,0.001);
        assertThat(result, closeTo(1111.68, 0.1));


    }

    @Test
    public void calculateMonthlyPayment() throws Exception {

        int loanAmount = 1000;
        double rate = 0.07;
        CreditUtils creditUtils = new CreditUtils();
        double result = creditUtils.calculateMonthlyPayment(loanAmount,rate);
        assertThat(result, closeTo(30.87, 0.1));
        assertNotEquals(result,30.78,0.01);

    }

    @Test
    public void isAmountAvailableInMarket() throws Exception {

        int loanAmount = 1000;
        int marketAmount = 1500;
        int invalidAmount = 1700;
        CreditUtils creditUtils = new CreditUtils();
        assertEquals(creditUtils.isAmountAvailableInMarket(loanAmount,marketAmount),true);
        assertEquals(creditUtils.isAmountAvailableInMarket(invalidAmount,marketAmount),false);

    }

}