package com.ReadEnjoyBack.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @Author:HB
 * @Description:  配置信息类
 * @Createdata:Created in  23:03  2018/5/26.
 */
public class PropertiesUtil {
    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
    private  static Properties pros;
    // 声明一个静态块  类加载时就执行 且只执行一次
    static {
        String fileName = "readenjoy.properties";
        pros = new Properties();
        try {
            pros.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName),"UTF-8"));
        } catch (IOException e) {
            logger.error("配置文件读取异常",e);
        }
    }
    ///根据key得到配置文件value
    public static String getProperty(String key){
        String value = pros.getProperty(key.trim());
        if (StringUtils.isBlank(value)){
            return null;
        }
        return value.trim();
    }
    ///根据key得到配置文件value(有默认值)
    public static String getProperty(String key,String defaultValue){
        String value = pros.getProperty(key.trim());
        if (StringUtils.isBlank(value)){
            return defaultValue;
        }
        return value.trim();
    }
}
