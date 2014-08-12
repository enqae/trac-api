package com.nineteendrops.tracdrops;

import com.nineteendrops.tracdrops.client.Trac;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 16-sep-2009
 * Time: 22:10:56
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
@Test(groups = "TRACDrops")
public class TracDropsTestBase {

    private Log log = LogFactory.getLog(TracDropsTestBase.class);

    protected Trac trac = null;

    @Parameters({"tracUrl", "user", "password"})
    @BeforeClass
    public void connectToServer(String tracUrl, String user, String password){

        trac = new Trac(tracUrl, user, password);
        trac.initialize();
    }

}
