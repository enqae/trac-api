package com.nineteendrops.tracdrops.api.ticket.milestone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.nineteendrops.tracdrops.client.api.ticket.milestone.MilestoneManager;
import com.nineteendrops.tracdrops.TracDropsTestBase;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 26-sep-2009
 * Time: 12:18:24
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
@Test(groups = {"TRACDrops.Ticket.Milestone"})
public class MilestoneTestBase extends TracDropsTestBase {

    private Log log = LogFactory.getLog(MilestoneTestBase.class);

    MilestoneManager milestoneManager = null;

    @BeforeTest
    public void beforeTest(){

        milestoneManager = trac.getMilestoneManager();
    }

}
