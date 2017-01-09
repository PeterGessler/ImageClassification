package expAnalyzer;

import java.util.List;

import expGen.container.ImageCluster;
import expGen.container.ImageData;

/**
 * Model to hold and provide training and test experiment data.
 * 
 * @author Peter Gessler
 *
 */
public class ExperimentModel implements IInstantiateDatabase, IDatabaseInformation, IImageFeatureInformation {

	private final static String COMMON_EXP_FOLDER_PATH = "database/experiments/";
	
	private static String expFolderPath;
	
	private List<ImageCluster> imageTrSet = null;
	
	private List<ImageCluster> imageTeSet = null;
	
	private List<ImageData> imageFeaTrSet = null;
	
	private List<ImageData> imageFeaTeSet = null;
	
	public ExperimentModel(String expFolderSignature) {
		
		// load raw data and convert
		expFolderPath = COMMON_EXP_FOLDER_PATH + expFolderSignature;
		ExperimentLoader expLoader = new ExperimentLoader(this);
		expLoader.loadAndConvertDatabase();
	}
	
	public String getExpFolderPath() {
		return expFolderPath;
	}

	@Override
	public void setImageTrSet(List<ImageCluster> imageTrSet) {
		this.imageTrSet = imageTrSet;
	}

	@Override
	public void setImageTeSet(List<ImageCluster> imageTeSet) {
		this.imageTeSet = imageTeSet;		
	}

	@Override
	public void setImageFeaTrSet(List<ImageData> imageFeaTrSet) {
		this.imageFeaTrSet = imageFeaTrSet;		
	}

	@Override
	public void setImageFeaTeSet(List<ImageData> imageFeaTeSet) {
		this.imageFeaTeSet = imageFeaTeSet;		
	}

	@Override
	public List<ImageCluster> getImageTrSet() {
		return this.imageTrSet;
	}

	@Override
	public List<ImageCluster> getImageTeSet() {
		return this.imageTeSet;
	}

	@Override
	public List<ImageData> getImageFeaTrSet() {
		return this.imageFeaTrSet;
	}

	@Override
	public List<ImageData> getImageFeaTeSet() {
		return this.imageFeaTeSet;
	}

}
