package com.nineteendrops.tracdrops.client.api.ticket.version.encoders;

import com.nineteendrops.tracdrops.client.api.ticket.version.Version;
import com.nineteendrops.tracdrops.client.api.ticket.version.VersionKeys;
import com.nineteendrops.tracdrops.client.core.MessageUtils;
import com.nineteendrops.tracdrops.client.core.TracException;
import com.nineteendrops.tracdrops.client.core.encoders.ParameterEncoder;
import com.nineteendrops.tracdrops.client.core.multicall.MultiParameter;
import com.nineteendrops.tracdrops.client.core.properties.TracProperties;

import java.util.Date;
import java.util.Hashtable;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 09-sep-2009
 * Time: 22:53:35
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
public class VersionToCreateUpdateFormatEncoder implements ParameterEncoder {

    public Object encode(TracProperties tracProperties, Object parameter) {

        if(parameter == null){
            throw new TracException(MessageUtils.getMessage("core.parameter.not.found", "Version", this.getClass().getName()));
        }

        Version version = (Version)parameter;

        // check for mandatory fields
        String name = version.getName();
        if(name == null || name.trim().equals("")){
            throw new TracException(MessageUtils.getMessage("core.parameter.not.found", "Version.name", this.getClass().getName()));
        }

        Date time = version.getTime();
        if(time == null){
            throw new TracException(MessageUtils.getMessage("core.parameter.not.found", "Version.time", this.getClass().getName()));
        }

        String description = version.getDescription();
        if(description == null){
            description = "";
        }


        MultiParameter multiParameter = new MultiParameter();
        multiParameter.addParameter(name);

        Hashtable attributes = new Hashtable();
        attributes.put(VersionKeys.TIME, time);
        attributes.put(VersionKeys.DESCRIPTION, description);

        multiParameter.addParameter(attributes);

        return multiParameter;
    }
}