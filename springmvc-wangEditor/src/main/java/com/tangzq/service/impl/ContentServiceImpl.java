package com.tangzq.service.impl;

import com.tangzq.service.ContentService;
import com.tangzq.tools.TemplateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 模版封装类
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Value("${appname}")
    private String configAppName;

    @Autowired
    private TemplateManager templateManager;

    public String getAppName() {
        return configAppName;
    }

    /**
     * 使用模版技术定制网页内容
     * @param content
     * @return
     */
    public String combineContent(String content) {
        return templateManager.parseTemplate(content);
    }
}
