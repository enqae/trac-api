package com.nineteendrops.tracdrops.client.core.multicall;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 25-ago-2009
 * Time: 20:36:02
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
public class CallElement {

    private String tracMethodName;

    private Vector parameters;

    private Class invokingClass;

    private Class returnDecoder;

    private ArrayList keptParametersForDecoder;

    public CallElement(Class invokingClass, String tracMethodName, Vector parameters, Class returnDecoder, ArrayList keptParametersForDecoder) {
        this.tracMethodName = tracMethodName;
        this.parameters = parameters;
        this.invokingClass = invokingClass;
        this.returnDecoder = returnDecoder;
        this.keptParametersForDecoder = keptParametersForDecoder;
    }

    public String getTracMethodName() {
        return tracMethodName;
    }

    public Vector getParameters() {
        return parameters;
    }

    public Class getInvokingClass() {
        return invokingClass;
    }

    public Class getReturnDecoder() {
        return returnDecoder;
    }

    public ArrayList getKeptParametersForDecoder() {
        return keptParametersForDecoder;
    }

    @Override
    public String toString() {
        return "CallElement{" +
                "tracMethodName='" + tracMethodName + '\'' +
                ", parameters=" + parameters +
                ", invokingClass=" + invokingClass +
                ", returnDecoder=" + returnDecoder +
                ", keptParametersForDecoder=" + keptParametersForDecoder +
                '}';
    }
}
