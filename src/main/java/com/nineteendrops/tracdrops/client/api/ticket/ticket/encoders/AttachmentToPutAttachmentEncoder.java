package com.nineteendrops.tracdrops.client.api.ticket.ticket.encoders;

import com.nineteendrops.tracdrops.client.api.ticket.ticket.TicketAttachment;
import com.nineteendrops.tracdrops.client.core.MessageUtils;
import com.nineteendrops.tracdrops.client.core.TracException;
import com.nineteendrops.tracdrops.client.core.Utils;
import com.nineteendrops.tracdrops.client.core.encoders.ParameterEncoder;
import com.nineteendrops.tracdrops.client.core.multicall.MultiParameter;
import com.nineteendrops.tracdrops.client.core.properties.TracProperties;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 07-sep-2009
 * Time: 22:37:48
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
public class AttachmentToPutAttachmentEncoder implements ParameterEncoder{

    public Object encode(TracProperties tracProperties, Object parameter) {

        if(parameter == null){
            throw new TracException(MessageUtils.getMessage("core.parameter.not.found", "TicketAttachment", this.getClass().getName()));
        }

        TicketAttachment ticketAttachment = (TicketAttachment)parameter;


        // check for mandatory fields
        Integer idTicket = ticketAttachment.getIdTicket();
        if(idTicket == 0){
            throw new TracException(MessageUtils.getMessage("core.parameter.not.found", "TicketAttachment.idTicket", this.getClass().getName()));
        }

        String fileName = ticketAttachment.getFilename();
        if(fileName == null || fileName.trim().equals("")){
            throw new TracException(MessageUtils.getMessage("core.parameter.not.found", "TicketAttachment.fileName", this.getClass().getName()));
        }

        String description = ticketAttachment.getDescription();
        if(description == null) description = "";

        String fullFileName = ticketAttachment.getFullFileName();
        if(fullFileName == null || fullFileName.trim().equals("")){
            throw new TracException(MessageUtils.getMessage("core.parameter.not.found", "TicketAttachment.fullFileName", this.getClass().getName()));
        }

        byte[] binaryData = Utils.getBinaryData(fullFileName, tracProperties);

        MultiParameter multiParameter = new MultiParameter();
        multiParameter.addParameter(idTicket);
        multiParameter.addParameter(fileName);
        multiParameter.addParameter(description);
        multiParameter.addParameter(binaryData);

        return multiParameter;
    }
}