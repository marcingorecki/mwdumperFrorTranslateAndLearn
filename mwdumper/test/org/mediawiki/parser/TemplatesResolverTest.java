package org.mediawiki.parser;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class TemplatesResolverTest {



	@Test
	public void testL(){
		String resolved=TemplatesResolver.resolveTemplate("* {{l|en|wordbook}}");
		assertEquals("* [[wordbook]]", resolved);
	}

	@Test
	public void testW(){
		String resolved=TemplatesResolver.resolveTemplate("* {{w|Albert Schweitzer}}");
		assertEquals("* Albert Schweitzer", resolved);
	}
	
	@Ignore
	@Test 
	public void testQuoteNews(){
		String resolved=TemplatesResolver.resolveTemplate(("{{quote-news|year=2011|date=September 21|author=Sam Lyon|title=Man City 2 - 0 Birmingham|work=BBC Sport|url=http://news.bbc.co.uk/sport2/hi/football/14910208.stm|page=|passage=Hargreaves, who left Manchester United on a '''free''' during the summer, drilled a 22-yard beauty to open the scoring.}}"));
		assertEquals("Hargreaves, who left Manchester United on a '''free''' during the summer, drilled a 22-yard beauty to open the scoring.",resolved);
		resolved=TemplatesResolver.resolveTemplate("* {{quote-news|date = 2002-12-28|first = Bill|last = Keller|authorlink = Bill Keller|url = http://www.nytimes.com/2002/12/28/opinion/28KELL.html?pagewanted=2&todaysheadlines|title = Who's Sorry Now?|newspaper = {{w|The New York Times}}|issn = 0362-4331|page = A-19|accessdate = 2007-06-24|passage = \"'''Googling''' in [[search]] of an [[apology]] from the former Enron C.E.O....\"}}");
		assertEquals("\"'''Googling''' in [[search]] of an [[apology]] from the former Enron C.E.O....\"",resolved);
	}
	
	@Test 
	public void testGenericWithOneArgs(){
		String resolved=TemplatesResolver.resolveTemplate(("{{zoology}}"));
		assertEquals("<<zoology>>",resolved);
	}
	
	@Test 
	public void testGenericWithTwoArgs(){
		String resolved=TemplatesResolver.resolveTemplate(("{{wikipedia|supermini}}"));
		assertEquals("<<supermini>>",resolved);
	}
	
	@Test 
	public void testGenericWithThreeArgs(){
		String resolved=TemplatesResolver.resolveTemplate(("{{en-adv|hindsightli|er|more}}"));
		assertEquals("<<more>>",resolved);
	}
}
