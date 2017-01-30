package expGen;

import java.io.File;

import dataSelection.ADataSelection;
import expGen.container.DataSet;

/**
 * Class calculate new experiment Set size and fill Sets with image and image
 * feature data.
 * 
 * @author Peter Gessler & Martin Buschack
 *
 */
public class ExpGenHandler {

	private IWriteInformation processModel = null;

	public ExpGenHandler(File imageFiles, File edgeHistogram) {
		processModel = new ExpGenModel(imageFiles, edgeHistogram);
	}

	public void startSplitProcess(ADataSelection dataSelector) {

		dataSelector.setProcessModel((ICtrlInformation) processModel);
		dataSelector.startSplitProcess();

		/**
		 * Write all set files.
		 */
		AExpWriter writer = new ARFFWriter(dataSelector.getSignature());

		// write adaption set
		writer.writeExperiment(processModel.getAdaptKSet().getImageCluster(),
				processModel.getAdaptKSet().getImageData(), "/Adapt_Set.arff");
		
		// write test set
		writer.writeExperiment(processModel.getTeSet().getImageCluster(),
				processModel.getTeSet().getImageData(), "/Te_Set.arff");

		// write training sets
		int cnt = 0;

		while (cnt < processModel.sizeOfTrSets()) {

			DataSet set = processModel.getTrSet(cnt);
			writer.writeExperiment(set.getImageCluster(), set.getImageData(),
					"/Tr_Set_" + cnt + ".arff");

			cnt++;
		}
	}

}
