package com.finance.data;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;

/**
 * Created by eanayas on 13.11.2017.
 */
public class CreditMarketTest {

    public ArrayList<LenderProposal> lenderProposalList;

    {
        lenderProposalList = new ArrayList<>();
        lenderProposalList.add(new LenderProposal("Bob", 0.075, 640));
        lenderProposalList.add(new LenderProposal("Jane", 0.069, 480));
        lenderProposalList.add(new LenderProposal("Fred", 0.071, 520));
    }

    @Test
    public void getLenderProposalList() throws Exception {
        CreditMarket creditMarket = new CreditMarket(lenderProposalList);
        List<LenderProposal> resultList = creditMarket.getLenderProposalList();
        assertNotNull(resultList);
        assertThat(resultList, IsNull.notNullValue());
        assertThat(resultList.size(), Is.is(3));
        assertThat(resultList.get(0).getLenderName(), Is.is(lenderProposalList.get(0).getLenderName()));
    }

    @Test
    public void getTotalMarketAmount() throws Exception {

        CreditMarket creditMarket = new CreditMarket(lenderProposalList);
        int result = creditMarket.getTotalMarketAmount();
        assertThat(result, is(1640));

    }
}