package com.nineteendrops.tracdrops.client.core;

import com.nineteendrops.tracdrops.client.core.annotations.TracClassMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.common.TypeConverter;
import org.apache.xmlrpc.common.TypeConverterFactory;
import org.apache.xmlrpc.common.TypeConverterFactoryImpl;
import org.apache.xmlrpc.common.XmlRpcInvocationException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 12-sep-2009
 * Time: 13:08:28
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
public class TracInvocationObjectFactoryImpl implements TracInvocationObjectFactory{

    private Log log = LogFactory.getLog(TracInvocationObjectFactoryImpl.class);

    private XmlRpcClient client = null;
    private TypeConverterFactory typeConverterFactory = null;

    public TracInvocationObjectFactoryImpl(XmlRpcClient xmlRpcClient, TypeConverterFactory typeConverterFactory) {
        this.client = xmlRpcClient;
        this.typeConverterFactory = typeConverterFactory;
    }

    public TracInvocationObjectFactoryImpl(XmlRpcClient xmlRpcClient) {
        this(xmlRpcClient, new TypeConverterFactoryImpl());
    }

    public XmlRpcClient getClient() {
        return client;
    }

    public Object newInstance(Class pClass) {
        return newInstance(Thread.currentThread().getContextClassLoader(), pClass);
    }

    public Object newInstance(ClassLoader pClassLoader, Class pClass) {
        return newInstance(pClassLoader, pClass, pClass.getName());
    }

    public Object newInstance(ClassLoader classLoader, final Class aClass, final String remoteName) {

        return Proxy.newProxyInstance(classLoader, new Class[]{aClass}, new InvocationHandler(){

            public Object invoke(Object pProxy, Method pMethod, Object[] pArgs) throws Throwable {

                String tracClassName = Utils.findTracClassName(aClass);
                if(tracClassName == null){
                    throw new TracException(MessageUtils.registerErrorLog(log, "core.no.trac.classname.found", aClass.getName()));
                }

                TracClassMethod tracClassMethodMetadata = Utils.getTracClassMethodAnnotation(pMethod);

                String methodName = Utils.buildTracMethodNameInvocation(tracClassName, tracClassMethodMetadata);

                Object result = null;
                try {

                    result = getClient().execute(methodName, pArgs);

                } catch (XmlRpcInvocationException e) {
                    throw new TracException(MessageUtils.registerErrorLog(log, "core.invocation.factory.invocation.exception", e.getMessage()), e);

                } catch(XmlRpcException e){
                    throw new TracException(MessageUtils.registerErrorLog(log, "core.invocation.factory.xmlrpc.exception", e.getMessage()), e);
                }
                catch (Throwable e){
                    throw new TracException(MessageUtils.registerErrorLog(log, "core.invocation.factory.unknown.exception", e.getMessage()), e);
                }

                Class returnType = tracClassMethodMetadata.tracReturnType();
                if(returnType == Object.class){
                    returnType = pMethod.getReturnType();
                }

                TypeConverter typeConverter = typeConverterFactory.getTypeConverter(returnType);
                return typeConverter.convert(result);

             }
         });

    }
}
