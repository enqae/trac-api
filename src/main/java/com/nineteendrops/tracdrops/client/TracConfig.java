package com.nineteendrops.tracdrops.client;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 08-sep-2009
 * Time: 21:06:25
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
public class TracConfig {

    private String url = null;
    private String user = null;
    private String password = null;
    private String pathBase = null;

    public TracConfig(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public TracConfig(String url, String user, String password, String pathBase) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.pathBase = pathBase;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getPathBase() {
        return pathBase;
    }
}
