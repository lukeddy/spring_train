package com.tangzq;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

public class HelloWorldService extends Service<HelloWorldConfiguration> {


    //服务入口

    /**
     * 如何运行：java -jar <jar包> server <config_file>
     *
     *注意：
     * 1、在打包的时候一定要把依赖库也打进去
     * 2、配置文件的名字一定要和Service类中设置的一样
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        if (args == null || args.length != 2) {
            args = new String[] { "server", "E:\\dev\\work\\java\\dropwizard-train\\src\\main\\java\\com\\tangzq\\university-foo-local.yml" };
        }
        new HelloWorldService().run(args);
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        //指定配置文件的名字
        //bootstrap.setName("helloWorld");
    }

    @Override
    public void run(HelloWorldConfiguration helloWorldConfiguration, Environment environment) throws Exception {
        final String template = helloWorldConfiguration.getTemplate();
        final String defaultName = helloWorldConfiguration.getDefaultName();
        environment.addResource(new HelloWorldResource(template,defaultName));
        environment.addHealthCheck(new TemplateHealthCheck(template));
    }
}
