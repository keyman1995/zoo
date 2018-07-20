package com.zoo.util;

import org.apache.ibatis.io.Resources;

import java.io.FileOutputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropUtil {

    private static PropUtil propUtil = new PropUtil();
    private static String fileName = "application.properties";
    private static PropertiesLoader loader;
    private static Map<String, String> map;

    public PropUtil() {
    }

    public static PropUtil getInstance() {
        return propUtil;
    }

    public static String getConfig(String key) {
        String value = (String)map.get(key);
        if (value == null) {
            value = loader.getProperty(key);
            map.put(key, value != null ? value : "");
        }

        return value;
    }

    public static void modifyConfig(String key, String value) {
        try {
            Properties prop = getProperties();
            prop.setProperty(key, value);
            String path = PropUtil.class.getResource("/" + fileName).getPath();
            FileOutputStream outputFile = new FileOutputStream(path);
            prop.store(outputFile, "modify");
            outputFile.close();
            outputFile.flush();
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public static Properties getProperties() {
        Properties prop = new Properties();

        try {
            Reader reader = Resources.getResourceAsReader("/" + fileName);
            prop.load(reader);
            return prop;
        } catch (Exception var2) {
            return null;
        }
    }

    static {
        loader = new PropertiesLoader(new String[]{fileName});
        map = new HashMap();
    }

}
