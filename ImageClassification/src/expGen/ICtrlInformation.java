package expGen;

import java.util.List;

import expGen.container.DataSet;
import expGen.container.ImageCluster;
import expGen.container.ImageData;

/**
 * Interface to add and set training and test elements in  process model.
 * 
 * @author Peter Gessler
 *
 */
public interface ICtrlInformation {

	public List<ImageCluster> getImageDb();
	
	public List<ImageData> getImageFeaturesDb();
	
	public void addTrSet(Integer key, DataSet set);
	
	public void setAdaptKValSet(DataSet set);
	
	public void setTeSet(DataSet set);
}
