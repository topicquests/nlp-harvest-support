/*
 * Copyright 2018 TopicQuests Foundation
 *  This source code is available under the terms of the Affero General Public License v3.
 *  Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
 */
package org.topicquests.asr.nlp.doc;
import java.util.*;

import org.topicquests.asr.nlp.api.ITQCoreOntology;
import org.topicquests.asr.nlp.api.IAbstract;
import org.topicquests.asr.nlp.api.IAuthor;
import org.topicquests.asr.nlp.api.IPublication;
import org.topicquests.asr.nlp.doc.AuthorPojo;
import org.topicquests.asr.nlp.doc.PublicationPojo;

//import org.topicquests.hyperbrane.api.IHarvestingOntology;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;


/**
 * @author park
 */
public class JSONDocumentObject {
	public static final String
		_PMID				= "pmid", //
		_PMCID				= "pmcid",
		_PUBMED_PREFIX 		= "PubMed",
		_TITLE				= "title",
		_CITATIONS			= "citations",
		_COPYRIGHT			= "cpyright",
		_ABSTRACT			= "abstract", // now a list
		_CONTENT			= "content",
		_PARAGRAPHS			= "parags",
		_LANGUAGE			= "language",
		_URL				= "url",
		_AUTHORS			= "authors",
		_PUBLICATION 		= "publication",
		_ISO_ABBREV			= "isoA",
		_EDITORS			= "editors",
		_CLUSTER_LOCATOR	= "clusterLocator",
		_CLUSTER_TITLE		= "clusterTitle",
		_CLUSTER_DATA_LIST	= "clusterDataList",
		_TAG_LIST			= "tagList",
		_SUBSTANCE_LIST		= "substList",
		_TFIDF_MAP			= "tfidfMap";
	
	private JsonObject data;
	
	public JSONDocumentObject(JsonObject jo) {
		data = jo;
	}
	/**
	 * @param userId
	 * @throws Exception
	 */
	public JSONDocumentObject(String userId) throws Exception {
		if (userId == null)
			throw new Exception("JSONDocumentObject missing userId");
		data = new JsonObject();
		data.addProperty(ITQCoreOntology.CREATOR_ID_PROPERTY, userId);
	}
	
	public String getCreatorId() {
		return data.get(ITQCoreOntology.CREATOR_ID_PROPERTY).getAsString();
	}
	/**
	 * For pubmed docs
	 * @param pmid
	 */
	public void setPMID(String pmid) {
		data.addProperty(_PMID, pmid);
		// TODO setLocator(_PUBMED_PREFIX+pmid);
	}
	
	/**
	 * For pubmed docs with a PMC document
	 * @return
	 */
	public String getPMID() {
		
		return data.get(_PMID).getAsString();
	}
	
	public void setPMCID(String pmid) {
		data.addProperty(_PMCID, pmid);
	}
	
	public String getPMCID() {
		return data.get(_PMCID).getAsString();
	}
	/**
	 * Set the document's topic <em>locator</em>
	 * @param locator
	 */
	public void setLocator(String locator) {
		data.addProperty(ITQCoreOntology.LOCATOR_PROPERTY, locator);
	}
	/**
	 * Return the document's topic <em>locator</em>
	 * @return can return <code>null</code>
	 */
	public String getLocator() {
		String result = data.get(ITQCoreOntology.LOCATOR_PROPERTY).getAsString();
		if (result == null)
			result = data.get("locator").getAsString(); // old version
		return result;
	}
	
	public void setPublicationISOAbbreviation(String a) {
		data.addProperty(_ISO_ABBREV, a);
	}
	
	public String getPublicationISOAbbreviation() {
		return data.get(_ISO_ABBREV).getAsString();
	}
	
	public void setCopyright(String copyright) {
		data.addProperty(_COPYRIGHT, copyright);
	}
	
	public String getCopyright() {
		return data.get(_COPYRIGHT).getAsString();
	}
	public void setTagList(JsonArray tags) {
		data.add(_TAG_LIST, tags);
	}
	public void addTag(String t) {
		JsonArray l = data.get(_TAG_LIST).getAsJsonArray();
		if (l == null)
			l = new JsonArray();
		if (!contains(l, t)) {
			l.add(t);
		}
		data.add(_TAG_LIST,l);
	}
	
	boolean contains(JsonArray l, String s) {
		boolean result = false;
		Iterator<JsonElement> itr = l.iterator();
		while (itr.hasNext()) {
			if (itr.next().getAsString().equals(s))
				return true;
		}
		return result;
	}

/*
	public void addKeyword(String t) {
		List<String>l = (List<String>)data.get(_KEYWORD_LIST);
		if (l == null)
			l = new ArrayList<String>();
		if (!l.contains(t)) {
			l.add(t);
			data.put(_KEYWORD_LIST,l);
		}
	}
	public List<String> listKeywords() {
		return (List<String>)data.get(_KEYWORD_LIST);
	}
*/
	public void addSubstance(String t) {
		
		JsonArray l = data.get(_SUBSTANCE_LIST).getAsJsonArray();
		if (l == null)
			l = new JsonArray();
		if (!contains(l, t)) {
			l.add(t);
		}
		data.add(_SUBSTANCE_LIST,l);
	}
	public List<String> listSubstances() {
		return (List<String>)data.get(_SUBSTANCE_LIST);
	}

