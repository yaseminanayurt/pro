package com.finance.handler;

import com.finance.data.LenderProposal;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by eanayas on 12.11.2017.
 */
public class CSVFileHandlerTest {

    String fileName = "input.csv";

    @Test
    public void validateAmount() throws Exception {

        String args[] = new String[]{fileName, "9000"};
        CSVFileHandler csvFileHandler = new CSVFileHandler(args);
        int amount = csvFileHandler.getLoanAmount();
        assertThat(csvFileHandler.validateAmount(amount), Is.is(true));
        amount = 300;
        assertThat(csvFileHandler.validateAmount(amount), Is.is(false));
        amount = 15000;
        assertThat(csvFileHandler.validateAmount(amount), Is.is(true));
        amount = 1000;
        assertThat(csvFileHandler.validateAmount(amount), Is.is(true));
        amount = 15001;
        assertThat(csvFileHandler.validateAmount(amount), Is.is(false));
        amount = 999;
        assertThat(csvFileHandler.validateAmount(amount), Is.is(false));
        amount = 1010;
        assertThat(csvFileHandler.validateAmount(amount), Is.is(false));
        amount = -9;
        assertThat(csvFileHandler.validateAmount(amount), Is.is(false));
    }
    /*
    Already tested with readLendererFile and getLenderPropasalList
    @Test(expected = FileNotFoundException.class)
    public void getProposals() throws Exception {

    }*/

    @Test(expected = IllegalArgumentException.class)
    public void getCreditAmountFromInput() {

        String invalidAmount = "LEN";

        String args[] = new String[]{fileName, invalidAmount};
        CSVFileHandler csvFileHandler = new CSVFileHandler(args);

        int result = csvFileHandler.getCreditAmountFromInput(invalidAmount);
        assertThat(result, is(1230));

        String validAmount = "1230";
        result = csvFileHandler.getCreditAmountFromInput(validAmount);

        assertThat(result, is(1230));

    }

    /*@Test(expected = FileNotFoundException.class)
    public void readLendererFile() throws FileNotFoundException {
        int amount = 1000;
        String fileName = "input.csv";
        CSVFileHandler CSVFileHandler = new CSVFileHandler(fileName);

        List<List<String>> result = CSVFileHandler.readLendererFile();

        assertThat(result, IsNull.notNullValue());
        assertThat(result.size(), Is.is(7));
    }*/

    @Test(expected = IllegalArgumentException.class)
    public void getCreditRateFromInput() {

        String invalidRate = "INV55";
        String args[] = new String[]{fileName,invalidRate};
        CSVFileHandler csvFileHandler = new CSVFileHandler(args);
        double result = csvFileHandler.getCreditRateFromInput(invalidRate);
        String validRate = "0.075";
        result = csvFileHandler.getCreditRateFromInput(validRate);

        assertThat(result, is(0.075));

    }

    @Test(expected = IllegalArgumentException.class)
    public void parseLine() throws Exception {
        String line = "Bob,0.075,640";
        String args[] = new String[]{fileName, "1000"};
        CSVFileHandler csvFileHandler = new CSVFileHandler(args);
        List<String> result = csvFileHandler.parseLine(line, Constants.DEFAULT_SEPARATOR, 3);
        assertThat(result, IsNull.notNullValue());
        assertThat(result.size(), is(3));
        assertThat(result.get(0), is("Bob"));
        assertThat(result.get(1), is("0.075"));
        assertThat(result.get(2), is("640"));
        result = csvFileHandler.parseLine(line, Constants.DEFAULT_SEPARATOR, 4);
    }

    @Test
    public void getLenderProposalList() {

        String[] line1 = {"Bob", "0.71", "680"};
        List<String> lineList = Arrays.asList(line1);
        List<List<String>> stringList = new ArrayList<>();
        stringList.add(lineList);
        String args[] = new String[]{fileName, "1000"};
        CSVFileHandler csvFileHandler = new CSVFileHandler(args);
        List<LenderProposal> lenderProposalList = csvFileHandler.getLenderProposalList(stringList);
        assertThat(lenderProposalList, IsNull.notNullValue());
        assertThat(lenderProposalList.size(), is(1));
        LenderProposal lenderProposal = lenderProposalList.get(0);
        assertThat(lenderProposal.getLenderName(), is("Bob"));

    }

    @Test
    public void getCreditInfo() {

        List<String> line = Arrays.asList(new String[]{"Bob", "0.075", "640"});
        String args[] = new String[]{fileName, "1000"};
        CSVFileHandler csvFileHandler = new CSVFileHandler(args);
        LenderProposal lenderProposal = csvFileHandler.getCreditInfo(line);
        assertThat(lenderProposal, IsNull.notNullValue());
        assertThat(lenderProposal.getLenderName(), is("Bob"));
        assertThat(lenderProposal.getCreditAmount(), is(640));
        assertThat(lenderProposal.getRate(), is(0.075));

    }


}