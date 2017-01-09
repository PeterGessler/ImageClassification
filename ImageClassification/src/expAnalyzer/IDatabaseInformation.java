package expAnalyzer;

import java.util.List;

import expGen.container.ImageCluster;

/**
 * Get training and test sets in {@link ExperimentModel}.
 * 
 * @author Peter Gessler
 *
 */
public interface IDatabaseInformation extends IImageFeatureInformation {

	public List<ImageCluster> getImageTrSet();
	
	public List<ImageCluster> getImageTeSet();
}
