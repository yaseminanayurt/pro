package com.finance.calculator;

import com.finance.data.CreditMarket;
import com.finance.data.CreditOffer;
import com.finance.data.LenderProposal;
import com.finance.handler.Constants;

public class RateCalculator {

    private CreditMarket creditMarket;

    public RateCalculator(CreditMarket creditMarket) {
        this.creditMarket = creditMarket;
    }


    public double getRate(int requestedAmount) {

        int amount = requestedAmount;
        if (isAmountAvailableInMarket(amount, creditMarket.getTotalMarketAmount())) {

            double rate = 0.0;
            double result = getRequestedAmountFromLendersList(amount);

            rate = result / requestedAmount;
            return rate;

        } else {

            throw new IllegalArgumentException(Constants.INSUFFICIENT_AMOUNT);
        }

    }

    public double getRequestedAmountFromLendersList(int amount) {
        double result = 0F;

        for (LenderProposal proposal : creditMarket.getLenderProposalList()
                ) {
            double amountSupplied = Math.min(amount, proposal.getCreditAmount());
            result += proposal.getRate() * amountSupplied;
            amount -= amountSupplied;
            if (amount == 0)
                break;
        }
        return result;


    }


    public CreditOffer getOffer(int loanAmount) {

        double rate = getRate(loanAmount);
        double monthlyPayment = calculateMonthlyPayment(loanAmount, rate);
        double totalPayment = calculateTotalPayment(Constants.TOTAL_MONTH, monthlyPayment);
        CreditOffer creditOffer = new CreditOffer(totalPayment, monthlyPayment, loanAmount, rate);
        return creditOffer;


    }

    public double calculateTotalPayment(int totalMonth, double monthlyPayment) {

        return monthlyPayment * totalMonth;
    }


    public double calculateMonthlyPayment(
            int loanAmount, double interestRate) {

        double monthlyRate = interestRate / 12.0;
        // The length of the term in months
        // is the number of years times 12
        int termInMonths = 36;
        // Calculate the monthly payment
        // Typically this formula is provided so
        // we won't go into the details
        // The Math.pow() method is used calculate values raised to a power
        return (loanAmount * monthlyRate) /
                (1 - Math.pow(1 + monthlyRate, -termInMonths));

    }

    public boolean isAmountAvailableInMarket(int amount, int totalAmount) {

        if (amount > totalAmount)
            return false;
        return true;
    }
}
