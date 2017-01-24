package expAnalyzer;

import utils.ClassificationUtils;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class ExperimentLoader {

	private String expFileFolder;
	
	private IInstantiateDatabase processModel = null;

	public ExperimentLoader(IInstantiateDatabase processModel) {

		expFileFolder = processModel.getExpFolderPath();
		this.processModel = processModel;
	}

	/**
	 * Load training and test files from selected experiment folder.
	 * Write .csv file data to process model.
	 */
	public void loadAndConvertDatabase() {

		// read training and test files
		String imageTrSet = expFileFolder + "/Tr_Set.arff";
		String imageTeSet = expFileFolder + "/Te_Set.arff";
		
		DataSource trSource = null;
		DataSource teSource = null;
		
		//System.out.println(ClassificationUtils.getAbsPathFromFile(imageTrSet).getAbsolutePath());
		
		try {
			trSource = new DataSource(ClassificationUtils.getAbsPathFromFile(imageTrSet).getAbsolutePath());
			teSource = new DataSource(ClassificationUtils.getAbsPathFromFile(imageTeSet).getAbsolutePath());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Instances trSet = null;
		Instances teSet = null;
		try {
			trSet = trSource.getDataSet();
			teSet = teSource.getDataSet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setClassIndex(trSet);
		setClassIndex(teSet);
		
		// set lists in process model
		processModel.setImageTrSet(trSet);	
		processModel.setImageTeSet(teSet);
	}
	
	private void setClassIndex(Instances instance) {
		if (instance.classIndex() == -1)
			instance.setClassIndex(instance.numAttributes() - 1);
	}
}
