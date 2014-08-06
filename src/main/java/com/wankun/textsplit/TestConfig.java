package com.wankun.textsplit;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

public class TestConfig {

	static {
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		JoranConfigurator configurator = new JoranConfigurator();
		configurator.setContext(lc);
		lc.reset();
		try {
			configurator.doConfigure("target/conf/logback.xml");
		} catch (JoranException e) {
			System.out.println("加载logback配置文件失败！");
			e.printStackTrace();
		}
	}

	private final static Logger logger = LoggerFactory.getLogger(TestConfig.class);
	
	public static void main(String[] args) {
		logger.info("doing my job by info");
	}
}
