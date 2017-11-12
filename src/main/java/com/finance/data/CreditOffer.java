package com.finance.data;

/**
 * Created by eanayas on 12.11.2017.
 */

import com.finance.handler.Constants;

/**
 * Created by eanayas on 11.11.2017.
 */
public class CreditOffer {


    public CreditOffer(double totalPayment,double monthlyPayment,int creditAmount,double rate){
        this.totalPayment = totalPayment;
        this.monthlyPayment = monthlyPayment;
        this.creditAmount = creditAmount;
        this.rate = rate;
        this.currency = Constants.CURRENCY;


    }
    private double totalPayment;
    private double monthlyPayment;
    private int creditAmount;
    private double rate;
    private String currency;

    public double getTotalPayment() {
        return totalPayment;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public int getCreditAmount() {
        return creditAmount;
    }

    public double getRate() {
        return rate;
    }

    public String getCurrency() {
        return currency;
    }


    @Override
    public String toString(){

        String newLine = System.lineSeparator();
        StringBuilder result = new StringBuilder();

        result.append(Constants.AMOUNT + getCreditAmount() + getCurrency());
        result.append(newLine);
        result.append(Constants.RATE + String.format("%.2f", getRate()) + Constants.PERCENT_SIGN);
        result.append(newLine);
        result.append(Constants.MONTHLY_PAYMENT + String.format("%.2f", getMonthlyPayment()) + getCurrency());
        result.append(newLine);
        result.append(Constants.TOTAL_PAYMENT + String.format("%.2f", getTotalPayment()) + getCurrency());

        return  result.toString();
    }



}
