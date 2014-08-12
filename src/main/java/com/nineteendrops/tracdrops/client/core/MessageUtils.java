package com.nineteendrops.tracdrops.client.core;

import org.apache.commons.logging.Log;

import java.util.ResourceBundle;
import java.text.MessageFormat;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 13-sep-2009
 * Time: 16:23:26
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
public class MessageUtils {

    public static String getMessage(String key){
        return getBundle().getString(key);
    }

    public static String getMessage(String key, String... parm){
        String keyValue = getBundle().getString(key);
        return MessageFormat.format(keyValue, parm);
    }

    private static ResourceBundle getBundle(){
        return ResourceBundle.getBundle("TRACdropsMessage");
    }

    static String getFullFileNameNotFoundMessage(String fullFileName){
        return getMessage("core.file.not.found", fullFileName);
    }

    public static String registerErrorLog(Log log, String key, String... parameters){
        String errorMessage = getMessage(key, parameters);
        log.error(errorMessage);

        return errorMessage;
    }

    public static String registerDebugLog(Log log, String key, String... parameters){
        String errorMessage = getMessage(key, parameters);
        log.debug(errorMessage);

        return errorMessage;
    }
}
