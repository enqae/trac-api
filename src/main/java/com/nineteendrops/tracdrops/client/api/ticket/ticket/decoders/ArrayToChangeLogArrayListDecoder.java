package com.nineteendrops.tracdrops.client.api.ticket.ticket.decoders;

import com.nineteendrops.tracdrops.client.api.ticket.ticket.TicketChangeLog;
import com.nineteendrops.tracdrops.client.core.decoders.ReturnDecoder;
import com.nineteendrops.tracdrops.client.core.properties.TracProperties;

import java.util.ArrayList;
import java.util.Date;

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
public class ArrayToChangeLogArrayListDecoder implements ReturnDecoder {

    public Object decode(Object result, TracProperties tracProperties, ArrayList keptParametersForDecoder) {

        Object[] resultArray = (Object[])result;

        ArrayList<TicketChangeLog> ticketChanges = new ArrayList<TicketChangeLog>();

        for(Object resultArrayElement: resultArray){

            Object[] element = (Object[])resultArrayElement;

            // Return only rows when there ticketChanges
            if(!element[3].equals(element[4])){
                TicketChangeLog ticketChangeLog = new TicketChangeLog((Date)element[0],
                                                    (String)element[1],
                                                    (String)element[2],
                                                    (String)element[3],
                                                    (String)element[4],
                                                    element[5].equals("1"));
                ticketChanges.add(ticketChangeLog);
            }
        }


        return ticketChanges;
    }
}