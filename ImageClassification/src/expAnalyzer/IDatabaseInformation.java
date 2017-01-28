package expAnalyzer;

import weka.core.Instances;


/**
 * Get training and test sets in {@link ExperimentModel}.
 * 
 * @author Peter Gessler
 *
 */
public interface IDatabaseInformation {

	public Instances getTrSet(int index);
	
	public Instances getAdaptSet();
	
	public Instances getTeSet();
	
	public String getEvalFolderPath();
}
