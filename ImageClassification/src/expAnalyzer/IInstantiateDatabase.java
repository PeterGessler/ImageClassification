package expAnalyzer;

import java.util.List;

import expGen.container.ImageCluster;
import expGen.container.ImageData;

/**
 * Set experiment database informations.
 * 
 * @author Peter Gessler
 *
 */
public interface IInstantiateDatabase {

	public String getExpFolderPath();
	
	public void setImageTrSet(List<ImageCluster> imageTrSet);
	
	public void setImageTeSet(List<ImageCluster> imageTeSet);
	
	public void setImageFeaTrSet(List<ImageData> imageFeaTrSet);
	
	public void setImageFeaTeSet(List<ImageData> imageFeaTeSet);
}
