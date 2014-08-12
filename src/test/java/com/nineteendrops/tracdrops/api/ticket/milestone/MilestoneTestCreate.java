package com.nineteendrops.tracdrops.api.ticket.milestone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.nineteendrops.tracdrops.client.api.ticket.milestone.Milestone;

import java.util.Date;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 26-sep-2009
 * Time: 12:22:21
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
public class MilestoneTestCreate extends MilestoneTestBase{

    private Log log = LogFactory.getLog(MilestoneTestCreate.class);

    public void create(){

        Milestone milestone = new Milestone("M1TESTNG", "test de create", true, new Date());

        Integer returnValue = milestoneManager.create(milestone);

        System.out.println(returnValue);
    }

}
