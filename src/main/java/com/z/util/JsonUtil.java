package com.z.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.z.bean.jsonres.ReturnCodeUtil;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.z.ImportTemplateFieldVo;
//import com.z.ImportTemplateVo;

public class JsonUtil {
    /**
     * 静态的json转换对象
     */
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 把对象转成json串
     *
     * @param obj 对象，可以是VO、List、HashMap等等
     * @return 返回生成的json值
     */
    public static String toStr(Object obj) {
        String json_str = "";
        try {
            json_str = objectMapper.writer().writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json_str;
    }

    /**
     * json转对象
     *
     * @param jsonStr   json字符串
     * @param valueType 要转成的对象类型，采用泛型的方式
     * @param <T>       泛型参数
     * @return 转成的对象
     */
    public static <T> T toObject(String jsonStr, Class<T> valueType) {
        try {
            return objectMapper.readValue(jsonStr, valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json数据转换成pojo对象lis
     */
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            List<T> list = objectMapper.readValue(jsonData, javaType);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 把json转成map
     *
     * @param jsonStr json字符串
     * @return 转成的map对象
     */
    public static Map toMap(String jsonStr) {
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);

        Map map = new HashMap();
        try {
            map = objectMapper.readValue(jsonStr, Map.class); //json转换成map
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 反序列化生成对象
     *
     * @param collectionClass collectionClass
     * @param elementClasses  elementClasses
     * @return 集合类
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * 生成操作后的json串，{success:false,msgText:'删除失败'}
     *
     * @param b   是否
     * @param msg 提示消息
     * @return json字符串
     */
    public static String createOperaStr(boolean b, String msg) {
        String msgContext = ReturnCodeUtil.getMsg(msg);
        return "{\"success\":" + b + ",\"msgText\":\"" + msgContext + "\"}";
    }


    /**
     * 直接读取json串里面某个节点的值
     *
     * @param json    json字符串
     * @param nodeStr 节点名
     * @return json的节点
     */
    public static String getNode(String json, String nodeStr) {
        String str = "";
        try {
            str = objectMapper.readTree(json).get(nodeStr).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 返回json的jsonNode
     *
     * @param json json字符串
     * @return json的节点
     */
    public static JsonNode getNode(String json) {
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonNode;
    }

    /**
     * 通过反射将javaBean转换成带默认值的json对象
     */
    public static void toJosnStringWithDefaultValue(Class... javaBeans) {

        for (Class javaBean : javaBeans) {
            StringBuilder res = new StringBuilder();
            res.append("{");

            Field[] declaredFields = javaBean.getDeclaredFields();

            int i = declaredFields.length;
            for (Field field : declaredFields) {
                Class<?> aClass = field.getType();
                String name = field.getName();
                if (aClass.equals(Object.class)) {
                    res.append(String.format("\"%s\": {}", name));
                }
                if (aClass.equals(Integer.class)) {
                    res.append(String.format("\"%s\": 0", name));
                }
                if (aClass.equals(String.class)) {
                    res.append(String.format("\"%s\": \"\"", name));
                }
                if (aClass.equals(List.class)) {
                    res.append(String.format("\"%s\": []", name));
                }
                if (aClass.equals(Byte.class)) {
                    res.append(String.format("\"%s\": 0", name));
                }
                if (i-- > 1)
                    res.append(",");
            }
            res.append("}");
            String string = res.toString();
            System.out.println(string);
        }

    }

    public static void main(String[] args) {
    }
}
