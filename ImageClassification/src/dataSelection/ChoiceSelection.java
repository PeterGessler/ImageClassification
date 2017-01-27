package dataSelection;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import expGen.ICtrlInformation;
import expGen.container.ImageCluster;
import expGen.container.ImageData;

public class ChoiceSelection extends ADataSelection {

	private int numTrData = 0;
	private int numTeData = 0;
	
	public ChoiceSelection(ICtrlInformation processModel, int numTrData, int numTeData) {
		super(processModel);
		
		this.numTrData = numTrData;
		this.numTeData = numTeData;
	}

	@Override
	public void startSplitProcess() {
		
		int minDataNum = numTrData + numTeData;
		List<ImageCluster> trimmedSet = getTrimmedSet(processModel.getImageDb(), minDataNum);
		
		addElementsToTrSets(numTrData, trimmedSet);
		addElementsToTeSets(numTeData, trimmedSet);
		
	}
	
	private void addElementsToTrSets(int dataNum, List<ImageCluster> set) {

		Iterator<ImageCluster> clusters = set.iterator();
		
		while (clusters.hasNext()) {

			ImageCluster cluster = clusters.next();
			int cnt = 0;
			
			while (cnt != dataNum) {

				Random rndmRed = new Random();
				int pos = rndmRed.nextInt(cluster.getSetSize());

				String id = cluster.getIds().get(pos);
				processModel.addElemToImageTrSet(cluster.getName(), id);
				
				ImageData imgData = findAssociatedImageFeatures(
						processModel.getImageFeaturesDb(), id);
				
				processModel
						.addElemToImgFeaTrSet(imgData);
				
				cluster.removeId(id);
				processModel.getImageFeaturesDb().remove(imgData);

				cnt++;
			}
		}

	}
	
	private void addElementsToTeSets(int trDataNum, List<ImageCluster> set) {
		
		for (ImageCluster cluster : set) {

			int cnt = 0;

			while (cnt != trDataNum) {

				Random rndmRed = new Random();
				int pos = rndmRed.nextInt(cluster.getSetSize());

				String id = cluster.getIds().get(pos);
				processModel.addElemToImageTeSet(cluster.getName(), id);

				ImageData imgData = findAssociatedImageFeatures(
						processModel.getImageFeaturesDb(), id);

				processModel.addElemToImgFeaTeSet(imgData);

				cluster.removeId(id);
				processModel.getImageFeaturesDb().remove(imgData);

				cnt++;
			}
		}
	}

	@Override
	public String getSignature() {
		return "choice";
	}

}
