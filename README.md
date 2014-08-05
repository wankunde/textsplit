## 原始数据准备

delete from finance_comment_demov1 where 证券代码='证券代码';
select 发贴人,内容 from finance_comment_demov1  into outfile "outfile" fields terminated by '\t';


### 使用Hamcrest增强JUnit的测试能力
# 介绍
	Hamcrest框架提供了一些相对通俗并高效的方法来进行一些junit比较困难的测试。  比如比较数值大小、测试对象类型、测试数组元素等等。
# 实例：
	pom.xml:
		<dependency>
			<groupId>org.hamcrest</groupId>
			<artifactId>hamcrest-all</artifactId>
			<version>1.3</version>
		</dependency>
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.10</version>
			<scope>test</scope>
		</dependency>
	
	例子：
		TestByHamcrest.java
		TestByHamcrest2.java
# 注意事项
	测试时可能报告类似这个的异常java.lang.NoSuchMethodError: org.hamcrest.core.AllOf.allOf.这时只需将hamcrest.jar移到junit.jar的前面就可以了,否则组合条件如allOf、anyOff等都会抛此异常
 