package dataSelection;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import expGen.ICtrlInformation;
import expGen.container.ImageCluster;
import expGen.container.ImageData;

public abstract class AAccumulatedSelection extends ADataSelection {

	private int minDataNum = 0; 
	
	protected AAccumulatedSelection(ICtrlInformation processModel, int minDataNum) {
		super(processModel);
		this.minDataNum = minDataNum;
	}
	
	protected abstract void addElementsToTeSets(int trDataNum);
	
	@Override
	public void startSplitProcess() {

		List<ImageCluster> trimmedSet = getTrimmedSet(processModel.getImageDb(), this.minDataNum);
		
		int minClElem = findClMinimum(trimmedSet);

		// to get even set to split
		if (minClElem % 2 != 0)
			minClElem--;

		int dataNum = minClElem / 2;

		addElementsToTrSets(dataNum, trimmedSet);
		addElementsToTeSets(dataNum);
		
	}
	
	private int findClMinimum(List<ImageCluster> imageDb) {

		int minClElem = Integer.MAX_VALUE;
		
		for (ImageCluster cluster : imageDb) {
			if (minClElem > cluster.getSetSize())
				minClElem = cluster.getSetSize();
		}
		return minClElem;
	}
	
	private void addElementsToTrSets(int dataNum, List<ImageCluster> trimmedSet) {

		Iterator<ImageCluster> clusters = trimmedSet.iterator();
		
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
}
