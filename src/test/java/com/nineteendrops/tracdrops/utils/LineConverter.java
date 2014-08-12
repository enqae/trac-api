package com.nineteendrops.tracdrops.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 27-sep-2009
 * Time: 1:45:00
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
public abstract class LineConverter{

    private Log log = LogFactory.getLog(LineConverter.class);

    public Object[] convert(String line) {

        String[] values = line.split(",");

        String[] normalizedValues = new String[values.length];
        int i = 0;
        for(String stringValue: values){
            normalizedValues[i++] = stringValue.trim();
        }

        return adjustValues(normalizedValues);
    }

    protected abstract Object[] adjustValues(String[] values);
}