package com.nineteendrops.tracdrops.client.core;

import com.nineteendrops.tracdrops.client.core.annotations.TracClass;
import com.nineteendrops.tracdrops.client.core.annotations.TracClassMethod;
import com.nineteendrops.tracdrops.client.core.properties.TracProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 25-ago-2009
 * Time: 23:37:38
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
public class Utils {

    private static Log log = LogFactory.getLog(Utils.class);

    public static String findTracClassName(Class clazz){

        TracClass annotationsForTracClass = (TracClass)clazz.getAnnotation(TracClass.class);

        return annotationsForTracClass == null ? null : annotationsForTracClass.tracClass();

    }

    public static TracClassMethod getTracClassMethodAnnotation(Method method){
        TracClassMethod annotationsForTracClassMethod = (TracClassMethod)method.getAnnotation(TracClassMethod.class);

        return annotationsForTracClassMethod;
    }


    public static String buildTracMethodNameInvocation(String tracClassName, TracClassMethod tracClassMethodMetadata){

        String tracClassMethodName = tracClassMethodMetadata.tracClassMethodName();
        if(tracClassMethodName == null){
            throw new TracException(MessageUtils.registerErrorLog(log, "core.no.trac.methodname.found", tracClassName));
        }

        String methodName = null;
        if(tracClassMethodMetadata.useOnlyMethodName()){
            methodName = tracClassMethodName;
        } else {
            methodName = tracClassName + "." + tracClassMethodName;
        }

        return methodName;
    }

    public static Vector fillVectorFromArrayList(ArrayList<String> arrayList){

        Vector vector = new Vector();
        if(arrayList != null){
            for(String s: arrayList){
                vector.add(s);
            }
        }

        return vector;
    }

    public static byte[] getBinaryData(String fullFileName, TracProperties tracProperties) {

        File file = new File(fullFileName);
        if(!file.exists()){
            throw new TracException(MessageUtils.getFullFileNameNotFoundMessage(fullFileName));
        }

        if(!file.canRead()){
            throw new TracException(MessageUtils.getFullFileNameNotFoundMessage(fullFileName));
        }

        if(!file.isFile()){
            throw new TracException(MessageUtils.getFullFileNameNotFoundMessage(fullFileName));
        }

        int maxUploadSize = tracProperties.getIntegerProperty(TracProperties.ATTACHMENT_UPLOAD_MAX_SIZE);
        if(file.length() > maxUploadSize){
            throw new TracException(MessageUtils.getMessage("core.file.size.exceeded", fullFileName, String.valueOf(maxUploadSize)));
        }

        byte[] binaryData;
        try {
            binaryData = new byte[(int)file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(binaryData);
            fis.close();
        } catch (IOException e) {
            throw new TracException(MessageUtils.registerErrorLog(log, "core.unexpected.exception", e.getMessage()), e);
        }
        return binaryData;
    }

}
