package com.finance.data;

/**
 * Created by eanayas on 11.11.2017.
 */
public class LenderProposal {


    private int creditAmount;
    private String lenderName;
    private double rate;
    public LenderProposal(String lenderName, double rate, int creditAmount) {
        this.lenderName = lenderName;
        this.creditAmount = creditAmount;
        this.rate = rate;
    }

    public static int compareByRate(LenderProposal lenderProposal1, LenderProposal lenderProposal2) {

        return Double.compare(lenderProposal1.getRate(), lenderProposal2.getRate());
    }

    public int getCreditAmount() {
        return creditAmount;
    }

    public String getLenderName() {
        return lenderName;
    }

    public double getRate() {
        return rate;
    }

    @Override
    public String toString() {

        return "name: " + lenderName + " rate : " + getRate() + " amount : " + getCreditAmount();
    }


}
