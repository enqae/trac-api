package com.nineteendrops.tracdrops.client.core.decoders;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 31-ago-2009
 * Time: 21:13:08
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
public class DecodingErrorException extends RuntimeException{
    public DecodingErrorException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
