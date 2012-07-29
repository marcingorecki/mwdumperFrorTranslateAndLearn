package org.mediawiki.parser;

import static org.junit.Assert.*;

import org.junit.Test;

public class TemplatesResolverTest {



	@Test
	public void testL(){
		String resolved=TemplatesResolver.resolveTemplate("* {{l|en|wordbook}}");
		assertEquals("* [[wordbook]]", resolved);
	}
	
	@Test 
	public void testQuoteNews(){
		String resolved=TemplatesResolver.resolveTemplate(("{{quote-news|year=2011|date=September 21|author=Sam Lyon|title=Man City 2 - 0 Birmingham|work=BBC Sport|url=http://news.bbc.co.uk/sport2/hi/football/14910208.stm|page=|passage=Hargreaves, who left Manchester United on a '''free''' during the summer, drilled a 22-yard beauty to open the scoring.}}"));
		
		assertEquals("Hargreaves, who left Manchester United on a '''free''' during the summer, drilled a 22-yard beauty to open the scoring.",resolved);
	}
}
