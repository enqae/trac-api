package com.nineteendrops.tracdrops.client.api.wiki.decoders;

import com.nineteendrops.tracdrops.client.api.wiki.WikiChangeLog;
import com.nineteendrops.tracdrops.client.api.wiki.WikiKeys;
import com.nineteendrops.tracdrops.client.core.decoders.ReturnDecoder;
import com.nineteendrops.tracdrops.client.core.properties.TracProperties;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 12-sep-2009
 * Time: 12:12:31
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
public class ArrayToChangeLogDecoder implements ReturnDecoder {

    public Object decode(Object result, TracProperties tracProperties, ArrayList keptParametersForDecoder) {

        WikiChangeLog wikiChangeLog = null;

        if(result instanceof HashMap){
            HashMap element = (HashMap)result;

            wikiChangeLog = new WikiChangeLog((Date)element.get(WikiKeys.LAST_MODIFIED),
                                      (String)element.get(WikiKeys.COMMENT),
                                      (String)element.get(WikiKeys.NAME),
                                      (String)element.get(WikiKeys.AUTHOR),
                                      (Integer)element.get(WikiKeys.VERSION));
        }

        return wikiChangeLog;
    }
}
