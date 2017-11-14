package com.finance.handler;

import com.finance.data.LenderProposal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by eanayas on 11.11.2017.
 */
public class CSVFileHandler implements InputHandler {

    String fileName;
    private int loanAmount;

    public CSVFileHandler(String[] args) {
        if (args.length < Constants.INPUT_PARAMETER_LEN) {
            throw new IllegalArgumentException(Constants.INSUFFICIENT_INPUT);
        }
        String filename = args[0];
        try {
            int amount = Integer.parseInt(args[1]);
            this.loanAmount = amount;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Constants.INVALID_AMOUNT);
        }
        this.fileName = filename;
    }


    public boolean validateAmount(int amount) {
        if (amount < Constants.MIN_AMOUNT || amount > Constants.MAX_AMOUNT || (amount % 100 != 0))
            return false;
        return true;

    }


    public boolean validateAmount() {
        return validateAmount(this.getLoanAmount());
    }


    @Override
    public int getCreditAmountFromInput(String amountValue) {
        try {
            int amount = Integer.parseInt(amountValue);
            if (amount < 0)
                throw new IllegalArgumentException(Constants.INVALID_AMOUNT);
            return amount;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Constants.INVALID_AMOUNT);
        }
    }

    @Override
    public double getCreditRateFromInput(String rateValue) {

        try {
            double rate = Double.parseDouble(rateValue);
            if (rate < 0)
                throw new IllegalArgumentException(Constants.INVALID_RATE);
            return rate;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Constants.INVALID_RATE);
        }
    }

    public List<String> parseLine(String line, String regex, int level) {

        List<String> result = new ArrayList<>();

        //if empty, return!
        if (line == null && line.isEmpty()) {
            return result;
        }

        String[] splits = line.split(regex);

        if (level < 0 || level > splits.length) {
            throw new IllegalArgumentException();
        }
        result = Arrays.asList(splits);
        return result;
    }

    public List<List<String>> readLenderFile() throws FileNotFoundException {


        List<List<String>> parsedLinesList = new ArrayList<>();
        Scanner scanner = null;
        try {
            File csvFile = new File(fileName);
            scanner = new Scanner(csvFile);
            while (scanner.hasNext()) {
                List<String> line = parseLine(scanner.nextLine(), Constants.DEFAULT_SEPARATOR, Constants.NO_OF_COLUMNS_IN_INPUT_FILE);
                parsedLinesList.add(line);
            }
        }finally {
            if (scanner != null) {
                scanner.close();
            }

        }
        return parsedLinesList;


    }


    public List<LenderProposal> getLenderProposalList(List<List<String>> stringList) {

        List<LenderProposal> lenderProposalList = new ArrayList<>();
        for (List<String> line : stringList
                ) {
            LenderProposal lenderProposal = getCreditInfo(line);
            lenderProposalList.add(lenderProposal);

        }
        lenderProposalList.sort(LenderProposal::compareByRate);
        return lenderProposalList;
    }


    @Override
    public LenderProposal getCreditInfo(List<String> inputList) {

        int creditAmount = getCreditAmountFromInput(inputList.get(Constants.AMOUNT_DATA_INDEX));
        String name = inputList.get(Constants.LENDER_DATA_INDEX);
        double rate = getCreditRateFromInput(inputList.get(Constants.RATE_DATA_INDEX));
        LenderProposal lenderProposal = new LenderProposal(name, rate, creditAmount);
        return lenderProposal;
    }

    @Override
    public List<LenderProposal> getProposals() throws FileNotFoundException {
        List<List<String>> lenderFileLines = readLenderFile();
        List<LenderProposal> lenderProposalList = getLenderProposalList(lenderFileLines);
        return lenderProposalList;
    }
    public int getLoanAmount() {
        return loanAmount;
    }
}
