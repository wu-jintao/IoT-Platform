package com.iotplatform.backend.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ Created by liwenqiang on 2017/5/9 0009.
 * @ Description:
 */
public class StringUtil {
    /** 判断字符串是否为空*/
    public static Boolean isEmpty(String str){
        if(null!=str && str.trim().length()>0 && !"null".equals(str)){
            return false;
        }
        return true;
    }
    /** 将unicode码转为字符串*/
    public static String Unicode2String(String unicode){
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(unicode);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            unicode = unicode.replace(matcher.group(1), ch+"" );
        }
        return unicode;
    }

    /*@Test
    public void test(){
        System.out.println(isEmpty(" null"));
    }

    @Test
    public void testUnicode(){
        String str = "{\n" +
                "  \"dForm\": {\n" +
                "    \"title\": \"\\u8bbe\\u5907\\u0031\",\n" +
                "    \"dev_type\": \"sb1\",\n" +
                "    \"mac\": \"string\",\n" +
                "    \"description\": \"\\u6d4b\\u8bd5\\u8bbe\\u5907\\u0075\\u006e\\u0069\\u0063\\u006f\\u0064\\u0065\\u0020\\u8f6c\\u4e2d\\u6587\"\n" +
                "  },\n" +
                "  \"dsFormList\": [\n" +
                "    {\n" +
                "      \"title\": \"\\u901a\\u9053\",\n" +
                "      \"dataType\": 0,\n" +
                "      \"direct\": 0,\n" +
                "      \"unit\": \"\\u6444\\u6c0f\\u5ea6\",\n" +
                "      \"unitsymbol\": \"℃\"\n" +
                "    }\n," +
                "    { \"title\": \"\\u6e29\\u5ea6\"," +
                "      \"dataType\": 0," +
                "      \"direct\": 0," +
                "      \"unit\": \"\\u767e\\u5206\\u6bd4\"," +
                "      \"unitsymbol\": \"℃\"" +
                "      }" +
                "  ]\n" +
                "}";
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch+"" );
        }
        System.out.println(str);
    }*/
}
