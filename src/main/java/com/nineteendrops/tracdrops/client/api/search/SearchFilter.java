package com.nineteendrops.tracdrops.client.api.search;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 23-ago-2009
 * Time: 21:10:10
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

/**
 * Helper class to maintain the code and the description of the query filters availables in the TRAC server
 */
public class SearchFilter {

    private String filterName = null;
    private String filterDescription = null;

    /**
     * Basic Constructor
     * @param filterName code for the filter in the TRAC server
     * @param filterDescription description of the filter in the TRAC server
     */
    public SearchFilter(String filterName, String filterDescription) {
        this.filterName = filterName;
        this.filterDescription = filterDescription;
    }

    public String getFilterName() {
        return filterName;
    }

    public String getFilterDescription() {
        return filterDescription;
    }

    @Override
    public String toString() {
        return "SearchFilter{" +
                "filterName='" + filterName + '\'' +
                ", filterDescription='" + filterDescription + '\'' +
                '}';
    }
}
