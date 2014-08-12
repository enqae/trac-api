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
 * Type of objects to search in the TRAC server
 */
public class SearchKeys {

    /**
     * To search on ticket objects
     */
    public static final String ON_TICKETS = "ticket";

    /**
     * To search on changeset objects
     */
    public static final String ON_CHANGESETS = "changeset";

    /**
     * To search on milestone objects
     */
    public static final String ON_MILESTONES = "milestone";

    /**
     * To search on wiki objects
     */
    public static final String ON_WIKI = "wiki";
    
}