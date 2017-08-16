/*
 * Copyright 2010 by IPS. Floor 3,Universal Industrial Building, 
 * Tian Yaoqiao Road 1178,Shanghai, P.R. China�?200300. All rights reserved.
 *
 * This software is the confidential and proprietary information of IPS
 * ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with IPS.
 */
package com.example.Utils;


import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * StringUtils
 *
 */
public class StringUtils {

    public static final String EMPTY = "";

    /**
     * 判断字符串是否不为空串
     *
     * @param str
     * @return
     */
    public static boolean isNotNULL(String str) {
        return str == null ? false : true;
    }


    /**
     * 判断字符串是否为空串(null or "")
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return ((str == null) || (str.length() == 0));
    }



    /**
     * 判断字符串是否为空白串
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        int strLen;
        if ((str == null) || ((strLen = str.length()) == 0))
            return true;
        for (int i = 0; i < strLen; ++i) {
            if (!(Character.isWhitespace(str.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否不为空白串
     *
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        return (!(isBlank(str)));
    }

    /**
     * @Description:判断字符串在长度里
     * @param @param str
     * @param @return
     * @throws
     */
    public static boolean isLeng(String str, int leng) {
        if(isBlank(str)) {
            return (0 == leng);
        }

        return (length(trim(str)) <= leng);
    }
    /**
     * @Description:字符串实际长度
     * @param @param str
     * @param @return 
     * @throws
     */
    public static int length(String str) {
    	
    	if(isBlank(str)) {
    		return 0;
    	}
    	
    	byte[] buf = str.getBytes();
    	int len = str.length();
		
		int count = 0;
		int i = 0;
		for (i = len - 1; i >= 0; i--) {
			if (buf[i] < 0) {
				count++;
			} else {
				break;
			}
		}
		
		return (len + count);
    }
    
    /**
     * @Description:判断字符串在长度里
     * @param @param str
     * @param @param begLen
     * @param @param endLen
     * @param @return 
     * @throws
     */
    public static boolean isLeng(String str, int begLen, int endLen) {
    	if(isBlank(str)) {
    		return (0 == endLen);
    	}
    	
    	if (begLen < 0) {
			 return false;
	    }
	     
	    if ((endLen - begLen) < 0) {
	    	 return false;
	    }
    	
	     str = trim(str);
    	return ((length(str) >= begLen) && (length(str) <= endLen));
    }

    /**
     * trim字符串
     *
     * @param str
     * @return
     */
    public static String trim(String str) {
        return ((str == null) ? null : str.trim());
    }

	/**
	 * 截断字符
	 * 
	 * @param str
	 * @param beginIndex
	 * @param endIndex
	 * @return
	 */
	public static String truncate(String str, int beginIndex, int endIndex) {
		
		 if(isEmpty(str)) {
			 return str;
		 }
		 if (beginIndex < 0) {
			 return str;
	     }
	     if (endIndex > str.length()) {
	    	 return str;
	     }
	     
	     if ((endIndex - beginIndex) < 0) {
	    	 return str;
	     }
		
		return str.substring(beginIndex, endIndex);
	}
	
	/**
	 * @Description:集合转换String
	 * @param @param list
	 * @param @return 
	 * @throws
	 */
	public static String listConverString(List<String> list) {
		StringBuffer sb = new StringBuffer();
		if(list == null || list.isEmpty()) {
			return "";
		}
		for (String string : list) {
			if(sb.length() > 0 && StringUtils.isNotBlank(string)) {
				sb.append(",");
			}
			if(StringUtils.isNotBlank(string)) {
				sb.append(string);
			}
		}
		return sb.toString();
	}

    /**
     * 源串是否包含目标字符
     *
     * @param source
     * @param destination
     * @return
     */
    public static boolean contains(String source,String destination){
        boolean flag=false;
        if(isBlank(source)||isBlank(destination)){
            return flag;
        }
        if(source.contains(destination)){
            flag=true;
        }
        return flag;
    }
    
    /**
     * @Description:替换文本
     * @param @param html
     * @param @return 
     * @throws
     */
    public static String replaceHtml(String html) {
    	if(StringUtils.isBlank(html)) {
    		return "";
    	}
    	
        html = html.replaceAll("//\\]\\]\\>", "");
        html = html.replaceAll("//\\<\\!\\[CDATA\\[", "");
        html = html.replaceAll("\\<script.*?\\>|\\</script\\>", "");

        if(StringUtils.isBlank(html)) {
    		return "";
    	}
        
        html = html.trim();
        return html;
    }

    /**
     * @Description:判断某个字符串为数字
     * @param @param html
     * @param @return
     * @throws
     * */
    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

    /**
     * @Description:判断某个字符串为数字或是浮点
     * @param @param html
     * @param @return
     * @throws
     * */
    public static boolean isFloat(String html) {

        if(StringUtils.isBlank(html)) {
            return false;
        }

        return html.matches("[0-9]+\\.?[0-9]*");
    }

    public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

    //base64字符串转化成图片
    public static boolean GenerateImage(String imgStr, String imgFilePath)
    {   //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try
        {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            //生成jpeg图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
