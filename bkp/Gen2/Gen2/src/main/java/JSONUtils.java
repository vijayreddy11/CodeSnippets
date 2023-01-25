import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.postgresql.util.PGobject;

import java.sql.Timestamp;
import java.util.*;


public class JSONUtils {


    /**
     * Checks to see if a String can be parsed into a JSONObject.
     *
     * @param json - The String to parse.
     * @return true if there is no exception generated when the string is parsed.
     */
    public static boolean isJSON(String json) {
        boolean retval = true;
        try {
            JSONObject jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            retval = false;
        }
        return retval;
    }

    public static LinkedHashMap<String, Object> jsonToLinkedMap(String json) throws JSONException {
        HashMap<String, Object> retMap = new HashMap<String, Object>();
        if (json != null) {
            JSONObject jsonObject = new JSONObject(json);
            retMap = toMap(jsonObject);
        }
        return new LinkedHashMap<>(retMap);
    }

    public static HashMap<String, Object> jsonToMap(String json) throws JSONException {
        HashMap<String, Object> retMap = new HashMap<String, Object>();
        if (json != null) {
            JSONObject jsonObject = new JSONObject(json);
            retMap = toMap(jsonObject);
        }
        return retMap;
    }

    public static HashMap<String, Object> jsonToMap(JSONObject json) throws JSONException {
        HashMap<String, Object> retMap = new HashMap<String, Object>();

        if (json != JSONObject.NULL) {
            retMap = toMap(json);
        }
        return retMap;
    }

    public static List<Object> jsonToList(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        return toList(jsonArray);
    }


    public static HashMap<String, Object> toMap(JSONObject object) throws JSONException {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Iterator<String> keysItr = object.keys();
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }

            if (value == JSONObject.NULL)
                map.put(key, null);
            else
                map.put(key, value);
        }
        return map;
    }

    public static ArrayList<Object> toList(JSONArray array) throws JSONException {
        ArrayList<Object> list = new ArrayList<Object>();
        for (int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);

        }
        return list;
    }

    public static Object jsonToObject(String jsonString) {
        if (jsonString != null) {
            Object retObj = null;
            try {
                retObj = new JSONObject(jsonString);

            } catch (Exception e1) {
                try {
                    retObj = new JSONArray(jsonString);

                } catch (Exception e2) {

                }
            }
            if (retObj != null) {
                if (retObj instanceof JSONArray) {
                    retObj = toList((JSONArray) retObj);
                } else if (retObj instanceof JSONObject) {
                    retObj = toMap((JSONObject) retObj);
                }
                return retObj;
            }
        }
        return null;
    }

    /**
     * Convert an object tree consisting of Map, List, and primitive types to a
     * JSON string.
     *
     * @param object "Data Structure" in the form of nested Map, List, and
     *               primitive types
     * @return JSON String
     */
    public static String object2JsonString(Object object) {
        return object2JsonString(object, 0, 0);
    }

    public static String object2JsonString(Object object, int indentSize, int indentLevel) {
        if (object instanceof String) {
            return quote((String) object);
        } else if (object instanceof Document) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(object);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return null;
            }
        } else if (object instanceof Map<?, ?>) {
            return map2JsonString((Map<String, Object>) object, indentSize, indentLevel);
        } else if (object instanceof List<?>) {
            return list2JsonString((List<Object>) object, indentSize, indentLevel);
        } else if (object instanceof Timestamp || object instanceof Date) {
            return "\"" + quoteQuote(object) + "\"";
        } else if (object instanceof JSONObject) {
            return ((JSONObject) object).toString();
        } else if (object instanceof PGobject && ((PGobject) object).getType().equalsIgnoreCase("json")) {
            return object2JsonString(jsonToObject(object.toString()), indentSize, indentLevel);
        } else {
            return object == null ? null : object.toString();
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
                    //                if (b == '<') {
                    sb.append('\\');
                    //                }
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


    private static String quoteQuote(Object object) {
        return object.toString().replaceAll("\"", "\\\\\"");
    }

    /**
     * Convert a map to a JSON string.
     *
     * @param map Map of Map, List, or primitive types
     * @return String representation of map as JSON
     */
    public static String map2JsonString(Map<String, Object> map) {
        return map2JsonString(map, 0);
    }

    public static String map2JsonString(Map<String, Object> map, int indentSize) {
        return map2JsonString(map, indentSize, 0);
    }

    /**
     * Convert a map to a JSON string.
     *
     * @param map        Map of Map, List, or primitive types
     * @param indentSize size of indent to include in the output string, 0 implies no
     *                   indentation
     * @return String representation of map as JSON
     */
    public static String map2JsonString(Map<String, Object> map, int indentSize, int indentLevel) {
        StringBuilder string = new StringBuilder();
        if (map.size() == 0) {
            string.append("{}");
        } else {
            for (String key : map.keySet()) {
                if (string.length() == 0) {
                    string.append(indentSize == 0 ? "{ " : "{\n");
                    if (indentSize > 0) {
                        ++indentLevel;
                        indent(string, indentSize, indentLevel);
                    }
                } else {
                    string.append(indentSize == 0 ? ", " : ",\n");
                    indent(string, indentSize, indentLevel);
                }
                string.append(object2JsonString(key)).append(" : ").append(object2JsonString(map.get(key), indentSize, indentLevel));
            }
            string.append(indentSize == 0 ? " }" : "\n");
            if (indentSize > 0) {
                --indentLevel;
                indent(string, indentSize, indentLevel);
                string.append("}");
            }
        }
        return string.toString();
    }

    private static void indent(StringBuilder string, int indentSize, int indentLevel) {
        for (int j = 0; j < indentLevel; j++) {
            for (int i = 0; i < indentSize; i++) {
                string.append(" ");
            }
        }
    }

    /**
     * Convert a list to a JSON string.
     *
     * @param list of Map, List, or primitive type elements
     * @return String representation of list as JSON
     */
    public static String list2JsonString(List<Object> list) {
        return list2JsonString(list, 0);
    }

    public static String list2JsonString(List<Object> list, int indentSize) {
        return list2JsonString(list, indentSize, 0);
    }

    public static String list2JsonString(List<Object> list, int indentSize, int indentLevel) {
        StringBuilder string = new StringBuilder();
        List<Object> notNullList = new ArrayList<>();
        for (Object value : list) {
            if (value != null && !(value.equals(JSONObject.NULL))) {
                notNullList.add(value);
            }
        }

        if (notNullList.size() == 0) {
            string.append("[]");
        } else {
            for (Object value : notNullList) {
                if (string.length() == 0) {
                    string.append(indentSize == 0 ? "[ " : "[\n");
                    if (indentSize > 0) {
                        ++indentLevel;
                        indent(string, indentSize, indentLevel);
                    }
                } else {
                    string.append(indentSize == 0 ? ", " : ",\n");
                    indent(string, indentSize, indentLevel);
                }
                string.append(object2JsonString(value, indentSize, indentLevel));
            }
            string.append(indentSize == 0 ? " ]" : "\n");
            if (indentSize > 0) {
                --indentLevel;
                indent(string, indentSize, indentLevel);
                string.append("]");
            }
        }
        return string.toString();
    }
}

