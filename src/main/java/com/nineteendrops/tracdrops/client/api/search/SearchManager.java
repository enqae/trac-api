package com.nineteendrops.tracdrops.client.api.search;

import com.nineteendrops.tracdrops.client.api.search.decoders.ArrayToSearchFilterDecoder;
import com.nineteendrops.tracdrops.client.api.search.decoders.ArrayToSearchResultDecoder;
import com.nineteendrops.tracdrops.client.api.search.encoders.SearchQueryToQueryFormatEncoder;
import com.nineteendrops.tracdrops.client.core.TracException;
import com.nineteendrops.tracdrops.client.core.TracMethodExecutionException;
import com.nineteendrops.tracdrops.client.core.annotations.TracClass;
import com.nineteendrops.tracdrops.client.core.annotations.TracClassMethod;
import com.nineteendrops.tracdrops.client.core.annotations.TracParameterEncoder;

import java.util.List;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 23-ago-2009
 * Time: 18:57:02
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

/**
 * Wraps the functionality referred to the search api provided by the TRAC XML-RPC plugin
 * Samples of use are the following:<br/>
 * <ul>
 * <li>Find all filters:<br/>
 * <pre>
 *      SearchManager searchManager = trac.getSearchManager();
 *      List<SearchFilter> searchFilters = searchManager.getFilters();
 * </pre>
 * </li>
 * <li>Make a simple query:<br/>
 * <pre>
 *      SearchManager searchManger = trac.getSearchManager();
 *      List<SearchResult> results = searchManager.search("realease 1.0");
 * </pre>
 * </li>
 * <li>Make a complex query:<br/>
 * <pre>
 *      SearchManager searchManager = trac.getSearchManager();
 *      SearchQuery query = new SearchQuery("installation procedure");
 *      query.setOnWiki(true);
 *      List<SearchResult> results = searchManager.search(query);
 * </pre>
 * </li>
 * </ul>
 *
 */
@TracClass(tracClass = "search")
public interface SearchManager {

    /**
     * Retrieves the types of filters available in the TRAC server.
     *
     * @return returns the filters avaliables in the TRAC server
     * @throws TracMethodExecutionException when there is an error in the execution of method in the server side
     *
     * @see com.nineteendrops.tracdrops.client.api.search.SearchFilter
     */
    @TracClassMethod(tracClassMethodName = "getSearchFilters",
                    returnDecoder = ArrayToSearchFilterDecoder.class)
    public List<SearchFilter> getFilters() throws TracMethodExecutionException;


    /**
     * Finds in the whole TRAC server some results according to the issued query
     *
     * @param query any expression accepted by the query functionality in TRAC server
     * @return the list of SearchResults found in the TRAC server
     * @throws TracMethodExecutionException when there is an error in the execution of method in the server side
     *
     * @see com.nineteendrops.tracdrops.client.api.search.SearchResult
     */
    @TracClassMethod(tracClassMethodName = "performSearch",
                    returnDecoder = ArrayToSearchResultDecoder.class)
    public List<SearchResult> search(String query) throws TracMethodExecutionException;

    
    /**
     * Finds in the TRAC server some results according to the issued query, but restricted to the type of objects specified by query filters
     *
     * @param query a SearchQuery object with the query and the type of objects in which to search
     * @return the list of SearchResults found in the TRAC server
     * @throws TracMethodExecutionException when there is an error in the execution of method in the server side
     * @throws TracException when wrong query object is provided
     *
     * @see com.nineteendrops.tracdrops.client.api.search.SearchQuery
     * @see com.nineteendrops.tracdrops.client.api.search.SearchResult
     */
    @TracClassMethod(tracClassMethodName = "performSearch",
                    returnDecoder = ArrayToSearchResultDecoder.class)
    public List<SearchResult> search(@TracParameterEncoder(encoder = SearchQueryToQueryFormatEncoder.class)
                                    SearchQuery query) throws TracMethodExecutionException, TracException;

}
