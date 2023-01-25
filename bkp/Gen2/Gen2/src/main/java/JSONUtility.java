
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JSONUtility {
    private static final Logger logger = LoggerFactory.getLogger(JSONUtility.class);

    public static boolean isJSON(String json) {
        boolean retval = true;
        ObjectMapper mapperObj = new ObjectMapper();
        try {
            mapperObj.readValue(json, HashMap.class);
            mapperObj.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
                    .enable(SerializationFeature.INDENT_OUTPUT).disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        } catch (Exception e) {
            e.printStackTrace();
            retval = false;
        }
        return retval;
    }

    public static HashMap<String, Object> jsonToMap(String json) throws Exception {
        HashMap<String, Object> retMap = new HashMap<String, Object>();
        if (json != null) {
            retMap = toMap(json);
        }
        return retMap;
    }

    public static HashMap<String, Object> toMap(String value) throws Exception {
        HashMap<String, Object> map;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        try {
            map = objectMapper.readValue(value, HashMap.class);
        } catch (Exception e) {
            throw new Exception(UtilityCommon.FAILED_CONVERT_JSON + MiscUtility.formatString(value, true), e);
        }
        return map;
    }

    public static ArrayList<Object> toList(String array) throws Exception {
        ArrayList<Object> list;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            list = objectMapper.readValue(array, ArrayList.class);
        } catch (Exception e) {
            throw new Exception(UtilityCommon.FAILED_CONVERT_JSON + MiscUtility.formatString(array, true), e);
        }
        return list;
    }

    public static String object2JsonString(Object object) throws Exception {
        if (object instanceof String) {
            return object != null ? quote((String) object) : null;
        } else if (object instanceof Map<?, ?>) {
            return map2JsonString((Map<String, Object>) object);
        } else if (object instanceof List<?>) {
            return list2JsonString((List<Object>) object);
        } else {
            return object == null ? "null" : object.toString();
        }
    }

    public static String quote(String string) {
        if (string == null || string.length() == 0) {
            return "\"\"";
        }
        char c = 0;
        int i;
        int len = string.length();
        StringBuilder sb = new StringBuilder(len + 4);
        String t;
        sb.append('"');
        for (i = 0; i < len; i += 1) {
            c = string.charAt(i);
            switch (c) {
                case '\\':
                case '"':
                    sb.append('\\');
                    sb.append(c);
                    break;
                case '/':
                    //      if(b=='<'){
                    sb.append('\\');
                    //              }
                    sb.append(c);
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                default:
                    if (c < ' ') {
                        t = "000" + Integer.toHexString(c);
                        sb.append("\\u" + t.substring(t.length() - 4));
                    } else {
                        sb.append(c);
                    }
            }
        }
        sb.append('"');
        return sb.toString();
    }

    public static String quoteQuote(Object object) {
        return object.toString().replaceAll("\"", "\\\\\"");
    }

    public static String map2JsonString(Map<String, Object> map) throws Exception {
        ObjectMapper mapperObj = new ObjectMapper();
        return mapperObj.writeValueAsString(map);
    }

    public static String list2JsonString(List<Object> list) throws Exception {
        ObjectMapper mapperObj = new ObjectMapper();
        return mapperObj.writeValueAsString(list);
    }

    public static HashMap<String, Object> loadJSONFile(String filePath) {
        HashMap<String, Object> jsonMap = null;
        try {
            String jsonConfig = FileUtils.readFileToString(new File(filePath), Charset.defaultCharset());
            jsonMap = jsonToMap(jsonConfig);
        } catch (Exception e) {
            logger.error("failed to load json file", e);
        }
        return jsonMap;
    }
}