package com.wankun.textsplit;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

public class TestLogback {

	private final static Logger logger = LoggerFactory.getLogger(TestLogback.class);
//	static {
//		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
//		JoranConfigurator configurator = new JoranConfigurator();
//		configurator.setContext(lc);
//		lc.reset();
//		try {
//			configurator.doConfigure("conf/logback.xml");
//		} catch (JoranException e) {
//			System.out.println("加载logback配置文件失败！");
//			e.printStackTrace();
//		}
//	}
	
	@Test
	public void testLogDebug() {
		logger.debug("doing my job by debug");
	}

	@Test
	public void testLogInfo() {
		logger.info("doing my job by info");
	}
}
