/**
 * 
 */
package org.topicquests.asr.nlp.parsers;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;
import java.util.Iterator;

import org.topicquests.asr.nlp.Environment;
import org.topicquests.asr.nlp.api.IPublication;
import org.topicquests.asr.nlp.api.IAbstract;
import org.topicquests.asr.nlp.api.IAuthor;
import org.topicquests.asr.nlp.api.IGrant;
import org.topicquests.asr.nlp.doc.JSONDocumentObject;
import org.topicquests.asr.nlp.doc.PublicationPojo;
import org.topicquests.support.ResultPojo;
import org.topicquests.support.api.IResult;
import org.topicquests.asr.nlp.doc.AbstractPojo;
import org.topicquests.asr.nlp.doc.AuthorPojo;
import org.topicquests.asr.nlp.doc.GrantPojo;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.google.gson.JsonObject;

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
	    try {
	         XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
	         factory.setNamespaceAware(false);
	         XmlPullParser xpp = factory.newPullParser();

	         BufferedReader in = new BufferedReader(new InputStreamReader(ins));
	         xpp.setInput(in);
	         
	        //the working document
	         JSONDocumentObject theDocument = null;
	         String temp = null;
	         String text = null;
	         String articleType = null;
	         String pmcID = null, pmid=null, publisherId = null, doi= null;
	         String foo = null;
	         boolean isPMC = false, isPMID = false, isPubId = false, isDOI = false;
	         String title = null;
	         boolean isAuthor = false, isSection = false, isAbstract = false;
	         String surName = null, givenName = null, email = null, affilId = null;
	         IAuthor thisAuthor = null;
	         IPublication thisPub = null;
	         IAbstract thisAbs = null;
	         String absTitle = null, absText = null;
	         Map<String, IAuthor> myAuthors=null;  // key is affiliation ID
	         int authorCount = 0; // we must count contribs to know when to null out myAuthors
	        		 
	         String label = null;
	         String category = null;
	         String refType = null;
	         String lastName = null, firstName = null, initials = null, affiliation = null;
	         String grantId=null, agency=null, country = null;
	         String pages=null, pubVolume=null, pubYear=null, pubMonth=null, pubTitle=null;
	         String pubName=null, pubLoc=null, pubDate=null, pubISSN=null, pubIsoAbbrev=null;
	         String articleIdType = null;
	         boolean isJournal = false;
	         boolean isValid = false;
	         boolean isRefType = false;
	         boolean isGrant = true;
	         Map<String,String> props = null;
	         int eventType = xpp.getEventType();
	         boolean isStop = false;
	         while (!(isStop || eventType == XmlPullParser.END_DOCUMENT)) {
	        	 Thread.yield();
	            temp = xpp.getName();
	            if(eventType == XmlPullParser.START_DOCUMENT) {
	                System.out.println("PM Start document");
	            } else if(eventType == XmlPullParser.END_DOCUMENT) {
	                System.out.println("PM End document");
	                //TODO Temporary
	                
	            } else if(eventType == XmlPullParser.START_TAG) {
	                System.out.println("PM Start tag "+temp);
	                props = getAttributes(xpp);
	                articleIdType = props.get("IdType");
	                if (temp.equalsIgnoreCase("article")) {
	                	articleType = (String)props.get("article-type");
	                	theDocument = new JSONDocumentObject("SystemUser");
	                	result.setResultObject(theDocument);
	                	environment.logDebug("PMRPP.start");
	                } else if (temp.equalsIgnoreCase("article-id")) {
	                	// this repeats 4 times -pmcid, pmid, publisherid, doi
	                	foo = (String)props.get("pub-id-type");
	                	if (foo.equals("pmc"))
	                		isPMC = true;
	                	else if (foo.equals("pmid"))
		                		isPMID = true;
	                	else if (foo.equals("publisher-id"))
		                		isPubId = true;
	                	else foo = (String)props.get("pub-id-type");
		                	if (foo.equals("doi"))
		                		isDOI = true;
	                } else if (temp.equalsIgnoreCase("contrib")) {
	                	foo = (String)props.get("contrib-type=");
	                	if ("author".equals(foo)) {
	                		authorCount++;
	                		isAuthor = true;
	                	} else {
	                		environment.logError("PMCBadContribType: "+foo, null);
	                	}
	                } else if (temp.equalsIgnoreCase("xref")) {
                		affilId = (String)props.get("rid");// TODO other xrefs???
	                } else if (temp.equalsIgnoreCase("aff")) {
                		affilId = (String)props.get("id");// TODO other xrefs???
	                } else if (temp.equalsIgnoreCase("abstract")) {
	                	isAbstract = true;
	                	thisAbs = new AbstractPojo();
	                } else if (temp.equalsIgnoreCase("sec")) {
	                	isSection = true;
	                } else if (temp.equalsIgnoreCase("body")) {
	                	isAbstract = false;
	                	thisAbs = new AbstractPojo();
	                }

	                
	            } else if(eventType == XmlPullParser.END_TAG) {
	                System.out.println("PM End tag "+temp+" // "+text);
	                
	                if (temp.equalsIgnoreCase("article")) {
	                	//TODO
	                } else if (temp.equalsIgnoreCase("article-id")) {
	                	//we have 4 types of ID values
	                	// they come from "text" and must be added to theDoc
	                	// then set all values to null and booleans to false
	                	// TODO
	                } else if (temp.equalsIgnoreCase("article-title")) {
	                	title = text;
	                	theDocument.setTitle(title);
	                	title = null;
	                } else if (temp.equalsIgnoreCase("contrib")) {
	                	if (isAuthor) {
	                		if (myAuthors == null)
	                			myAuthors = new HashMap<String, IAuthor>();
	                		
	                		thisAuthor = new AuthorPojo();
	                		thisAuthor.setAuthorLastName(surName);
	                		thisAuthor.addAuthorFirstName(givenName);
	                		thisAuthor.setAuthorEmail(email);
	                		myAuthors.put(affilId, thisAuthor);
	                		thisAuthor = null;
	                		affilId = null;
	                		isAuthor = false;
	                		surName = null;
	                		givenName = null;
	                		email = null;
	                	}
	                } else if (temp.equalsIgnoreCase("surname")) {
	                	surName = text;
	                } else if (temp.equalsIgnoreCase("given-names")) {
	                	givenName = text;
	                } else if (temp.equalsIgnoreCase("email")) {
	                	email = text;
	                } else if (temp.equalsIgnoreCase("aff")) {
	                	authorCount--;
	                	thisAuthor = myAuthors.get(affilId);
	                	thisAuthor.setAffiliationLocator(text);
	                	if (authorCount == 0) {
	                		//TODO add authors to thvDoc
	                		Collection<IAuthor> auths = myAuthors.values();
	                		Iterator<IAuthor> itr = auths.iterator();
	                		while (itr.hasNext())
	                			theDocument.addAuthor(itr.next());
	                		myAuthors = null;
	                	}
	                } else if (temp.equalsIgnoreCase("abstract")) {
	                	theDocument.addDocAbstract(thisAbs);
	                	thisAbs = null;
	                } else if (temp.equalsIgnoreCase("sec")) {
	                	JsonObject jo = new JsonObject();
	                	if (isAbstract)
	                		thisAbs.addSection(absTitle, absText, "en");
	                	else {
	                		thisAbs = new AbstractPojo();
	                		thisAbs.addSection(absTitle, absText, "en");
	                		theDocument.addParagraph(thisAbs);
	                		thisAbs = null;
	                	}
	                	isSection = false;
	                } else if (temp.equalsIgnoreCase("title")) {
	                	if (isSection)
	                		absTitle = text;
	                } else if (temp.equalsIgnoreCase("p")) {
	                	if (isSection)
	                		absText = text;
	                	else  //TODO a guess
	                		thisAbs.addParagraph(text, "en");
	                }
	                
	                
	                

	            } else if(eventType == XmlPullParser.TEXT) {
	                text = xpp.getText().trim();
	             } else if(eventType == XmlPullParser.CDSECT) {
	                text = xpp.getText().trim();
	            }
	            eventType = xpp.next();
	          }
	      } catch (Exception e) {
	      		environment.logError(e.getMessage(), e);
	      		result.addErrorString(e.getMessage());
	      } 	
	}
		
	/**
	 * Clean up the xml input
	 * @param inString
	 * @return instance of {@link JSONDocumentObject}
	 */
	String cleanXML(String inString) {
		StringBuilder buf = new StringBuilder();
		int len = inString.length();
		int lll = (int)'l';
		int ggg = (int)'g';
		int ttt = (int)'t';
		int aaa = (int)'&';
				
		int c = 0, x,y;
		for (int i=0;i<len;i++) {
			c = inString.charAt(i);
			if (c == aaa) {
				x = inString.charAt(i+1);
				if (x == lll || x == ggg) {
					y = inString.charAt(i+2);
					if (y == ttt) {
						if (x == lll) 
							buf.append('<');
						else
							buf.append('>');
						i +=3;
						c=-1;
					}
				}
			}
			if (c > -1)
				buf.append((char)c);
		}
		return buf.toString();
	}
	
	/**
	 * </p>@see http://www.ncbi.nlm.nih.gov/pubmed/23329350 for
	 * an abstract that has wild characters '0''9'</p>
	 * <p>@see http://www.ncbi.nlm.nih.gov/pubmed/23325918 uses:
	 * "Expt." for experiment</p>
	 * <p>Bad break:
	 * Trichophyton rubrum (T.
 rubrum) represents the most important agent of dermatophytosis in humans.
	 * </p>
	 * <p> (p &lt; 0.001),  bad sentence break here, and 0009 and &lg;
	 *   has UTF-8 characters</p>
	 * @param inString
	 * @return
	 * Note: grand possiblility of outOfBounds errors here
	 */
	String cleanText(String inString) {
		int lparen = (int)'(';
		String foo = inString;
		foo = foo.replace("Expt.", "Experiment"); //worked!
		StringBuilder buf = new StringBuilder();
		int len = foo.length();
		int c = 0;
		boolean blockNewLine = false;
		for (int i=0;i<len;i++) {
			c = foo.charAt(i);
			//case of 23329350 
			//TODO did not work
			if (blockNewLine) {
				if (c != 0x0D && c != 0x0A) {
					//we just passed the newline line feed we wanted to ignore
					blockNewLine = false;
					buf.append((char)c);
				}
			}
			if (c == 0 && foo.charAt(i+1) == 9)
				i++; // skip those
			else if (c == lparen) {
				//bad sentence 
				//TODO did not work
				////////////////////////////////
				// outof bounds at i+2 and i+3
				if (i+3 < foo.length() && foo.charAt(i+2)==(int)'.' &&
					foo.charAt(i+3)==0x0D) {
					blockNewLine = true;
				}
				buf.append((char)c);
			}
			else
				buf.append((char)c);
		}
		return buf.toString();
	}
	String trimAffiliation(String affiliation) {
		int len = affiliation.length();
		//if (len > 200)
		//	len = 200;
		StringBuilder buf = new StringBuilder();
		char c;
		for (int i=0;i<len;i++) {
			c = affiliation.charAt(i);
			if (canUse(c))
				buf.append(c);
		}
		return buf.toString().trim();
	}
	
	boolean canUse(char c) {
		if ( c == '"' ||
			 c == ',' ||
			 c == '.' ||
			 c == ':' ||
			 c == ';')
			return false;
		return true;
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
