package com.wankun.textsplit;

import java.io.File;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

/**
 * 当logback.xml不在根目录下时处理方式<br>
 * 通过代码加载target目录下配置的方式：main和test均测试通过，但是路径会写死，不利于程序部署。<br>
 * 过maven-surefire-plugin插件，自动去寻找编译好的logback文件：main不可以，test类通过mvn
 * test可以，在eclipse中不行<br>
 * 
 * <pre>
 * LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
 * JoranConfigurator configurator = new JoranConfigurator();
 * configurator.setContext(lc);
 * lc.reset();
 * try {
 * 	configurator.doConfigure(&quot;target/conf/logback.xml&quot;);
 * } catch (JoranException e) {
 * 	System.out.println(&quot;加载logback配置文件失败！&quot;);
 * 	e.printStackTrace();
 * }
 * </pre>
 */
public class TestLogback {

	private final static Logger logger = LoggerFactory.getLogger(TestLogback.class);

	static {
		logger.info("" + new File("target/conf/logback.xml").exists());
	}

	@Test
	public void testLogDebug() {
		logger.debug("doing my job by debug");
	}

	@Test
	public void testLogInfo() {
		logger.info("doing my job by info");
	}
}
