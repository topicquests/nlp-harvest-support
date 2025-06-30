/**
 * 
 */
package org.topicquests.asr.nlp;

import org.topicquests.asr.nlp.api.IProcessModel;
import org.topicquests.asr.nlp.model.ProcessModel;
import org.topicquests.asr.nlp.parsers.PMCPullParser;
import org.topicquests.asr.nlp.parsers.PubMedReportPullParser;

/**
 * 
 */
public class Environment {
	private PubMedReportPullParser pubmedPP;
	private PMCPullParser pmcPP;
	private IProcessModel model;
	/**
	 * 
	 */
	public Environment() {
		//TODO logging setup
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
