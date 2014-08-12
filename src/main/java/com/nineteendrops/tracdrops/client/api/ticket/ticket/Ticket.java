package com.nineteendrops.tracdrops.client.api.ticket.ticket;

import java.util.Date;
import java.util.HashMap;

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
 * The representation on a Ticket object with the attributes managed by the TRAC server for this kind of objects.
 * The fields to take into account for a ticket:
 * <ul>
 *   <li>idTicket: the ID of ticket on TRAC server (mandatory on updates and deletes)</li>
 *   <li>dateCreation: the time when the ticket was created</li>
 *   <li>dateChange: the time when the ticket had any change</li>
 *   <li>summary: the title of the ticket</li>
 *   <li>description: what the ticket is about</li>
 *   <li>keywords: keywords that a ticket is marked with</li>
 *   <li>status: what is the current status? One of new, assigned, closed, reopened (update only)</li>
 *   <li>blockedby: another ticket blocking this one</li>
 *   <li>blocking: another ticket being blocked by this one</li>
 *   <li>resolution - Reason for why a ticket was closed. One of fixed, invalid, wontfix, duplicate, worksforme</li>
 *   <li>type: type of the ticket. One of defect, task, enhanment...</li>
 *   <li>version: when the ticket should be resolved</li>
 *   <li>milestone: when this issue should be resolved at the latest</li>
 *   <li>component: the component the issue refers to</li>
 *   <li>priority: the importance of this issue, ranging from trivial to blocker</li>
 *   <li>reporter: person reporting the issue</li>
 *   <li>assigned to/Owner: principal person responsible for handling the issue</li>
 *   <li>cc: a comma-separated list of other users or E-Mail addresses to notify. Note that this does not imply responsiblity or any other policy</li>
 *   <li>comment: anything relevant to the issue (mandatory on updates)</li>
 * </ul>
 */
public class Ticket {

    private int idTicket;
    private Date dateCreation;
    private Date dateChange;

    private HashMap attributes;

    /**
     * Default constructor
     */
    public Ticket() {
        this.attributes = new HashMap();
    }

    /**
     * Basic constructor
     * @param summary title for ticket
     * @param description full description of the issue the ticket was open about //TODO
     */
    public Ticket(String summary, String description) {

        this.attributes = new HashMap();
        this.attributes.put(TicketKeys.FIELD_SUMMARY, summary);
        this.attributes.put(TicketKeys.FIELD_DESCRIPTION, description);

    }

    /**
     * Builds a 'full' existing Ticket. This is mainly for internal purposes
     * @param idTicket the ticket id on the TRAC server
     * @param dateCreation the date the ticket was created
     * @param dateChange the date the last change on ticket was made
     * @param attributes rest of ticket definition on the form of attribute.key, attribute.vale
     *
     * @see com.nineteendrops.tracdrops.client.api.ticket.ticket.TicketKeys
     */
    public Ticket(int idTicket, Date dateCreation, Date dateChange, HashMap attributes) {
        this.idTicket = idTicket;
        this.dateCreation = dateCreation;
        this.dateChange = dateChange;
        if(attributes != null){
            this.attributes = new HashMap(attributes);
        } else {
            this.attributes = new HashMap();
        }
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateChange() {
        return dateChange;
    }

    public void setDateChange(Date dateChange) {
        this.dateChange = dateChange;
    }

    public String getSummary(){
        return (String)attributes.get(TicketKeys.FIELD_SUMMARY);
    }

    public void setSummary(String value){
        attributes.put(TicketKeys.FIELD_SUMMARY, value);
    }

    public String getDescription(){
        return (String)attributes.get(TicketKeys.FIELD_DESCRIPTION);
    }

    public void setDescription(String value){
        attributes.put(TicketKeys.FIELD_DESCRIPTION, value);
    }

    public String getKeywords(){
        return (String)attributes.get(TicketKeys.FIELD_KEYWORDS);
    }

    public void setKeywords(String value){
        attributes.put(TicketKeys.FIELD_KEYWORDS, value);
    }

    public String getStatus(){
        return (String)attributes.get(TicketKeys.FIELD_STATUS);
    }

    public void setStatus(String value){
        attributes.put(TicketKeys.FIELD_STATUS, value);
    }

    public String getBlockedBy(){
        return (String)attributes.get(TicketKeys.FIELD_BLOCKEDBY);
    }

    public void setBlockedBy(String value){
        attributes.put(TicketKeys.FIELD_BLOCKEDBY, value);
    }

    public String getBlocking(){
        return (String)attributes.get(TicketKeys.FIELD_BLOCKING);
    }

    public void setBlocking(String value){
        attributes.put(TicketKeys.FIELD_BLOCKING, value);
    }

    public String getResolution(){
        return (String)attributes.get(TicketKeys.FIELD_RESOLUTION);
    }

    public void setResolution(String value){
        attributes.put(TicketKeys.FIELD_RESOLUTION, value);
    }

    public String getType(){
        return (String)attributes.get(TicketKeys.FIELD_TYPE);
    }

    public void setType(String value){
        attributes.put(TicketKeys.FIELD_TYPE, value);
    }

    public String getVersion(){
        return (String)attributes.get(TicketKeys.FIELD_VERSION);
    }

    public void setVersion(String value){
        attributes.put(TicketKeys.FIELD_VERSION, value);
    }

    public String getMilestone(){
        return (String)attributes.get(TicketKeys.FIELD_MILESTONE);
    }

    public void setMilestone(String value){
        attributes.put(TicketKeys.FIELD_MILESTONE, value);
    }

    public String getComponent(){
        return (String)attributes.get(TicketKeys.FIELD_COMPONENT);
    }

    public void setComponent(String value){
        attributes.put(TicketKeys.FIELD_COMPONENT, value);
    }

    public String getPriority(){
        return (String)attributes.get(TicketKeys.FIELD_PRIORITY);
    }

    public void setPriority(String value){
        attributes.put(TicketKeys.FIELD_PRIORITY, value);
    }

    public String getReporter(){
        return (String)attributes.get(TicketKeys.FIELD_REPORTER);
    }

    public void setReporter(String value){
        attributes.put(TicketKeys.FIELD_REPORTER, value);
    }

    public String getOwner(){
        return (String)attributes.get(TicketKeys.FIELD_OWNER);
    }

    public void setOwner(String value){
        attributes.put(TicketKeys.FIELD_OWNER, value);
    }

    public String getCc(){
        return (String)attributes.get(TicketKeys.FIELD_CC);
    }

    public void setCc(String value){
        attributes.put(TicketKeys.FIELD_CC, value);
    }

    public String getComment(){
        return (String)attributes.get(TicketKeys.FIELD_COMMENT);
    }

    public void setComment(String value){
        attributes.put(TicketKeys.FIELD_COMMENT, value);
    }


    public String getOtherAttribute(String attributeName){
        return (String)attributes.get(attributeName);
    }

    public void setOtherAttribute(String attributeName, String value){
        attributes.put(attributeName, value);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "idTicket=" + idTicket +
                ", dateCreation=" + dateCreation +
                ", dateChange=" + dateChange +
                ", attributes=" + attributes +
                '}';
    }
}
