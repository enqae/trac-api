package com.nineteendrops.tracdrops.client.api.ticket.ticket;

import java.util.ArrayList;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 26-sep-2009
 * Time: 13:08:22
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
public class TicketQuery {

    ArrayList<TicketQueryFilter> filters = new ArrayList<TicketQueryFilter>();

    public void addFilter(TicketQueryFilter filter){
        filters.add(filter);
    }

    public String getFullQuery(){

        String finalQuery = "";

        if(filters.size()>0){
            StringBuilder fullQuery = new StringBuilder();
            for(TicketQueryFilter filter: filters){
                fullQuery.append(filter.getFilter()).append("&");
            }

            fullQuery.deleteCharAt(fullQuery.length()-1);
            finalQuery = fullQuery.toString();
        }

        return finalQuery;
    }

    public void reset(){
        filters.clear();
    }
}
