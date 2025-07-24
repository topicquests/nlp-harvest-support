/**
 * 
 */
package org.topicquests.asr.nlp.parsers;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.topicquests.support.ResultPojo;
import org.topicquests.support.api.IResult;

import net.sf.saxon.s9api.*;
import net.sf.saxon.functions.*;

/**
 * @author jackpark
 * <p>A class to strip XML <p></p> of any other xml sections using saxon</p>
 * @see https://github.com/snac-cooperative/saxon-9-he-samples/blob/master/ext_simple.java
 */
public class CleanXML {

	/**
	 * 
	 */
	public CleanXML() {
		// TODO Auto-generated constructor stub
	}
	
	IResult cleanXML(String xml) {
		IResult result = new ResultPojo();
		//TODO
		return result;
	}

}
