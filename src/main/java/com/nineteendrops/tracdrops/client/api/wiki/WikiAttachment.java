package com.nineteendrops.tracdrops.client.api.wiki;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 08-sep-2009
 * Time: 0:12:48
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
public class WikiAttachment {

    private String pageName;
    private String fileName;
    private String description;
    private boolean replaceAttachment = true;

    private String fullFileName;

    public WikiAttachment(String pageName, String fileName) {
        this.pageName = pageName;
        this.fileName = fileName;
    }

    public WikiAttachment(String pageName, String fileName, String fullFileName) {
        this.pageName = pageName;
        this.fileName = fileName;
        this.fullFileName = fullFileName;
    }

    public WikiAttachment(String pageName, String fileName, String description, String fullFileName) {
        this.pageName = pageName;
        this.fileName = fileName;
        this.description = description;
        this.fullFileName = fullFileName;
    }

    public WikiAttachment(String pageName, String fileName, String description, String fullFileName, boolean replaceAttachment) {
        this.pageName = pageName;
        this.fileName = fileName;
        this.description = description;
        this.fullFileName = fullFileName;
        this.replaceAttachment = replaceAttachment;
    }

    public String getPageName() {
        return pageName;
    }

    public String getFileName() {
        return fileName;
    }

    public String getDescription() {
        return description;
    }

    public boolean isReplaceAttachment() {
        return replaceAttachment;
    }

    public String getFullFileName() {
        return fullFileName;
    }

    @Override
    public String toString() {
        return "TicketAttachment{" +
                "pageName='" + pageName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", description='" + description + '\'' +
                ", replaceAttachment=" + replaceAttachment +
                ", fullFileName='" + fullFileName + '\'' +
                '}';
    }
}