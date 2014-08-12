package com.nineteendrops.tracdrops.client.core.decoders;

import com.nineteendrops.tracdrops.client.core.MessageUtils;
import com.nineteendrops.tracdrops.client.core.TracException;
import com.nineteendrops.tracdrops.client.core.properties.TracProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 08-sep-2009
 * Time: 21:22:46
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
public class ByteArrayToFileDecoder implements ReturnDecoder{

    private Log log = LogFactory.getLog(ByteArrayToFileDecoder.class);

    public Object decode(Object result, TracProperties tracProperties, ArrayList keptParametersForDecoder) {

        String fileName = (String)keptParametersForDecoder.get(0);
        String pathBase = null;
        if(keptParametersForDecoder.size() == 2){
            pathBase = (String)keptParametersForDecoder.get(1);
        } else {
            pathBase = tracProperties.getStringProperty(TracProperties.ATTACHMENT_DOWNLOAD_PATH_BASE);
        }


        if(fileName == null || fileName.trim().equals("")){
            throw new TracException(MessageUtils.getMessage("core.parameter.not.found", "filename", this.getClass().getName()));
        }
        
        // remove from filename any "/"
        fileName = fileName.replace("/", "_");


        if(pathBase == null || pathBase.trim().equals("")){
            throw new TracException(MessageUtils.getMessage("core.parameter.not.found", "pathBase", this.getClass().getName()));
        }

        pathBase = pathBase.trim();
        if(!pathBase.endsWith("/")){
            pathBase = pathBase + "/";
        }

        String fullFileName = pathBase + fileName.trim();
        File file = new File(fullFileName);
        if(file.exists()){
            fullFileName = fullFileName + "_" + new Date().getTime();
            file = new File(fullFileName);
        }
        
        if(!new File(pathBase).canWrite()){
            throw new TracException(MessageUtils.getMessage("core.unable.to.write.to.file", pathBase));
        }

        try {
            byte[] fileFromTrac = (byte[])result;
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(fileFromTrac);
            fos.close();
        } catch (IOException e) {
            throw new DecodingErrorException(MessageUtils.registerErrorLog(log, "core.unexpected.exception", e.getMessage()), e);
        }


        return fullFileName;
    }
}
