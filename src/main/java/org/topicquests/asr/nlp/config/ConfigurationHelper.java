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
package org.topicquests.asr.nlp.config;

import java.io.File;

/**
 * 
 * @author jackpark
 *
 */
public class ConfigurationHelper {
	public ConfigurationHelper() {
	}

	public static String findPath(String inFilePath) {
		File f = new File(inFilePath);
		if (f.exists()) {
			return inFilePath;
		} else {
			String result = "config/" + inFilePath;
			f = new File(result);
			if (f.exists()) {
				return result;
			} else {
				result = "conf/" + inFilePath;
				f = new File(result);
				if (f.exists()) {
					return result;
				} else {
					result = "cfg/" + inFilePath;
					f = new File(result);
					if (f.exists()) {
						return result;
					} else {
						throw new RuntimeException("File " + result + " not found");
					}
				}
			}
		}
	}
}
