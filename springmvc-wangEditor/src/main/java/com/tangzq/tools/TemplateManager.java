package com.tangzq.tools;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;


public class TemplateManager {

    public static final Logger logger = LoggerFactory.getLogger(TemplateManager.class);
    @Autowired
    @Qualifier("velocityEngine")
    private VelocityEngine velocityEngine;

    /**
     * 使用模版技术将内容进行整合，拼装一些定制的东西
     * @param content
     * @return
     */
    public String parseTemplate(String content){
        Map<String,Object> data=new HashMap<String,Object>();
        data.put("content", content);
        logger.info("原始内容:"+content);
        String newContent=VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/mytemplate.vm", "utf-8", data);
        logger.info("整合后的内容："+newContent);
        return newContent;
    }
}
