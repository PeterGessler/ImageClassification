package dataSelection;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import utils.ClassificationUtils;
import expGen.ICtrlInformation;
import expGen.container.ImageCluster;
import expGen.container.ImageData;

public class SingleSelection extends ADataSelection {

	public SingleSelection(ICtrlInformation processModel) {
		super(processModel);
	}

	@Override
	public void startSplitProcess() {

		List<ImageCluster> clusterSet = processModel.getImageDb();

		Iterator<ImageCluster> clusters = clusterSet.iterator();

		int[] incompatibleSortNum = {0, 1};
		
		while (clusters.hasNext()) {

			ImageCluster cluster = clusters.next();

			while (!ClassificationUtils.isCorrectNum(cluster.getSetSize(),
					incompatibleSortNum)) {
				
				Random rndmTr = new Random();
				int posTr = rndmTr.nextInt(cluster.getSetSize());

				Random rndmTe = null;
				int posTe = 0;

				do {
					rndmTe = new Random();
					posTe = rndmTe.nextInt(cluster.getSetSize());
				} while (posTr == posTe);

				String idTr = cluster.getIds().get(posTr);
				String idTe = cluster.getIds().get(posTe);

				processModel.addElemToImageTrSet(cluster.getName(), idTr);
				processModel.addElemToImageTeSet(cluster.getName(), idTe);

				ImageData imgDataTr = findAssociatedImageFeatures(
						processModel.getImageFeaturesDb(), idTr);

				ImageData imgDataTe = findAssociatedImageFeatures(
						processModel.getImageFeaturesDb(), idTe);

				processModel.addElemToImgFeaTrSet(imgDataTr);
				processModel.addElemToImgFeaTeSet(imgDataTe);

				cluster.removeId(idTr);
				cluster.removeId(idTe);

				processModel.getImageFeaturesDb().remove(imgDataTr);
				processModel.getImageFeaturesDb().remove(imgDataTe);
			}

		}
	}

	@Override
	public String getSignature() {
		
		return "single";
	}

}
