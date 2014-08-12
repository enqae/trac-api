package com.nineteendrops.tracdrops.client.api.wiki;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 12-sep-2009
 * Time: 13:53:26
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
public class WikiPage {

    private String name;
    private String content;
    private String comment;

    public WikiPage() {
    }

    public WikiPage(String name, String content, String comment) {
        this.name = name;
        this.content = content;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "WikiPage{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
