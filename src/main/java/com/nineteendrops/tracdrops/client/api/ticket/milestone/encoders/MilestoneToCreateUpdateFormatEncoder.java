package com.nineteendrops.tracdrops.client.api.ticket.milestone.encoders;

import com.nineteendrops.tracdrops.client.api.ticket.milestone.Milestone;
import com.nineteendrops.tracdrops.client.api.ticket.milestone.MilestoneKeys;
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
public class MilestoneToCreateUpdateFormatEncoder implements ParameterEncoder {

    public Object encode(TracProperties tracProperties, Object parameter) {

        if(parameter == null){
            throw new TracException(MessageUtils.getMessage("core.parameter.not.found", "Milestone", this.getClass().getName()));
        }

        Milestone milestone = (Milestone)parameter;

        // check for mandatory fields
        String name = milestone.getName();
        if(name == null || name.trim().equals("")){
            throw new TracException(MessageUtils.getMessage("core.parameter.not.found", "Milestone.name", this.getClass().getName()));
        }

        Date due = milestone.getDue();
        if(due == null){
            throw new TracException(MessageUtils.getMessage("core.parameter.not.found", "Milestone.due", this.getClass().getName()));
        }

        String description = milestone.getDescription();
        if(description == null){
            description = "";
        }


        MultiParameter multiParameter = new MultiParameter();
        multiParameter.addParameter(name);

        Hashtable attributes = new Hashtable();
        attributes.put(MilestoneKeys.DUE, due);
        attributes.put(MilestoneKeys.DESCRIPTION, description);

        // TODO: review codification <i4> is not good for TRAC it has to be <int>
        //attributes.put(MilestoneKeys.COMPLETED, (milestone.getCompleted() ? 1 : 0));

        multiParameter.addParameter(attributes);

        return multiParameter;
    }
}