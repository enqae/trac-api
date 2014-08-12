package com.nineteendrops.tracdrops.client.api.ticket.priority;

import com.nineteendrops.tracdrops.client.api.ticket.common.SimpleObjectToCreateUpdateFormatEncoder;
import com.nineteendrops.tracdrops.client.core.annotations.TracClass;
import com.nineteendrops.tracdrops.client.core.annotations.TracClassMethod;
import com.nineteendrops.tracdrops.client.core.annotations.TracParameterEncoder;
import com.nineteendrops.tracdrops.client.core.decoders.ArrayToStringArrayListDecoder;
import com.nineteendrops.tracdrops.client.core.TracMethodExecutionException;

import java.util.ArrayList;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 09-sep-2009
 * Time: 22:35:19
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
@TracClass(tracClass = "ticket.priority")
public interface PriorityManager {

    @TracClassMethod(tracClassMethodName = "getAll",
                     returnDecoder = ArrayToStringArrayListDecoder.class)
    public ArrayList getPriorities() throws TracMethodExecutionException;

    @TracClassMethod(tracClassMethodName = "get")
    public String getPriorityOrder(String priorityName) throws TracMethodExecutionException;

    @TracClassMethod(tracClassMethodName = "delete")
    public Integer delete(String priorityName) throws TracMethodExecutionException;

    @TracClassMethod(tracClassMethodName = "create")
    public Integer create(@TracParameterEncoder(encoder = SimpleObjectToCreateUpdateFormatEncoder.class)
                          Priority priority) throws TracMethodExecutionException;

    @TracClassMethod(tracClassMethodName = "update")
    public Integer update(@TracParameterEncoder(encoder = SimpleObjectToCreateUpdateFormatEncoder.class)
                          Priority priority) throws TracMethodExecutionException;

}