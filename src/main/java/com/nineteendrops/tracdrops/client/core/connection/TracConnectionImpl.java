package com.nineteendrops.tracdrops.client.core.connection;

import com.nineteendrops.tracdrops.client.core.MessageUtils;
import com.nineteendrops.tracdrops.client.core.TracException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 13-sep-2009
 * Time: 16:17:39
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
public class TracConnectionImpl implements TracConnection{

    private Log log = LogFactory.getLog(TracConnectionImpl.class);

    private TracConnectionConfig tracConnectionConfig = null;

    private XmlRpcClient xmlRpcClient = null;

    public TracConnectionImpl(TracConnectionConfig tracConnectionConfig) {
        this.tracConnectionConfig = tracConnectionConfig;
    }

    public void connect() {

        if(tracConnectionConfig == null){
            throw new TracException(MessageUtils.getMessage("core.connection.configuration.not.initialized"));
        }

        String url = tracConnectionConfig.getUrl();
        String user = tracConnectionConfig.getUser();
        String password = tracConnectionConfig.getPassword();

        if(url == null
            || user == null
            || password == null
            || url.trim().equals("")
            || user.trim().equals("")
            || password.trim().equals("")){
            throw new IllegalArgumentException(MessageUtils.getMessage("core.connection.configuration.parameters.not.initialized"));
        }

        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setBasicUserName(user.trim());
        config.setBasicPassword(password.trim());

        config.setEnabledForExceptions(true);

        try {

            config.setServerURL(new URL(url.trim()));

        } catch (MalformedURLException e) {
            throw new TracException(MessageUtils.getMessage("core.connection.configuration.malformed.url", url), e);
        }

        XmlRpcClient xmlRpcClient = new XmlRpcClient();
        xmlRpcClient.setConfig(config);
        if(tracConnectionConfig.getXmlRpcTransportFactory() != null){
            xmlRpcClient.setTransportFactory(tracConnectionConfig.getXmlRpcTransportFactory());
        }

        this.xmlRpcClient = xmlRpcClient;

    }

    public XmlRpcClient getXmlRpcClient() {
        return xmlRpcClient;
    }
}
