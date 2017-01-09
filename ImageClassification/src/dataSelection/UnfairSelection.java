package dataSelection;

import expGen.ICtrlInformation;

public class UnfairSelection extends AAccumulatedSelection {

	public UnfairSelection(ICtrlInformation processModel, int minDataNum) {
		super(processModel, minDataNum);
	}

	@Override
	protected void addElementsToTeSets(int trDataNum) {
		processModel.setImageTeSet(processModel.getImageDb());
		processModel.setImageFeatureTeSet(processModel.getImageFeaturesDb());		
	}

	@Override
	public String getSignature() {
		return "unfair";
	}

}
