package expGen;

import java.util.List;

import expGen.container.ImageCluster;
import expGen.container.ImageData;

/**
 * Interface return process model informations.
 * 
 * @author Peter Gessler
 *
 */
public interface IWriteInformation {

	public List<ImageCluster> getImageTrSet();
	
	public List<ImageData> getImageFeatureTrSet();
	
	public List<ImageCluster> getImageTeSet();
	
	public List<ImageData> getImageFeatureTeSet();
}
