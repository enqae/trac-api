package com.nineteendrops.tracdrops.client.api.core;

import com.nineteendrops.tracdrops.client.core.multicall.MulticallInvalidStateException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 25-ago-2009
 * Time: 20:26:05
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
public class CoreUtil {

        public ArrayList<Package> getPackages() throws MulticallInvalidStateException {

        ArrayList<Package> packages = new ArrayList<Package>();

        Vector methodsTrac = null; //getMethods();

        Iterator methodsTracIterator = methodsTrac.iterator();
        String currentPackageName = "";
        Package currentPackage = null;
        while(methodsTracIterator.hasNext()){
            String methodTrac = (String) methodsTracIterator.next();
            String[] packageAndMethod = methodTrac.split("\\.");

            if(!currentPackageName.equals(packageAndMethod[0])){
                currentPackageName = packageAndMethod[0];
                currentPackage = new Package(currentPackageName);
                packages.add(currentPackage);
            }

            Method method = new Method(packageAndMethod[1], methodTrac);
            currentPackage.addMethod(method);

            //method.setHelp(getMethodHelp(method.getFullName()));

            Vector signatures = null; //getMethodSignatures(method.getFullName());
            Iterator signaturesIterator = signatures.iterator();
            while(signaturesIterator.hasNext()){
                method.addSignature((String)signaturesIterator.next());
            }
        }

        return packages;
    }
}
