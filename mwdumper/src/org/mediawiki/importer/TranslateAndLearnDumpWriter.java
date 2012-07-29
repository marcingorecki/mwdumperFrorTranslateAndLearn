package org.mediawiki.importer;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mediawiki.parser.WordPageParser;

public class TranslateAndLearnDumpWriter implements DumpWriter {
	/**
	 * @uml.property name="stream"
	 */
	protected OutputStream stream;
	/**
	 * @uml.property name="writer"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	protected XmlWriter writer;

	String title = "";
	String contents = "";
	int namespace;
	private int wordsCount;
	private int noLangFound;
	Pattern p = Pattern.compile("==([a-zA-Z ]+)==");
	Map<String, Integer> langsFound = new HashMap<String, Integer>();
	private WordPageParser wordPageParser = new WordPageParser();

	public TranslateAndLearnDumpWriter(OutputStream output) throws IOException {
		stream = output;
		writer = new XmlWriter(stream);
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeStartWiki() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeEndWiki() throws IOException {
		System.out.println("words:" + wordsCount);
		System.out.println("noLangFound:" + noLangFound);
		System.out.println(wordPageParser.allHeaders);
		
	}

	@Override
	public void writeSiteinfo(Siteinfo info) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeStartPage(Page page) throws IOException {
		namespace = page.Title.Namespace;
		title = page.Title.Text;
	}

	@Override
	public void writeEndPage() throws IOException {
		if (namespace == 0) {
			wordsCount++;

			Matcher m = p.matcher(contents);
			if (m.find()) {

				String lang=m.group(1);
				Integer i = langsFound.get(lang);
				if (i == null) {
					i = 1;
				} else {
					i++;
				}
				langsFound.put(lang, i);
				if(lang.equals("English")){
					try {
						wordPageParser.parse(title, contents);
					} catch (Exception e) {
						throw new IOException(e);
					}
				}

			} else {
				noLangFound++;
			}
		}

	}

	@Override
	public void writeRevision(Revision revision) throws IOException {
		contents = revision.Text;
	}

}
