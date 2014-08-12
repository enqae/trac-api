package com.nineteendrops.tracdrops.api.search.populator;

import com.nineteendrops.tracdrops.TracDropsTestBase;
import com.nineteendrops.tracdrops.client.api.ticket.milestone.MilestoneManager;
import com.nineteendrops.tracdrops.client.api.ticket.milestone.Milestone;
import com.nineteendrops.tracdrops.client.api.ticket.ticket.*;
import com.nineteendrops.tracdrops.client.api.wiki.WikiManager;
import com.nineteendrops.tracdrops.client.api.wiki.WikiPage;
import com.nineteendrops.tracdrops.client.core.TracMethodExecutionException;
import com.nineteendrops.tracdrops.utils.LinesIterator;
import com.nineteendrops.tracdrops.utils.SimpleLineConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 26-sep-2009
 * Time: 18:45:12
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
@Test(groups = {"TRACDrops.Search.Init"})
public class SearchTestInitializator extends TracDropsTestBase{

    private Log log = LogFactory.getLog(SearchTestInitializator.class);

    private TicketManager ticketManager = null;
    private MilestoneManager milestoneManager = null;
    private WikiManager wikiManager = null;


    @BeforeClass
    public void getManagers(){

        ticketManager = trac.getTicketManager();
        milestoneManager = trac.getMilestoneManager();
        wikiManager = trac.getWikiManager();
    }    


    // Prepare Tickets
    @Test
    public void ticketsDelete(){

        TicketQuery ticketQuery = new TicketQuery();
        ticketQuery.addFilter(new TicketQueryFilter(TicketKeys.FIELD_SUMMARY, TicketKeys.OP_STARTS_WITH, "TRAC"));
        List results = ticketManager.query(ticketQuery);

        trac.multicallStart();
            for(Object id: results){
                ticketManager.delete((Integer)id);
            }
        trac.launchMulticall();
    }

    @Test (dataProvider = "ticketProvider", dependsOnMethods = "ticketsDelete")
    public void ticketsCreate(String summary, String description){

        ticketManager.create(new Ticket(summary, description), false);
    }

    @DataProvider(name = "ticketProvider")
    public Iterator<Object[]> ticketProvider(Method method){

        return new LinesIterator(method.getDeclaringClass(), method, "tickets.txt", new SimpleLineConverter());
    }


    // Prepare Milestones
    @Test
    public void milestoneDelete(){

        ArrayList milestones = milestoneManager.getMilestones();

        trac.multicallStart();
            for(Object milestone: milestones){
                if(((String)milestone).startsWith("TRAC_TEST")){
                    milestoneManager.delete((String)milestone);
                }
            }
        trac.launchMulticall();
    }

    @Test (dataProvider = "milestoneProvider", dependsOnMethods = "milestoneDelete")
    public void milestoneCreate(String name, String description, Date due){

        milestoneManager.create(new Milestone(name, description, false, due));
    }

    @DataProvider(name = "milestoneProvider")
    public Iterator<Object[]> milestoneProvider(Method method){

        return new LinesIterator(method.getDeclaringClass(), method, "milestones.txt", new MilestoneLineConverter());
    }


    // Prepare Wiki Pages
    @Test(dataProvider = "wikiPagesProvider", expectedExceptions = {TracMethodExecutionException.class})
    public void wikiPagesCreateOrUpdate(String name, String content, String comment){

        wikiManager.putPage(new WikiPage(name, content, comment));
    }

    @DataProvider(name = "wikiPagesProvider")
    public Iterator<Object[]> wikiPagesProvider(Method method){

        return new LinesIterator(method.getDeclaringClass(), method, "wikiPages.txt", new SimpleLineConverter());
    }

}
