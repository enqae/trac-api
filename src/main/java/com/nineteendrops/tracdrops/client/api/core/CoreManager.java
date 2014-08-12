package com.nineteendrops.tracdrops.client.api.core;

import com.nineteendrops.tracdrops.client.api.core.decoders.ArrayToApiVersionDecoder;
import com.nineteendrops.tracdrops.client.core.annotations.TracClass;
import com.nineteendrops.tracdrops.client.core.annotations.TracClassMethod;
import com.nineteendrops.tracdrops.client.core.decoders.ArrayToStringArrayListDecoder;

import java.util.ArrayList;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 25-ago-2009
 * Time: 19:21:51
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
@TracClass(tracClass = "system")
public interface CoreManager {

    @TracClassMethod(tracClassMethodName = "listMethods",
                    returnDecoder = ArrayToStringArrayListDecoder.class)
    public ArrayList getMethods();

    @TracClassMethod(tracClassMethodName = "methodHelp")
    public String getMethodHelp(String method);

    @TracClassMethod(tracClassMethodName = "methodSignature",
                    returnDecoder = ArrayToStringArrayListDecoder.class)
    public ArrayList getMethodSignatures(String method);

    @TracClassMethod(tracClassMethodName = "getAPIVersion",
                    returnDecoder = ArrayToApiVersionDecoder.class)
    public ApiVersion getApiVersion();

}
