package com.wankun.textsplit;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * 使用Hamcrest增强JUnit的测试能力
 * 
 * @see Hamcrest框架提供了一些相对通俗并高效的方法来进行一些junit比较困难的测试
 * @see 比如比较数值大小、测试对象类型、测试数组元素等等
 * @see Hamcrest下载地址为https://code.google.com/p/hamcrest/downloads/list
 * @see JUnit下载地址为https://github.com/junit-team/junit/wiki/Download-and-Install
 * @see 
 *      --------------------------------------------------------------------------
 *      ----------------------
 * @see 1)所需jar(有两个)
 * @see junit-4.10.jar
 * @see hamcrest-all-1.3.jar
 * @see 
 *      --------------------------------------------------------------------------
 *      ----------------------
 * @see 2)注意事项
 * @see 测试时可能报告类似这个的异常java.lang.NoSuchMethodError: org.hamcrest.core.AllOf.allOf
 * @see 这时只需将hamcrest.jar移到junit.jar的前面就可以了,否则组合条件如allOf、anyOff等都会抛此异常
 * @see 
 *      --------------------------------------------------------------------------
 *      ----------------------
 * @create Jul 6, 2013 5:11:27 PM
 * @author 玄玉<http://blog.csdn.net/jadyer>
 */
public class TestByHamcrest2 {
	/**
	 * 如果用的是JUnit-4.10,那么这里可以使用org.junit.Assert类,它提供了assertThat的功能
	 * 如果用的是JUnit低版本,如MyEclipse6.5自带的JUnit-4.3.1则未提供MatcherAssert.assertThat功能
	 * 这时可以使用hamcrest
	 * -all-1.3.jar自带的org.hamcrest.MatcherMatcherAssert.assertThat()方法
	 */
	@Test
	public void testHamcrest() {
		MatcherAssert.assertThat(0, is(not(1))); // passes
		// 50是否大于20
		MatcherAssert.assertThat(50, greaterThan(20));
		// 50是否大于或等于50
		MatcherAssert.assertThat(50, greaterThanOrEqualTo(50));

		// 50是否即大于20又小于60(allOf方法类似于Java中的&&)
		MatcherAssert.assertThat(50, anyOf(greaterThan(20), lessThan(160)));
		// 50是否大于20或小于40(anyOf方法类似于Java中的||)
		MatcherAssert.assertThat(50, anyOf(greaterThan(20), lessThan(40)));

		// 测试"abc.txt"是否以"txt"结束
		MatcherAssert.assertThat("abc.txt", endsWith("txt"));
		MatcherAssert.assertThat("abc.txt", startsWith("ab"));
		MatcherAssert.assertThat("abc.txt", containsString("c.t"));
		MatcherAssert.assertThat(22 + "aa", hasToString("22aa"));
		MatcherAssert.assertThat("http://blog.csdn.net/jadyer", instanceOf(String.class));
		MatcherAssert.assertThat("http://blog.csdn.net/jadyer", notNullValue());
		MatcherAssert.assertThat(null, nullValue());

		// 针对集合的测试
		String[] users = { "玄玉", "Jadyer" };
		MatcherAssert.assertThat(users, hasItemInArray("玄玉"));
		Map<String, String> userMap = new HashMap<String, String>();
		userMap.put("11", "玄玉");
		userMap.put("22", "Jadyer");
		userMap.put("33", "http://blog.csdn.net/jadyer");
		MatcherAssert.assertThat(userMap, hasKey("22"));
		MatcherAssert.assertThat(userMap, hasValue("http://blog.csdn.net/jadyer"));
	}
}