import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MiscUtility {
    private static final Logger logger = LoggerFactory.getLogger(MiscUtility.class);

    public static String formatString(String input, boolean remove_newlines) {
        if (input != null && remove_newlines) {
            String output = input;
            String replacedString = output.replaceAll("[\n\r\t]", "");
            output = replacedString.replaceAll("( )+", " ");
            return output;
        }
        return input;
    }

    public static String convertExceptionToString(Exception exp) {
        if (exp != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            exp.printStackTrace();
            return sw.toString();
        }
        return null;
    }

    public static Object purifySingletonList(List<Map<String, Object>> objectList) {
        if (objectList != null) {
            if (objectList.size() == 1) {
                return objectList.get(0);
            }
        }
        return objectList;
    }

    public static HashMap<String, String> convertHashMapStringObject(HashMap<String, Object> inputMap) {
        HashMap<String, String> newMap = new HashMap<String, String>();
        if (inputMap != null) {
            for (Map.Entry<String, Object> entry : inputMap.entrySet()) {
                Object value = entry.getValue();
                if (value instanceof String) {
                    newMap.put(entry.getKey(), value.toString());
                } else {
                    newMap.put(entry.getKey(), null);
                }
            }
            return newMap;
        }
        return null;
    }

    public static String convertToCSVString(List<String> names) {
        StringBuilder namesStr = new StringBuilder();
        for (String name : names) {
            namesStr = namesStr.length() > 0 ? namesStr.append(",").append(name) : namesStr.append(name);
        }
        return namesStr.toString();
    }

    public static String formatObjectAsString(Object input, boolean remove_newlines) {
        if (input == null) {
            return null;
        } else if (input instanceof String) {
            return formatString(input.toString(), remove_newlines);
        } else {
            try {
                return JSONUtility.object2JsonString(input);
            } catch (Exception e) {
                logger.error("Failed to format String", e);
            }
        }
        return null;
    }

    public static String getCurrentHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            logger.error("Failed to get hostName", e);
        }
        return null;
    }

    public static java.sql.Date convertToSqlDate(Object utilDate) {
        if (utilDate instanceof Date) {
            return new java.sql.Date(((Date) utilDate).getTime());
        } else {
            return null;
        }
    }

    public static <T> T safeCast(Object o, Class<T> clazz) {
        return clazz != null && clazz.isInstance(o) ? clazz.cast(o) : null;
    }
}
