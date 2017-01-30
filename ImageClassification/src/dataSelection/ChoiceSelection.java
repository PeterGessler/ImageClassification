package dataSelection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.ArrayUtils;

import expGen.ICtrlInformation;
import expGen.container.DataSet;
import expGen.container.ImageCluster;
import expGen.container.ImageData;

/**
 * Concrete data selection process to divide raw image data set in test-set,
 * adaption-set and four training sets.
 * 
 * First step: Select $l images (random selection) for each image class and put
 * these images in test-set object. Remove images in raw image set.
 * 
 * Second step: Select $m images (random selection) for each image class and put
 * these images in adaption-set object. Remove images in raw image set.
 * 
 * Third step: Select $n images (random selection) for each image class and put
 * these images in training-set object. Repeat the step four times.
 * 
 * @author Peter Gessler & Martin Buschack
 *
 */
public class ChoiceSelection extends ADataSelection {

	/**
	 * Number of training data objects per class.
	 */
	private int numTrData = 0;

	/**
	 * Number of optimize k-value objects per class.
	 */
	private int numOptKValue = 0;

	/**
	 * Number of test data objects per class.
	 */
	private int numTeData = 0;

	/**
	 * Number of training rounds.
	 */
	private final static int TRAINIG_ROUNDS = 4;

	// constructor
	public ChoiceSelection(int numTrData, int numOptKValue, int numTeData) {

		this.numTrData = numTrData;
		this.numOptKValue = numOptKValue;
		this.numTeData = numTeData;
	}

	@Override
	public void startSplitProcess() {

		int minDataNum = numTrData + numOptKValue + numTeData;
		List<ImageCluster> trimmedSet = getTrimmedSet(
				processModel.getImageDb(), minDataNum);

		// generate adaption- and test-set
		processModel
				.setAdaptKValSet(addElementsToSet(numOptKValue, trimmedSet));
		processModel.setTeSet(addElementsToSet(numTeData, trimmedSet));

		for (int pos = 0; pos < TRAINIG_ROUNDS; pos++) {
			DataSet trSet = calculateTrainingSet(numTrData, trimmedSet);
			processModel.addTrSet(pos, trSet);
		}

	}

	private void addImageIdToSet(List<ImageCluster> set, String clusterName,
			String id) {

		Iterator<ImageCluster> setIt = set.iterator();

		while (setIt.hasNext()) {

			ImageCluster setElement = setIt.next();

			if (setElement.getName().equals(clusterName)) {
				setElement.addId(id);
				return;
			}
		}

		ImageCluster newCl = new ImageCluster(clusterName);
		newCl.addId(id);
		set.add(newCl);
	}

	/**
	 * Generate {@link DataSet} object with {@link ImageCluster} and
	 * {@link ImageData} list. Depend by parameter dataNum, system randomly
	 * selected |dataNum| elements (image id's) from each {@link ImageCluster}
	 * and put them to {@link DataSet} object. Remove all selected image id's in
	 * {@link ImageCluster} objects.
	 * 
	 * 
	 * @param dataNum
	 * @param set
	 * @return
	 */
	private DataSet addElementsToSet(int dataNum, List<ImageCluster> set) {

		List<ImageCluster> imgCluster = new ArrayList<ImageCluster>();
		List<ImageData> imgData = new ArrayList<ImageData>();

		for (ImageCluster cluster : set) {

			int cnt = 0;

			while (cnt != dataNum) {
				Random rndmRed = new Random();
				int pos = rndmRed.nextInt(cluster.getSetSize());

				String id = cluster.getIds().get(pos);
				addImageIdToSet(imgCluster, cluster.getName(), id);
				cluster.removeId(id);

				// find associated ImageData object with feature values.
				ImageData selectedImgData = findAssociatedImageFeatures(
						processModel.getImageFeaturesDb(), id);

				imgData.add(selectedImgData);
				processModel.getImageFeaturesDb().remove(imgData);
				cnt++;
			}
		}

		return new DataSet(imgCluster, imgData);
	}

	/**
	 * Same process like top method without remove command. Method store
	 * selected image id's temporary to get different id's in single
	 * training-set. So it's possible that selected id's depend by image class
	 * between training-set different equal but not in an single image class.
	 * 
	 * @param dataNum
	 * @param set
	 * @return
	 */
	private DataSet calculateTrainingSet(int dataNum, List<ImageCluster> set) {

		Iterator<ImageCluster> clusters = set.iterator();

		// include all training data to lists
		List<ImageCluster> trCluster = new ArrayList<ImageCluster>();
		List<ImageData> trImgData = new ArrayList<ImageData>();

		while (clusters.hasNext()) {

			ImageCluster cluster = clusters.next();
			String[] selectedIds = new String[dataNum];
			int cnt = 0;

			while (cnt != dataNum) {

				Random rnd = new Random();
				int pos = rnd.nextInt(cluster.getSetSize());

				String id = cluster.getIds().get(pos);

				// check selected id with stored image class id's in the past.
				if ((cnt == 0) || !(ArrayUtils.contains(selectedIds, id))) {
					selectedIds[cnt] = id;
					addImageIdToSet(trCluster, cluster.getName(), id);
					cnt++;
				} else {
					continue;
				}

				ImageData imgData = findAssociatedImageFeatures(
						processModel.getImageFeaturesDb(), id);

				trImgData.add(imgData);
			}
		}

		return new DataSet(trCluster, trImgData);
	}

	@Override
	public String getSignature() {
		return "choice";
	}

	@Override
	public void setProcessModel(ICtrlInformation processModel) {
		super.processModel = processModel;
	}

}