	/**
	 * Can return <code>null</code>
	 * @return
	 */
	public List<String> listTags() {
		return (List<String>)data.get(_TAG_LIST);
	}
	/**
	 * Return userId
	 * @return
	 */
	public String getUserId() {
		return data.get(ITQCoreOntology.CREATOR_ID_PROPERTY).getAsString();
	}
	
	/**
	 * Metadata for the {@link IDocument}
	 * @param title
	 */
	public void setClusterTitle(String title) {
		data.addProperty(_CLUSTER_TITLE, title);
	}
	
	public String getClusterTitle() {
		return data.get(_CLUSTER_TITLE).getAsString();
	}
	
	/**
	 * Metadata for the {@link IDocument}
	 * @param title
	 */
	//public void setClusterQuery(String title) {
	//	data.put(_CLUSTER_QUERY, title);
	//}
	
/*	public void addClusterData(String clusterLocator, String query, String clusterPhrase, String clusterScore) {
		Map<String,String>m = new HashMap<String,String>();
		m.put(_CLUSTER_LOCATOR, clusterLocator);
		m.put(IHarvestingOntology.CLUSTER_QUERY, query);
		m.put(_CLUSTER_TITLE, clusterPhrase);
		m.put(IHarvestingOntology.CLUSTER_WEIGHT, clusterScore);
		List<Map<String,String>>l = (List<Map<String,String>>)data.get(_CLUSTER_DATA_LIST);
		if (l == null)
			l = new ArrayList<Map<String,String>>();
		l.add(m);
		data.put(_CLUSTER_DATA_LIST, l);
	}
	
	public String getClusterQuery() {
		return data.getAsString(IHarvestingOntology.CLUSTER_QUERY);
	}
	
	public String getClusterWeight() {
		return data.getAsString(IHarvestingOntology.CLUSTER_WEIGHT);
	} */
	/**
	 * The full text to harvest
	 * @param content
	 * @param language defaults to "en"
	 * /
	public void setContent(String content, String language) {
		data.addProperty(_CONTENT, content);
		if (language != null)
			data.addProperty(_LANGUAGE, language);
		else
			data.addProperty(_LANGUAGE, "en");
	}
	*/
	public void addParagraph(IAbstract para) {
		JsonArray ja = listParagraphs();
		if (ja == null) {
			ja = new JsonArray();
			data.add(_PARAGRAPHS, ja);
		}
		ja.add(para.getData());
	}
	
	public JsonArray listParagraphs() {
		return data.get(_PARAGRAPHS).getAsJsonArray();
	}
	public void setTitle(String title) {
		data.addProperty(_TITLE, title);
	}
	
	public String getTitle() {
		return data.get(_TITLE).getAsString();
	}
	/**
	 * Text of a document's abstract
	 * @param abs
	 */
	//public void setAbstract(String abs) {
	//	data.put(_ABSTRACT, abs);
	//}
	
	/**
	 * Some documents have just one abstract paragraph, others several. //TODO challenge this: only one with multiple paragraphs
	 * @param a
	 */
	public void addDocAbstract(IAbstract a) {
		JsonArray ab = this.listAbstract();
		if (ab == null)
			ab = new JsonArray();
		ab.add(a.getData());
		data.add(_ABSTRACT, ab);
	}

	public JsonArray listAbstract() {
		return data.get(_ABSTRACT).getAsJsonArray();
	}
	/*public String getAbstract() {
		return data.getAsString(_ABSTRACT);
	}*/
	
	public String getLanguage() {
		return data.get(_LANGUAGE).getAsString();
	}
	
	public void setLanguage(String lang) {
		String x = lang;
		//heuristic punt
		if (x.equals("eng"))
			x = "en";
		data.addProperty(_LANGUAGE, x);
	}
//	public void setTFIDFData(SortedMap<Double,String>d) {
//		data.put(_TFIDF_MAP, d);
//	}
	
/*	public SortedMap<Double,String> getTFIDFData(Comparator c) {
		JSONObject jo = (JSONObject)data.get(_TFIDF_MAP);
		if (jo == null)
			return null;
		SortedMap<Double,String>result = new TreeMap(c);
		Iterator<String>itr = jo.keySet().iterator();
		String d;
		while (itr.hasNext()) {
			d = itr.next();
			result.put(new Double(d),(String)jo.get(d));
		}
		return result;
	}
*/
	/**
	 * Return the content
	 * @return
	 * /
	public String getContent() {
		return data.get(_CONTENT).getAsString();
	}
	
	public void setURL(String url) {
		data.addProperty(_URL, url);
	}
	
	/**
	 * Return url or empty string
	 * @return does not return {@code null}
	 */
	public String getURL() {
		String result = data.get(_URL).getAsString();
		if (result == null)
			result = "";
		return result;
	}
	
