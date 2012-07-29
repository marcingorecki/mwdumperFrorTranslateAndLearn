/*
 * MediaWiki import/export processing tools
 * Copyright 2005 by Brion Vibber
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * $Id: Page.java 111169 2012-02-10 17:05:03Z oren $
 */

package org.mediawiki.importer;

import java.util.Hashtable;

public class Page {
	/**
	 * @uml.property  name="title"
	 * @uml.associationEnd  
	 */
	public Title Title;
	/**
	 * @uml.property  name="id"
	 */
	public int Id;
	/**
	 * @uml.property  name="discussionThreadingInfo"
	 * @uml.associationEnd  qualifier="attrib:java.lang.String java.lang.String"
	 */
	public Hashtable<String,Object> DiscussionThreadingInfo;
	/**
	 * @uml.property  name="restrictions"
	 */
	public String Restrictions;
	
	public Page() {
		// <restrictions> is optional...
		Restrictions = "";
		DiscussionThreadingInfo = new Hashtable<String, Object>();
	}
}
