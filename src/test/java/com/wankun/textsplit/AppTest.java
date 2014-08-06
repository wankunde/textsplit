package com.wankun.textsplit;

import java.util.List;

import org.ansj.domain.Nature;
import org.ansj.domain.Term;
import org.ansj.recognition.NatureRecognition;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.ansj.splitWord.analysis.ToAnalysis;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	public static void main(String[] args) {
		//String str = "本赛季德甲#Q球队霍芬海姆的两名年轻球员菲尔米诺和福兰德表现出色，但球队主帅吉斯多尔态度强硬。";
		String str = "去年中国的GDP增长率为8.8%。";
		System.out.println(ToAnalysis.parse(str));
		System.out.println(NlpAnalysis.parse(str));
		String res=ansjAnalyzerNature(str);
		System.out.println(res);
		StringBuilder sb = new StringBuilder();
		List<Term> terms = NlpAnalysis.parse(str);
		for (Term term : terms) {
			sb.append(term.getName()+"|");
		}
		sb.deleteCharAt(sb.length()-1);
		System.out.println(sb.toString());
		
	}

	public static String ansjAnalyzerNature(String str) {
		List<Term> terms = NlpAnalysis.parse(str);
		new NatureRecognition(terms).recognition();
		StringBuilder sb = new StringBuilder();

		System.out.println(terms.toString());

		// 词性过滤
		Nature nature;
		for (Term term : terms) {
			nature = term.getNatrue();

			if (nature.natureStr.subSequence(0, 1).equals("n") || nature.natureStr.subSequence(0, 1).equals("h")) {
				if (!" ".equals(term.getName()) && !" ".equals(term.getName())
						&& term.getName().trim().replaceAll("[\\pP\\pM\\pS]", "").length() > 1) {
					sb.append(term.getName()).append("|");
				}
			}
		}

		return sb.toString();
	}
}
