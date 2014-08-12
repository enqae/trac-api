package com.nineteendrops.tracdrops.api;

import com.nineteendrops.tracdrops.client.Trac;
import com.nineteendrops.tracdrops.client.api.core.ApiVersion;
import com.nineteendrops.tracdrops.client.api.core.CoreManager;
import com.nineteendrops.tracdrops.client.api.wiki.WikiManager;
import com.nineteendrops.tracdrops.client.api.ticket.ticket.TicketAttachment;
import com.nineteendrops.tracdrops.client.api.ticket.ticket.TicketManager;

import java.util.Date;


/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 23-ago-2009
 * Time: 13:12:55
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
public class Test {

    public static void main(String[] args) throws Exception{

        //"http://192.168.56.3/projects/test3/xmlrpc"
        Trac trac = new Trac("http://127.0.0.1:8080/projects/test3/xmlrpc", "admin", "admin");
        trac.initialize();

        //trac.multicallStart();


        //ArrayList<String> filters = new ArrayList<String>();
        //filters.add("wiki");
        //filters.add("ticket");
        //Object osp1 = trac.getSearch().search("PANTALLA", filters);

        //Object o = trac.getCoreManager().getPackages();
        //Object o = trac.getCoreManager().getApiVersion();

        //Object o = trac.getCoreManager().launchMulticall();


        CoreManager core = trac.getCoreManager();

            //Object o = core.getMethods();

            //String s = core.getMethodHelp("system.getAPIVersion");

            //ArrayList almts = core.getMethodSignatures("system.getAPIVersion");

            ApiVersion av = core.getApiVersion();


        TicketManager ticketManager = trac.getTicketManager();
        //Object otg = ticketManager.get(2);
        //Object otq = ticketManager.query("milestone=ALPHA M1");

        //Object otgrc = ticketManager.getRecentChanges(new Date());
        //Object otat = ticketManager.getAvailableActions(2);


/*
        Ticket te = new Ticket("SUMMARY_ES 2", "DESCRIPTION_ES 2");
        te.setKeywords("KEYWORD_ONE 2");
        te.setComponent("NO COMPONET!!!! 2");

        Integer idTicket = ticketManager.create(te, false);

        te.setIdTicket(idTicket);
        te.setSummary("NEW FIELD_SUMMARY ES 2 ");
        te.setComment("NEW COMMENT ES ES ES 2");

        te = ticketManager.update(te, false);
*/

        //ticketManager.delete(9);

        //Object o = ticketManager.getChangeLog(8, 0);
        //Object o = ticketManager.getAttachmentsInfo(8);
/*
        Object o = ticketManager.getAttachment(8, "superhero-300x212.jpg");
        FileOutputStream fos = new FileOutputStream("/Users/ka2/" + "superhero-300x212.jpg");
        fos.write((byte[])o);
        fos.close();
*/

        String fileName = ticketManager.getAttachmentToFile(8, "superhero-300x212.jpg", "/Users/ka2/");

        TicketAttachment ticketTicketAttachment = new TicketAttachment(7, "superhero-300x212.jpg", "", "/Users/ka2/superhero-300x212.jpg");
        String fileName2 = ticketManager.addAttachement(ticketTicketAttachment, true);

        boolean ok = ticketManager.deleteAttachment(7, "superhero-300x212.jpg");


        //Object otf = ticketManager.getTicketFields();


        //ComponentManager cm = trac.getComponentManager();

        //Object o = cm.getComponents();
        //Object o = cm.getComponent("Database Model");
        //Object o = cm.delete("TEST_TO_DELETE");

/*
        Component component = new Component("OWNER_A", "description2", "COMPONENT_ONE_ONE");
        Object o = cm.create(component);
*/

/*
        Component component = new Component("OWNER_Ab", "description2zzzzz", "COMPONENT_ONE_ONE22");
        Object o = cm.update(component);
*/

/*
        VersionManager versionManager = trac.getVersionManager();

        //Object o = versionManager.getVersions();
        //Object o = versionManager.getVersion("RELEASE VERSION33");
        //Object o = versionManager.delete("VERSION_TO_DELETE_C");

        Version version = new Version("VERSION_ONE", "VVV!!!!", new Date());
        Object o = versionManager.create(version);

        Version versionu = new Version("VERSION_ONE", "VVV!!!!uuuuuuuuu", new Date());
        Object ou = versionManager.update(versionu);
*/
/*
        MilestoneManager milestoneManager = trac.getMilestoneManager();


*//*
        Object o = milestoneManager.getMilestones();
        Object o11444 = milestoneManager.getMilestone("ALPHA M1");
*//*

        Milestone milestone = new Milestone("MILESTONE_ ONE", "DESC ONE", true, new Date());
        Object o4444 = milestoneManager.create(milestone);
        Object o11 = milestoneManager.getMilestone("MILESTONE_ ONE");


        Milestone milestoneu = new Milestone("MILESTONE_ ONE", "DESC ONEuuuuuuuuuuuuuu", true, new Date());
        Object ou = milestoneManager.update(milestoneu);

        Object od = milestoneManager.delete("MILESTONE_ ONE");   */


/*        TypeManager typeManager = trac.getTypeManager();


        //Object o = typeManager.getTypes();
        //Object o11444 = typeManager.getTypeOrder("defect");
        //Object o114443 = typeManager.getTypeOrder("enhancement");




        Type type = new Type("type ONE", "2");
        Object o4444 = typeManager.create(type);

        Type typeu = new Type("type ONE", "4");
        Object ou = typeManager.update(typeu);

        Object od = typeManager.delete("type ONE"); */

/*        StatusManager statusManager = trac.getStatusManager();


        //Object o = statusManager.getStatuses();
        //Object o11444 = statusManager.getStatus("accepted");
        //Object o114443 = typeManager.getTypeOrder("enhancement");




        Status status = new Status("Status ONE", "0");
        Object o4444 = statusManager.create(status);
        Object o = statusManager.getStatuses();

        Status statusu = new Status("Status ONE", "1");
        Object ou = statusManager.update(statusu);
        Object o11444 = statusManager.getStatus("Status ONE");

        Object od = statusManager.delete("Status ONE222");*/

/*         ResolutionManager resolutionManager = trac.getResolutionManager();


        //Object o = resolutionManager.getResolutions();
        //Object o11444 = resolutionManager.getResolutionOrder("fixed");
        //Object o114443 = typeManager.getTypeOrder("enhancement");




        Resolution resolution = new Resolution("RESO ONE1", "8");
        Object o4444 = resolutionManager.create(resolution);
        Object o = resolutionManager.getResolutions();

        Resolution statusu = new Resolution("RESO ONE1", "1");
        Object ou = resolutionManager.update(statusu);
        Object o11444 = resolutionManager.getResolutionOrder("RESO ONE1");

        Object od = resolutionManager.delete("RESO ONE1");

        */
/*         PriorityManager priorityManager = trac.getPriorityManager();


        //Object o = priorityManager.getPriorities();
        //Object o11444 = priorityManager.getPriorityOrder("major");
        //Object o114443 = typeManager.getTypeOrder("enhancement");




        Priority resolution = new Priority("PRI ONE2", "1");
        Object o4444 = priorityManager.create(resolution);
        Object o = priorityManager.getPriorities();

        Priority priority = new Priority("PRI ONE2", "4");
        Object ou = priorityManager.update(priority);
        Object o11444 = priorityManager.getPriorityOrder("PRI ONE2");

        Object od = priorityManager.delete("PRI ONE2");   */

/*
        SeverityManager severityManager = trac.getSeverityManager();


        //Object o = severityManager.getSeverities();
        //Object o11444 = severityManager.getSeverityOrder("URGENT VERY");
        //Object o114443 = typeManager.getTypeOrder("enhancement");




        Severity resolution = new Severity("SEV ONE2", "1");
        Object o4444 = severityManager.create(resolution);
        Object o = severityManager.getSeverities();

*//*        Severity priority = new Severity("SEV ONE2", "4");
        Object ou = severityManager.update(priority);
        Object o11444 = severityManager.getSeverityOrder("SEV ONE2");*//*

        Object od = severityManager.delete("SEV ONE2");*/

        WikiManager wm = trac.getWikiManager();

        Object o1 = wm.getChanges(new Date(new Date().getTime()-10000000));


        //Object olm = trac.launchMulticall();



//        Wiki2 wiki = (Wiki2) factory.newInstance(Wiki2.class);
//        Integer version = wiki.getRPCVersionSupported();
/*

        SearchTrac search = (SearchTrac)factory.newInstance(SearchTrac.class);
        Vector v = search.getSearchFilters();

        Vector p = search.search("PANTALLA");

*/
/*
        SystemTrac core = (SystemTrac)factory.newInstance(SystemTrac.class);
        Vector lm = core.listMethods();
*/

/*
        MilestoneTrac milestoneTrac = (MilestoneTrac)factory.newInstance(MilestoneTrac.class);
        Vector v = milestoneTrac.getAll();
*/

        System.out.println("Nothing...");
    }
}
