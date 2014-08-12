package com.nineteendrops.tracdrops.utils;

import java.lang.reflect.Method;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TestUtils {


    public static Object[][] getLines(Method method, String fileBaseName) {

        String methodName = method.getName().toLowerCase();
        String fileName = null;
        if (methodName.contains("ok")) {
            fileName = fileBaseName + "OK.txt";
        } else if (methodName.contains("failure")) {
            fileName = fileBaseName + "Failure.txt";
        } else {
            throw new RuntimeException("Non supported method to be provided with lines:" + method.getName());
        }

        String packageName = method.getDeclaringClass().getPackage().getName();
        String fileDirectory = packageName.replace(".", "/");
        String filePath = fileDirectory + "/" + fileName;

        return getLinesFromFile(method.getDeclaringClass(), filePath);

    }

    public static Object[][] getLinesFromFile(Class cls, String filePath) {

        Object[][] returnedData = null;
        InputStream is = null;

        try {

            is = cls.getClassLoader().getResourceAsStream(filePath);
            BufferedReader isr = new BufferedReader(new InputStreamReader(is));

            ArrayList<String> listOfPages = new ArrayList<String>();
            String line = isr.readLine();
            while (line != null) {
                listOfPages.add(line);
                line = isr.readLine();
            }

            // Convert
            returnedData = new Object[listOfPages.size()][];
            int index = 0;
            for (String page : listOfPages) {
                returnedData[index++] = new Object[]{page};
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
                // Ignore -- although is not elegant
            }
        }

        return returnedData;
    }
}