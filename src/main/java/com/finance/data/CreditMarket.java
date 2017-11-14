package com.finance.data;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by eanayas on 13.11.2017.
 */
public class CreditMarket {

    private List<LenderProposal> lenderProposalList;
    private int totalMarketAmount;

    public CreditMarket(List<LenderProposal> lenderProposalList){
        this.lenderProposalList = lenderProposalList;
        this.totalMarketAmount = calculateTotalMarketAmount();
    }

    public List<LenderProposal> getLenderProposalList() {
        return lenderProposalList;
    }

    public int getTotalMarketAmount() {
        return totalMarketAmount;
    }


    private int calculateTotalMarketAmount(){

        Stream<LenderProposal> lenderProposalStream = lenderProposalList.stream();
        int creditAmountSum = lenderProposalStream.map(e -> e.getCreditAmount()).reduce(0, (x, y) -> x + y);
        return creditAmountSum;

    }
}
