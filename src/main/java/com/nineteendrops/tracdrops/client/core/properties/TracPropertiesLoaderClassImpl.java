package com.nineteendrops.tracdrops.client.core.properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Properties;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 13-sep-2009
 * Time: 17:28:31
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
public class TracPropertiesLoaderClassImpl implements TracPropertiesLoader{

    private Log log = LogFactory.getLog(TracPropertiesLoaderClassImpl.class);

    public TracProperties load() {

        TracProperties tracConfigProperties = new TracProperties();

        Properties properties = new Properties();
        properties.setProperty(TracProperties.ATTACHMENT_UPLOAD_MAX_SIZE, "256 * 1024");
        properties.setProperty(TracProperties.ATTACHMENT_DOWNLOAD_PATH_BASE, System.getProperty("user.home"));
        properties.setProperty(TracProperties.TRAC_INVOCATION_METHOD_FAILURE_PATTERN, "cannot marshal <class 'xmlrpclib.Fault'> objects");

        tracConfigProperties.populate(properties);

        return tracConfigProperties;
    }
}
