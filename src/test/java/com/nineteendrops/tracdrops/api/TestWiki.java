package com.nineteendrops.tracdrops.api;

import com.nineteendrops.tracdrops.client.Trac;
import com.nineteendrops.tracdrops.client.api.wiki.WikiManager;


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
public class TestWiki {

    public static void main(String[] args) throws Exception{

        //"http://192.168.56.3/projects/test3/xmlrpc"
        Trac trac = new Trac("http://127.0.0.1:8080/projects/test3/xmlrpc", "admin", "admin");
        trac.initialize();

        //trac.multicallStart();


        WikiManager wm = trac.getWikiManager();


//        Object o1 = wm.getChanges(new Date(new Date().getTime()-1000000000000L));

        //Object o2 = wm.getRPCVersionSupported();
        Object o3 = wm.getPage("CamelCase");

//        Object o4 = wm.getPageVersion("WikiStart", 1);
//
//        Object o3h = wm.getPageHTML("WikiStart");
//        Object o4h = wm.getPageHTMLVersion("WikiStart", 1);
//
//        Object o5 = wm.getAllPagesNames();
//
        //Object o6 = wm.getPageInfo("WikiStart");
        //Object o7 = wm.getPageInfoVersion("WikiStart", 1);

        // REVIEW Object o8 = wm.getPageListOfVersions("WikiStart");

        //WikiPage page = new WikiPage("new_page_api", "nuevo para el API222333", "no comment2");
        //Object o9 = wm.putPage(page);

//        ArrayList o10 = wm.getPageListOfAttachments("WikiStart");
//        Object o11 = wm.getAttachment((String)o10.get(0));
//        Object o12 = wm.getAttachmentToFile((String)o10.get(0));
//        Object o13 = wm.getAttachmentToFile((String)o10.get(0), "/Users/ka2/tools/");
//
//        TicketAttachment aa = new TicketAttachment("new_page_api", "cristina.jpg", "/Users/ka2/met.jpg");
//        Object o14 = wm.putAttachment(aa);
//
//        TicketAttachment aa2 = new TicketAttachment("new_page_api", "cristina2.jpg", "from met", "/Users/ka2/met.jpg", true);
//        Object o142 = wm.putAttachmentEx(aa2);
//


//        TicketAttachment aa3 = new TicketAttachment("new_page_api", "cristina5555555");
//        Object o15 = wm.deleteAttachment(aa3);


        //Object o16 = wm.wikiToHtml("ESTO ES PAA WIKI");

//        Object o17 = wm.pageHasChildren("new_page_api");
//        Object o18 = wm.pageHasChildren("new_page_api_2");


        Object olm = trac.launchMulticall();


        System.out.println("Nothing...");
    }
}