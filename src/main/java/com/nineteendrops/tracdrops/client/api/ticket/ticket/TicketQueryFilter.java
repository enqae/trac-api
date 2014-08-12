package com.nineteendrops.tracdrops.client.api.ticket.ticket;

import com.nineteendrops.tracdrops.client.core.MessageUtils;
import com.nineteendrops.tracdrops.client.core.TracException;

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
public class TicketQueryFilter {

    private String field = "";
    private String operator = "";
    private String value = "";

    public TicketQueryFilter(String field, String operator, String value) {

        // Basic checks
        if(field == null){
            throw new TracException(MessageUtils.getMessage("api.ticket.ticket.query.wrong.field", field));
        }

        if(operator == null){
            throw new TracException(MessageUtils.getMessage("api.ticket.ticket.query.wrong.operator", operator));
        }

        if(value == null){
            throw new TracException(MessageUtils.getMessage("api.ticket.ticket.query.wrong.value", value));
        }

        this.field = field.trim();
        this.operator = operator.trim();
        this.value = value.trim();

        // Complementary checks
        if(!TicketKeys.FIELDS_ARRAY.contains(field)){
            throw new TracException(MessageUtils.getMessage("api.ticket.ticket.query.wrong.field", field));
        }

        if(!TicketKeys.OPERATORS_ARRAY.contains(operator)){
            throw new TracException(MessageUtils.getMessage("api.ticket.ticket.query.wrong.operator", operator));
        }
    }

    public String getFilter(){
        return field + operator + value;
    }

    @Override
    public String toString() {
        return "TicketQueryFilter{" +
                "field='" + field + '\'' +
                ", operator='" + operator + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
