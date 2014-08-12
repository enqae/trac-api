package com.nineteendrops.tracdrops.api.wiki;

import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeTest;
import com.nineteendrops.tracdrops.client.Trac;
import com.nineteendrops.tracdrops.client.api.wiki.WikiManager;
import com.nineteendrops.tracdrops.TracDropsTestBase;

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
@Test(groups = "TRACDrops.Wiki")
public class WikiTestBase extends TracDropsTestBase {

    Trac trac = null;
    WikiManager wikiManager = null;


    @Parameters({"tracUrl", "user", "password"})
    @BeforeTest
    public void beforeTest(String tracUrl, String user, String password){
        trac = new Trac(tracUrl, user, password);
        trac.initialize();

        wikiManager = trac.getWikiManager();
    }
}
