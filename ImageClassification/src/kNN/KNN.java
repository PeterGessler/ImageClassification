package kNN;

import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import utils.ClassificationUtils;
import utils.MathFunction;
import expAnalyzer.IImageFeatureInformation;
import expGen.container.ImageCluster;
import expGen.container.ImageData;

public class KNN extends Thread {

	private String teClName;
	private String teId;
	private int kValue;
	private boolean isCorrectClass = false;
	private List<ImageCluster> clRepresenter;
	private List<ImageData> imgFeaTrSet = null;
	private List<ImageData> imgFeaTeSet = null;

	public KNN(String clName, String teId, int kValue,
			List<ImageCluster> repClData,
			IImageFeatureInformation imgFeaDatabase) {
		this.teClName = clName;
		this.teId = teId;
		this.kValue = kValue;
		this.clRepresenter = repClData;
		this.imgFeaTrSet = imgFeaDatabase.getImageFeaTrSet();
		this.imgFeaTeSet = imgFeaDatabase.getImageFeaTeSet();
	}

	@Override
	public void run() {

		ImageData imgTeData = ClassificationUtils.findImageData(imgFeaTeSet,
				teId);

		Hashtable<String, Double> recToImg = new Hashtable<String, Double>();
		int sleepCnt = 0;

		for (ImageCluster imgRepCl : clRepresenter) {

			for (String repId : imgRepCl.getIds()) {

				ImageData repImgData = ClassificationUtils.findImageData(
						imgFeaTrSet, repId);

				double euclDistance = MathFunction.euclideanDistance(
						imgTeData.getFeatures(), repImgData.getFeatures());

				if (recToImg.size() != kValue) {
					recToImg.put(imgRepCl.getName(), euclDistance);
				} else {
					replaceMaxValueEntry(recToImg, imgRepCl.getName(),
							euclDistance);
				}
			}
			if (sleepCnt < 50) {
				sleepCnt++;
			} else {
				try {
					sleepCnt = 0;
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		Collection<String> recCl = recToImg.keySet();
		Iterator<String> itr = recCl.iterator();

		int tmpT = 0;
		int tmpF = 0;

		// calculate correct and incorrect recognitions
		while (itr.hasNext()) {

			if (itr.next().equals(teClName))
				tmpT++;
			else
				tmpF++;
		}

		// add test image to correct or incorrect classified
		if (tmpT >= tmpF)
			isCorrectClass = true;
	}

	public boolean isCorrectClassified() {
		return this.isCorrectClass;
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
		}
	}
}
