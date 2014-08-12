package com.nineteendrops.tracdrops.client.api.ticket.ticket;

import com.nineteendrops.tracdrops.client.api.ticket.ticket.decoders.ArrayToAttachmentArrayListDecoder;
import com.nineteendrops.tracdrops.client.api.ticket.ticket.decoders.ArrayToChangeLogArrayListDecoder;
import com.nineteendrops.tracdrops.client.api.ticket.ticket.decoders.ArrayToTicketDecoder;
import com.nineteendrops.tracdrops.client.api.ticket.ticket.decoders.ArrayToTicketFieldDecoder;
import com.nineteendrops.tracdrops.client.api.ticket.ticket.encoders.AttachmentToPutAttachmentEncoder;
import com.nineteendrops.tracdrops.client.api.ticket.ticket.encoders.TicketQueryToStringEncoder;
import com.nineteendrops.tracdrops.client.api.ticket.ticket.encoders.TicketToCreateFormatEncoder;
import com.nineteendrops.tracdrops.client.api.ticket.ticket.encoders.TicketToUpdateFormatEncoder;
import com.nineteendrops.tracdrops.client.core.TracException;
import com.nineteendrops.tracdrops.client.core.TracMethodExecutionException;
import com.nineteendrops.tracdrops.client.core.decoders.ArrayToIntegerArrayListDecoder;
import com.nineteendrops.tracdrops.client.core.decoders.ArrayToStringArrayListDecoder;
import com.nineteendrops.tracdrops.client.core.decoders.ByteArrayToFileDecoder;
import com.nineteendrops.tracdrops.client.core.annotations.TracClass;
import com.nineteendrops.tracdrops.client.core.annotations.TracClassMethod;
import com.nineteendrops.tracdrops.client.core.annotations.TracParameterEncoder;
import com.nineteendrops.tracdrops.client.core.annotations.TracParameterPolicy;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 23-ago-2009
 * Time: 18:57:02
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
 *  Wraps the functionality referred to the ticket api provided by the TRAC XML-RPC plugin.
 *  Some samples of use are the following:
 * <ul>
 * <li> <br/>
 * <pre>
 * </pre>
 * </li>
 * </ul>
 */
@TracClass(tracClass = "ticket")
public interface TicketManager {

    /**
     * Retrieves changes in the TRAC server since the given date.
     * @param date since which changes should be retrieved
     * @return an array of Integers with the ids of tickets which have been changed since the given date
     * @throws TracMethodExecutionException when there is an error processing the request on the TRAC server
     */
    @TracClassMethod(tracClassMethodName = "getRecentChanges",
                     returnDecoder = ArrayToIntegerArrayListDecoder.class)
    public ArrayList getRecentChanges(Date date) throws TracMethodExecutionException;

    /**
     * Retrieves the available actions on the ticket. This actions are related to the ticket workflow defined
     * in the TRAC server. Usual actions are:
     * <ul>
     * <li>leave: the ticket remains with the same status</li>
     * <li>resolve: the ticket changes its status to another one ("new2 and "assigned" are excluded)</li>
     * <li>reassign: the ticket owner can be changed, and if done next status will be "assigned"</li>
     * <li>accept: the ticket is taken for current user and next status is "accepted"</li>
     * </ul>
     * The list of actions retrieved changes depending on the current status of each ticket.
     * These actions tell us the next status values that a ticket can accept.
     * @param idTicket id of the ticket
     * @return a list of Strings with the name of the posible actions to be taken on the requested ticket
     * @throws TracMethodExecutionException when there is an error processing the request on the TRAC server
     */
    @TracClassMethod(tracClassMethodName = "getAvailableActions",
                     returnDecoder = ArrayToStringArrayListDecoder.class)
    public ArrayList getAvailableActions(int idTicket) throws TracMethodExecutionException;


    /**
     * Retrives the whole information linked to a Ticket on the TRAC server.
     * @param idTicket id of the ticket to retrieve
     * @return a Ticket object
     * @throws TracMethodExecutionException when there is an error processing the request on the TRAC server
     *
     * @see com.nineteendrops.tracdrops.client.api.ticket.ticket.Ticket
     */
    @TracClassMethod(tracClassMethodName = "get",
                     returnDecoder = ArrayToTicketDecoder.class)
    public Ticket get(int idTicket) throws TracMethodExecutionException;


