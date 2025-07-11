/**
 * 
 */
package org.topicquests.asr.nlp;

import java.util.Map;

import org.topicquests.asr.nlp.parsers.PMCPullParser;
import org.topicquests.asr.nlp.parsers.PubMedPullParser;
import org.topicquests.support.api.IEnvironment;
import org.topicquests.asr.nlp.config.ConfigPullParser;

/**
 * 
 */
public abstract class Environment implements IEnvironment {
	private PubMedPullParser pubmedPP;
	private PMCPullParser pmcPP;
	private Map<String,Object>properties;
	private final String configPath = "config/props.xml";
	/**
	 * @author jackpark
	 */
	public Environment() {
		//TODO logging setup
		// config
		ConfigPullParser p = new ConfigPullParser(configPath);
		properties = p.getProperties();

		pubmedPP = new PubMedPullParser(this);
		pmcPP = new PMCPullParser(this);
	}
	
	public PubMedPullParser getPubMedParser() {
		return pubmedPP;
	}

	public PMCPullParser getPMCParser() {
		return pmcPP;
	}
	
	
	public Map<String, Object> getProperties() {
		return properties;
	}
	
	public String getStringProperty(String key) {
		return (String)properties.get(key);
	}
	//////////////////
	// Config
	/////////////////
	public Object getProperty(String key) {
		return properties.get(key);
	}

	////////////////////
	// Logging
	// TODO
	////////////////////
	
	public void logError(String msg, Exception e) {
		System.out.println("Error: +msg"); //TODO
	}
	
	public void logDebug(String msg) {
		System.out.println("Log: +msg"); //TODO

	}

	@Override
	public abstract void shutDown();
}
