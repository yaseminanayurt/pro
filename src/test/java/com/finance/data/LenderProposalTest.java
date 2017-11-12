package com.finance.data;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.*;

/**
 * Created by eanayas on 12.11.2017.
 */
public class LenderProposalTest {
    @Test
    public void getCreditAmount(){

        LenderProposal lenderProposal = new LenderProposal("Bob",0.07,680);
        int result = lenderProposal.getCreditAmount();
        assertThat(result,is(680));
        assertNotEquals(result,(700));

    }

    @Test
    public void getLenderName(){

        LenderProposal lenderProposal = new LenderProposal("Bob",0.07,680);
        String result = lenderProposal.getLenderName();
        assertThat(result,is("Bob"));
        assertNotEquals(result,("Bobbb"));

    }

    @Test
    public void getRate(){
        LenderProposal lenderProposal = new LenderProposal("Bob",0.07,680);
        double result  = lenderProposal.getRate();
        assertThat(result, closeTo(0.07, 0.1));
        assertEquals(result,0.07,0.01);
        assertNotEquals(result,1.07);
    }

    @Test
    public void compareByRate() throws Exception {

        LenderProposal lenderProposal = new LenderProposal("Bob",0.07,680);
        LenderProposal lenderProposal2 = new LenderProposal("Jane",0.09,900);

        int result = LenderProposal.compareByRate(lenderProposal,lenderProposal);
        assertThat(result, is(0));

        result = LenderProposal.compareByRate(lenderProposal,lenderProposal2);
        assertThat(result, is(-1));

        result = LenderProposal.compareByRate(lenderProposal2,lenderProposal);
        assertThat(result, is(1));

    }

}