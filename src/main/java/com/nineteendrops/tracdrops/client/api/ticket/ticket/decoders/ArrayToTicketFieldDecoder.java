package com.nineteendrops.tracdrops.client.api.ticket.ticket.decoders;

import com.nineteendrops.tracdrops.client.api.ticket.ticket.TicketField;
import com.nineteendrops.tracdrops.client.core.decoders.ReturnDecoder;
import com.nineteendrops.tracdrops.client.core.properties.TracProperties;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 08-sep-2009
 * Time: 23:37:21
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
public class ArrayToTicketFieldDecoder implements ReturnDecoder {

    private static String TYPE = "type";
    private static String LABEL = "label";
    private static String NAME = "name";

    public Object decode(Object result, TracProperties tracProperties, ArrayList keptParametersForDecoder) {

        ArrayList<TicketField> results = new ArrayList<TicketField>();

        Object[] resultArray = (Object[])result;
        for(int i=0; i<resultArray.length; i++){
            HashMap map = (HashMap)resultArray[i];
            results.add(new TicketField((String)map.get(TYPE),
                                        (String)map.get(LABEL),
                                        (String)map.get(NAME)));
        }

        return results;
    }
}
