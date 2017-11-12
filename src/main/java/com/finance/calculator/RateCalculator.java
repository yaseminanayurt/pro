package com.finance.calculator;

import com.finance.data.CreditOffer;
import com.finance.handler.Constants;
import com.finance.data.LenderProposal;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by eanayas on 11.11.2017.
 */
public class RateCalculator {

    private int requestedAmount;
    private  List<LenderProposal> lenderProposalList;
    private CreditUtils creditUtils;
    private int totalMarketAmount;

   public  RateCalculator(int requestedAmount, List<LenderProposal> lenderProposalList){
       this.requestedAmount = requestedAmount;
       this.lenderProposalList = lenderProposalList;
       creditUtils = new CreditUtils();
       initialize();
   }


    public void initialize(){

        totalMarketAmount = getTotalMarketAmount();
    }


    //1000
    public double getRate(int requestedAmount){

        int amount = requestedAmount;
        if(creditUtils.isAmountAvailableInMarket(amount,totalMarketAmount)) {

            double rate = 0F;
            double result = getRequestedAmountFromLendersList(amount);

            rate = result / requestedAmount;
            return rate;

        }else{

            throw new IllegalArgumentException("Amount is not available on the market!");
        }

    }

    public double getRequestedAmountFromLendersList(int amount){
        double result = 0F;

        for (LenderProposal proposal: lenderProposalList
                ) {

            //0.69  480
            double amountSupplied = Math.min(amount,proposal.getCreditAmount());
            result += (Double)proposal.getRate()*amountSupplied;
            amount -= amountSupplied;
            if(amount == 0)
                break;
        }
        return result;


    }

    public int getTotalMarketAmount(){

        Stream<LenderProposal> lenderProposalStream = lenderProposalList.stream();
        int creditAmountSum = lenderProposalStream.map(e -> e.getCreditAmount()).reduce(0, (x, y) -> x + y);
        return creditAmountSum;

    }

    public CreditOffer getOffer(int loanAmount) {

        double rate = getRate(loanAmount);
        double monthlyPayment = creditUtils.calculateMonthlyPayment(loanAmount,rate);

        double totalPayment = creditUtils.calculateTotalPayment(Constants.TOTAL_MONTH,monthlyPayment);

        CreditOffer creditOffer = new CreditOffer(monthlyPayment,totalPayment,loanAmount,rate);
        return creditOffer;


    }
}
