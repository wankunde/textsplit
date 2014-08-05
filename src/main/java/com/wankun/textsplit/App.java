package com.wankun.textsplit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import love.cq.domain.Forest;

import org.ansj.library.UserDefineLibrary;

import com.google.common.base.Joiner;
import com.google.common.base.Stopwatch;

/**
 * Hello world!
 * 
 */
public class App {

	public BlockingQueue<String> msgqueue = new ArrayBlockingQueue<String>(1000000);
	public BlockingQueue<String> resqueue = new ArrayBlockingQueue<String>(1000000);

	/**
	 * 
	 * <pre>
	 * `证券代码` varchar(100) DEFAULT NULL,
	 * 	  `贴子编号` varchar(50) DEFAULT NULL,
	 * 	  `内容类型` varchar(4) NOT NULL DEFAULT '',
	 * 	  `发贴人` varchar(100) DEFAULT NULL,
	 * 	  `发表时间` varchar(19) DEFAULT NULL,
	 * 	  `评论编号` varchar(50) DEFAULT NULL,
	 * 	  `回复对应发贴人` varchar(100) DEFAULT NULL,
	 * 	  `访问次数` varchar(11) DEFAULT NULL,
	 * 	  `评论条数` varchar(11) DEFAULT NULL,
	 * 	  `类别` varchar(50) DEFAULT NULL,
	 * 	  `源链接` text,
	 * 	  `标题` varchar(500) DEFAULT NULL,
	 * 	  `内容` mediumtext
	 * </pre>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// System.out.println("\t".length());
		// System.exit(0);

		Path infile = Paths.get("E:/tmp", "finance_comment_demov2.log");
		Path outfile = Paths.get("E:/tmp", "outfile2.log");
		try {
			UserDefineLibrary.loadFile(new Forest(), new File(App.class.getClassLoader().getResource("library/finance.dict").toURI()));
		} catch (URISyntaxException e1) {
			System.out.println("加载自定义词库失败");
			e1.printStackTrace();
		}
		
		Charset charset = Charset.forName("UTF-8");
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			reader = Files.newBufferedReader(infile, charset);
			if (Files.exists(outfile))
				Files.delete(outfile);
			Files.createFile(outfile);
			writer = Files.newBufferedWriter(outfile, charset, StandardOpenOption.APPEND);

			Stopwatch sw=new Stopwatch();
			sw.start();
			String line = null;
			if ((line = reader.readLine()) != null) {
				String line1=line.replaceAll("\\\\\'", "\\\'");
				line1=line1.replaceAll("\\\\\n", "\\\n");
				List<String> fields = parseLine(line1);
				Objects.requireNonNull(fields);
				if (fields.size() > 13) {
					List<String> tmp = new ArrayList<>(fields.subList(0, 12));
					// Collections.copy(tmp, fields.subList(0, 12));
					if (line1.indexOf(fields.get(12)) < 0) {
						System.out.println("帖子内容找不到了：" + line);
						System.out.println("帖子内容：" + fields.get(12));
						tmp.add(fields.get(12));
					} else {
						tmp.add(line.substring(line.indexOf(fields.get(12))));
					}
					fields = tmp;
				}
				if (fields.size() < 13) {
					System.out.println("数据不符合规范：" + line);
				} else {
					fields.set(12, SplitUtil.splitText(fields.get(12)));
					String newline = "'" + Joiner.on("','").join(fields) + "'" + "\n";
					writer.append(newline);
				}
			}
			long elapsed=sw.elapsedMillis();
			sw.stop();
			System.out.println("分词耗时："+elapsed);
		} catch (IOException e) {
			System.err.println(e);
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (writer != null)
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	public static List<String> parseLine(String line) {
		List<String> list = new ArrayList<>();
		StringBuffer field = new StringBuffer();
		boolean inquot = false;
		boolean hasEscape = false;
		for (int i = 0; i < line.length(); i++) {
			char ch = line.charAt(i);
			if (inquot) {
				if (ch == '\\' && hasEscape == false) {
					hasEscape = true;
					field.append(ch);
				} else if (ch == '\'' && hasEscape == false) {
					inquot = false;
					if (field.length() > 0)
						list.add(field.toString());
					field = new StringBuffer();
				} else {
					if (hasEscape)
						hasEscape = false;
					field.append(ch);
				}
			} else if (ch == ',') {
				if (field.length() > 0)
					list.add(field.toString());
				field = new StringBuffer();
			} else if (ch == '\'') {
				inquot = true;
			} else
				field.append(ch);
		}
		if(field.length()>0)
			list.add(field.toString());
		return list;
	}

}
