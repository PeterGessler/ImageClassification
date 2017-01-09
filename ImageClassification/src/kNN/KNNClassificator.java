package kNN;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import expAnalyzer.IImageFeatureInformation;
import expGen.container.ImageCluster;

/**
 * Classify images in cluster by image features and compare recognition result
 * with correct result.
 * 
 * @author Peter Gessler
 *
 */
public class KNNClassificator {

	private List<ImageCluster> repClData = null;
	private IImageFeatureInformation imgFeaDatabases = null;

	public KNNClassificator(List<ImageCluster> repClData,
			IImageFeatureInformation imgFeaDatabases) {
		this.repClData = repClData;
		this.imgFeaDatabases = imgFeaDatabases;
	}

	public CRR classifyClMember(ImageCluster imgTeCluster, int kValue) {

		int cRec = 0;
		int wRec = 0;

		HashSet<KNN> clProcs = new HashSet<KNN>();
		
		// classify test image to correct (own) or incorrect (other) image class
		for (String teId : imgTeCluster.getIds()) {
			clProcs.add(new KNN(imgTeCluster.getName(), teId, kValue, repClData, imgFeaDatabases));
		}
		
		Iterator<KNN> itrStartKNN = clProcs.iterator();
		while (itrStartKNN.hasNext())
			itrStartKNN.next().start();
		
		Iterator<KNN> itrStopKNN = clProcs.iterator();
		while (itrStopKNN.hasNext())
			try {
				KNN kNN = itrStopKNN.next();
				kNN.join();
				
				if (kNN.isCorrectClassified())
					cRec++;
				else
					wRec++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return new CRR(imgTeCluster.getName(), cRec, wRec);
	}
}
