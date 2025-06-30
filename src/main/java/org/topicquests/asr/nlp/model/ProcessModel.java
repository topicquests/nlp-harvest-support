/**
 * 
 */
package org.topicquests.asr.nlp.model;

import java.io.File;

import org.topicquests.asr.nlp.Environment;
import org.topicquests.asr.nlp.api.IProcessModel;
import org.topicquests.asr.nlp.api.IResult;
import org.topicquests.asr.nlp.parsers.PMCPullParser;
import org.topicquests.asr.nlp.parsers.PubMedReportPullParser;

/**
 * 
 */
public class ProcessModel implements IProcessModel {
	private Environment environment;
	private PubMedReportPullParser pubmedPP;
	private PMCPullParser pmcPP;
	/**
	 * 
	 */
	public ProcessModel(Environment env) {
		environment = env;
		pubmedPP = environment.getPubMedParser();
		pmcPP = environment.getPMCParser();
	}

	@Override
	public IResult processPubMedXML(File xml) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResult processPubMedDirectory(String directory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResult processPMCdXML(File xml) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResult processPMCDirectory(String directory) {
		// TODO Auto-generated method stub
		return null;
	}

}
