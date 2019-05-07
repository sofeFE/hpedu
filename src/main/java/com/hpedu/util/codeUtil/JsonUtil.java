package com.hpedu.util.codeUtil;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;

//import org.codehaus.jackson.JsonFactory;
//import org.codehaus.jackson.JsonGenerator;



import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Json 转换工具
 *
 */
public class JsonUtil {

	private static Logger logger = BaseUtil.getLogger(JsonUtil.class);

	  private static ObjectMapper mapper;
	  
	  /**
	   * 取出Mapper做进一步的设置或使用其他序列化API.
	   */
	  public ObjectMapper getMapper() {
	    return mapper;
	  }

	  public JsonUtil() {
	    this(null);
	  }

	  public JsonUtil(Include include) {
	    mapper = new ObjectMapper();

	    // 设置输出时包含属性的风格
	    if (include != null) {
	      mapper.setSerializationInclusion(include);
	    }

	    // 设置输入时忽略在JSON字符串中存在, 但Java对象实际没有的属性
	    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	  }
	  
	  /**
	   * Object可以是POJO, 也可以是Collection或数组.
	   * 如果对象为Null, 返回"null".
	   * 如果集合为空集合, 返回"[]".
	   */
	  public String toJson(Object object) {

	    try {
	      return mapper.writeValueAsString(object);
	    } catch (IOException e) {
	      logger.warn("write to json string error:" + object, e);
	      return null;
	    }
	  }

	  /**
	   * 反序列化POJO或简单Collection如List<String>.
	   * 
	   * 如果JSON字符串为Null或"null"字符串, 返回Null.
	   * 如果JSON字符串为"[]", 返回空集合.
	   * 
	   * 如需反序列化复杂Collection如List<MyBean>, 请使用fromJson(String, Class<T> CollectionType, Class<T> clazz)
	   * 调用方式：fromJson("{\"ttt\":\"ttt\",\"ttt\":\"ttt\"}", Map.class);
	   */
	  public <T> T fromJson(String jsonString, Class<T> clazz) {
		jsonString = StringUtil.clearNull(jsonString);
		if (jsonString.equals("")) {
	      return null;
	    }

	    try {
	      return mapper.readValue(jsonString, clazz);
	    } catch (IOException e) {
	      logger.warn("parse json string error:" + jsonString, e);
	      return null;
	    }
	  }
	  
	/**
	* 反序列化复杂Collection如List<Bean>, 先使用函数createCollectionType构造类型, 然后调用本函数.
	* 调用方式：fromJson("{\"ttt\":\"ttt\",\"ttt\":\"ttt\"}", ArrayList.class, User.class);
	*/
	@SuppressWarnings("unchecked")
	public <T, S> T fromJson(String jsonString, Class<T> CollectionType, Class<S> clazz) {
		jsonString = StringUtil.clearNull(jsonString);
		if (jsonString.equals("")) {
			return null;
		}
	
		try {
			JavaType jt = JsonUtil.mapper.getTypeFactory().constructParametricType(CollectionType, clazz);   
			//jackson可以方便地实现对象和json字串的转换，但是如果json字串的键是大写字母开头的话，默认情况下会出错。所以我写成小写做测试验证，果然是有这个问题
			return (T) JsonUtil.mapper.readValue(jsonString, jt);
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String ObjToJson(Object object) {
		JsonUtil ju = new JsonUtil();
		return ju.toJson(object);
	}
	
	public static <T, S> T fromJsonToObj(String jsonString, Class<T> CollectionType, Class<S> clazz) {
		JsonUtil ju = new JsonUtil();
		return ju.fromJson(jsonString, CollectionType, clazz);
	}
	
	public static <T> T fromJsonToObj(String jsonString, Class<T> clazz) {
		JsonUtil ju = new JsonUtil();
		return ju.fromJson(jsonString, clazz);
	}

   /* public static String toJsonString(Object obj) throws IOException {

        StringWriter writer = new StringWriter();
        JsonGenerator gen = new JsonFactory().createJsonGenerator(writer);
        mapper.writeValue(writer, gen);
        gen.close();
        String json = writer.toString();
        writer.close();
        return json;

    }*/

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map readJson2Map(String json) {
        Map<String, String> maps = null;
        try {
            maps = mapper.readValue(json, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return maps;
    }
}
