package com.finance.handler;

import com.finance.calculator.RateCalculator;
import com.finance.data.CreditOffer;
import com.finance.data.LenderProposal;
import com.finance.output.PrintUtils;

import java.io.*;
import java.util.*;

/**
 * Created by eanayas on 11.11.2017.
 */
public class CreditHandler {



    private int loanAmount;
    private RateCalculator rateCalculator;
    private InputHandler inputHandler;



    public CreditHandler(int loanAmount){

        this.loanAmount = loanAmount;
        inputHandler = new InputHandler();

    }

    public static void main(String[] args) throws IOException {
        //get inputs
       // int loanAmount = Integer.parseInt(args[1]);
        int loanAmount = 1000;

        //String fileName = args[0];

        String fileName = "input.csv";
        CreditHandler creditHandler = new CreditHandler(loanAmount);
        creditHandler.rateCalculator = creditHandler.getGetCreditRequestFromFile(loanAmount, fileName);
        creditHandler.handleCreditRequest();
    }

    private RateCalculator getGetCreditRequestFromFile(int loanAmount, String fileName) {

        List<List<String>> lendererFilelines = inputHandler.readLendererFile(fileName);
        List<LenderProposal> lenderProposalList = getLenderPropasalList(lendererFilelines);
        return new RateCalculator(loanAmount,lenderProposalList);
    }

    private void handleCreditRequest() {
        CreditOffer creditOffer = rateCalculator.getOffer(loanAmount);
        PrintUtils.printMessage(String .valueOf(creditOffer));
    }

    public List<LenderProposal> getLenderPropasalList(List<List<String>> stringList) {

        List<LenderProposal> lenderProposalList = new ArrayList<>();
        for (List<String> line: stringList
             ) {
            LenderProposal lenderProposal = inputHandler.getCreditInfo(line);
            lenderProposalList.add(lenderProposal);

        }
        lenderProposalList.sort(LenderProposal::compareByRate);
        return  lenderProposalList;
    }






}
