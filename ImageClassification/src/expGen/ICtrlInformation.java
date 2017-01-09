package expGen;

import java.util.List;

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
	
	public void addElemToImageTrSet(String cluster, String id);
	
	public void addElemToImgFeaTrSet(ImageData imageData);
	
	public void addElemToImageTeSet(String cluster, String id);
	
	public void addElemToImgFeaTeSet(ImageData imageData);
	
	public void setImageTeSet(List<ImageCluster> imageTeSet);
	
	public void setImageFeatureTeSet(List<ImageData> imageFeatureTeSet);
}
