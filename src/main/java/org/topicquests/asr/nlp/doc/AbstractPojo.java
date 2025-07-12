/**
 * 
 */
package org.topicquests.asr.nlp.doc;

import org.topicquests.asr.nlp.api.IAbstract;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * 
 */
public class AbstractPojo implements IAbstract {
	public static final String
		PARAGRAPHS		= "paras",
		SECTIONS		= "sects",
		TITLE			= "titl",
		TEXT			= "txt",
		LANG			= "lang";
	
	private JsonObject data;
	/**
	 * 
	 */
	public AbstractPojo() {
		data = new JsonObject();
	}
	public AbstractPojo(JsonObject data) {
		this.data = data;
	}
	
	@Override
	public void addSection(String title, String text, String language) {
		JsonArray ja = listSections();
		JsonObject jo = new JsonObject();
		jo.addProperty(TITLE, title);
		jo.addProperty(TEXT, text);
		jo.addProperty(LANG, language);
		ja.add(jo);

	}

	@Override
	public void addParagraph(String text, String language) {
		System.out.println("AddPara-");
		JsonArray ja = listParagraphs();
		JsonObject jo = new JsonObject();
		jo.addProperty(TEXT, text);
		jo.addProperty(LANG, language);
		ja.add(jo);
		System.out.println("AddPara+");
	}

	@Override
	public JsonObject getData() {
		return data;
	}

	/**
	 * 
	 * @return does not return ){@code null}
	 */
	JsonArray listSections() {
		Object o = data.get(SECTIONS);
		JsonArray ja;
		if (o == null) {
			ja = new JsonArray();
			data.add(SECTIONS, ja);
		} else
			ja = data.get(SECTIONS).getAsJsonArray();
		return ja;
	}
	
	/**
	 * 
	 * @return does not return ){@code null}
	 */
	JsonArray listParagraphs() {
		Object o = data.get(PARAGRAPHS);
		JsonArray ja;
		if (o == null) {
			ja = new JsonArray();
			data.add(PARAGRAPHS, ja);
		} else
			ja = data.get(PARAGRAPHS).getAsJsonArray();
		
		return ja;
	}
}
