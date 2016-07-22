package com.neu.demo.main;

import java.io.File;

import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * 系统启动
 * @author zhubx
 *
 */
public class ServerMain {

	private static final Logger LOG = LoggerFactory.getLogger(ServerMain.class);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 初始化日志配置
        initLog();
        // 加载spring配置文件,初始化各个模块
        initConfig();
	}
	
	/**
     * 初始化日志配置文件
     */
    private static void initLog() {
        // 从当前程序根目录读取日志配置文件log4j.xml
        DOMConfigurator.configureAndWatch(System.getProperty("user.dir") + File.separator + "conf"
                + File.separator + "log4j.xml");
        LOG.info("log init finished.");
    }
    
    /**
     * 初始化spring配置文件
     */
    @SuppressWarnings("resource")
	private static void initConfig() {
        new FileSystemXmlApplicationContext("classpath:spring/main.xml");
        LOG.info("module init finished.");
    }

}
