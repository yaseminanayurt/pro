package com.finance.handler;

import com.finance.data.LenderProposal;
import org.hamcrest.core.IsNull;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by eanayas on 11.11.2017.
 */
public class CreditHandlerTest {
    @Test
    public void handleCreditRequest() throws Exception {

    }


    @Test
    public void getLenderPropasalList()  {

        int loanAmount = 1000;
        CreditHandler creditHandler = new CreditHandler(1000);
        String[] line1 = {"Bob", "0.71", "680"};
        List<String> lineList = Arrays.asList(line1);
        List<List<String>> stringList = new ArrayList<>();
        stringList.add(lineList);
        List<LenderProposal> lenderProposalList = creditHandler.getLenderPropasalList(stringList);
        assertThat(lenderProposalList, IsNull.notNullValue());
        assertThat(lenderProposalList.size(), is(1));
        LenderProposal lenderProposal = lenderProposalList.get(0);
        assertThat(lenderProposal.getLenderName(), is("Bob"));

    }





}
