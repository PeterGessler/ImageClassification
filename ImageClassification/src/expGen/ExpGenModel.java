package expGen;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import launcher.ExpGenerator;
import utils.ClassificationUtils;
import utils.CsvDbConverter;
import expGen.container.ImageCluster;
import expGen.container.ImageData;

/**
 * Model to hold all {@link ExpGenerator} informations.
 * 
 * @author Peter Gessler
 *
 */
public class ExpGenModel implements ICtrlInformation, IWriteInformation {

	private List<ImageCluster> imageDb = null;

	private List<ImageData> imageFeaturesDb = null;

	private List<ImageCluster> imageTrSet = null;
	private List<ImageData> imageFeatureTrSet = null;

	private List<ImageCluster> imageTeSet = null;
	private List<ImageData> imageFeatureTeSet = null;

	public ExpGenModel(File imageDb, File edgeHistogram) {	

		CsvDbConverter dataConv = new CsvDbConverter(imageDb, edgeHistogram);
		
		this.imageTrSet = new ArrayList<ImageCluster>();
		this.imageFeatureTrSet = new ArrayList<ImageData>();
		this.imageTeSet = new ArrayList<ImageCluster>();
		this.imageFeatureTeSet = new ArrayList<ImageData>();

		this.imageDb = dataConv.getImageDb();
		this.imageFeaturesDb = dataConv.getImageFeaturesDb();
	}

	@Override
	public List<ImageCluster> getImageDb() {
		return this.imageDb;
	}

	@Override
	public List<ImageData> getImageFeaturesDb() {
		return this.imageFeaturesDb;
	}

	@Override
	public void addElemToImageTrSet(String clusterName, String id) {

		ImageCluster selectedCluster = ClassificationUtils.findEqualCluster(
				imageTrSet, clusterName);

		if (selectedCluster != null) {
			selectedCluster.addId(id);
		} else {
			ImageCluster newClEntry = new ImageCluster(clusterName);
			newClEntry.addId(id);
			imageTrSet.add(newClEntry);
		}
	}

	@Override
	public void addElemToImgFeaTrSet(ImageData imageData) {
		imageFeatureTrSet.add(imageData);
	}

	@Override
	public void addElemToImageTeSet(String clusterName, String id) {
		
		ImageCluster selectedCluster = ClassificationUtils.findEqualCluster(
				imageTeSet, clusterName);

		if (selectedCluster != null) {
			selectedCluster.addId(id);
		} else {
			ImageCluster newClEntry = new ImageCluster(clusterName);
			newClEntry.addId(id);
			imageTeSet.add(newClEntry);
		}

	}

	@Override
	public void addElemToImgFeaTeSet(ImageData imageData) {
		imageFeatureTeSet.add(imageData);
	}
	
	@Override
	public void setImageTeSet(List<ImageCluster> imageTeSet) {
		this.imageTeSet = imageTeSet;
		
	}

	@Override
	public void setImageFeatureTeSet(List<ImageData> imageFeatureTeSet) {
		this.imageFeatureTeSet = imageFeatureTeSet;		
	}

	@Override
	public List<ImageCluster> getImageTrSet() {
		return this.imageTrSet;
	}

	@Override
	public List<ImageData> getImageFeatureTrSet() {
		return this.imageFeatureTrSet;
	}

	@Override
	public List<ImageCluster> getImageTeSet() {
		return this.imageTeSet;
	}

	@Override
	public List<ImageData> getImageFeatureTeSet() {
		return this.imageFeatureTeSet;
	}

}
