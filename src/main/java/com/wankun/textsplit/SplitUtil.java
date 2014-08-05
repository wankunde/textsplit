package com.wankun.textsplit;

import java.util.List;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;

public class SplitUtil {
	public static String splitText(String text) {
		return splitText(text, "|");
	}

	public static String splitText(String text, String separator) {
		StringBuilder sb = new StringBuilder();
		List<Term> terms = NlpAnalysis.parse(text);
		for (Term term : terms) {
			sb.append(term.getName() + separator);
		}
		sb.deleteCharAt(sb.length() - separator.length());
		return sb.toString();
	}
}
