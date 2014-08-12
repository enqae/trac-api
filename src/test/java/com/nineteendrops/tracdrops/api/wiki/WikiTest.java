package com.nineteendrops.tracdrops.api.wiki;

import com.nineteendrops.tracdrops.client.core.TracMethodExecutionException;
import com.nineteendrops.tracdrops.utils.TestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

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
public class WikiTest extends WikiTestBase {

    private Log log = LogFactory.getLog(WikiTest.class);

    @Test(dataProvider = "pagesProvider")
    public void wikiGetPageOK(String pageOK){
        String pageString = wikiManager.getPage(pageOK);
        assert pageString != null : "WikiPage empty or not found";
    }

    @Test(expectedExceptions = TracMethodExecutionException.class,
          dataProvider = "pagesProvider")
    public void wikiPageFailure(String pageFailure){
        wikiManager.getPage(pageFailure);
    }

    @DataProvider(name = "pagesProvider")
    public Object[][] pagesProvider(Method method, ITestContext iTestContext){

        return TestUtils.getLines(method, "pages");

    }

}
