package com.nineteendrops.tracdrops.client.api.core;

import java.util.ArrayList;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 25-ago-2009
 * Time: 19:54:22
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
public class Method {

    private String name;
    private String fullName;
    private String help;
    private ArrayList<String> signatures = new ArrayList<String>();

    public Method(String name, String fullName) {
        this.name = name;
        this.fullName = fullName;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public void addSignature(String signature){
        signatures.add(signature);
    }

    public ArrayList<String> getSignatures() {
        return signatures;
    }

    @Override
    public String toString() {
        return "Method{" +
                "name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", help='" + help + '\'' +
                ", signatures=" + signatures +
                '}';
    }
}
