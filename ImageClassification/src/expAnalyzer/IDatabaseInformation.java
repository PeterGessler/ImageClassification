package expAnalyzer;

import weka.core.Instances;


/**
 * Get training and test sets in {@link ExperimentModel}.
 * 
 * @author Peter Gessler
 *
 */
public interface IDatabaseInformation {

	public Instances getImgTrSet();
	
	public Instances getImgTeSet();
	
	public String getEvalFolderPath();
}
