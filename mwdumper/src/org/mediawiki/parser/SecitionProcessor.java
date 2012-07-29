package org.mediawiki.parser;

import java.util.Arrays;
import java.util.List;

public class SecitionProcessor {

	static List<String> headers = Arrays.asList(new String[] {
			"Verb",
			"Noun",
			"Adjective",
			"Declension",
			"Related terms",
			"Synonyms",
			"Participle",
			"Proper noun",
			"Derived terms",
			"Conjugation",
			"Adverb",
			"Antonyms",
			"Descendants",
			"Pronoun",
			"Suffix",
			"Phrase",
			"Preposition",
			"Quotations",
			"Idiom",
			"Abbreviation",
			"Proverb",
			"Alternative spellings",
			"Usage notes",	
			"Abbreviations"});
	
	static List<String> minimumSetHeaders = Arrays.asList(new String[] {
			"Verb",
			"Noun",
			"Adjective",
			"Participle",
			"Proper noun",
			"Adverb",
			"Pronoun",
			"Proverb"
	});

	
	
	/**
	 * First implementation treats all sections the same, but it can be changed so each section will be treated to match its unique
	 * characteristics.
	 */
	public static String processSection(String header, String body){
		String result="";
		if(minimumSetHeaders.contains(header)){
			result=header+"\n"+body+"\n";
		}
		return result;
	}
}
