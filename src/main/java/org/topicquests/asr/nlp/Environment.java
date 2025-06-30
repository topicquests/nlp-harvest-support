/**
 * 
 */
package org.topicquests.asr.nlp;

import java.util.Map;

import org.topicquests.asr.nlp.api.IProcessModel;
import org.topicquests.asr.nlp.model.ProcessModel;
import org.topicquests.asr.nlp.parsers.PMCPullParser;
import org.topicquests.asr.nlp.parsers.PubMedReportPullParser;
import org.topicquests.asr.nlp.config.ConfigPullParser;

/**
 * 
 */
public class Environment {
	private PubMedReportPullParser pubmedPP;
	private PMCPullParser pmcPP;
	private IProcessModel model;
	private Map<String,Object>properties;
	private final String configPath = "config/props.xml";
	/**
	 * 
	 */
	public Environment() {
		//TODO logging setup
		// config
		ConfigPullParser p = new ConfigPullParser(configPath);
		properties = p.getProperties();

		pubmedPP = new PubMedReportPullParser(this);
		pmcPP = new PMCPullParser(this);
		model = new ProcessModel(this);
	}
	
	public PubMedReportPullParser getPubMedParser() {
		return pubmedPP;
	}

	public PMCPullParser getPMCParser() {
		return pmcPP;
	}
	
	public IProcessModel getModel() {
		return model;
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
}