    /**
     * Creates a new ticket on the TRAC server. On creation fields from Ticket object like idTicket, status or comment are not taken into account.
     * @param ticket a Ticket object with the ticket information
     * @param notify if a notification should be sent
     * @return the ID of the created ticekt
     * @throws TracMethodExecutionException when there is an error processing the request on the TRAC server
     * @throws TracException when a wrong Ticket object is provided
     *
     * @see com.nineteendrops.tracdrops.client.api.ticket.ticket.Ticket
     */
    @TracClassMethod(tracClassMethodName = "create")
    public Integer create(@TracParameterEncoder(encoder = TicketToCreateFormatEncoder.class)
                          Ticket ticket,
                          boolean notify) throws TracMethodExecutionException, TracException;


    /**
     * Updates an existing ticket. The following fields of the Ticket object are mandatory: idTicket and comment
     * @param ticket a Ticket Object with the values to change
     * @param notify if a notification should be sent
     * @return a Ticket object modified with the new values
     * @throws TracMethodExecutionException when there is an error processing the request on the TRAC server
     * @throws TracException when a wrong Ticket object is provided
     */
    @TracClassMethod(tracClassMethodName = "update",
                     returnDecoder = ArrayToTicketDecoder.class)
    public Ticket update(@TracParameterEncoder(encoder = TicketToUpdateFormatEncoder.class)
                         Ticket ticket,
                         boolean notify) throws TracMethodExecutionException, TracException;


    /**
     * Deletes an existing ticket
     * @param idTicket ID of the ticket to be deleted
     * @return
     * @throws TracMethodExecutionException
     */
    @TracClassMethod(tracClassMethodName = "delete",
                     tracReturnType = Integer.class)
    public Integer delete(int idTicket) throws TracMethodExecutionException;


    /**
     * Retrieves the list of changes that have happend on the given ticket. This list is returned as a list of
     * ChangeLog objects.
     * @param idTicket id of the ticket
     * @return a list of ChangeLog object
     *
     * @see TicketChangeLog
     */
    @TracClassMethod(tracClassMethodName = "changeLog",
                     returnDecoder = ArrayToChangeLogArrayListDecoder.class)
    public ArrayList getChangeLog(int idTicket);

    // TODO: Revisar parámetro WHEN
    //@TracClassMethod(tracClassMethodName = "changeLog",
    //                 returnDecoder = ArrayToChangeLogArrayListDecoder.class)
    //public ArrayList getChangeLog(int idTicket, int when);



    @TracClassMethod(tracClassMethodName = "listAttachments",
                     returnDecoder = ArrayToAttachmentArrayListDecoder.class)
    public ArrayList getAttachmentsInfo(int idTicket);

    @TracClassMethod(tracClassMethodName = "getAttachment")
    public byte[] getAttachment(int idTicket, String attachmentName);

    @TracClassMethod(tracClassMethodName = "getAttachment",
                     returnDecoder = ByteArrayToFileDecoder.class)
    public String getAttachmentToFile(int idTicket,
                                      @TracParameterPolicy(keptForDecoder = true)
                                      String attachmentName);

    @TracClassMethod(tracClassMethodName = "getAttachment",
                     returnDecoder = ByteArrayToFileDecoder.class)
    public String getAttachmentToFile(int idTicket,
                                      @TracParameterPolicy(keptForDecoder = true)
                                      String attachmentName,
                                      @TracParameterPolicy(keptForDecoder = true, removeFromInvocation = true)
                                      String pathBase);

    @TracClassMethod(tracClassMethodName = "putAttachment")
    public String addAttachement(@TracParameterEncoder(encoder = AttachmentToPutAttachmentEncoder.class)
    TicketAttachment ticketAttachment,
                                 boolean replace);

    @TracClassMethod(tracClassMethodName = "deleteAttachment")
    public boolean deleteAttachment(int idTicket, String fileName);

    @TracClassMethod(tracClassMethodName = "getTicketFields",
                     returnDecoder = ArrayToTicketFieldDecoder.class)
    public ArrayList getTicketFields();


    @TracClassMethod(tracClassMethodName = "query",
                     returnDecoder = ArrayToIntegerArrayListDecoder.class)
    public ArrayList query(@TracParameterEncoder(encoder = TicketQueryToStringEncoder.class) TicketQuery ticketQuery);


}
