/**
 * 
 */
package org.topicquests.asr.nlp.parsers;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import org.topicquests.asr.nlp.Environment;
import org.topicquests.asr.nlp.api.IResult;
import org.topicquests.asr.nlp.doc.JSONDocumentObject;
import org.topicquests.asr.nlp.uitil.ResultPojo;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 * @author jackpark
 */
public class PMCPullParser {
	private Environment environment;

	/**
	 * 
	 */
	public PMCPullParser(Environment env) {
		environment = env;
	}

	public IResult parseXML(String xml) {
		IResult result = new ResultPojo();
		try {
			InputStream ins = new ByteArrayInputStream(xml.getBytes());
			BufferedInputStream bis = new BufferedInputStream(ins);
			
			parse(bis, result);
		} catch (Exception e) {
			e.printStackTrace();
			result.addErrorString(e.getMessage());
		}
		return result;
	}

	void parse(InputStream ins, IResult result) {
		//TODO
	}
		
	/**
     * does not return null if no attributes
     */
	Map<String,String> getAttributes(XmlPullParser p) {
      HashMap <String,String>result =  new HashMap<String,String>();;
      int count = p.getAttributeCount();
      if (count > 0) {
        String name = null;
        for (int i = 0; i < count; i++) {
          name = p.getAttributeName(i);
          result.put(name,p.getAttributeValue(i));
        }
      }
      return result;
    }		

}
