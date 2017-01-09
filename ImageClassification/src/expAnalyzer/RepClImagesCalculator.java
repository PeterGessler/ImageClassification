package expAnalyzer;

import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import utils.ClassificationUtils;
import utils.MathFunction;
import expGen.container.ImageCluster;
import expGen.container.ImageData;

public class RepClImagesCalculator extends Thread {

	private ImageCluster cluster = null;
	private List<ImageData> imageFeaTrSet = null;
	private int clRepsNum = 0;
	private Hashtable<String, Double> candidates = null;

	public RepClImagesCalculator(ImageCluster cluster,
			List<ImageData> imageFeaTrSet, int clRepsNum) {

		this.cluster = cluster;
		this.imageFeaTrSet = imageFeaTrSet;
		this.clRepsNum = clRepsNum;
	}

	@Override
	public void run() {

		double[] clusterCenter = MathFunction.clusterCenter(cluster,
				imageFeaTrSet);

		List<String> imageIds = cluster.getIds();
		candidates = new Hashtable<String, Double>();

		// update candidate list if euclidean distance of current imageId is
		// smaller
		// as another distance in candidate list.
		for (String imageId : imageIds) {
			double[] imgFeatures = ClassificationUtils.findImageData(
					imageFeaTrSet, imageId).getFeatures();
			double eucDist = MathFunction.euclideanDistance(clusterCenter,
					imgFeatures);

			if (candidates.size() != clRepsNum) {
				candidates.put(imageId, new Double(eucDist));
				continue;
			}

			replaceMaxValueEntry(candidates, imageId, eucDist);
		}
	}

	public String getClusterName() {
		return this.cluster.getName();
	}

	public Hashtable<String, Double> getCandidates() {
		return this.candidates;
	}

	public void replaceMaxValueEntry(Hashtable<String, Double> table,
			String newKey, double newValue) {

		Double maxValue = Collections.max(table.values());

		if (newValue < maxValue) {
			String associatedKey = table
					.entrySet()
					.stream()
					.max((entry1, entry2) -> entry1.getValue() > entry2
							.getValue() ? 1 : -1).get().getKey();

			table.remove(associatedKey);
			table.put(newKey, newValue);

			System.out.println("New entry: " + newKey + " | " + newValue + "<"
					+ maxValue);
		}
	}
}
