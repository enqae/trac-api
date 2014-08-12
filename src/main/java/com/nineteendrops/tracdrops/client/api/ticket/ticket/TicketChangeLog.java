package com.nineteendrops.tracdrops.client.api.ticket.ticket;

import java.util.Date;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 07-sep-2009
 * Time: 23:44:21
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
 * Represent a change on one ticket. A ChangeLog is saved for every change on one ticket.
 */
public class TicketChangeLog {

    private Date time;
    private String author;
    private String fieldChanged;
    private String fieldOldValue;
    private String fieldNewValue;
    private boolean permanent;

    /**
     * Basic constructor
     * @param time the time when the change happened
     * @param author the user who made the change
     * @param fieldChanged the field which changed
     * @param fieldOldValue the value previous to the change
     * @param fieldNewValue the new value
     * @param permanent At this time is not clear the meaning of this parameter (needs further investigation)
     */
    public TicketChangeLog(Date time, String author, String fieldChanged, String fieldOldValue, String fieldNewValue, boolean permanent) {
        this.time = time;
        this.author = author;
        this.fieldChanged = fieldChanged;
        this.fieldOldValue = fieldOldValue;
        this.fieldNewValue = fieldNewValue;
        this.permanent = permanent;
    }

    /**
     *
     * @return the time when the change happened
     */
    public Date getTime() {
        return time;
    }

    /**
     *
     * @return the user who made the change
     */
    public String getAuthor() {
        return author;
    }

    /**
     *
     * @return the filed changed
     */
    public String getFieldChanged() {
        return fieldChanged;
    }

    /**
     *
     * @return the values previous to the change
     */
    public String getFieldOldValue() {
        return fieldOldValue;
    }

    /**
     *
     * @return the new value
     */
    public String getFieldNewValue() {
        return fieldNewValue;
    }

    /**
     *
     * @return needs further investigation
     */
    public boolean isPermanent() {
        return permanent;
    }

    @Override
    public String toString() {
        return "TicketChangeLog{" +
                "time=" + time +
                ", author='" + author + '\'' +
                ", fieldChanged='" + fieldChanged + '\'' +
                ", fieldOldValue='" + fieldOldValue + '\'' +
                ", fieldNewValue='" + fieldNewValue + '\'' +
                ", permanent=" + permanent +
                '}';
    }
}
