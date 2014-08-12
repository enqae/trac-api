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
 * Time: 11:45:52
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
public class ArrayToChangeLogArrayListDecoder implements ReturnDecoder {

    public Object decode(Object result, TracProperties tracProperties, ArrayList keptParametersForDecoder) {

        Object[] resultArray = (Object[])result;

        ArrayList<WikiChangeLog> wikiChanges = new ArrayList<WikiChangeLog>();

        for(Object resultElement: resultArray){

            HashMap element = (HashMap)resultElement;

            WikiChangeLog wikiChangeLog = new WikiChangeLog((Date)element.get(WikiKeys.LAST_MODIFIED),
                                                (String)element.get(WikiKeys.COMMENT),
                                                (String)element.get(WikiKeys.NAME),
                                                (String)element.get(WikiKeys.AUTHOR),
                                                (Integer)element.get(WikiKeys.VERSION));
            wikiChanges.add(wikiChangeLog);
        }


        return wikiChanges;
    }
}