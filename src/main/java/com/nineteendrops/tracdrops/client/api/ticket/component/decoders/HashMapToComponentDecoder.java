package com.nineteendrops.tracdrops.client.api.ticket.component.decoders;

import com.nineteendrops.tracdrops.client.api.ticket.component.Component;
import com.nineteendrops.tracdrops.client.api.ticket.component.ComponentKeys;
import com.nineteendrops.tracdrops.client.core.decoders.ReturnDecoder;
import com.nineteendrops.tracdrops.client.core.properties.TracProperties;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 09-sep-2009
 * Time: 22:05:35
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
public class HashMapToComponentDecoder implements ReturnDecoder {

    public Object decode(Object result, TracProperties tracProperties, ArrayList keptParametersForDecoder) {

        HashMap map = (HashMap)result;
        Component component =  new Component((String)map.get(ComponentKeys.OWNER),
                                             (String)map.get(ComponentKeys.DESCRIPTION),
                                             (String)map.get(ComponentKeys.NAME));

        return component;
    }
}