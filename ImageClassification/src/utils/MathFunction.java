package utils;

import java.util.List;

import expGen.container.ImageCluster;
import expGen.container.ImageData;

/**
 * Calculation functions.
 * 
 * @author Peter Gessler
 *
 */
public class MathFunction {

	/**
	 * Get cluster center as n-dim vector.
	 * 
	 * @param cluster
	 * @param imageFeaData
	 * @return
	 */
	public static double[] clusterCenter(ImageCluster cluster,
			List<ImageData> imageFeaData) {

		double[] center = new double[imageFeaData.get(0).getFeatures().length];
		int centerPos = 0;

		while (centerPos < center.length) {

			double summarizedVal = 0;

			// summarize values in single dimension and divide by elements in set.
			for (String imageId : cluster.getIds()) {
				double[] imageFeatures = ClassificationUtils.findImageData(
						imageFeaData, imageId).getFeatures();
				summarizedVal = summarizedVal + imageFeatures[centerPos];
			}
			center[centerPos] = new Double(summarizedVal) / new Double(cluster.getSetSize());

			centerPos++;
		}
		return center;
	}

	/**
	 * Calculate euclidean Distance of two feature vectors.
	 * 
	 * @param features1
	 * @param features2
	 * @return
	 */
	public synchronized static double euclideanDistance(double[] features1, double[] features2) {
		
		double distance = 0;
		
		for (int pos = 0; pos < features1.length; pos++) {
			distance = distance + Math.pow((features1[pos] - features2[pos]), 2);
		}
		return Math.sqrt(distance);
	}
}
