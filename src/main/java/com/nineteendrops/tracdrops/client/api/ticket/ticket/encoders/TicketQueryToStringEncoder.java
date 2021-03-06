package com.nineteendrops.tracdrops.client.api.ticket.ticket.encoders;

import com.nineteendrops.tracdrops.client.core.encoders.ParameterEncoder;
import com.nineteendrops.tracdrops.client.core.properties.TracProperties;
import com.nineteendrops.tracdrops.client.core.TracException;
import com.nineteendrops.tracdrops.client.core.MessageUtils;
import com.nineteendrops.tracdrops.client.api.ticket.ticket.TicketQuery;

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
public class TicketQueryToStringEncoder implements ParameterEncoder {

    public Object encode(TracProperties tracProperties, Object parameter) {

        TicketQuery ticketQuery = (TicketQuery)parameter;

        String query = ticketQuery.getFullQuery();

        if(query.equals("")){
            throw new TracException(MessageUtils.getMessage("api.ticket.ticket.query.no.filters"));
        }

        return query;
    }
}
