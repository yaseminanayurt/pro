package com.finance.handler;

import com.finance.data.LenderProposal;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by eanayas on 13.11.2017.
 */
public interface InputHandler {
    List<LenderProposal> getProposals() throws FileNotFoundException;
    boolean validateAmount();
    LenderProposal getCreditInfo(List<String> inputList);
    int getCreditAmountFromInput(String amountValue);
    double getCreditRateFromInput(String rateValue);

}
