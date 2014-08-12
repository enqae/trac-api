package com.nineteendrops.tracdrops.api.ticket.ticket.populator;

import com.nineteendrops.tracdrops.TracDropsTestBase;
import com.nineteendrops.tracdrops.client.api.ticket.ticket.*;
import com.nineteendrops.tracdrops.utils.LinesIterator;
import com.nineteendrops.tracdrops.utils.SimpleLineConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

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
@Test(groups = {"TRACDrops.Ticket.Init"})
public class TicketTestInitializator extends TracDropsTestBase {

    private Log log = LogFactory.getLog(TicketTestInitializator.class);

    private TicketManager ticketManager = null;

    @BeforeClass
    public void getManagers(){

        ticketManager = trac.getTicketManager();
    }

    // Create DB to test tickets
    public void ticketsDelete(){

        TicketQuery ticketQuery = new TicketQuery();
        ticketQuery.addFilter(new TicketQueryFilter(TicketKeys.FIELD_SUMMARY, TicketKeys.OP_STARTS_WITH, "TRAC_TEST_TICKET"));
        ticketQuery.addFilter(new TicketQueryFilter(TicketKeys.FIELD_SUMMARY, TicketKeys.OP_STARTS_WITH, "TRAC_TEST_SUMMARY_ONE_1"));
        List results = ticketManager.query(ticketQuery);

        trac.multicallStart();
            for(Object id: results){
                ticketManager.delete((Integer)id);
            }
        trac.launchMulticall();
    }

    @Test (dataProvider = "ticketProvider", dependsOnMethods = "ticketsDelete")
    public void ticketsCreate(String summary, String keywords, String type, String reporter, String description){

        Ticket ticket = new Ticket(summary, description);
        ticket.setKeywords(keywords);
        ticket.setType(type);
        ticket.setReporter(reporter);
        ticketManager.create(ticket, false);
    }

    @DataProvider(name = "ticketProvider")
    public Iterator<Object[]> ticketProvider(Method method){

        return new LinesIterator(method.getDeclaringClass(), method, "tickets.txt", new SimpleLineConverter());
    }
}
