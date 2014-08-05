package com.wankun.textsplit;

import java.io.File;
import java.net.URISyntaxException;

import love.cq.domain.Forest;

import org.ansj.library.UserDefineLibrary;

public class Test2 {

	public static void main(String[] args) {
		String line ="你好，马勒戈壁的";
		System.out.println(SplitUtil.splitText(line));
		try {
			UserDefineLibrary.loadFile(new Forest(), new File(App.class.getClassLoader().getResource("library/my.dict").toURI()));
		} catch (URISyntaxException e1) {
			System.out.println("加载自定义词库失败");
			e1.printStackTrace();
		}
		System.out.println(SplitUtil.splitText(line));
	}
	
}
