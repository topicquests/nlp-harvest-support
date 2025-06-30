/**
 * 
 */
package org.topicquests.asr.nlp.api;

import java.io.File;

/**
 * @author jackpark
 * <p>The process is to take XML documents from PubMed</br>
 * 	or PMC and turn them into JSON documents which can be further processed</p>
 * <p> we have two options:<br/>
 * <ol><li>generate JSON files to be read</li>
 * <li>ship JSON out over a wire to a server such as Kafka or Reddis</li></ol></p>
 */
public interface IProcessModel {

	/**
	 * Process an individual file
	 * @param xml
	 * @return
	 */
	IResult processPubMedXML(File xml);
	
	/**
	 * Process all files in a given directory
	 * @param directory
	 * @return
	 */
	IResult processPubMedDirectory(String directory);
	
	/**
	 * Process an individual file
	 * @param xml
	 * @return
	 */
	IResult processPMCdXML(File xml);
	
	/**
	 * Process all files in a given directory
	 * @param directory
	 * @return
	 */
	IResult processPMCDirectory(String directory);
}
