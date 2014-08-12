package com.nineteendrops.tracdrops.client.api.wiki;

import java.util.Date;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 12-sep-2009
 * Time: 11:42:16
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
public class WikiChangeLog {

    private Date lastModified;
    private String comment;
    private String name;
    private String author;
    private int version;

    public WikiChangeLog(Date lastModified, String comment, String name, String author, int version) {
        this.lastModified = lastModified;
        this.comment = comment;
        this.name = name;
        this.author = author;
        this.version = version;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public String getComment() {
        return comment;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "TicketChangeLog{" +
                "lastModified=" + lastModified +
                ", comment='" + comment + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", version=" + version +
                '}';
    }
}
