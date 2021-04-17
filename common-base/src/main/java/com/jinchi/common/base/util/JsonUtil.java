package com.jinchi.common.base.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class JsonUtil {
    private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    /**
     * 对象转换成Json 字符串
     *
     * @param obj 被转换的对象
     * @param <T> 对象类型
     * @return 返回json字符串。如果是
     */
    public static <T> String toJson(T obj) {
        if (obj instanceof ServletResponse) {
            return "{}";
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.registerModule(new ParameterNamesModule());
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
        String json;
        try {
            json = objectMapper.writeValueAsString(obj);
        } catch (Exception ex) {
            logger.warn(ex.getMessage());
            json = "";
        }
        return json;
    }

    /**
     * Json字符串转换为对象
     *
     * @param json json字符串
     * @param c    类型
     * @param <T>  转换的对象类型
     * @return 转换的对象
     */
    public static <T> T fromJson(String json, Class<T> c) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.registerModule(new ParameterNamesModule());
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
        try {
            return objectMapper.readValue(json, c);
        } catch (Throwable e) {
            logger.warn("json:{},class:{},解析失败{}", json, c.getName(), e.getMessage());
            return null;
        }
    }

    /**
     * Json字符串转换为对象.支持集合类型
     *
     * @param json json字符串
     * @param t    类型
     * @param <T>  转换的对象类型
     * @return 转换的对象
     */
    public static <T> T fromJson(String json, TypeReference<T> t) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.registerModule(new ParameterNamesModule());
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
        try {
            return objectMapper.readValue(json, t);
        } catch (Exception e) {
            logger.warn("json:{},class:{},解析失败{}", json, t.getType().getTypeName(), e.getMessage());
            return null;
        }
    }

    public static Object fromJsonToSortMap(String json) {
        Object value = null;
        try {
            if (StringUtils.isNoneBlank(json)) {
                value = JsonUtil.fromJson(json, TreeMap.class);
            }
            if (value == null) {
                value = JsonUtil.fromJson(json, ArrayList.class);
            }
        } catch (Exception ex) {
            return null;
        }
        return orderMap(value);
    }

    public static Object orderMap(Object srcObj) {
        if (srcObj == null) {
            return null;
        }
        if (srcObj instanceof List) {
            List srcList = (List) srcObj;
            List descList = new ArrayList();
            for (Object item : srcList) {
                descList.add(orderMap(item));
            }
            return descList;
        } else if (srcObj instanceof Map) {
            Map srcMap = (Map) srcObj;
            TreeMap map = new TreeMap(srcMap);
            for (Object key : map.keySet()) {
                map.put(key, orderMap(map.get(key)));
            }
            return map;
        } else {
            return srcObj;
        }
    }
}
