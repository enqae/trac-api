package com.nineteendrops.tracdrops.client.api.search.decoders;

import com.nineteendrops.tracdrops.client.api.search.SearchFilter;
import com.nineteendrops.tracdrops.client.core.decoders.ReturnDecoder;
import com.nineteendrops.tracdrops.client.core.properties.TracProperties;

import java.util.ArrayList;


/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 25-ago-2009
 * Time: 20:36:02
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
 * Decodes an array of object comming from the XML-RPC deserialization into an array of SearchFilter objects
 *
 * @see com.nineteendrops.tracdrops.client.api.search.SearchFilter
 */
public class ArrayToSearchFilterDecoder implements ReturnDecoder {

    /**
     * Decodes the XML-RPC returned data into an array of SearchFilter
     *
     * @param result XML-RPC data from TRAC server
     * @param tracProperties configuration properties from client
     * @param keptParametersForDecoder configuration data from client invocation to TRAC server
     *
     * @return an array of decoded SearchFilter Objects
     *
     * @see com.nineteendrops.tracdrops.client.core.properties.TracProperties
     * @see com.nineteendrops.tracdrops.client.api.search.SearchFilter
     */
    public Object decode(Object result, TracProperties tracProperties, ArrayList keptParametersForDecoder) {

        ArrayList<SearchFilter> filters = new ArrayList<SearchFilter>();

        Object[] resultArray = (Object[])result;
        for(int i=0; i<resultArray.length; i++){
            Object[] o = (Object[])resultArray[i];
            filters.add(new SearchFilter(o[0].toString(), o[1].toString()));
        }

        return filters;
    }
}
