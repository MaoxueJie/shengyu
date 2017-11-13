package com.shengyu.photopay.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.jfbank.jfprop.bean.AESPropConfig;
import com.jfbank.jfprop.secret.TextSecretInterface;
import com.jfbank.jfprop.secret.impl.DefaultAESTextSecret;
import com.jfbank.jfprop.spring.EncryptPropertyPlaceholderConfigurer;
import com.jfbank.jfprop.util.AESUtils;
import com.jfbank.jfprop.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * 属性配置文件属性值处理
 * Created by huangyiwei on 16/4/1.
 */
public class Config extends EncryptPropertyPlaceholderConfigurer
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private AESPropConfig aesPropConfig = new AESPropConfig();

    private static Map<String, Object> ctxPropertiesMap; 
    
    
    @Override  
    protected void processProperties(ConfigurableListableBeanFactory beanFactory,  
            Properties props)throws BeansException {  
  
        super.processProperties(beanFactory, props);  
        //load properties to ctxPropertiesMap  
        ctxPropertiesMap = new HashMap<String, Object>();  
        for (Object key : props.keySet()) {  
            String keyStr = key.toString();  
            String value = props.getProperty(keyStr);  
            ctxPropertiesMap.put(keyStr, value);  
        }  
    }  
  
    //static method for accessing context properties  
    public static Object getContextProperty(String name) {  
        return ctxPropertiesMap.get(name);  
    } 
    
    //static method for accessing context properties  
    public static Object getContextProperty(String name,Object defaultValue) {
    	Object value = ctxPropertiesMap.get(name);  
    	if (value!=null)
    		return value;
    	return defaultValue;
    } 
    
    @Override
    protected TextSecretInterface getTextSecretInterface() {
        String aesKeyValue = FileUtils.readFile(aesPropConfig.getAesKeyFilePath()).trim();
        return new DefaultAESTextSecret(aesKeyValue);
    }

    @Override
    protected AESPropConfig getAesPropConfig() {
        return this.aesPropConfig;
    }
}
