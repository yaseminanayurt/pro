package com.finance.calculator;

import com.finance.data.CreditMarket;
import com.finance.data.CreditOffer;
import com.finance.data.LenderProposal;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by eanayas on 11.11.2017.
 */
public class RateCalculatorTest {

    public ArrayList<LenderProposal> lenderProposalList;

    {
        lenderProposalList = new ArrayList<>();
        lenderProposalList.add(new LenderProposal("Bob", 0.075, 640));
        lenderProposalList.add(new LenderProposal("Jane", 0.069, 480));
        lenderProposalList.add(new LenderProposal("Fred", 0.071, 520));
        lenderProposalList.add(new LenderProposal("Mary", 0.104, 170));
        lenderProposalList.add(new LenderProposal("John", 0.081, 320));
        lenderProposalList.add(new LenderProposal("Dave", 0.074, 140));
        lenderProposalList.add(new LenderProposal("Angela", 0.071, 60));
        lenderProposalList.sort(LenderProposal::compareByRate);
    }


    @Test
    public void getRate() throws Exception {

        CreditMarket creditMarket = new CreditMarket(lenderProposalList);
        RateCalculator rateCalculator = new RateCalculator(creditMarket);
        double result = rateCalculator.getRate(1000);
        assertThat(result, closeTo(0.07, 0.001));
        assertNotEquals(result, 0.08, 0.001);

    }

    @Test
    public void getRequestedAmountFromLendersList() throws Exception {
        CreditMarket creditMarket = new CreditMarket(lenderProposalList);
        RateCalculator rateCalculator = new RateCalculator(creditMarket);
        double result = rateCalculator.getRequestedAmountFromLendersList(1000);
        assertThat(result, closeTo(70.04, 0.01));
        assertNotEquals(result, 70.05, 0.001);
    }

    @Test
    public void getOffer() throws Exception {

        int loanAmount = 1000;
        CreditMarket creditMarket = new CreditMarket(lenderProposalList);
        RateCalculator rateCalculator = new RateCalculator(creditMarket);

        CreditOffer expected = new CreditOffer(1111.64, 30.88, 1000, 0.07);
        CreditOffer creditOffer = rateCalculator.getOffer(loanAmount);

        assertNotNull(creditOffer);
        assertThat(creditOffer.getCreditAmount(), is(expected.getCreditAmount()));
        assertThat(creditOffer.getMonthlyPayment(), closeTo(expected.getMonthlyPayment(), 0.01));
        assertThat(creditOffer.getTotalPayment(), closeTo(expected.getTotalPayment(), 0.01));
        assertThat(creditOffer.getRate(), closeTo(expected.getRate(), 0.01));
    }

    @Test
    public void calculateTotalPayment() throws Exception {

        double amount = 30.88;
        CreditMarket creditMarket = new CreditMarket(lenderProposalList);
        RateCalculator rateCalculator = new RateCalculator(creditMarket);
        double result = rateCalculator.calculateTotalPayment(36, amount);
        assertNotEquals(result, 1100.68, 0.001);
        assertThat(result, closeTo(1111.68, 0.1));
    }

    @Test
    public void calculateMonthlyPayment() throws Exception {

        int loanAmount = 1000;
        double rate = 0.07;
        CreditMarket creditMarket = new CreditMarket(lenderProposalList);
        RateCalculator rateCalculator = new RateCalculator(creditMarket);
        double result = rateCalculator.calculateMonthlyPayment(loanAmount, rate);
        assertThat(result, closeTo(30.87, 0.1));
        assertNotEquals(result, 30.78, 0.01);

    }

    @Test
    public void isAmountAvailableInMarket() throws Exception {

        int loanAmount = 1000;
        int marketAmount = 1500;
        int invalidAmount = 1700;
        CreditMarket creditMarket = new CreditMarket(lenderProposalList);
        RateCalculator rateCalculator = new RateCalculator(creditMarket);
        assertEquals(rateCalculator.isAmountAvailableInMarket(loanAmount, marketAmount), true);
        assertEquals(rateCalculator.isAmountAvailableInMarket(invalidAmount, marketAmount), false);

    }
}