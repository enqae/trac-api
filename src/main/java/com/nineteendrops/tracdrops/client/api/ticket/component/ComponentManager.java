package com.nineteendrops.tracdrops.client.api.ticket.component;

import com.nineteendrops.tracdrops.client.api.ticket.component.decoders.HashMapToComponentDecoder;
import com.nineteendrops.tracdrops.client.api.ticket.component.encoders.ComponentToCreateUpdateFormatEncoder;
import com.nineteendrops.tracdrops.client.core.annotations.TracClass;
import com.nineteendrops.tracdrops.client.core.annotations.TracClassMethod;
import com.nineteendrops.tracdrops.client.core.annotations.TracParameterEncoder;
import com.nineteendrops.tracdrops.client.core.decoders.ArrayToStringArrayListDecoder;
import com.nineteendrops.tracdrops.client.core.decoders.EmptyDecoder;
import com.nineteendrops.tracdrops.client.core.TracMethodExecutionException;

import java.util.ArrayList;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 09-sep-2009
 * Time: 21:50:24
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
@TracClass(tracClass = "ticket.component")
public interface ComponentManager {

    @TracClassMethod(tracClassMethodName = "getAll",
                     returnDecoder = ArrayToStringArrayListDecoder.class)
    public ArrayList getComponents() throws TracMethodExecutionException;

    @TracClassMethod(tracClassMethodName = "get",
                     returnDecoder = HashMapToComponentDecoder.class)
    public Component getComponent(String componentName) throws TracMethodExecutionException;

    @TracClassMethod(tracClassMethodName = "delete",
                     returnDecoder = EmptyDecoder.class)
    public Integer delete(String componentName) throws TracMethodExecutionException;

    @TracClassMethod(tracClassMethodName = "create",
                     returnDecoder = EmptyDecoder.class)
    public Integer create(@TracParameterEncoder(encoder = ComponentToCreateUpdateFormatEncoder.class)
                          Component component) throws TracMethodExecutionException;

    @TracClassMethod(tracClassMethodName = "update",
                     returnDecoder = EmptyDecoder.class)
    public Integer update(@TracParameterEncoder(encoder = ComponentToCreateUpdateFormatEncoder.class)
                          Component component) throws TracMethodExecutionException;

}