	public void setPublication(String title, String publicationName,
			String volume, String number, String pages, String date, String year,
			String publisherName, String publisherLocation,
			String doi, String issn, String publicationType, String isoAbbreviation) {
		
		IPublication p = new PublicationPojo();
		if (title != null && !title.equals(""))
			p.setTitle(title);
		if (publicationName != null && !publicationName.equals(""))
			p.setPublicationName(publicationName);
		if (volume != null && !volume.equals(""))
			p.setPubicationVolume(volume);
		if (number != null && !number.equals(""))
			p.setPublicationNumber(number);
		if (pages != null && !pages.equals(""))
			p.setPages(pages);
		if (date != null && !date.equals(""))
			p.setPublicationDate(date);
		if (year != null && !year.equals(""))
			p.setPublicationYear(year);
		if (publisherName != null && !publisherName.equals(""))
			p.setPublisherName(publisherName);
		if (publisherLocation != null && !publisherLocation.equals(""))
			p.setPublisherLocation(publisherLocation);
		if (doi != null && !doi.equals(""))
			p.setDOI(doi);
		if (issn != null && !issn.equals(""))
			p.setISSN(issn);
		if (isoAbbreviation != null && !isoAbbreviation.equals(""))
			p.setISOAbbreviation(isoAbbreviation);
		setPublication(p);
	}
	
	public void setPublication(IPublication p) {
		data.add(_PUBLICATION, p.getData());
	}
	
	public IPublication getPublication() {
		return (IPublication)data.get(_PUBLICATION);
	}

	public String toJSONString() {
		return data.getAsString();
	}
	
	/**
	 * Citations I make of other documents typically PMIDs
	 * @param type .e.g. doi, pubmed
	 * @param value
	 */
	public void addCitation(String type, String value) {
		JsonObject jo = new JsonObject();
		jo.addProperty("type", type);
		jo.addProperty("value", value);
		JsonArray l = data.get(_CITATIONS).getAsJsonArray();
		if (l == null)
			l = new JsonArray();
		l.add(jo);
		data.add(_CITATIONS,l);
	}
	
	public JsonArray listCitations() {
		return data.get(_CITATIONS).getAsJsonArray();
	}
	
	/**
	 * 
	 * @param title
	 * @param initials
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @param suffix e.g. jr, sr, II, III, ...
	 * @param degree e.g. M.D., PhD, ..
	 * @param fullName
	 * @param authorLocator
	 * @param publicationName
	 * @param publicationLocator
	 * @param publisherName
	 * @param publisherLocator
	 * @param affiliationName
	 * @param affiliationLocator
	 * @param funderName
	 * @param funderLocator
	 * @param fundingContractId
	 */
	public IAuthor addAuthor(String title, String initials, String firstName, String middleName, String lastName,
						  String suffix, String degree, String fullName, String authorLocator, 
						  String publicationName, String publicationLocator, 
						  String publisherName, String publisherLocator, 
						  String affiliationName, String affiliationLocator) {
		IAuthor a = new AuthorPojo();
		if (title != null && !title.equals(""))
			a.setAuthorTitle(title);
		if (initials != null && !initials.equals(""))
			a.setAuthorInitials(initials);
		if (firstName != null && !firstName.equals(""))
			a.addAuthorFirstName(firstName);
		if (middleName != null && !middleName.equals(""))
			a.setAuthorMiddleName(middleName);
		if (lastName != null && !lastName.equals(""))
			a.setAuthorLastName(lastName);
		if (suffix != null && !suffix.equals(""))
			a.setAuthorSuffix(suffix);
		if (degree != null && !degree.equals(""))
			a.setAuthorDegree(degree);
		if (fullName != null && !fullName.equals(""))
			a.setAuthorFullName(fullName);
		if (authorLocator != null && !authorLocator.equals(""))
			a.setAuthorLocator(authorLocator);
		if (publicationName != null && !publicationName.equals(""))
			a.setPublicationName(publicationName);
		if (publicationLocator != null && !publicationLocator.equals(""))
			a.setPublicationLocator(publicationLocator);
		if (publisherName != null && !publisherName.equals(""))
			a.setPublisherName(publisherName);
		if (publisherLocator != null && !publisherLocator.equals(""))
			a.setPublisherLocator(publisherLocator);
		if (affiliationName != null && !affiliationName.equals(""))
			a.addAffiliationName(affiliationName);
		if (affiliationLocator != null && !affiliationLocator.equals(""))
			a.setAffiliationLocator(affiliationLocator);
		this.addAuthor(a);
		return a;
	}	
	
	public void updateAuthor(IAuthor author) {
		
	}
	public void addAuthor(IAuthor author) {
		JsonArray a = this.listAuthors();
		if (a == null)
			a = new JsonArray();
		a.add(author.getData());
		this.setAuthorList(a);
	}

	public void setAuthorList(JsonArray authors) {
		data.add(_AUTHORS, authors);
	}
	/**
	 * List authors
	 * @return can return <code>null</code>
	 */
	public JsonArray listAuthors() {
		return data.get(_AUTHORS).getAsJsonArray();
	}	

	@Override
	public String toString() {
		return data.getAsString();
	}
	
	public JsonObject getData() {
		return data;
	}
}
