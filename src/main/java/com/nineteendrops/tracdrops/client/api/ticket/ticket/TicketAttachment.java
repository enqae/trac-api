package com.nineteendrops.tracdrops.client.api.ticket.ticket;

import java.util.Date;

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
public class TicketAttachment {

    private int idTicket;
    private String filename;
    private String fullFileName;
    private String description;
    private int size;
    private Date time;
    private String author;


    public TicketAttachment(int idTicket, String filename, String description, String fullFileName) {
        this.idTicket = idTicket;
        this.filename = filename;
        this.description = description;
        this.fullFileName = fullFileName;
    }

    public TicketAttachment(String filename, String description, int size, Date time, String author) {
        this.filename = filename;
        this.description = description;
        this.size = size;
        this.time = time;
        this.author = author;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public String getFilename() {
        return filename;
    }

    public String getFullFileName() {
        return fullFileName;
    }

    public String getDescription() {
        return description;
    }

    public int getSize() {
        return size;
    }

    public Date getTime() {
        return time;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "TicketAttachment{" +
                "idTicket=" + idTicket +
                ", filename='" + filename + '\'' +
                ", fullFileName='" + fullFileName + '\'' +
                ", description='" + description + '\'' +
                ", size=" + size +
                ", time=" + time +
                ", author='" + author + '\'' +
                '}';
    }
}
