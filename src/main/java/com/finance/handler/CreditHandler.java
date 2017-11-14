package com.finance.handler;

import com.finance.calculator.RateCalculator;
import com.finance.data.CreditMarket;
import com.finance.data.CreditOffer;
import com.finance.data.LenderProposal;
import com.finance.output.OutputHandler;
import com.finance.output.PrintHandler;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by eanayas on 11.11.2017.
 */
public class CreditHandler {


    private OutputHandler outputHandler;
    private RateCalculator rateCalculator;
    private InputHandler inputHandler;

    public CreditHandler(InputHandler inputHandler, OutputHandler outputHandler) {

        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
    }

    public static void main(String[] args) {

        OutputHandler outputHandler = new PrintHandler();
        String args1[] = new String[]{"input.csv","1000"};
        InputHandler inputHandler = new CSVFileHandler(args1);

        if (!inputHandler.validateAmount()) {
            throw new IllegalArgumentException(Constants.INVALID_AMOUNT);
        }
        try {
            CreditHandler creditHandler = new CreditHandler(inputHandler, outputHandler);
            creditHandler.rateCalculator = creditHandler.parseFileGetRateCalculator();
            creditHandler.handleCreditRequest();
        } catch (FileNotFoundException e) {
            outputHandler.printOutput(Constants.FILE_NOT_FOUND_ERROR);
        }
    }

    private RateCalculator parseFileGetRateCalculator() throws FileNotFoundException {
        List<LenderProposal> lenderProposalList = inputHandler.getProposals();
        CreditMarket creditMarket = new CreditMarket(lenderProposalList);
        return new RateCalculator(creditMarket);
    }

    private void handleCreditRequest() {
        int loanAmount = ((CSVFileHandler) inputHandler).getLoanAmount();
        CreditOffer creditOffer = rateCalculator.getOffer(loanAmount);
        outputHandler.printOutput(String.valueOf(creditOffer));
    }
}
