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

/**
 * 
 * @author jackpark
 *
 */
public class StringUtil {
	public StringUtil() {
	}

	public static String stripNonCharCodepoints(String input) {
		StringBuilder retval = new StringBuilder();

		for (int i = 0; i < input.length(); ++i) {
			char ch = input.charAt(i);
			if (ch % 65536 != '\uffff' && ch % 65536 != '\ufffe' && (ch <= '\ufdd0' || ch >= '\ufdef') && (ch > 31 || ch == 9 || ch == 10 || ch == 13)) {
				retval.append(ch);
			}
		}

		return retval.toString();
	}
}
