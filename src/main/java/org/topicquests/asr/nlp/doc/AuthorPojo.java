/**
 * Copyright 2018, TopicQuests Foundation
 *  This source code is available under the terms of the Affero General Public License v3.
 *  Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
 */
package org.topicquests.asr.nlp.doc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

import org.topicquests.asr.nlp.api.IAuthor;

import com.google.gson.JsonObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

/**
 * @author jackpark
 *
 */
public class AuthorPojo implements IAuthor {
	private JsonObject data;
	private Gson gson = new Gson();
	/**
	 * 
	 */
	public AuthorPojo() {
		data = new JsonObject();
	}

	/**
	 * @param map
	 */
	public AuthorPojo(JsonObject map) {
		data = map;
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IAuthor#setAuthorTitle(java.lang.String)
	 */
	@Override
	public void setAuthorTitle(String title) {
		data.addProperty(IAuthor.TITLE_FIELD, title);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IAuthor#getAuthorTitle()
	 */
	@Override
	public String getAuthorTitle() {
		return data.get(IAuthor.TITLE_FIELD).getAsString();
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IAuthor#setAuthorFullName(java.lang.String)
	 */
	@Override
	public void setAuthorFullName(String fullName) {
		data.addProperty(IAuthor.FULL_NAME_FIELD, fullName);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IAuthor#getAuthorFullName()
	 */
	@Override
	public String getAuthorFullName() {
		String result = null;
		Object o = data.get(IAuthor.FULL_NAME_FIELD);
		if (o == null) {
			String firstName = null;
			JsonArray l = listAuthorFirstNames();
			if (l != null && !l.isEmpty())
				firstName = l.get(0).getAsString();
			if (firstName == null)
				firstName = getInitials();
			if (firstName == null)
				firstName = getAuthorNickName();
			result = firstName+" "+getAuthorLastName();
			data.addProperty(IAuthor.FULL_NAME_FIELD, result);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IAuthor#setAuthorFirstName(java.lang.String)
	 */
	@Override
	public void addAuthorFirstName(String firstName) {
		JsonArray l = listAuthorFirstNames();
		if (l == null)
			l = new JsonArray();
		if (!contains(l, firstName))
			l.add(firstName);
		data.add(IAuthor.FIRST_NAME_FIELD, l);
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
	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IAuthor#getAuthorFirstName()
	 */
	@Override
	public JsonArray listAuthorFirstNames() {
		Object o = data.get(IAuthor.FIRST_NAME_FIELD);
		if (o == null) {
			JsonArray ja = new JsonArray();
			data.add(IAuthor.FIRST_NAME_FIELD, ja);
			return ja;
		}
		return data.get(IAuthor.FIRST_NAME_FIELD).getAsJsonArray();
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IAuthor#setAuthorMiddleName(java.lang.String)
	 */
	@Override
	public void setAuthorMiddleName(String middleName) {
		data.addProperty(IAuthor.MIDDLE_NAME_FIELD, middleName);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IAuthor#getAuthorMiddleName()
	 */
	@Override
	public String getAuthorMiddleName() {
		Object o = data.get(IAuthor.MIDDLE_NAME_FIELD);
		if (o == null)
			return null;
		return data.get(IAuthor.MIDDLE_NAME_FIELD).getAsString();
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IAuthor#setAuthorLastName(java.lang.String)
	 */
	@Override
	public void setAuthorLastName(String lastName) {
		data.addProperty(IAuthor.LAST_NAME_FIELD, lastName);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IAuthor#getAuthorLastName()
	 */
	@Override
	public String getAuthorLastName() {
		Object o = data.get(IAuthor.LAST_NAME_FIELD);
		if (o == null)
			return null;
		else
			return data.get(IAuthor.LAST_NAME_FIELD).getAsString();
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IAuthor#setAuthorJrSr(java.lang.String)
	 */
	@Override
	public void setAuthorSuffix(String t) {
		data.addProperty(IAuthor.SUFFIX, t);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IAuthor#getAuthorJrSr()
	 */
	@Override
	public String getAuthorSuffix() {
		Object o = data.get(IAuthor.SUFFIX);
		if (o == null)
			return null;
		else
			return data.get(IAuthor.SUFFIX).getAsString();
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IAuthor#setAuthorDegree(java.lang.String)
	 */
	@Override
	public void setAuthorDegree(String deg) {
		data.addProperty(IAuthor.DEGREE_FIELD, deg);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IAuthor#getAuthorDegree()
	 */
	@Override
	public String getAuthorDegree() {
		Object o = data.get(IAuthor.DEGREE_FIELD);
		if (o == null)
			return null;
		else
			return data.get(IAuthor.DEGREE_FIELD).getAsString();
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IAuthor#setAuthorInitials(java.lang.String)
	 */
	@Override
	public void setAuthorInitials(String initials) {
		data.addProperty(IAuthor.INITIALS_FIELD, initials);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IAuthor#getInitials()
	 */
	@Override
	public String getInitials() {
		Object o = data.get(IAuthor.INITIALS_FIELD);
		if (o == null)
			return null;
		return data.get(IAuthor.INITIALS_FIELD).getAsString();
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IAuthor#setAuthorNickName(java.lang.String)
	 */
	@Override
	public void setAuthorNickName(String name) {
		data.addProperty(IAuthor.NICK_NAME_FIELD, name);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IAuthor#getAuthorNickName()
	 */
	@Override
	public String getAuthorNickName() {
		Object o = data.get(IAuthor.NICK_NAME_FIELD);
		if (o == null)
			return null;
		return data.get(IAuthor.NICK_NAME_FIELD).getAsString();
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IAuthor#setDocumentId(java.lang.String)
	 */
	@Override
	public void setDocumentId(String id) {
		data.addProperty(IAuthor.DOC_ID_FIELD, id);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IAuthor#getDocumentId(java.lang.String)
	 */
	@Override
	public String getDocumentId(String id) {
		Object o = data.get(IAuthor.DOC_ID_FIELD);
		if (o == null)
			return null;
		return data.get(IAuthor.DOC_ID_FIELD).getAsString();
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IAuthor#setDocumentTitle(java.lang.String)
	 */
	@Override
	public void setDocumentTitle(String title) {
		data.addProperty(IAuthor.DOC_TITLE_FIELD, title);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IAuthor#getDocumentTitle()
	 */
	@Override
	public String getDocumentTitle() {
		Object o = data.get(IAuthor.DOC_TITLE_FIELD);
		if (o == null)
			return null;
		return data.get(IAuthor.DOC_TITLE_FIELD).getAsString();
	}


	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IAuthor#setPublisherName(java.lang.String)
	 */
	@Override
	public void setPublisherName(String name) {
		data.addProperty(IAuthor.PUBLISHER_NAME_FIELD, name);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IAuthor#getPublisherName()
	 */
	@Override
	public String getPublisherName() {
		Object o = data.get(IAuthor.PUBLISHER_NAME_FIELD);
		if (o == null)
			return null;
		return data.get(IAuthor.PUBLISHER_NAME_FIELD).getAsString();
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IAuthor#setPublisherLocator(java.lang.String)
	 */
	@Override
	public void setPublisherLocator(String locator) {
		data.addProperty(IAuthor.PUBLISHER_LOCATOR_FIELD, locator);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IAuthor#getPublisherLocator()
	 */
	@Override
	public String getPublisherLocator() {
		Object o = data.get(IAuthor.PUBLISHER_LOCATOR_FIELD);
		if (o == null)
			return null;
		return data.get(IAuthor.PUBLISHER_LOCATOR_FIELD).getAsString();
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IAuthor#setPublicationName(java.lang.String)
	 */
	@Override
	public void setPublicationName(String name) {
		data.addProperty(IAuthor.PUBLICATION_NAME_FIELD, name);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IAuthor#getPublicationName()
	 */
	@Override
	public String getPublicationName() {
		Object o = data.get(IAuthor.PUBLICATION_NAME_FIELD);
		if (o == null)
			return null;
		return data.get(IAuthor.PUBLICATION_NAME_FIELD).getAsString();
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IAuthor#setPublicationLocator(java.lang.String)
	 */
	@Override
	public void setPublicationLocator(String locator) {
		data.addProperty(IAuthor.PUBLICATION_LOCATOR, locator);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.os.asr.api.IAuthor#getPublicationLocator()
	 */
	@Override
	public String getPublicationLocator() {
		Object o = data.get(IAuthor.PUBLICATION_LOCATOR);
		if (o == null)
			return null;
		return data.get(IAuthor.PUBLICATION_LOCATOR).getAsString();
	}


	@Override
	public void setAffiliationLocator(String locator) {
		data.addProperty(IAuthor.AFFILIATION_LOCATOR, locator);
	}

	@Override
	public String getAffiliationLocator() {
		Object o = data.get(IAuthor.AFFILIATION_LOCATOR);
		if (o == null)
			return null;
		return data.get(IAuthor.AFFILIATION_LOCATOR).getAsString();
	}

	@Override
	public void setAuthorLocator(String locator) {
		data.addProperty(IAuthor.AUTHOR_LOCATOR, locator);
	}

	@Override
	public String getAuthorLocator() {
		Object o = data.get(IAuthor.AUTHOR_LOCATOR);
		if (o == null)
			return null;
		return data.get(IAuthor.AUTHOR_LOCATOR).getAsString();
	}

	@Override
	public void setId(String id) {
		data.addProperty(IAuthor.ID, id);
	}

	@Override
	public String getId() {
		Object o = data.get(IAuthor.ID);
		if (o == null)
			return null;
		return data.get(IAuthor.ID).getAsString();
	}

	@Override
	public void addAffiliationName(String name) {
		JsonArray l = listAffiliationNames();
		if (!contains(l, name))
			l.add(name);		
	}

	@Override
	public JsonArray listAffiliationNames() {
		Object o = data.get(IAuthor.AFFILIATION_NAME_FIELD);
		
		if (o == null) {
			JsonArray ja = new JsonArray();
			data.add(IAuthor.AFFILIATION_NAME_FIELD, ja);
			return ja;
		}
		return data.get(IAuthor.AFFILIATION_NAME_FIELD).getAsJsonArray();
	}

	@Override
	public void setAuthorEmail(String email) {
		data.addProperty(IAuthor.EMAIL_FIELD, email);
	}

	@Override
	public String getAuthorEmail() {
		Object o = data.get(IAuthor.EMAIL_FIELD);
		if (o == null)
			return null;
		return data.get(IAuthor.EMAIL_FIELD).getAsString();
	}

	@Override
	public JsonObject getData() {
		return data;
	}
	
}
