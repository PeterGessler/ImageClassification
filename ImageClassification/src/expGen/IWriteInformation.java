package expGen;

import expGen.container.DataSet;


/**
 * Interface return process model informations.
 * 
 * @author Peter Gessler
 *
 */
public interface IWriteInformation {
	
	public DataSet getTrSet (Integer key);
	
	public int sizeOfTrSets();
	
	public DataSet getAdaptKSet();
	
	public DataSet getTeSet();
}
