package com.nineteendrops.tracdrops.api.search;

import com.nineteendrops.tracdrops.utils.LineConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Arrays;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 27-sep-2009
 * Time: 19:20:27
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
public class ComplexQueriesProvider extends LineConverter {

    private Log log = LogFactory.getLog(ComplexQueriesProvider.class);

    protected Object[] adjustValues(String[] values) {

        Object[] returnValues = new Object[values.length];

        // query
        returnValues[0] = values[0];

        // filters
        String[] filters = values[1].split("\\|");
        returnValues[1] = Arrays.asList(filters);

        // number of expected results
        returnValues[2] = new Integer(values[2].trim());

        return returnValues;
    }
}