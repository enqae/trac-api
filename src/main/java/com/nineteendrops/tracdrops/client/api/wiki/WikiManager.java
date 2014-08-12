package com.nineteendrops.tracdrops.client.api.wiki;



import com.nineteendrops.tracdrops.client.api.wiki.decoders.ArrayToChangeLogArrayListDecoder;
import com.nineteendrops.tracdrops.client.api.wiki.decoders.ArrayToChangeLogDecoder;
import com.nineteendrops.tracdrops.client.api.wiki.encoders.PageToPutFormatEncoder;
import com.nineteendrops.tracdrops.client.api.wiki.encoders.AttachmentToPutAttachmentEncoder;
import com.nineteendrops.tracdrops.client.api.wiki.encoders.AttachmentToAttachmentPathEncoder;
import com.nineteendrops.tracdrops.client.core.annotations.TracClass;
import com.nineteendrops.tracdrops.client.core.annotations.TracClassMethod;
import com.nineteendrops.tracdrops.client.core.annotations.TracParameterEncoder;
import com.nineteendrops.tracdrops.client.core.annotations.TracParameterPolicy;
import com.nineteendrops.tracdrops.client.core.decoders.ArrayToIntegerArrayListDecoder;
import com.nineteendrops.tracdrops.client.core.decoders.ArrayToStringArrayListDecoder;
import com.nineteendrops.tracdrops.client.core.decoders.ByteArrayToFileDecoder;
import com.nineteendrops.tracdrops.client.core.TracMethodExecutionException;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 12-sep-2009
 * Time: 11:34:46
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
@TracClass(tracClass = "wiki")
public interface WikiManager {

    @TracClassMethod(tracClassMethodName = "getRecentChanges",
                     returnDecoder = ArrayToChangeLogArrayListDecoder.class)
    public ArrayList getChanges(Date changesFrom);

    @TracClassMethod(tracClassMethodName = "getRPCVersionSupported")
    public Integer getRPCVersionSupported();

    @TracClassMethod(tracClassMethodName = "getPage")
    public String getPage(String pagename) throws TracMethodExecutionException;

    // TODO: It seems not to work
    @TracClassMethod(tracClassMethodName = "wikiext.getPageVersions", useOnlyMethodName = true,
                     returnDecoder = ArrayToIntegerArrayListDecoder.class)
    public ArrayList getPageListOfVersions(String pagename) throws TracMethodExecutionException;

    @TracClassMethod(tracClassMethodName = "getPageVersion")
    public String getPageVersion(String pagename, int version) throws TracMethodExecutionException;

    @TracClassMethod(tracClassMethodName = "getPageHTML")
    public String getPageHTML(String pagename) throws TracMethodExecutionException;

    @TracClassMethod(tracClassMethodName = "getPageHTMLVersion")
    public String getPageHTMLVersion(String pagename, int version) throws TracMethodExecutionException;

    @TracClassMethod(tracClassMethodName = "getAllPages",
                     returnDecoder = ArrayToStringArrayListDecoder.class)
    public ArrayList getAllPagesNames() throws TracMethodExecutionException;

    @TracClassMethod(tracClassMethodName = "getPageInfo",
                     returnDecoder = ArrayToChangeLogDecoder.class)
    public WikiChangeLog getPageInfo(String page) throws TracMethodExecutionException;

    @TracClassMethod(tracClassMethodName = "getPageInfoVersion",
                     returnDecoder = ArrayToChangeLogDecoder.class)
    public WikiChangeLog getPageInfoVersion(String page, int version) throws TracMethodExecutionException;

    @TracClassMethod(tracClassMethodName = "putPage")
    public Boolean putPage(@TracParameterEncoder(encoder = PageToPutFormatEncoder.class)
    WikiPage wikiPage) throws TracMethodExecutionException;

    @TracClassMethod(tracClassMethodName = "listAttachments",
                     returnDecoder = ArrayToStringArrayListDecoder.class)
    public ArrayList getPageListOfAttachments(String pagename) throws TracMethodExecutionException;

    @TracClassMethod(tracClassMethodName = "getAttachment")
    public byte[] getAttachment(String pathToAttachment) throws TracMethodExecutionException;

    // TODO: use Attachemnt
    @TracClassMethod(tracClassMethodName = "getAttachment",
                     returnDecoder = ByteArrayToFileDecoder.class)
    public String getAttachmentToFile(@TracParameterPolicy(keptForDecoder = true)
                                      String pathToAttachment) throws TracMethodExecutionException;

    @TracClassMethod(tracClassMethodName = "getAttachment",
                     returnDecoder = ByteArrayToFileDecoder.class)
    public String getAttachmentToFile(@TracParameterPolicy(keptForDecoder = true)
                                      String pathToAttachment,
                                      @TracParameterPolicy(keptForDecoder = true, removeFromInvocation = true)
                                      String pathBase) throws TracMethodExecutionException;

    @TracClassMethod(tracClassMethodName = "putAttachment")
    public boolean putAttachment(@TracParameterEncoder(encoder = AttachmentToPutAttachmentEncoder.class)
    WikiAttachment wikiAttachment) throws TracMethodExecutionException;

    @TracClassMethod(tracClassMethodName = "putAttachment")
    public Object putAttachmentEx(@TracParameterEncoder(encoder = AttachmentToPutAttachmentEncoder.class)
    WikiAttachment wikiAttachment) throws TracMethodExecutionException;

    @TracClassMethod(tracClassMethodName = "deleteAttachment")
    public boolean deleteAttachment(@TracParameterEncoder(encoder = AttachmentToAttachmentPathEncoder.class)
    WikiAttachment fullFileName) throws TracMethodExecutionException;

    @TracClassMethod(tracClassMethodName = "wikiToHtml")
    public String wikiToHtml(String text) throws TracMethodExecutionException;

    // TODO: seems no to work
    @TracClassMethod(tracClassMethodName = "wikiext.hasChildren", useOnlyMethodName = true)
    public boolean pageHasChildren(String pageName) throws TracMethodExecutionException;


    

}
