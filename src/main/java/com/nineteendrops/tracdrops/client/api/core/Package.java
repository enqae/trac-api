package com.nineteendrops.tracdrops.client.api.core;

import java.util.ArrayList;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 25-ago-2009
 * Time: 20:02:52
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
public class Package {

    private String name;
    private ArrayList<Method> methods = new ArrayList<Method>();

    public Package(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addMethod(Method method){
        methods.add(method);
    }

    public ArrayList<Method> getMethods() {
        return methods;
    }

    @Override
    public String toString() {
        return "Package{" +
                "name='" + name + '\'' +
                ", methods=" + methods +
                '}';
    }
}
