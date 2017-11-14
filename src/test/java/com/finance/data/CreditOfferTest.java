package com.finance.data;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by eanayas on 13.11.2017.
 */
public class CreditOfferTest {
    @Test
    public void toString_test() throws Exception {

        String newLine = System.lineSeparator();
        StringBuilder expected = new StringBuilder();
        expected.append("Requested amount 1900 £ ");
        expected.append(newLine);
        expected.append("Rate: 7,2 % ");
        expected.append(newLine);
        expected.append("Monthly repayment 58,87 £ ");
        expected.append(newLine);
        expected.append("Total repayment 2119,44 £ ");

        CreditOffer creditOffer = new CreditOffer(2119.44, 58.87, 1900, 0.072);
        String result = creditOffer.toString();

        assertThat(result, is(expected.toString()));


    }

}