/*
 * Copyright 2018 TopicQuests Foundation
 *  This source code is available under the terms of the Affero General Public License v3.
 *  Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
 */
package org.topicquests.asr.nlp.doc;

import java.util.Map;

import org.topicquests.asr.nlp.api.IGrant;

import com.google.gson.JsonObject;
/**
 * @author jackpark
 *
 */
public class GrantPojo implements IGrant {
	private JsonObject data;
	/**
	 * 
	 */
	public GrantPojo() {
		data = new JsonObject();
	}

	/**
	 * @param map
	 */
	public GrantPojo(JsonObject map) {
		data = map;
	}

	/* (non-Javadoc)
	 * @see org.topicquests.hyperbrane.api.IGrant#setGrantId()
	 */
	@Override
	public void setGrantId(String id) {
		data.addProperty(IGrant.GRANT_ID, id);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.hyperbrane.api.IGrant#getGrantId()
	 */
	@Override
	public String getGrantId() {
		return data.get(IGrant.GRANT_ID).getAsString();
	}

	/* (non-Javadoc)
	 * @see org.topicquests.hyperbrane.api.IGrant#setAgency(java.lang.String)
	 */
	@Override
	public void setAgency(String agency) {
		data.addProperty(IGrant.AGENCY, agency);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.hyperbrane.api.IGrant#getAgency()
	 */
	@Override
	public String getAgency() {
		return data.get(IGrant.AGENCY).getAsString();
	}

	/* (non-Javadoc)
	 * @see org.topicquests.hyperbrane.api.IGrant#setCountry(java.lang.String)
	 */
	@Override
	public void setCountry(String country) {
		data.addProperty(IGrant.COUNTRY, country);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.hyperbrane.api.IGrant#getCountry()
	 */
	@Override
	public String getCountry() {
		return data.get(IGrant.COUNTRY).getAsString();
	}

	@Override
	public JsonObject getData() {
		return data;
	}

}
