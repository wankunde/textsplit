package com.wankun.textsplit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test1 {
	public static Logger logger = LoggerFactory.getLogger(Test1.class);

	public static void main(String[] args) {
//		logger.info("This is a log");
//		List<String> tmp = new ArrayList<>(13);
//		System.out.println(tmp.size());
//		
//		List src1 = new  ArrayList( 3 );
//		src1.add( " a " );
//		src1.add( " b " );
//		src1.add( " c " );
//
//		List des1 = new  ArrayList(src1);
//		System.out.println(src1.size());
//		System.out.println(des1.size());
//		Collections.copy(des1,src1);
		String[] arr={"aaa","bbb","cccc"};
		System.out.println(arr.getClass());
		Object[] arr2={new Object(),new Object(),new Object()};
		Object[] arr3={new Test1(),new Test1(),new Test1()};
		System.out.println(arr2.getClass()+"\t\t"+arr3.getClass());
		System.out.println(arr2.getClass() == Object[].class);
		System.out.println(arr3.getClass() == Object[].class);
	}

}
