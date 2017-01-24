package expAnalyzer;

import weka.core.Instances;

/**
 * Set experiment database informations.
 * 
 * @author Peter Gessler
 *
 */
public interface IInstantiateDatabase {

	public String getExpFolderPath();
	
	public void setImageTrSet(Instances trSet);
	
	public void setImageTeSet(Instances teSet);
}
