package com.finance.handler;

import com.finance.data.LenderProposal;
import com.finance.output.PrintUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by eanayas on 11.11.2017.
 */
public class InputHandler {

    public int getCreditAmountFromInput(String amountValue){

        int amount = Integer.parseInt(amountValue);
        if(amount<0)
            throw new IllegalArgumentException("invalid credit amount");
        else
            return amount;

    }

    public  double getCreditRateFromInput(String rateValue){

        double rate = Double.parseDouble(rateValue);
        if(rate<0)
            throw new IllegalArgumentException("invalid credit rate");
        else
            return rate;

    }

    public  List<String> parseLine(String line, String regex, int level) {

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

    public List<List<String>> readLendererFile(String fileName){

        File csvFile = new File(fileName);
        Scanner scanner = null;
        List<List<String>> parsedLinesList = new ArrayList<>();

        try {
            scanner = new Scanner(csvFile);

        while (scanner.hasNext()){

            List<String> line = parseLine(scanner.nextLine(),Constants.DEFAULT_SEPARATOR,3);
            parsedLinesList.add(line);
        }
        } catch (FileNotFoundException e) {
            PrintUtils.printMessage(e.getStackTrace().toString());

        }finally {
            scanner.close();
        }

        return  parsedLinesList;
    }

       public LenderProposal getCreditInfo(List<String> inputList) {

        int creditAmount = getCreditAmountFromInput(inputList.get(2));
        String name = inputList.get(0);
        double rate = getCreditRateFromInput(inputList.get(1));

        LenderProposal lenderProposal = new LenderProposal(name,rate,creditAmount);

        return lenderProposal;
    }


}
