package com.nineteendrops.tracdrops.client.core.properties;

import com.nineteendrops.tracdrops.client.core.TracException;
import com.nineteendrops.tracdrops.client.core.MessageUtils;

import java.util.HashMap;
import java.util.Properties;

/**
 * Created www.19drops.com
 * User: 19drops
 * Date: 13-sep-2009
 * Time: 16:04:42
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
public class TracProperties {

    public static final String ATTACHMENT_UPLOAD_MAX_SIZE = "attachment.upload.max.size";
    public static final Integer ATTACHMENT_UPLOAD_MAX_SIZE_DEFAULT = 256 * 1024;

    public static final String ATTACHMENT_DOWNLOAD_PATH_BASE = "attachment.download.path.base";
    public static final String ATTACHMENT_DOWNLOAD_PATH_BASE_DEFAULT = "user.home";

    public static final String TRAC_INVOCATION_METHOD_FAILURE_PATTERN = "trac.invocation.method.failure.pattern";
    public static final String TRAC_INVOCATION_METHOD_FAILURE_PATTERN_DEFAULT = "cannot marshal <class 'xmlrpclib.Fault'> objects";

    private HashMap<String, Object> tracProperties = new HashMap<String, Object>();

    public String getStringProperty(String property){
        return (String) tracProperties.get(property);
    }

    public int getIntegerProperty(String property){
        return (Integer) tracProperties.get(property);
    }

    private void setProperty(String property, Object value){
        tracProperties.put(property, value);
    }

    public void populate(Properties properties){

        String value = null;

        try {
            value = properties.getProperty(ATTACHMENT_UPLOAD_MAX_SIZE);
            Integer attachmentUploadMaxSize = ATTACHMENT_UPLOAD_MAX_SIZE_DEFAULT;
            if(value != null && !value.trim().equals("")){
                attachmentUploadMaxSize = convertNumber(value);
            }
            setProperty(ATTACHMENT_UPLOAD_MAX_SIZE, attachmentUploadMaxSize);
        } catch (Exception e) {
            throw new TracException(MessageUtils.getMessage("core.trac.config.wrong.property.value", ATTACHMENT_UPLOAD_MAX_SIZE, value), e);
        }

        try {
            value = properties.getProperty(ATTACHMENT_DOWNLOAD_PATH_BASE);
            if(value == null || value.trim().equals("")){
                value = ATTACHMENT_DOWNLOAD_PATH_BASE_DEFAULT;
            }
            setProperty(ATTACHMENT_DOWNLOAD_PATH_BASE, convertPath(value));
        } catch (Exception e) {
            throw new TracException(MessageUtils.getMessage("core.trac.config.wrong.property.value", ATTACHMENT_DOWNLOAD_PATH_BASE, value), e);
        }

        try {
            value = properties.getProperty(TRAC_INVOCATION_METHOD_FAILURE_PATTERN);
            if(value == null || value.trim().equals("")){
                value = TRAC_INVOCATION_METHOD_FAILURE_PATTERN_DEFAULT;
            }
            setProperty(TRAC_INVOCATION_METHOD_FAILURE_PATTERN, normalizeString(value));
        } catch (Exception e) {
            throw new TracException(MessageUtils.getMessage("core.trac.config.wrong.property.value", TRAC_INVOCATION_METHOD_FAILURE_PATTERN, value), e);
        }

    }

    private Integer convertNumber(String expression){

        // naive
        String[] numbers = expression.split("\\*");
        if(numbers.length == 1){
            return new Integer(numbers[0]);
        } else {
            int value = 1;

            for(String number: numbers){
                value *= Integer.valueOf(number.trim());
            }

            return value;
        }
    }

    private String convertPath(String expression){

        String path;
        if(expression.trim().toLowerCase().equals("user.home")){
            path = System.getProperty("user.home");
        } else {
            path = expression.trim();
        }

        return path;
    }

    private String normalizeString(String expression){
        return expression.trim();
    }

}
