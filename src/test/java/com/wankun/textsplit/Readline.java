package com.wankun.textsplit;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class Readline {

	@Test
	public void TestParseLine() {
		String line = "'600000','107081489','评论','车友老糊涂728','2014-04-09 18:17:35','zwli7639680024',null,null,null,null,'http://guba.eastmoney.com/news,600000,107081489.html',null,'支持'";
		List<String> list = App.parseLine(line);
		String[] arr = { "600000", "107081489", "评论", "车友老糊涂728", "2014-04-09 18:17:35", "zwli7639680024", "null",
				"null", "null", "null", "http://guba.eastmoney.com/news,600000,107081489.html", "null", "支持" };
		assertThat(Arrays.asList(arr), is(list));

		String line2 = "'SZ000719','5234','评论','陕西西安股友','2007-06-07 11:39:41','zwli6240030723',null,null,null,null,'http://guba.eastmoney.com/news,000719,5234_2.html',null,null";
		List<String> list2 = App.parseLine(line2);
		String[] arr2 = { "SZ000719", "5234", "评论", "陕西西安股友", "2007-06-07 11:39:41", "zwli6240030723", "null", "null",
				"null", "null", "http://guba.eastmoney.com/news,000719,5234_2.html", "null", "null" };
		assertThat(Arrays.asList(arr2), is(list2));
		
		String line3 = "'SZ000719','100394412','贴子','天津股友','2014-02-21 01:29:21',null,null,'1318','1',null,'http://guba.eastmoney.com/news,000719,100394412.html','二月二十四日\\\\'中央发布文化体制改革计划','\\\\n\\	\\	\\	油改过后\\\\'\\\\'文改将要掀起高潮\\\\'\\\\'文化传媒继续持有 \\\\n\\\\n\\	\\	'";
		line3=line3.replaceAll("\\\'", " ");
		List<String> list3 = App.parseLine(line3);
		for (String tmp : list3)
			System.out.println(tmp);
		String[] arr3 = { "SZ000719", "5234", "评论", "陕西西安股友", "2007-06-07 11:39:41", "zwli6240030723", "null", "null",
				"null", "null", "http://guba.eastmoney.com/news,000719,5234_2.html", "null", "null" };
		assertThat(Arrays.asList(arr3), is(list3));
	}

}
