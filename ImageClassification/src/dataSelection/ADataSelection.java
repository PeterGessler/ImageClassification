package dataSelection;

import java.util.List;
import java.util.function.Predicate;

import expGen.ICtrlInformation;
import expGen.container.ImageData;

public abstract class ADataSelection {

	protected ICtrlInformation processModel = null;

	public ADataSelection(ICtrlInformation processModel) {
		this.processModel = processModel;
	}

	public abstract void startSplitProcess();

	public abstract String getSignature();

	protected ImageData findAssociatedImageFeatures(
			List<ImageData> imageFeatureData, String id) {

		Predicate<ImageData> pred = dataObj -> dataObj.getId().equals(id);
		ImageData imgDataObj = imageFeatureData.stream().filter(pred)
				.findFirst().orElse(null);
		return imgDataObj;
	}
}
