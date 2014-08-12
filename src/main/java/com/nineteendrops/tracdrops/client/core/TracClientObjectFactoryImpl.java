package com.nineteendrops.tracdrops.client.core;

import com.nineteendrops.tracdrops.client.core.encoders.ParameterEncoder;
import com.nineteendrops.tracdrops.client.core.annotations.TracClassMethod;
import com.nineteendrops.tracdrops.client.core.annotations.TracParameterEncoder;
import com.nineteendrops.tracdrops.client.core.annotations.TracParameterPolicy;
import com.nineteendrops.tracdrops.client.core.multicall.MultiParameter;
import com.nineteendrops.tracdrops.client.core.multicall.MulticallManager;
import com.nineteendrops.tracdrops.client.core.properties.TracProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 23-ago-2009
 * Time: 19:01:30
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
public class TracClientObjectFactoryImpl implements TracClientObjectFactory{

    private Log log = LogFactory.getLog(TracClientObjectFactoryImpl.class);

    private TracProperties tracProperties = null;

    private TracInvocationObjectFactory tracInvocationObjectFactory;

    public TracClientObjectFactoryImpl(TracProperties tracProperties, TracInvocationObjectFactory tracInvocationObjectFactory) {
        this.tracProperties = tracProperties;
        this.tracInvocationObjectFactory = tracInvocationObjectFactory;
    }

    public Object newInstance(Class pClass) {
        return newInstance(Thread.currentThread().getContextClassLoader(), pClass);
    }

    protected Object newInstance(ClassLoader pClassLoader, Class pClass) {
        return newInstance(pClassLoader, pClass, pClass.getName());
    }

    protected Object newInstance(ClassLoader classLoader, final Class aClass, final String remoteName) {

        return Proxy.newProxyInstance(classLoader, new Class[]{aClass}, new InvocationHandler() {

            public Object invoke(Object pProxy, Method pMethod, Object[] pArgs) throws Throwable {

                String tracClassName = Utils.findTracClassName(aClass);
                if (tracClassName == null) {
                    throw new TracException(MessageUtils.registerErrorLog(log, "core.no.trac.classname.found", aClass.getName()));
                }

                TracClassMethod tracClassMethodMetadata = Utils.getTracClassMethodAnnotation(pMethod);

                String tracClassMethodName = Utils.buildTracMethodNameInvocation(tracClassName, tracClassMethodMetadata);

                Class returnDecoder = tracClassMethodMetadata.returnDecoder();

                ArrayList encodedPARGS = new ArrayList();
                Annotation[][] parameterAnnotations = pMethod.getParameterAnnotations();

                int parameterOrder = 0;
                ArrayList keptParametersForDecoder = new ArrayList();
                for (Annotation[] annotations : parameterAnnotations) {

                    Object currentParameter = pArgs[parameterOrder];
                    boolean skipInvocationRegistration = false;
                    for (Annotation annotation : annotations) {
                        if (annotation instanceof TracParameterEncoder) {
                            Class encoder = ((TracParameterEncoder) annotation).encoder();
                            ParameterEncoder parameterEncoder = (ParameterEncoder) encoder.newInstance();
                            currentParameter = parameterEncoder.encode(tracProperties, currentParameter);
                        }
                        if (annotation instanceof TracParameterPolicy){
                            TracParameterPolicy tracParameterPolicy = (TracParameterPolicy)annotation;
                            if(tracParameterPolicy.keptForDecoder()){
                                keptParametersForDecoder.add(currentParameter);
                            }
                            if(tracParameterPolicy.removeFromInvocation()){
                                skipInvocationRegistration = true;
                            }
                        }
                    }
                    if(!skipInvocationRegistration){
                        if (currentParameter instanceof MultiParameter) {
                            MultiParameter multiParameter = (MultiParameter) currentParameter;
                            for(Object objectFromMultiparameter: multiParameter.getParameters()){
                                encodedPARGS.add(objectFromMultiparameter);
                            }

                        } else {
                            encodedPARGS.add(currentParameter);
                        }
                    }
                    parameterOrder++;
                }

                if (MulticallManager.multicallActive()) {

                    MulticallManager.multicallRegisterCall(aClass,
                                                           tracClassMethodName,
                                                           encodedPARGS.toArray(),
                                                           returnDecoder, keptParametersForDecoder);

                    if(log.isDebugEnabled()){
                        log.debug(MessageUtils.getMessage("core.invocation.multicall"));
                    }

                    return null;

                } else {

                    MulticallManager.multicallStart(tracInvocationObjectFactory);
                    MulticallManager.multicallRegisterCall(aClass,
                                                           tracClassMethodName, encodedPARGS.toArray(),
                                                           returnDecoder, keptParametersForDecoder);

                    ArrayList result = MulticallManager.launchMulticall(tracProperties);

                    if(log.isDebugEnabled()){
                        log.debug(MessageUtils.getMessage("core.invocation.singlecall"));
                    }

                    return result.get(0);

                }
            }
        });

    }


}
