package com.finance.handler;

import com.finance.data.LenderProposal;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by eanayas on 12.11.2017.
 */
public class InputHandlerTest {
    @Test(expected = IllegalArgumentException.class)
    public void getCreditAmountFromInput(){

        String invalidAmount = "LEN";
        InputHandler inputHandler = new InputHandler();

        int result = inputHandler.getCreditAmountFromInput(invalidAmount);
        String validAmount = "1230";
        result =inputHandler.getCreditAmountFromInput(validAmount);

        assertThat(result,is(1230));

    }

    @Test(expected = FileNotFoundException.class)
    public void readLendererFile(){
        int amount = 1000;
        InputHandler inputHandler = new InputHandler();
        String fileName = "input.csv";
        List<List<String>> result = inputHandler.readLendererFile(fileName);

        assertThat(result, IsNull.notNullValue());
        assertThat(result.size(), Is.is(7));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCreditRateFromInput(){

        String invalidRate = "INV55";
        InputHandler inputHandler = new InputHandler();
        double result = inputHandler.getCreditRateFromInput(invalidRate);
        String validRate = "0.075";
        result = inputHandler.getCreditRateFromInput(validRate);

        assertThat(result,is(0.075));

    }

    @Test(expected = IllegalArgumentException.class)
    public void parseLine() throws Exception {
        String line = "Bob,0.075,640";
        InputHandler inputHandler = new InputHandler();
        List<String> result = inputHandler.parseLine(line,Constants.DEFAULT_SEPARATOR,3);
        assertThat(result, IsNull.notNullValue());
        assertThat(result.size(), is(3));
        assertThat(result.get(0), is("Bob"));
        assertThat(result.get(1), is("0.075"));
        assertThat(result.get(2), is("640"));
        result = inputHandler.parseLine(line,Constants.DEFAULT_SEPARATOR,4);
    }

    @Test
    public void getCreditInfo() {

        List<String> line = Arrays.asList(new String[]{"Bob", "0.075", "640"});
        int amount = 1000;
        InputHandler inputHandler = new InputHandler();
        LenderProposal lenderProposal = inputHandler.getCreditInfo(line);
        assertThat(lenderProposal, IsNull.notNullValue());
        assertThat(lenderProposal.getLenderName(), is("Bob"));

        assertThat(lenderProposal.getCreditAmount(), is(640));
        assertThat(lenderProposal.getRate(), is(0.075));

    }


}