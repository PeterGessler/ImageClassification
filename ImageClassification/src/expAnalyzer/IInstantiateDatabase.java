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
	
	public void addTrSet(Instances trSet);
	
	public void setAdaptSet(Instances adaptSet);
	
	public void setTeSet(Instances teSet);
}
