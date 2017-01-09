package expAnalyzer;

import java.util.List;

import expGen.container.ImageData;

/**
 * Get training and test sets image features.
 * 
 * @author Peter Gessler
 *
 */
public interface IImageFeatureInformation {

	public List<ImageData> getImageFeaTrSet();
	
	public List<ImageData> getImageFeaTeSet();
}
