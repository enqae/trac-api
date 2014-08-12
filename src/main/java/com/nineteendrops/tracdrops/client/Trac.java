package com.nineteendrops.tracdrops.client;

import com.nineteendrops.tracdrops.client.api.core.CoreManager;
import com.nineteendrops.tracdrops.client.api.search.SearchManager;
import com.nineteendrops.tracdrops.client.api.ticket.component.ComponentManager;
import com.nineteendrops.tracdrops.client.api.ticket.milestone.MilestoneManager;
import com.nineteendrops.tracdrops.client.api.ticket.priority.PriorityManager;
import com.nineteendrops.tracdrops.client.api.ticket.resolution.ResolutionManager;
import com.nineteendrops.tracdrops.client.api.ticket.severity.SeverityManager;
import com.nineteendrops.tracdrops.client.api.ticket.status.StatusManager;
import com.nineteendrops.tracdrops.client.api.ticket.ticket.TicketManager;
import com.nineteendrops.tracdrops.client.api.ticket.type.TypeManager;
import com.nineteendrops.tracdrops.client.api.ticket.version.VersionManager;
import com.nineteendrops.tracdrops.client.api.wiki.WikiManager;
import com.nineteendrops.tracdrops.client.core.*;
import com.nineteendrops.tracdrops.client.core.connection.TracConnection;
import com.nineteendrops.tracdrops.client.core.connection.TracConnectionConfig;
import com.nineteendrops.tracdrops.client.core.connection.TracConnectionImpl;
import com.nineteendrops.tracdrops.client.core.multicall.MulticallInvalidStateException;
import com.nineteendrops.tracdrops.client.core.multicall.MulticallManager;
import com.nineteendrops.tracdrops.client.core.properties.TracProperties;
import com.nineteendrops.tracdrops.client.core.properties.TracPropertiesLoader;
import com.nineteendrops.tracdrops.client.core.properties.TracPropertiesLoaderFileImpl;
import org.apache.xmlrpc.client.XmlRpcClient;

import java.lang.reflect.Constructor;
import java.util.ArrayList;


/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 23-ago-2009
 * Time: 18:38:09
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
public class Trac {

    private static final Class tracInvocationObjectFactoryClass = TracInvocationObjectFactoryImpl.class;
    private TracInvocationObjectFactory tracInvocationObjectFactory = null;

    private static final Class tracClientObjectFactoryClass = TracClientObjectFactoryImpl.class;
    private TracClientObjectFactory tracClientObjectFactory = null;

    private TracConnection tracConnection = null;

    private TracPropertiesLoader tracPropertiesLoader = null;
    private TracProperties tracProperties = null;

    public Trac(TracConnection tracConnection, TracPropertiesLoader tracPropertiesLoader) {

        if(tracConnection == null){
            throw new TracException(MessageUtils.getMessage("core.trac.null.trac.connection"));
        }

        this.tracConnection = tracConnection;

        if(tracPropertiesLoader == null){
            this.tracPropertiesLoader = new TracPropertiesLoaderFileImpl();
        } else {
            this.tracPropertiesLoader = tracPropertiesLoader;
        }
    }

    public Trac(String url, String user, String password){

        this(new TracConnectionImpl(new TracConnectionConfig(url, user, password)), null);
    }

    public void initialize() {

        initializeProperties();

        tracConnection.connect();

        initializeFactories();
    }

    private void initializeProperties(){

        tracProperties = tracPropertiesLoader.load();
    }

    private void initializeFactories(){

        try {
            Constructor c = tracInvocationObjectFactoryClass.getConstructor(XmlRpcClient.class);
            tracInvocationObjectFactory = (TracInvocationObjectFactory)c.newInstance(tracConnection.getXmlRpcClient());

        } catch (Exception e) {
            throw new TracException(MessageUtils.getMessage("core.trac.invalid.invocation.object.factory"), e);
        }

        try {
            Constructor c = tracClientObjectFactoryClass.getConstructor(TracProperties.class, TracInvocationObjectFactory.class);
            tracClientObjectFactory = (TracClientObjectFactory)c.newInstance(tracProperties, tracInvocationObjectFactory);

        } catch (Exception e) {
            throw new TracException(MessageUtils.getMessage("core.trac.invalid.client.object.factory"), e);
        }
    }

    public void setTracInvocationObjectFactory(TracInvocationObjectFactory tracInvocationObjectFactory) {
        this.tracInvocationObjectFactory = tracInvocationObjectFactory;
    }

    public void setTracClientObjectFactory(TracClientObjectFactory tracClientObjectFactory) {
        this.tracClientObjectFactory = tracClientObjectFactory;
    }


    // Get access to functionality
    private Object getObjectManager(Class typeClientObject){
        return tracClientObjectFactory.newInstance(typeClientObject);
    }

    public CoreManager getCoreManager(){
        return (CoreManager) getObjectManager(CoreManager.class);
    }

    public SearchManager getSearchManager(){
        return (SearchManager) getObjectManager(SearchManager.class);
    }

    public TicketManager getTicketManager(){
        return (TicketManager) getObjectManager(TicketManager.class);
    }

    public ComponentManager getComponentManager(){
        return (ComponentManager) getObjectManager(ComponentManager.class);
    }

    public VersionManager getVersionManager(){
        return (VersionManager) getObjectManager(VersionManager.class);
    }

    public MilestoneManager getMilestoneManager(){
        return (MilestoneManager) getObjectManager(MilestoneManager.class);
    }

    public TypeManager getTypeManager(){
        return (TypeManager) getObjectManager(TypeManager.class);
    }

    public StatusManager getStatusManager(){
        return (StatusManager) getObjectManager(StatusManager.class);
    }

    public ResolutionManager getResolutionManager(){
        return (ResolutionManager) getObjectManager(ResolutionManager.class);
    }

    public PriorityManager getPriorityManager(){
        return (PriorityManager) getObjectManager(PriorityManager.class);
    }

    public SeverityManager getSeverityManager(){
        return (SeverityManager) getObjectManager(SeverityManager.class);
    }

    public WikiManager getWikiManager(){
        return (WikiManager) getObjectManager(WikiManager.class);
    }


    // Multicall functionality
    public void multicallStart() {
        MulticallManager.multicallStart(tracInvocationObjectFactory);
    }

    public void multicallStop() {
        MulticallManager.multicallStop();
    }

    public boolean multicallActive(){

        return MulticallManager.multicallActive();
    }

    public ArrayList launchMulticall() throws MulticallInvalidStateException{
        return MulticallManager.launchMulticall(tracProperties);
    }


}
