package com.example.demo.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Jackson的工具
 */
public class JacksonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JacksonUtils.class);

	/**
	 * 页面传至后台时，json数据在request的参数名称
	 */
	public final static String JSON_ATTRIBUTE = "json";
	public final static String JSON_ATTRIBUTE1 = "json1";
	public final static String JSON_ATTRIBUTE2 = "json2";
	public final static String JSON_ATTRIBUTE3 = "json3";
	public final static String JSON_ATTRIBUTE4 = "json4";

	public static ObjectMapper mapper;

    private JacksonUtils() {
    }

    static {
        mapper = new ObjectMapper();
        // 美化输出
  		//mapper.enable(SerializationFeature.INDENT_OUTPUT);
  		// 设置时间格式
  		//mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 设置输出时包含属性的风格
        //mapper.setSerializationInclusion(Include.NON_EMPTY);
        //mapper.setSerializationInclusion(Include.NON_DEFAULT);
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }
    
	/**
	 * 将json string反序列化成非集合对象
	 *
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T jsonToBean(String json, Class<T> clazz) {
		try {
			return mapper.readValue(json, clazz);
		} catch (IOException e) {
			logger.error("将json字符串：{}转换成{}时发生异常。", json, String.valueOf(clazz), e);
		}
		return null;
	}

	/**
	 * 将json array反序列化为list对象
	 *
	 * @param json
	 * @param clazz 泛型类型
	 * @return
	 */
	public static <T> List<T> jsonToList(String json, Class<T> clazz) {
		JavaType javaType = getCollectionType(ArrayList.class, clazz);
		try {
			return mapper.readValue(json, javaType);
		} catch (IOException e) {
			logger.error("将json数组:{}转换成List<{}>时发生异常。", json, String.valueOf(clazz), e);
		}
		return null;
	}

	/**
	 * 将json array反序列化为array对象
	 *
	 * @param json
	 * @param clazz 数组类型
	 * @return
	 */
	public static <T> T[] jsonToArray(String json, Class<T[]> clazz) {
		try {
			return mapper.readValue(json, clazz);
		} catch (IOException e) {
			logger.error("将json数组: {} 转换成{}数组对象时发生异常。", json, String.valueOf(clazz), e);
		}
		return null;
	}

	/**
	 * 将json array反序列化为map对象
	 *
	 * @param json
	 * @param clazz value类型
	 * @return
	 */
	public static <T> Map<String, T> jsonToMap(String json, Class<T> clazz) {
		JavaType javaType = getCollectionType(Map.class, String.class, clazz);
		try {
			return mapper.readValue(json, javaType);
		} catch (IOException e) {
			logger.error("将json字符串: {} 转换成Map<String,{}>时发生异常。", json, String.valueOf(clazz), e);
		}
		return null;
	}

	/**
	 * 将对象序列化为json字符串
	 *
	 * @param obj
	 * @return
	 */
	public static String beanToJson(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			logger.error("将对象：{} 转换成json时发生异常", obj, e);
		}
		return null;
	}

	/**
	 * 将json字符串转换为jsonNode对象
	 *
	 * @param json
	 * @return
	 */
	public static JsonNode jsonToNode(String json) {
		try {
			return mapper.readTree(json);
		} catch (IOException e) {
			logger.error("将json字符串：{} 转换成jsonNode发生异常。", json, e);
		}
		return null;
	}

	/**
	 * 将jsonNode字符串转换为JavaBean对象
	 *
	 * @param node
	 * @return
	 */
	public static <T> T nodeToBean(JsonNode node, Class<T> clazz) {
		try {
			return mapper.treeToValue(node, clazz);
		} catch (IOException e) {
			logger.error("JsonNode：{}转换成{}时发生异常。", node, String.valueOf(clazz), e);
		}
		return null;
	}

	/**
	 * 获取collection带泛型的对象
	 *
	 * @param collectionClass
	 * @param elementClasses  不定参，按顺序输入泛型类型
	 * @return
	 */
	public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}

    /**
     * <p>转换方法，转换为字符串，并返回</p>
     *
     */
    public static String Convert(Object object) {
        if (object == null) {
            return null;
        }
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("转换对象异常", e);
        }
        return null;
    }

    /**
     * <p>转换json为对象</p>
     *
     */
    public static <T> T Convert(String json, Class<T> classz) {
        if (json == null) {
            return null;
        }
        try {
            return mapper.readValue(json, classz);
        } catch (IOException e) {
            logger.error("转换对象异常", e);
        }
        return null;
    }

    /**
     * <p>转换json为复杂对象javaType类型</p>
     *
     */
    public static <T> T Convert(String json, JavaType javaType) {
        if (json == null) {
            return null;
        }
        try {
            return mapper.readValue(json, javaType);
        } catch (IOException e) {
            logger.error("转换对象异常", e);
        }
        return null;
    }

    /**
     * <p>构造javaType类型</p>
     *
     */
    public static JavaType createJavaType(Class<? extends Collection> collectionClass, Class<?> elementClass) {
        return mapper.getTypeFactory().constructCollectionType(collectionClass, elementClass);
    }

    /**
     * <p>构造javaType类型</p>
     *
     */
    public static JavaType createJavaType(Class<? extends Map> mapClass, Class<?> keyClass, Class<?> valueClass) {
        return mapper.getTypeFactory().constructMapType(mapClass, keyClass, valueClass);
    }

    /**
     * <p>类型构造器工厂</p>
     *
     */
    public static TypeFactory getTypeFactory() {
        return mapper.getTypeFactory();
    }
}
