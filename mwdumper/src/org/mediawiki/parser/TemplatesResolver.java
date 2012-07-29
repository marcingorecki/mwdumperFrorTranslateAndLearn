package org.mediawiki.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplatesResolver {
	
	
	static Pattern templateNamePattern = Pattern.compile("\\{\\{([a-z -]+)");
	static Pattern quoteNewsPattern = Pattern.compile("passage=(.*)\\}\\}");
	
	private static String getFromPattern(String line, Pattern p) {
		Matcher m = p.matcher(line);
		if(m.find()){
			return m.group(1);
		}
		return line;
	}
	
	private static String getTemplateName(String line){
		return getFromPattern(line, templateNamePattern);
	}
	
	private static String resolveL(String line){
		line = line.replaceAll("\\{\\{l\\|en\\|([a-zA-Z -]+)\\}\\}", "[[$1]]");
		return line;
	}

	private static String resolveQuoteNews(String line) {
		return getFromPattern(line, quoteNewsPattern);
	}
	
	public static String resolveTemplate(String line){
		String templateName=getTemplateName(line);
		if("l".equals(templateName)){
			return resolveL(line);
		}else if("quote-news".equals(templateName)){
			return resolveQuoteNews(line);
		}
		return line;
	}


}
