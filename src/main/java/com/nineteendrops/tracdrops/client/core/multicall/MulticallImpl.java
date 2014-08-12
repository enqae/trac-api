package com.nineteendrops.tracdrops.client.core.multicall;

import com.nineteendrops.tracdrops.client.core.*;
import com.nineteendrops.tracdrops.client.core.decoders.DecodingErrorException;
import com.nineteendrops.tracdrops.client.core.decoders.ReturnDecoder;
import com.nineteendrops.tracdrops.client.core.TracMethodExecutionException;
import com.nineteendrops.tracdrops.client.core.properties.TracProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 25-ago-2009
 * Time: 23:48:02
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
public class MulticallImpl extends TracClientObject implements Multicall{

    private Log log = LogFactory.getLog(MulticallImpl.class);

    private ArrayList<CallElement> callElements= new ArrayList<CallElement>();

    public MulticallImpl(TracInvocationObjectFactory tracInvocationObjectFactory) {
        super(tracInvocationObjectFactory);
    }

    private void setMulticall(CallElement callElement){
        callElements.add(callElement);
    }

    private ArrayList<CallElement> getMulticalls(){
        return callElements;
    }


    public void registerCall(Class invokingClass,
                             String tracMethodName,
                             Object[] parameters,
                             Class returnDecoder, ArrayList keptParametersForDecoder){

        Vector callParameters = new Vector();
        if(parameters != null && parameters.length != 0){
            callParameters.addAll(Arrays.asList(parameters));
        }

        CallElement callElement = new CallElement(invokingClass,
                                                  tracMethodName,
                                                  callParameters,
                                                  returnDecoder, keptParametersForDecoder);
        if(log.isDebugEnabled()){
            logCallElement(callElement);
        }

        setMulticall(callElement);
    }

    public ArrayList launchMulticall(TracProperties tracProperties) throws MulticallInvalidStateException {

        return makeMulticall(tracProperties);
    }

    private ArrayList makeMulticall(TracProperties tracProperties) {

        ArrayList<CallElement> registeredCallElements = getMulticalls();
        if(registeredCallElements == null || registeredCallElements.size() == 0){
            //throw new MulticallInvalidStateException(MessageUtils.getMessage("core.multicall.no.callelements"));
            MessageUtils.registerDebugLog(log, "core.multicall.no.callelements");

            return null;
        }

        Multicall multicall = (Multicall) getTracInvocationObject(Multicall.class);

        if(log.isDebugEnabled()){
            log.debug(MessageUtils.getMessage("core.multicall.preparing.multicall"));
            log.debug(MessageUtils.getMessage("symbol.line.separator"));
        }

        Vector tracCalls = new Vector();
        for(CallElement callElement : registeredCallElements){
            Hashtable callToTrac = new Hashtable();
            callToTrac.put("methodName", callElement.getTracMethodName());
            callToTrac.put("params", callElement.getParameters());
            tracCalls.add(callToTrac);

            if(log.isDebugEnabled()){

                logCallElement(callElement);
            }
        }

        Vector results = null;
        try {
            results = multicall.internalMulticall(tracCalls);
        } catch (TracException e) {
            // There was an error in the invocation
            if(isFailureInMethodExecution(e, tracProperties)){
                dumpMulticallStack(registeredCallElements);
                throw new TracMethodExecutionException(MessageUtils.registerErrorLog(log, "core.multicall.failure.method.invocation"), e);
            } else {
                throw e;
            }
        }

        ArrayList resultsFinal = new ArrayList();

        try {
            int resultOrder = 0;
            for(CallElement call: registeredCallElements){
                ReturnDecoder returnDecoder = (ReturnDecoder)call.getReturnDecoder().newInstance();
                Object resultDecoded = returnDecoder.decode(((Object[])results.get(resultOrder++))[0],
                                                             tracProperties,
                                                             call.getKeptParametersForDecoder());
                resultsFinal.add(resultDecoded);
            }
        } catch (InstantiationException e) {
            throw new DecodingErrorException(MessageUtils.registerErrorLog(log, "core.multicall.error.decoding.return.value", e.getMessage()), e);
        } catch (IllegalAccessException e) {
            throw new DecodingErrorException(MessageUtils.registerErrorLog(log, "core.multicall.error.decoding.return.value", e.getMessage()), e);
        }

        return resultsFinal;
    }

    private void logCallElement(CallElement callElement) {
        log.debug(MessageUtils.getMessage("core.multicall.callelement.dump"));
        log.debug(callElement.toString());
    }

    public Vector internalMulticall(Vector calls) {

        Multicall multicall = (Multicall) getTracInvocationObject(Multicall.class);

        return multicall.internalMulticall(calls);
    }

    /** Finds out which was the cause of error
     *
     * @param e the original exception launched from the invocation factory
     * @param tracProperties repository for configuration properties for the library
     * @return true if the exception was supposed to be originated on the remote execution
     *
     * @see com.nineteendrops.tracdrops.client.core.TracInvocationObjectFactoryImpl
     */
    private boolean isFailureInMethodExecution(TracException e, TracProperties tracProperties){

        String methodInvocationFailurePattern = tracProperties.getStringProperty(TracProperties.TRAC_INVOCATION_METHOD_FAILURE_PATTERN);
        String messageException = e.getCause().getMessage();

        return messageException != null && messageException.contains(methodInvocationFailurePattern);
    }

    /** Logs the list of Calls queued for remote execution
     *
     * @param registeredCallElements list of Call queued for execution
     */
    private void dumpMulticallStack(ArrayList<CallElement> registeredCallElements){
        MessageUtils.registerErrorLog(log, "core.multicall.failure.method.invocation");
        for(CallElement callElement : registeredCallElements){
            logCallElement(callElement);
        }
        MessageUtils.registerErrorLog(log, "symbol.line.separator");
    }
}
