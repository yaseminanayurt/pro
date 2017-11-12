package com.finance.calculator;

/**
 * Created by eanayas on 11.11.2017.
 */
public class CreditUtils {

    public  double calculateTotalPayment(int totalMonth, double monthlyPayment) {

        return monthlyPayment*totalMonth;
    }


    public  double calculateMonthlyPayment(
            int loanAmount, double interestRate) {


        //interestRate /= 100.0;
        double monthlyRate = interestRate / 12.0;

        // The length of the term in months
        // is the number of years times 12

        int termInMonths = 36;

        // Calculate the monthly payment
        // Typically this formula is provided so
        // we won't go into the details

        // The Math.pow() method is used calculate values raised to a power

        return (loanAmount*monthlyRate) /
                        (1-Math.pow(1+monthlyRate, -termInMonths));

    }

    public boolean isAmountAvailableInMarket(int amount, int totalAmount){

        if(amount > totalAmount)
            return false;
        return true;
    }
}
