package com.nineteendrops.tracdrops.client.api.search;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 26-sep-2009
 * Time: 15:19:29
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
 * Helper class to keep the relevant information for a query: query itself and filters to apply to it
 */
public class SearchQuery {

    private String query;
    private boolean onTickets = false;
    private boolean onChangesets = false;
    private boolean onMilestones = false;
    private boolean onWiki = false;

    private boolean onAll = true;

    /**
     * Basic constructor. All filters will be applied by default
     *
     * @param query query any expression accepted by the query functionality in TRAC server
     */
    public SearchQuery(String query) {
        this.query = query;
    }

    /**
     * Full constructor. Only specified filters will be applied
     *
     * @param query any expression accepted by the query functionality in TRAC server
     */
    public SearchQuery(String query, boolean onTickets, boolean onChangesets, boolean onMilestones, boolean onWiki) {
        this.query = query;
        this.onTickets = onTickets;
        this.onChangesets = onChangesets;
        this.onMilestones = onMilestones;
        this.onWiki = onWiki;

        this.onAll = false;
    }

    public String getQuery() {
        return query;
    }

    public boolean isOnTickets() {
        return onTickets;
    }

    public void setOnTickets(boolean onTickets) {
        this.onTickets = onTickets;
        this.onAll = false;
    }

    public boolean isOnChangesets() {
        return onChangesets;
    }

    public void setOnChangesets(boolean onChangesets) {
        this.onChangesets = onChangesets;
        this.onAll = false;
    }

    public boolean isOnMilestones() {
        return onMilestones;
    }

    public void setOnMilestones(boolean onMilestones) {
        this.onMilestones = onMilestones;
        this.onAll = false;
    }

    public boolean isOnWiki() {
        return onWiki;
    }

    public void setOnWiki(boolean onWiki) {
        this.onWiki = onWiki;
        this.onAll = false;
    }

    public boolean isOnAll() {
        return onAll;
    }

    @Override
    public String toString() {
        return "SearchQuery{" +
                "query='" + query + '\'' +
                ", onTickets=" + onTickets +
                ", onChangesets=" + onChangesets +
                ", onMilestones=" + onMilestones +
                ", onWiki=" + onWiki +
                '}';
    }
}
