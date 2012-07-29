package org.mediawiki.parser.datamassager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindTemplatesInFile {
	
	static Pattern template = Pattern.compile("\\{\\{(.+?)\\}\\}");
	static TreeSet<String> templatesSet = new TreeSet<String>();
	static TreeSet<String> templateNames = new TreeSet<String>();

	public static void main(String[] args) throws IOException{
		findTemplates();
	}

	private static void findTemplates() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("/Users/marcin/wiktionary/parsed-qoutes")));
		String line=null;
		while((line=br.readLine())!=null){
			Matcher m = template.matcher(line);
			while(m.find()){
				String s=m.group(1);
				templatesSet.add(s);
				String[] tpl=s.split("\\|");
				templateNames.add(tpl[0]);
			}
		}
		for(String t:templateNames){
			System.out.println(t);
		}
	}
}
