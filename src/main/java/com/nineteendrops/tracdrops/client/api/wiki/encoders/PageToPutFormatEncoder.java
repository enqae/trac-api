package com.nineteendrops.tracdrops.client.api.wiki.encoders;

import com.nineteendrops.tracdrops.client.api.wiki.WikiPage;
import com.nineteendrops.tracdrops.client.api.wiki.WikiKeys;
import com.nineteendrops.tracdrops.client.core.MessageUtils;
import com.nineteendrops.tracdrops.client.core.TracException;
import com.nineteendrops.tracdrops.client.core.encoders.ParameterEncoder;
import com.nineteendrops.tracdrops.client.core.multicall.MultiParameter;
import com.nineteendrops.tracdrops.client.core.properties.TracProperties;

import java.util.Hashtable;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 12-sep-2009
 * Time: 13:57:47
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
public class PageToPutFormatEncoder implements ParameterEncoder {

    public Object encode(TracProperties tracProperties, Object parameter) {

        if(parameter == null){
            throw new TracException(MessageUtils.getMessage("core.parameter.not.found", "wikiPage", this.getClass().getName()));
        }

        WikiPage wikiPage = (WikiPage)parameter;

        // check for mandatory fields
        String name = wikiPage.getName();
        if(name == null || name.trim().equals("")){
            throw new TracException(MessageUtils.getMessage("core.parameter.not.found", "wikiPage.name", this.getClass().getName()));
        }

        String content = wikiPage.getContent();
        if(content == null || content.trim().equals("")){
            throw new TracException(MessageUtils.getMessage("core.parameter.not.found", "wikiPage.content", this.getClass().getName()));
        }

        MultiParameter multiParameter = new MultiParameter();
        multiParameter.addParameter(name);
        multiParameter.addParameter(content);

        Hashtable attributes = new Hashtable();
        String comment = wikiPage.getComment();
        if(comment != null && !comment.trim().equals("")){
            attributes.put(WikiKeys.COMMENT, comment);
        }

        multiParameter.addParameter(attributes);

        return multiParameter;
    }
}