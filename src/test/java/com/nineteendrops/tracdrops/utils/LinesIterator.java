package com.nineteendrops.tracdrops.utils;

import java.util.Iterator;
import java.io.*;
import java.lang.reflect.Method;


/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 26-sep-2009
 * Time: 19:06:57
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
public class LinesIterator implements Iterator<Object[]> {

    private BufferedReader reader;
    private String lastLine;
    private LineConverter lineConverter;

    public LinesIterator(Class clazz, Method method, String fileName, LineConverter lineConverter) {

        String packageName = method.getDeclaringClass().getPackage().getName();
        String fileDirectory = packageName.replace(".", "/");
        String filePath = fileDirectory + "/" + fileName;
        
        this.lineConverter = lineConverter;

        InputStream is = clazz.getClassLoader().getResourceAsStream(filePath);

        reader = new BufferedReader(new InputStreamReader(is));
    }

    public boolean hasNext() {

        try {
            lastLine = getValidLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lastLine != null;
    }

    public Object[] next() {

        String nextLine;
        if(lastLine != null){
            nextLine = lastLine;
        } else {
            nextLine = getNextLine();
        }

        lastLine = null;

        return parseLine(nextLine);
    }

    private String getNextLine(){

        try {
            if(lastLine == null){
                lastLine = getValidLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return lastLine;
    }

    private String getValidLine() throws IOException{
        String validLine = reader.readLine();
        while(validLine != null &&
                validLine.trim().startsWith("#") &&
                validLine.trim().length()>0){
            validLine = reader.readLine();
        }

        return validLine;
    }

    private Object[] parseLine(String line){
        return lineConverter.convert(line);
    }

    public void remove() {
        throw new UnsupportedOperationException("'remove' from this iterator is not supported");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        reader.close();
    }
}
