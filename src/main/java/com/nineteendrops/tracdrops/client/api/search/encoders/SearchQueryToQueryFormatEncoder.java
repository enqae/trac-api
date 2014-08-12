package com.nineteendrops.tracdrops.client.api.search.encoders;

import com.nineteendrops.tracdrops.client.api.search.SearchKeys;
import com.nineteendrops.tracdrops.client.api.search.SearchQuery;
import com.nineteendrops.tracdrops.client.core.MessageUtils;
import com.nineteendrops.tracdrops.client.core.TracException;
import com.nineteendrops.tracdrops.client.core.Utils;
import com.nineteendrops.tracdrops.client.core.encoders.ParameterEncoder;
import com.nineteendrops.tracdrops.client.core.multicall.MultiParameter;
import com.nineteendrops.tracdrops.client.core.properties.TracProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 26-sep-2009
 * Time: 15:33:51
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
 * Encodes a SearchQuery object into a more convenient format before the XML-RPC invocation
 *
 * @see com.nineteendrops.tracdrops.client.api.search.SearchQuery
 * @see com.nineteendrops.tracdrops.client.core.encoders.ParameterEncoder
 * @see com.nineteendrops.tracdrops.client.core.multicall.MultiParameter
 */
public class SearchQueryToQueryFormatEncoder implements ParameterEncoder {

    private Log log = LogFactory.getLog(SearchQueryToQueryFormatEncoder.class);

    /**
     * Encodes a SearchQuery object into a MultiParameter object
     * @param tracProperties local configuration of TRACDrops
     * @param parameter a SearchQuery object
     *
     * @throws TracException when no SearchQuery is provided
     *
     * @return a MultiParameter object with tje SearchQuery encoded
     *
     * @see com.nineteendrops.tracdrops.client.core.multicall.MultiParameter
     */
    public Object encode(TracProperties tracProperties, Object parameter) {

        if(parameter == null){
            throw new TracException(MessageUtils.getMessage("core.parameter.not.found", "SimpleObject", this.getClass().getName()));
        }

        SearchQuery searchQuery = (SearchQuery)parameter;

        // check for mandatory fields
        String query = searchQuery.getQuery();
        if(query == null){
            query = "";
        }

        MultiParameter multiParameter = new MultiParameter();
        multiParameter.addParameter(query);

        ArrayList<String> listOfFilters = new ArrayList<String>();
        if(!searchQuery.isOnAll()){
            if(searchQuery.isOnTickets()) listOfFilters.add(SearchKeys.ON_TICKETS);
            if(searchQuery.isOnChangesets()) listOfFilters.add(SearchKeys.ON_CHANGESETS);
            if(searchQuery.isOnMilestones()) listOfFilters.add(SearchKeys.ON_MILESTONES);
            if(searchQuery.isOnWiki()) listOfFilters.add(SearchKeys.ON_WIKI);

            multiParameter.addParameter(Utils.fillVectorFromArrayList(listOfFilters));
        }

        return multiParameter;
    }
}
