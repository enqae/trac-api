package com.nineteendrops.tracdrops.client.api.ticket.type;

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
@TracClass(tracClass = "ticket.type")
public interface TypeManager {

    @TracClassMethod(tracClassMethodName = "getAll",
                     returnDecoder = ArrayToStringArrayListDecoder.class)
    public ArrayList getTypes() throws TracMethodExecutionException;

    @TracClassMethod(tracClassMethodName = "get")
    public String getTypeOrder(String typeName) throws TracMethodExecutionException;

    @TracClassMethod(tracClassMethodName = "delete")
    public Integer delete(String typeName) throws TracMethodExecutionException;

    @TracClassMethod(tracClassMethodName = "create")
    public Integer create(@TracParameterEncoder(encoder = SimpleObjectToCreateUpdateFormatEncoder.class)
                          Type type) throws TracMethodExecutionException;

    @TracClassMethod(tracClassMethodName = "update")
    public Integer update(@TracParameterEncoder(encoder = SimpleObjectToCreateUpdateFormatEncoder.class)
                          Type type) throws TracMethodExecutionException;

}