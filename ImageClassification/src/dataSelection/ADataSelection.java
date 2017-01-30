package dataSelection;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import expGen.ICtrlInformation;
import expGen.container.ImageCluster;
import expGen.container.ImageData;

/**
 * System is designed to use different image class split strategies.
 * Each split strategy extends by {@link ADataSelection}.
 * Class contains shared methods.
 * 
 * @author Peter Gessler & Martin Buschack
 *
 */
public abstract class ADataSelection {

	/**
	 * Hold all process data.
	 */
	protected ICtrlInformation processModel = null;

	/**
	 * Set process model in class init process.
	 * @param processModel
	 */
	public abstract void setProcessModel(ICtrlInformation processModel);

	/**
	 * Contain data selection process structure.
	 */
	public abstract void startSplitProcess();

	/**
	 * @return specific data selection strategy name.
	 */
	public abstract String getSignature();

	/**
	 * Remove all image classes with to few data.
	 * 
	 * @param imgClusters
	 * @param minelementNum
	 * @return Set with {@link ImageCluster} objects - contain associated image id's
	 */
	protected List<ImageCluster> getTrimmedSet(List<ImageCluster> imgClusters, int minelementNum) {

		Iterator<ImageCluster> imgCluster = imgClusters.iterator();

		while (imgCluster.hasNext()) {
			
			ImageCluster cl = imgCluster.next();

			if (cl.getSetSize() < minelementNum)
				imgCluster.remove();

		}

		return imgClusters;
	}
	
	/**
	 * Select {@link ImageData} object with same id like input string.
	 * 
	 * @param imageFeatureData
	 * @param id
	 * @return
	 */
	protected ImageData findAssociatedImageFeatures(
			List<ImageData> imageFeatureData, String id) {

		Predicate<ImageData> pred = dataObj -> dataObj.getId().equals(id);
		ImageData imgDataObj = imageFeatureData.stream().filter(pred)
				.findFirst().orElse(null);
		return imgDataObj;
	}
}
