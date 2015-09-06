package com.tangzq.common;

import com.google.common.collect.Maps;
import com.tangzq.utils.PropertiesLoader;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 全局工具类
 */
public class Global {

    /**
     * 属性文件加载对象
     */
    private static PropertiesLoader loader = new PropertiesLoader("config.properties");

    /**
     * 保存全局属性值
     */
    private static Map<String, String> map = Maps.newHashMap();


    /**
     * 获取配置
     * @see ${fns:getConfig('adminPath')}
     */
    public static String getConfig(String key) {
        String value = map.get(key);
        if (value == null){
            value = loader.getProperty(key);
            map.put(key, value != null ? value : StringUtils.EMPTY);
        }
        return value;
    }

}
