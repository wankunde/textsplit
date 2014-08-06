# 项目说明
  本项目根据某金融论坛的帖子内容进行分词。
  
  * 本项目使用ANSJ进行中文分词
  * 使用hamcrest进行增强的单元测试
  * 使用了maven打包，单元测试等多个插件进行项目管理
  
# 原始数据准备

delete from finance_comment_demov1 where 证券代码='证券代码';
select 发贴人,内容 from finance_comment_demov1  into outfile "outfile" fields terminated by '\t';


# 使用Hamcrest增强JUnit的测试能力
## 介绍
  
  Hamcrest框架提供了一些相对通俗并高效的方法来进行一些junit比较困难的测试。  比如比较数值大小、测试对象类型、测试数组元素等等。
  
## 实例：
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
## 注意事项
  测试时可能报告类似这个的异常java.lang.NoSuchMethodError: org.hamcrest.core.AllOf.allOf.这时只需将hamcrest.jar移到junit.jar的前面就可以了,否则组合条件如allOf、anyOff等都会抛此异常
 
# logback 日志系统

## logback介绍 
  logback简单来说就是比log4j更好的日志库，Logback 分为三个模块：logback-core，logback-classic，logback-access。

  * logback-core 是核心；
  * logback-classic 改善了 log4j，且自身实现了 SLF4J API，所以即使用 Logback 你仍然可以使用其他的日志实现，如原始的 Log4J，java.util.logging 等；
  * logback-access 让你方便的访问日志信息，如通过 http 的方式。

## logback使用 
 pom.xml:
 
 	<dependency>
		<groupId>ch.qos.logback</groupId>
		<artifactId>logback-classic</artifactId>
		<version>1.1.2</version>
	</dependency>
	
  同时引入的包有：slf4j-api-1.7.6.jar，logback-classic-1.1.2.jar，logback-core-1.1.2.jar。
  
  增加配置文件logback.xml
  
 在程序中使用日志库
 
	Logger logger = LoggerFactory.getLogger(TestLogback.class);

	@Test
	public void testLogDebug() {
		logger.debug("doing my job by debug");
	} 
 
## 当logback.xml不在根目录下时处理方式
  * 通过代码加载target目录下配置的方式：main和test均测试通过，但是路径会写死，不利于程序部署。
  * 通过maven-surefire-plugin插件，自动去寻找编译好的logback文件：main不可以，test类通过mvn test可以，在eclipse中不行。

	 LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
	 JoranConfigurator configurator = new JoranConfigurator();
	 configurator.setContext(lc);
	 lc.reset();
	 try {
		configurator.doConfigure(&quot;target/conf/logback.xml&quot;);
	 } catch (JoranException e) {
		System.out.println(&quot;加载logback配置文件失败！&quot;);
		e.printStackTrace();
	 }