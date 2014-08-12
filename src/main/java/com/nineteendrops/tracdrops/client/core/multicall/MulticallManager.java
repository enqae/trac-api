package com.nineteendrops.tracdrops.client.core.multicall;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;

import com.nineteendrops.tracdrops.client.core.TracInvocationObjectFactory;
import com.nineteendrops.tracdrops.client.core.MessageUtils;
import com.nineteendrops.tracdrops.client.core.properties.TracProperties;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 13-sep-2009
 * Time: 18:06:38
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
public class MulticallManager {

    private static Log log = LogFactory.getLog(MulticallManager.class);

    // Multicall functionality
    private static ThreadLocal<Multicall> multicallContext = new ThreadLocal<Multicall>();

    public static void multicallStart(TracInvocationObjectFactory tracInvocationObjectFactory) {

        if(multicallActive()){
            throw new MulticallInvalidStateException(MessageUtils.getMessage("core.multicall.already.active"));
        }

        Multicall multicall = new MulticallImpl(tracInvocationObjectFactory);
        multicallContext.set(multicall);
    }

    public static void multicallStop() {
        multicallContext.remove();
    }

    public static boolean multicallActive(){
        Multicall multicall = multicallContext.get();
        return multicall != null;
    }

    public static void multicallRegisterCall(Class invokingClass,
                                      String tracMethodName,
                                      Object[] parameters,
                                      Class returnDecoder, ArrayList keptParametersForDecoder){
        Multicall multicall = multicallContext.get();
        multicall.registerCall(invokingClass, tracMethodName, parameters, returnDecoder, keptParametersForDecoder);
    }

    public static ArrayList launchMulticall(TracProperties tracProperties) throws MulticallInvalidStateException{

        ArrayList results = null;

        try {
            Multicall multicall = multicallContext.get();
            if(multicall == null){
                MessageUtils.registerDebugLog(log, "core.multicall.no.context.active");
                //throw new MulticallInvalidStateException(MessageUtils.getMessage("core.multicall.no.context.active"));
            } else {
                results = multicall.launchMulticall(tracProperties);
            }
        } finally {
            // After launch ALWAYS clean context
            multicallStop();
        }

        return results;
    }

}
