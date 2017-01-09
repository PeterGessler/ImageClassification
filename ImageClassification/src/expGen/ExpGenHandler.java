package expGen;

import dataSelection.ADataSelection;


/**
 * Class calculate new experiment Set size and fill Sets with image and image feature elements.
 * 
 * @author Peter Gessler
 *
 */
public class ExpGenHandler {

	private ICtrlInformation processModel = null;

	public ExpGenHandler(ICtrlInformation expGenModel) {
		this.processModel = expGenModel;
	}

	public void startSplitProcess(ADataSelection dataSelector) {
		
		dataSelector.startSplitProcess();
		
		ExpGenWriter writer = new ExpGenWriter((IWriteInformation) processModel, dataSelector.getSignature());
		writer.writeExperiment();
	}

}
