package com.nineteendrops.tracdrops.client.api.search;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 23-ago-2009
 * Time: 18:35:59
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
 * The result of a query returned by the TRAC server
 */

public class SearchResult {

    private String url = null;
    private String title = null;
    private String date = null;
    private String reporter = null;
    private String sumary = null;

    /**
     * Basic Constructor
     *
     * @param url access to the TRAC object found by the query
     * @param title title of th TRAC Object
     * @param date date the object was created
     * @param reporter who registered the object in the TRAC server
     * @param sumary brief description about the object
     */
    public SearchResult(String url, String title, String date, String reporter, String sumary) {
        this.url = url;
        this.title = title;
        this.date = date;
        this.reporter = reporter;
        this.sumary = sumary;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getReporter() {
        return reporter;
    }

    public String getSumary() {
        return sumary;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", reporter='" + reporter + '\'' +
                ", sumary='" + sumary + '\'' +
                '}';
    }
}
