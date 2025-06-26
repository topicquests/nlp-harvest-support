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

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.topicquests.asr.nlp.api.IRemovableCache;


/**
 * 
 * @author jackpark
 *
 */
public class LRUCache implements IRemovableCache {
	private Map<Object, Object> cache;
	private int MAX_SIZE = 100;

	public LRUCache(int maxSize) {
		this.MAX_SIZE = maxSize;
		this.cache = new LinkedHashMap<Object, Object>(maxSize + 1, 0.75F, true) {
			private static final long serialVersionUID = 1L;

			protected boolean removeEldestEntry(Entry eldest) {
				return this.size() > LRUCache.this.MAX_SIZE;
			}
		};
	}

	public void add(Object key, Object value) {
		Map var3 = this.cache;
		synchronized (this.cache) {
			this.cache.put(key, value);
		}
	}

	public Object get(Object key) {
		Map var2 = this.cache;
		synchronized (this.cache) {
			return this.cache.containsKey(key) ? this.cache.get(key) : null;
		}
	}

	public void remove(Object key) {
		Map var2 = this.cache;
		synchronized (this.cache) {
			if (this.cache.containsKey(key)) {
				this.cache.remove(key);
			}

		}
	}

	public void clear() {
		Map var1 = this.cache;
		synchronized (this.cache) {
			this.cache.clear();
		}
	}

	public int size() {
		Map var1 = this.cache;
		synchronized (this.cache) {
			return this.cache.size();
		}
	}
}
