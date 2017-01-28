package dataSelection;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import expGen.ICtrlInformation;
import expGen.container.ImageCluster;
import expGen.container.ImageData;

public abstract class ADataSelection {

	protected ICtrlInformation processModel = null;

	
	public abstract void setProcessModel(ICtrlInformation processModel);

	public abstract void startSplitProcess();

	public abstract String getSignature();

	/**
	 * Remove all cluster with to few elements.
	 * 
	 * @param imgClusters
	 * @param minelementNum
	 * @return
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
	 * Select ImageData object with equal id from input parameter list.
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
