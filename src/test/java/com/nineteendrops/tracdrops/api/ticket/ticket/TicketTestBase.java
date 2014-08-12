package com.nineteendrops.tracdrops.api.ticket.ticket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import com.nineteendrops.tracdrops.client.api.ticket.ticket.*;
import com.nineteendrops.tracdrops.client.core.TracMethodExecutionException;
import com.nineteendrops.tracdrops.TracDropsTestBase;
import com.nineteendrops.tracdrops.utils.LinesIterator;
import com.nineteendrops.tracdrops.utils.SimpleLineConverter;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.lang.reflect.Method;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 26-sep-2009
 * Time: 13:05:38
 * <p/>
 * This material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 * <p/>
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA 02110-1301 USA
 */
@Test(groups = {"TRACDrops.Ticket"}, dependsOnGroups = {"TRACDrops.Ticket.Init"})
public class TicketTestBase extends TracDropsTestBase{

    private Log log = LogFactory.getLog(TicketTestBase.class);

    TicketManager ticketManager = null;

    @BeforeClass
    public void getManagers(){

        ticketManager = trac.getTicketManager();
    }

    @Test
    public void getRecentChanges(){

        // This test is very weak, but enough to check the fucntionality
        ArrayList changedTickets = ticketManager.getRecentChanges(new Date(new Date().getTime()-20000000));

        assert changedTickets.size() >= 14 : "Wrong number of ticket changes";
    }

    @Test
    public void getAvailableActions(){

        int ticketToTest = getTicketToTest();
        ArrayList listOfActions = ticketManager.getAvailableActions(ticketToTest);

        assert listOfActions.size() == 4 : "Non expected number of actions on ticket:" + ticketToTest;
    }

    @Test(dataProvider = "createTicketProvider")
    public void createGetTicketOK(String summary, String keywords, String status, String blockedby,
                                  String type, String reporter, String milestone, String blocking,
                                  String description, String priority, String owner, String cc){

        Ticket ticket = new Ticket(summary, description);
        ticket.setKeywords(keywords);
        ticket.setStatus(status);
        ticket.setBlockedBy(blockedby);
        ticket.setType(type);
        ticket.setReporter(reporter);
        ticket.setMilestone(milestone);
        ticket.setBlocking(blocking);
        ticket.setPriority(priority);
        ticket.setOwner(owner);
        ticket.setCc(cc);

        int result = ticketManager.create(ticket, false);


        Ticket ticketCreated = ticketManager.get(result);

        assert summary.equals(ticketCreated.getSummary()) &&
                keywords.equals(ticketCreated.getKeywords()) &&
                status.equals(ticketCreated.getStatus()) &&
                blockedby.equals(ticketCreated.getBlockedBy()) &&
                type.equals(ticketCreated.getType()) &&
                reporter.equals(ticketCreated.getReporter()) &&
                milestone.equals(ticketCreated.getMilestone()) &&
                blocking.equals(ticketCreated.getBlocking()) &&
                description.equals(ticketCreated.getDescription()) &&
                priority.equals(ticketCreated.getPriority()) &&
                owner.equals(ticketCreated.getOwner()) &&
                cc.equals(ticketCreated.getCc()) : "Ticket was not correctly created";

    }

    @Test(dataProvider = "updateTicketProvider", dependsOnMethods = {"createGetTicketOK"})
    public void updateTicket(String summary, String status, String comment){

        TicketQuery ticketQuery = new TicketQuery();
        ticketQuery.addFilter(new TicketQueryFilter(TicketKeys.FIELD_SUMMARY, TicketKeys.OP_STARTS_WITH, summary));
        List results = ticketManager.query(ticketQuery);

        assert results.size() == 1: "Retrieved not the expected number of tickets";

        int ticketToUpdate = (Integer)results.get(0);

        Ticket ticket = new Ticket();
        ticket.setIdTicket(ticketToUpdate);
        ticket.setStatus(status);
        ticket.setComment(comment);

        Ticket ticketUpdated = ticketManager.update(ticket, false);

        List<TicketChangeLog> changeLogs = ticketManager.getChangeLog(ticketToUpdate);

        int changesOK = 0;
        for(TicketChangeLog changeLog: changeLogs){
            if(changeLog.getFieldChanged().equals(TicketKeys.FIELD_STATUS)){
                if(changeLog.getFieldNewValue().equals(status)) changesOK++;
            }
            if(changeLog.getFieldChanged().equals(TicketKeys.FIELD_COMMENT)){
                if(changeLog.getFieldNewValue().equals(comment)) changesOK++;
            }

        }

        assert changesOK == 2 : "Ticket was not correctly updated";

    }


    @Test(expectedExceptions = {TracMethodExecutionException.class})
    public void getTicketNOOK(){

        Ticket ticket = ticketManager.get(15000);
    }


    @Test
    public void getChangeLog(){

        int ticketToTest = getTicketToTest();

        ArrayList changeLogs = ticketManager.getChangeLog(ticketToTest);

        assert changeLogs.size() == 0 : "Non expected number of actions on ticket:" + ticketToTest;

    }

    // Auxiliary methods
    private Integer getTicketToTest(){

        String ticketSummary = "TRAC_TEST_TICKET1";
        TicketQuery ticketQuery = new TicketQuery();
            ticketQuery.addFilter(new TicketQueryFilter(TicketKeys.FIELD_SUMMARY, TicketKeys.OP_STARTS_WITH, ticketSummary));
            ticketQuery.addFilter(new TicketQueryFilter(TicketKeys.FIELD_DESCRIPTION, TicketKeys.OP_ENDS_WITH, ticketSummary));
        List results = ticketManager.query(ticketQuery);

        assert results.size() == 1 : "No ticket found for summary: " + ticketSummary;

        return (Integer)results.get(0);
    }

    @DataProvider(name = "createTicketProvider")
    public Iterator<Object[]> ticketProvider(Method method){

        return new LinesIterator(method.getDeclaringClass(), method, "ticketOne.txt", new SimpleLineConverter());
    }

    @DataProvider(name = "updateTicketProvider")
    public Iterator<Object[]> updateTicketProvider(Method method){

        return new LinesIterator(method.getDeclaringClass(), method, "ticketUpdate.txt", new SimpleLineConverter());
    }


}
