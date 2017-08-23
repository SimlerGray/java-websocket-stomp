package com.example.Utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtils {

    /**
     * json转化为bean
     *
     * @param json
     * @param beanClass
     * @param <T>
     * @return
     */
    public static <T> T json2Bean(String json,Class<T> beanClass){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            return objectMapper.readValue(json,beanClass);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    /**
     * bean转化为json
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public static String beanToJson(Object bean) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(bean);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    /**
     * bean转化为json
     *
     * @param reqParam
     * @return
     * @throws Exception
     */
    public static String beanToJson(List<NameValuePair> reqParam) {
        try{
            String json =  "{";
            for (NameValuePair nameValuePair : reqParam) {
                String con = "\"%s\":\"%s\",";
                con = String.format(con, nameValuePair.getName(), nameValuePair.getValue());
                json += con;
            }

            if (json.length() > 1)
                json = json.substring(0, json.length() - 1) + "}";

            return json;
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }


    /**
     * to jsonNode
     *
     * @param json
     * @return
     */
    public static JsonNode toJsonNode(String  json) {
        JsonNode rootNode = null;
        try{
            if(StringUtils.isNotBlank(json)) {
                ObjectMapper objectMapper = new ObjectMapper();
                rootNode = objectMapper.readValue(json, JsonNode.class);
            }
        }
        catch (JsonParseException ex){
            System.out.println("格式不正确："+json);
        }
        catch (Exception ex){
            System.out.println("toJsonNode：执行异常"+json);
        }
        return rootNode;
    }

    /**
     * page类型转Json格式
     *
     * @param page
     * @return
     */
    public static JsonNode pageToJson(Page page){

        JsonNode jsonNode = null;

        try{
            if(page != null){
                WebResponse response = page.getWebResponse();
                String json = response.getContentAsString();

                jsonNode = toJsonNode(json);
            }
        }
        catch (Exception ex){
            System.out.println("pageToJson：执行异常" + ex.toString());
        }

        return jsonNode;
    }

    /**
     * 根据Key值获取Json的Value值
     *
     * @param json
     * @param key
     * @return
     */
    public static String getJsonValue(String json, String key) {
        String retValue = "";
        try{

            if(StringUtils.isNotBlank(json)){
                JsonNode rootNode = toJsonNode(json);
                retValue = getJsonValue(rootNode, key);
            }
        }
        catch (Exception ex){
            System.out.println("获取value值异常: Key:" +key +"    json:"+ json);
        }

        return retValue;
    }

    /**
     * 根据Key值获取Json的Value值
     *
     * @param jsonNode
     * @param key
     * @return
     */
    public static String getJsonValue(JsonNode jsonNode, String key) {

        String retValue = "";
        try{

            if(jsonNode != null){
                retValue = jsonNode.has(key) //是否含有该Key值
                        ?jsonNode.get(key).toString().replaceAll("\"","") //含有Key 判断是否是字符串
                        : "";//不含有Key
                if("null".equals(retValue)){
                    retValue = "";
                }
            }

        }catch (Exception ex){
            System.out.println("获取value值异常: Key:" +key);
        }

        return retValue;
    }

    public static String mapToJson(Object o){
        Gson gson = new Gson();
        return gson.toJson(o);
    }

    public static Map<String,String> parseJsonWithGson(String jsonData) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject object = parser.parse(jsonData).getAsJsonObject();
        Map<String,Object> result = gson.fromJson(object, Map.class);
        Map<String,String> map = new HashMap<>();
        for (Map.Entry<String,Object> entry:result.entrySet()){
            map.put(entry.getKey(),mapToJson(entry.getValue()));
        }
        return map;
    }

}
