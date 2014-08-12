package com.nineteendrops.tracdrops.api.search;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.ITestContext;
import com.nineteendrops.tracdrops.client.api.search.*;
import com.nineteendrops.tracdrops.TracDropsTestBase;
import com.nineteendrops.tracdrops.utils.TestUtils;
import com.nineteendrops.tracdrops.utils.LinesIterator;

import java.util.List;
import java.util.Iterator;
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
@Test(groups = {"TRACDrops.Search"}, dependsOnGroups = {"TRACDrops.Search.Init"})
public class SearchTestBase extends TracDropsTestBase {

    private SearchManager searchManager = null;

    @BeforeClass
    public void getManagers(){
        searchManager = trac.getSearchManager();
    }

    @Test (groups = {"TRACDrops.Search.Simple"})
    public void getFiltersTest(){

        List<SearchFilter> result =  searchManager.getFilters();

        assert result != null && result.size()>0 : "No SearchFilters found";
    }

    @Test(groups= {"TRACDrops.Search.Simple"}, dataProvider = "simpleQueryProvider")
    public void simpleQueriesOK(String query){

        List<SearchResult> result = searchManager.search(query);
        
        assert result != null && result.size()>0: "No data found for query:" + query;
    }

    @Test(groups= {"TRACDrops.Search.Simple"}, dataProvider = "simpleQueryProvider")
    public void simpleQueriesFailure(String query){

        List<SearchResult> result = searchManager.search(query);

        assert result != null && result.size()==0: "Data found for query:" + query;
    }

    @DataProvider(name = "simpleQueryProvider")
    public Object[][] queryProvider(Method method, ITestContext iTestContext){
        return TestUtils.getLines(method, "simpleQueries");
    }

    // Complex queries
    @Test (groups = {"TRACDrops.Search.Complex"}, dataProvider = "complexQueriesProvider")
    public void complexQueries(String query, List<String> filters, Integer numberOfResults){

        SearchQuery searchQuery = new SearchQuery(query);
        for(String filter: filters){
            filter = filter.trim();
            if(filter.equals(SearchKeys.ON_WIKI)) searchQuery.setOnWiki(true);
            if(filter.equals(SearchKeys.ON_TICKETS)) searchQuery.setOnTickets(true);
            if(filter.equals(SearchKeys.ON_CHANGESETS)) searchQuery.setOnChangesets(true);
            if(filter.equals(SearchKeys.ON_MILESTONES)) searchQuery.setOnMilestones(true);
        }

        List<SearchResult> result = searchManager.search(searchQuery);

        assert result != null && result.size() == numberOfResults: "Found:" + result == null ? 0 : result.size() + " -- Expected:" + numberOfResults;
    }

    @DataProvider(name = "complexQueriesProvider")
    public Iterator<Object[]> complexQueriesProvider(Method method){

        return new LinesIterator(method.getDeclaringClass(), method, "complexQueries.txt", new ComplexQueriesProvider());
    }


}