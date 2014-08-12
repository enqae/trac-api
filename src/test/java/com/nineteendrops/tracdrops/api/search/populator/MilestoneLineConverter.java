package com.nineteendrops.tracdrops.api.search.populator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.nineteendrops.tracdrops.utils.LineConverter;

import java.util.Date;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 27-sep-2009
 * Time: 16:25:25
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
public class MilestoneLineConverter extends LineConverter {

    private Log log = LogFactory.getLog(MilestoneLineConverter.class);

    protected Object[] adjustValues(String[] values) {

        Object[] returnValues = new Object[values.length];

        System.arraycopy(values, 0, returnValues, 0, values.length);

        if(!((String)returnValues[2]).equals("today")){
            returnValues[2] = new Date();
        }

        return returnValues;
    }
}
