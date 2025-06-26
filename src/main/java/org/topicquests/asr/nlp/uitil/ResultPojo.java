/*
 * Copyright 2020 TopicQuests
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.topicquests.asr.nlp.uitil;

import org.topicquests.asr.nlp.api.IResult;

/**
 * 
 * @author jackpark
 *
 */
public class ResultPojo implements IResult {
	private String errorString = "";
	private boolean isError = false;
	private Object returnObject = null;
	private Object returnObjectA = null;

	public ResultPojo() {
	}

	public ResultPojo(Object v) {
		this.setResultObject(v);
	}

	public boolean hasError() {
		return this.isError;
	}

	public String getErrorString() {
		return this.errorString;
	}

	public void addErrorString(String e) {
		this.errorString = this.errorString + "; " + e;
		this.isError = true;
	}

	public Object getResultObject() {
		return this.returnObject;
	}

	public void setResultObject(Object v) {
		this.returnObject = v;
	}

	public Object getResultObjectA() {
		return this.returnObjectA;
	}

	public void setResultObjectA(Object v) {
		this.returnObjectA = v;
	}
}
