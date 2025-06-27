/*
 * Copyright 2018 TopicQuests Foundation
 *  This source code is available under the terms of the Affero General Public License v3.
 *  Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
 */
package org.topicquests.asr.nlp.doc;


import java.util.Iterator;

import org.topicquests.asr.nlp.api.IGrant;
import org.topicquests.asr.nlp.api.IPublication;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * @author jackpark
 *
 */
public class PublicationPojo implements IPublication {
	private JsonObject data;
	/**
	 * 
	 */
	public PublicationPojo() {
		data = new JsonObject();
	}

	/**
	 * @param map
	 */
	public PublicationPojo(JsonObject map) {
		data = map;
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IPublication#setTitle(java.lang.String)
	 */
	@Override
	public void setTitle(String title) {
		data.addProperty(IPublication.TITLE_FIELD, title);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IPublication#getTitle()
	 */
	@Override
	public String getTitle() {
		return data.get(IPublication.TITLE_FIELD).getAsString();
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IPublication#setPublicationName(java.lang.String)
	 */
	@Override
	public void setPublicationName(String name) {
		data.addProperty(IPublication.PUBLICATION_NAME_FIELD, name);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IPublication#getPublicationName()
	 */
	@Override
	public String getPublicationName() {
		return data.get(IPublication.PUBLICATION_NAME_FIELD).getAsString();
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IPublication#setPublicationDate(java.lang.String)
	 */
	@Override
	public void setPublicationDate(String date) {
		data.addProperty(IPublication.PUBLICATION_DATE_FIELD, date);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IPublication#getPublicationDate()
	 */
	@Override
	public String getPublicationDate() {
		return data.get(IPublication.PUBLICATION_DATE_FIELD).getAsString();
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IPublication#setPublicationYear(java.lang.String)
	 */
	@Override
	public void setPublicationYear(String year) {
		data.addProperty(IPublication.PUBLICATION_YEAR_FIELD, year);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IPublication#getPublicationYear()
	 */
	@Override
	public String getPublicationYear() {
		return data.get(IPublication.PUBLICATION_YEAR_FIELD).getAsString();
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IPublication#setPubicationVolume(java.lang.String)
	 */
	@Override
	public void setPubicationVolume(String volume) {
		data.addProperty(IPublication.PUBLICATION_VOLUME_FIELD, volume);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IPublication#getPublicationVolume()
	 */
	@Override
	public String getPublicationVolume() {
		return data.get(IPublication.PUBLICATION_VOLUME_FIELD).getAsString();
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IPublication#setPublicationNumber(java.lang.String)
	 */
	@Override
	public void setPublicationNumber(String number) {
		data.addProperty(IPublication.PUBLICATION_NUMBER_FIELD, number);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IPublication#getPublicationNumber()
	 */
	@Override
	public String getPublicationNumber() {
		return data.get(IPublication.PUBLICATION_NUMBER_FIELD).getAsString();
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IPublication#setPages(java.lang.String)
	 */
	@Override
	public void setPages(String pages) {
		data.addProperty(IPublication.PAGES_FIELD, pages);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IPublication#getPages()
	 */
	@Override
	public String getPages() {
		return data.get(IPublication.PAGES_FIELD).getAsString();
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IPublication#setPublisherName(java.lang.String)
	 */
	@Override
	public void setPublisherName(String name) {
		data.addProperty(IPublication.PUBLISHER_NAME_FIELD, name);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IPublication#getPublisherName()
	 */
	@Override
	public String getPublisherName() {
		return data.get(IPublication.PUBLISHER_NAME_FIELD).getAsString();
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IPublication#setPublisherLocation(java.lang.String)
	 */
	@Override
	public void setPublisherLocation(String location) {
		data.addProperty(IPublication.PUBLISHER_LOCATION_FIELD, location);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IPublication#getPublisherLocation()
	 */
	@Override
	public String getPublisherLocation() {
		return data.get(IPublication.PUBLISHER_LOCATION_FIELD).getAsString();
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IPublication#setDOI(java.lang.String)
	 */
	@Override
	public void setDOI(String doi) {
		data.addProperty(IPublication.DOI_FIELD, doi);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IPublication#getDOI()
	 */
	@Override
	public String getDOI() {
		return data.get(IPublication.DOI_FIELD).getAsString();
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IPublication#setISSN(java.lang.String)
	 */
	@Override
	public void setISSN(String issn) {
		data.addProperty(IPublication.ISSN_FIELD, issn);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IPublication#getISSN()
	 */
	@Override
	public String getISSN() {
		return data.get(IPublication.ISSN_FIELD).getAsString();
	}

	@Override
	public void setPublicationType(String type) {
		data.addProperty(IPublication.PUBLICATION_TYPE_FIELD, type);
	}

	@Override
	public String getPublicationType() {
		return data.get(IPublication.PUBLICATION_TYPE_FIELD).getAsString();
	}

	@Override
	public void setISOAbbreviation(String abbrev) {
		data.addProperty(IPublication.ISO_ABBREVIATION_FIELD, abbrev);
	}

	@Override
	public String getISOAbbreviation() {
		return data.get(IPublication.ISO_ABBREVIATION_FIELD).getAsString();
	}

	@Override
	public void setPublicationLocator(String locator) {
		data.addProperty(IPublication.PUBLICATION_LOCATOR_FIELD, locator);
	}

	@Override
	public String getPublicationLocator() {
		return data.get(IPublication.PUBLICATION_LOCATOR_FIELD).getAsString();
	}

	@Override
	public void setPublisherLocator(String locator) {
		data.addProperty(IPublication.PUBLISHER_LOCATOR_FIELD, locator);
	}

	@Override
	public String getPublisherLocator() {
		return data.get(IPublication.PUBLISHER_LOCATOR_FIELD).getAsString();
	}

	@Override
	public void setISBN(String isbn) {
		data.addProperty(IPublication.ISBN_FIELD, isbn);
	}

	@Override
	public String getISBN() {
		return data.get(IPublication.ISBN_FIELD).getAsString();
	}

	@Override
	public void setDocumentLocator(String locator) {
		data.addProperty(IPublication.DOCUMENT_LOCATOR, locator);
	}

	@Override
	public String getDocumentLocator() {
		return data.get(IPublication.DOCUMENT_LOCATOR).getAsString();
	}


	@Override
	public void addGrant(IGrant g) {
		JsonArray l = listGrants();
		if (l == null)
			l = new JsonArray();
		if (!contains(l, g.getData().getAsString()))
			l.add(g.getData());
		data.add(IPublication.GRANT_LIST, l);
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

	@Override
	public JsonArray listGrants() {
		return data.get(IPublication.GRANT_LIST).getAsJsonArray();
	}

	@Override
	public void setCopyright(String c) {
		data.addProperty(IPublication.COPYRIGHT_FIELD, c);
	}

	@Override
	public String getCopyright() {
		return data.get(IPublication.COPYRIGHT_FIELD).getAsString();
	}

	@Override
	public void setMonth(String month) {
		data.addProperty(IPublication.PUBLICATION_MONTH_FIELD, month);
	}

	@Override
	public String getMonth() {
		return data.get(IPublication.PUBLICATION_MONTH_FIELD).getAsString();
	}


	@Override
	public JsonObject getData() {
		return data;
	}

}
