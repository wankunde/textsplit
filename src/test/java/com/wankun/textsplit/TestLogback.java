package com.wankun.textsplit;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLogback {

	private final static Logger logger = LoggerFactory.getLogger(TestLogback.class);

	@Test
	public void testLogDebug() {
		logger.debug("doing my job by debug");
	}

	@Test
	public void testLogInfo() {
		logger.info("doing my job by info");
	}
}
