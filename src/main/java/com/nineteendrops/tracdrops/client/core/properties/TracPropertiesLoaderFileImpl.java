package com.nineteendrops.tracdrops.client.core.properties;

import com.nineteendrops.tracdrops.client.core.TracException;
import com.nineteendrops.tracdrops.client.core.MessageUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
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
public class TracPropertiesLoaderFileImpl implements TracPropertiesLoader{

    private Log log = LogFactory.getLog(TracPropertiesLoaderFileImpl.class);

    public TracProperties load() {

        TracProperties tracConfigProperties = new TracProperties();

        Properties propertiesFromFile = new Properties();

        try {

            InputStream is =  this.getClass().getClassLoader().getResourceAsStream("TRACdropsConfig.properties");
            propertiesFromFile.load(is);

        } catch (IOException e) {
            throw new TracException(MessageUtils.getMessage("core.trac.config.file.not.found"), e);
        }

        tracConfigProperties.populate(propertiesFromFile);

        return tracConfigProperties;
    }
}