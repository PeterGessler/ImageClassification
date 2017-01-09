package dataSelection;

import java.util.Random;

import expGen.ICtrlInformation;
import expGen.container.ImageCluster;
import expGen.container.ImageData;

public class FairSelection extends AAccumulatedSelection {

	public FairSelection(ICtrlInformation processModel, int minDataNum) {
		super(processModel, minDataNum);
	}

	@Override
	protected void addElementsToTeSets(int trDataNum) {
		
		for (ImageCluster cluster : processModel.getImageDb()) {

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
		return "fair";
	}
}
