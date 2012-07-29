package org.mediawiki.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordPageParser {

	private static final String QUOTES_LINK_PATTERN = "\\{\\{seeCites\\}\\}";
	private static final String DICTIONARY_LINK_PATTERN = "\\[\\[[a-z-]+:dictionary\\]\\]";
	Pattern level3 = Pattern.compile("===([a-zA-Z ]+)===");
	Pattern level4 = Pattern.compile("====([a-zA-Z ]+)====");

	Map<String, Integer> level3Labels = new HashMap<String, Integer>();
	Map<String, Integer> level4Labels = new HashMap<String, Integer>();
	public Map<String, Integer> allHeaders = new HashMap<String, Integer>();

	String body = "";
	String currentHeader = null;
	int stop=0;

	public void parse(String title, String contents) throws Exception {
		
		String definition;
		try {
			definition = parseByLine(contents);
		} catch (Exception e) {
			throw new Exception("Exception "+e.getLocalizedMessage()+" for title "+title, e);
		}
		System.out.println(title);
		System.out.println(definition);
		//System.out.println(contents);
		System.out.println();
		if(stop++ == 5){
			throw new Exception("Enough!");
		}
	}

	private String processSection(String header3, String header4) {

		String result="";
		String header = header3 != null ? header3 : header4;
		if (currentHeader != null) {
			addToMap(currentHeader, allHeaders);
			result= SecitionProcessor.processSection(currentHeader, body);
		}
		
		//reset for new header
		currentHeader = header;
		body = "";
		return result;
	}

	private void addToMap(String text, Map<String, Integer> map) {
		Integer i = map.get(text);
		if (i == null) {
			i = 1;
		} else {
			i++;
		}
		map.put(text, i);
	}
	

	
	private String cleanLine(String line){
		line=line.trim();
		line=removeLinksToOtherDictionaries(line);
		line=removeLinksToQuotes(line);
		line=removeEmptyBullets(line);
		line=TemplatesResolver.resolveTemplate(line);
		
		return line;
	}

	private String removeEmptyBullets(String line) {
		if(line.trim().equals("*") ||
				line.trim().equals("#")){
			return "";
		}
		return line;
	}

	private String removeLinksToQuotes(String line) {
		return line.replaceAll(QUOTES_LINK_PATTERN, "");
	}

	private String removeLinksToOtherDictionaries(String line) {
		return line.replaceAll(DICTIONARY_LINK_PATTERN, "");
	}
	
	private int templateBrackets(String line){
		String[] opening = line.split("\\{\\{",-1);
		String[] closing = line.split("\\}\\}",-1);
		return opening.length - closing.length;
	}

	private String parseByLine(String text) throws Exception {
		String[] lines = text.split("\n");
		String definition = "";

		String templateBlock="";
		int bracketBalance=0;
		
		for (String line : lines) {
			//combine multi-level template blocks into single block
			int bracketsInLine=templateBrackets(line);
			if(bracketsInLine>0){
				templateBlock=line;
				bracketBalance=bracketsInLine;
				continue;
			} else if(bracketsInLine<0 && (bracketsInLine+bracketBalance==0)){ //balanced!
				line=templateBlock+line;
			} else if(bracketBalance>0){
				templateBlock+=line;
				continue;
			}
			
			bracketBalance+=bracketsInLine;
			if(bracketBalance<0){
				throw new Exception("Template closes more times than it was opened!");
			}
			line = cleanLine(line);
			String header3 = getLevel(line, level3);
			String header4 = getLevel(line, level4);

			if (header3 != null || header4 != null) {
				definition += processSection(header3, header4);
			} else {
				if(!line.equals("")){
					body += line + "\n";
				}
			}
		}
		definition += processSection(null, null);
		return definition;
	}

	private String getLevel(String line, Pattern p) {
		Matcher m = p.matcher(line.trim());
		if (m.matches()) {
			return m.group(1);
		} else {
			return null;
		}
	}
}
