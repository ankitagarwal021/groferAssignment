package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created By: Ankit Agarwal
 **/

public class BaseClass {

    public static String baseurl;
    public static String groferLatUpper;
    public static String groferLatLower;
    public static String groferLongUpper;
    public static String groferLongLower;
    public static String mongoHostTND;
    public static String mongoPortTND;
    public static String percentageOfTodos;

    public static void initializeProperty() throws Exception{

        baseurl = getPropertyValue("url");
        groferLatUpper = getPropertyValue("groferLatUpper");
        groferLatLower = getPropertyValue("groferLatLower");
        groferLongUpper = getPropertyValue("groferLongUpper");
        groferLongLower = getPropertyValue("groferLongLower");
        mongoHostTND = getPropertyValue("mongoHostTND");
        mongoPortTND = getPropertyValue("mongoPortTND");
        percentageOfTodos = getPropertyValue("percentageOfTodos");
    }

    public static String dir = System.getProperty("user.dir");
    public static String propertyFileName = dir + "/src/main/java/config/envConfig.properties";

    public static String getPropertyValue(String property) throws Exception {
        Properties prop = new Properties();
        InputStream input = null;
        String requiredPropertyValue = null;
        try {
            input = new FileInputStream(propertyFileName);
            prop.load(input);
            requiredPropertyValue = prop.getProperty(property);
        } catch (Exception var9) {
            var9.printStackTrace();
        } finally {
            if (input != null) {
                input.close();
            }
        }
        return requiredPropertyValue;
    }

}
