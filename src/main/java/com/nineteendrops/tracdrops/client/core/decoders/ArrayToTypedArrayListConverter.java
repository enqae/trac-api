package com.nineteendrops.tracdrops.client.core.decoders;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 31-ago-2009
 * Time: 20:52:34
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
public class ArrayToTypedArrayListConverter<T>{

    public ArrayList<T> convert(T[] array){

        ArrayList<T> typedArrayList = new ArrayList<T>();

        typedArrayList.addAll(Arrays.asList(array));

        return typedArrayList;

    }
}
