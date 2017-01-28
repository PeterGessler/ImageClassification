package expGen;

import java.io.File;

import dataSelection.ADataSelection;
import expGen.container.DataSet;


/**
 * Class calculate new experiment Set size and fill Sets with image and image feature elements.
 * 
 * @author Peter Gessler
 *
 */
public class ExpGenHandler {

	private IWriteInformation processModel = null;

	public ExpGenHandler(File imageFiles, File edgeHistogram) {
		processModel = new ExpGenModel(imageFiles, edgeHistogram);
	}

	public void startSplitProcess(ADataSelection dataSelector) {
		
		dataSelector.setProcessModel((ICtrlInformation)processModel);
		dataSelector.startSplitProcess();
		
		/**
		 * Write all set files.
		 */
		AExpWriter writer = new ARFFWriter(dataSelector.getSignature());
		
		writer.writeExperiment(processModel.getAdaptKSet().getImageCluster(), processModel.getAdaptKSet().getImageData(), "/Adapt_Set.arff");
		writer.writeExperiment(processModel.getTeSet().getImageCluster(), processModel.getTeSet().getImageData(), "/Te_Set.arff");
		
		int cnt = 0;
		
		while (cnt < processModel.sizeOfTrSets()) {
			
			DataSet set = processModel.getTrSet(cnt);
			
			writer.writeExperiment(set.getImageCluster(), set.getImageData(), "/Tr_Set_" +cnt+ ".arff");
			
			cnt++;
		}		
	}

}
