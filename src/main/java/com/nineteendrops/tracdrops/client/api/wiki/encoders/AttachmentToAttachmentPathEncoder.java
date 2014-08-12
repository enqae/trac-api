package com.nineteendrops.tracdrops.client.api.wiki.encoders;

import com.nineteendrops.tracdrops.client.api.wiki.WikiAttachment;
import com.nineteendrops.tracdrops.client.core.MessageUtils;
import com.nineteendrops.tracdrops.client.core.TracException;
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
public class AttachmentToAttachmentPathEncoder implements ParameterEncoder{

    public Object encode(TracProperties tracProperties, Object parameter) {

        if(parameter == null){
            throw new TracException(MessageUtils.getMessage("core.parameter.not.found", "TicketAttachment", this.getClass().getName()));
        }

        WikiAttachment wikiAttachment = (WikiAttachment)parameter;

        // check for mandatory fields
        String pageName = wikiAttachment.getPageName();
        if(pageName == null || pageName.trim().equals("")){
            throw new TracException(MessageUtils.getMessage("core.parameter.not.found", "TicketAttachment.pageName", this.getClass().getName()));
        }

        String fileName = wikiAttachment.getFileName();
        if(fileName == null || fileName.trim().equals("")){
            throw new TracException(MessageUtils.getMessage("core.parameter.not.found", "TicketAttachment.fileName", this.getClass().getName()));
        }

        String pathToAttachment = pageName + "/" + fileName;

        MultiParameter multiParameter = new MultiParameter();
        multiParameter.addParameter(pathToAttachment);

        return multiParameter;
    }

}