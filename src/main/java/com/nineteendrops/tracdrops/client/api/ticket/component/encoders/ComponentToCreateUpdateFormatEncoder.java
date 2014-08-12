package com.nineteendrops.tracdrops.client.api.ticket.component.encoders;

import com.nineteendrops.tracdrops.client.api.ticket.component.Component;
import com.nineteendrops.tracdrops.client.api.ticket.component.ComponentKeys;
import com.nineteendrops.tracdrops.client.core.MessageUtils;
import com.nineteendrops.tracdrops.client.core.TracException;
import com.nineteendrops.tracdrops.client.core.encoders.ParameterEncoder;
import com.nineteendrops.tracdrops.client.core.multicall.MultiParameter;
import com.nineteendrops.tracdrops.client.core.properties.TracProperties;

import java.util.Hashtable;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 09-sep-2009
 * Time: 22:20:59
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
public class ComponentToCreateUpdateFormatEncoder implements ParameterEncoder {

    public Object encode(TracProperties tracProperties, Object parameter) {

        if(parameter == null){
            throw new TracException(MessageUtils.getMessage("core.parameter.not.found", "Component", this.getClass().getName()));
        }

        Component component = (Component)parameter;

        // check for mandatory fields
        String name = component.getName();
        if(name == null || name.trim().equals("")){
            throw new TracException(MessageUtils.getMessage("core.parameter.not.found", "Component.name", this.getClass().getName()));
        }

        String owner = component.getOwner();
        if(owner == null || owner.trim().equals("")){
            throw new TracException(MessageUtils.getMessage("core.parameter.not.found", "Component.owner", this.getClass().getName()));
        }

        String description = component.getDescription();
        if(description == null){
            description = "";
        }


        MultiParameter multiParameter = new MultiParameter();
        multiParameter.addParameter(name);

        Hashtable attributes = new Hashtable();
        attributes.put(ComponentKeys.OWNER, owner);
        attributes.put(ComponentKeys.DESCRIPTION, description);

        multiParameter.addParameter(attributes);

        return multiParameter;
    }
}
