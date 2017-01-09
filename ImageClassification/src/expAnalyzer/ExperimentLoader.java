package expAnalyzer;

import java.io.File;

import utils.ClassificationUtils;
import utils.CsvDbConverter;

public class ExperimentLoader {

	private String expFileFolder;
	
	private IInstantiateDatabase processModel = null;

	public ExperimentLoader(IInstantiateDatabase processModel) {

		expFileFolder = processModel.getExpFolderPath() + "/selectedData/";
		this.processModel = processModel;
	}

	/**
	 * Load training and test files from selected experiment folder.
	 * Write .csv file data to process model.
	 */
	public void loadAndConvertDatabase() {

		// read training and test files
		String imageTrSet = expFileFolder + "Image_Tr_Set.csv";
		String imageTeSet = expFileFolder + "Image_Te_Set.csv";
		String imageFeaTrSet = expFileFolder + "ImageFeature_Tr_Set.csv";
		String imageFeaTeSet = expFileFolder + "ImageFeature_Te_Set.csv";

		File imgTrSet = ClassificationUtils.getAbsPathFromFile(imageTrSet);
		File imgTeSet = ClassificationUtils.getAbsPathFromFile(imageTeSet);
		File imgFeaTrSet = ClassificationUtils
				.getAbsPathFromFile(imageFeaTrSet);
		File imgFeaTeSet = ClassificationUtils
				.getAbsPathFromFile(imageFeaTeSet);

		// load and convert csv files
		CsvDbConverter trainingData = new CsvDbConverter(imgTrSet,
				imgFeaTrSet);
		CsvDbConverter testData = new CsvDbConverter(imgTeSet, imgFeaTeSet);

		// set lists in process model
		processModel.setImageTrSet(trainingData.getImageDb());
		processModel.setImageFeaTrSet(trainingData.getImageFeaturesDb());
		
		processModel.setImageTeSet(testData.getImageDb());
		processModel.setImageFeaTeSet(testData.getImageFeaturesDb());
	}
}
