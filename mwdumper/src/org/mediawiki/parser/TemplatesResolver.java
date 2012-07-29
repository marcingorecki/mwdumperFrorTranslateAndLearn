package org.mediawiki.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplatesResolver {
	
	
	static Pattern templateNamePattern = Pattern.compile("\\{\\{([a-z -]+)");
	static Pattern templatePattern = Pattern.compile("\\{\\{(.+)\\|(.*?)\\}\\}");
	static Pattern templateWithNoArgsPattern = Pattern.compile("\\{\\{(.+)\\}\\}");
	static Pattern quoteNewsPattern = Pattern.compile("passage[\\w]*=(.*)\\}\\}");
	
	private static String getFromPattern(String line, Pattern p) {
		Matcher m = p.matcher(line);
		if(m.find()){
			return m.group(1);
		}
		return null;
	}
	
	private static String getTemplateName(String line){
		return getFromPattern(line, templateNamePattern);
	}
	
	private static String resolveL(String line){
		line = line.replaceAll("\\{\\{l\\|en\\|([a-zA-Z -]+)\\}\\}", "[[$1]]");
		return line;
	}
	
	private static String resolveW(String line){
		line = line.replaceAll("\\{\\{w\\|([a-zA-Z -]+)\\}\\}", "$1");
		return line;
	}

	private static String resolveQuoteNews(String line) {
		return getFromPattern(line, quoteNewsPattern);
	}
	
	private static String makePseudoTemplate(String line) {
		if(line.contains("|")){
			Matcher m = templatePattern.matcher(line);
			return m.replaceAll("<<$2>>");
		}else{
			Matcher m =templateWithNoArgsPattern.matcher(line);
			return m.replaceAll("<<$1>>");
		}
	}
	
	public static String resolveTemplate(String line){
		String templateName=getTemplateName(line);
		String result;
		if("l".equals(templateName)){
			result = resolveL(line);
		}else if("w".equals(templateName)){
			result = resolveW(line);
//		}else if("quote-news".equals(templateName)){
//			result = resolveQuoteNews(line);
		}else if(templateName!=null){
			result = makePseudoTemplate(line);
		}else{
			result = line;
		}
		if(result==null){
			System.out.println("null result for line "+line);
		}
		return result;
	}


}
