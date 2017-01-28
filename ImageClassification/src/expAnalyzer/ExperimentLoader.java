package expAnalyzer;

import java.util.HashMap;

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
	 * @throws Exception 
	 */
	public void loadAndConvertDatabase(String[] fileNames) throws Exception {

		HashMap<Integer, Instances> instanceSets = new HashMap<Integer, Instances>();
		
		for (int pos = 0; pos < fileNames.length; pos++) {
			
			String filePath = expFileFolder + "/" + fileNames[pos] + ".arff";
			
			DataSource dataSrc = new DataSource(ClassificationUtils.getAbsPathFromFile(filePath).getAbsolutePath());
			Instances dataInst = dataSrc.getDataSet();
			setClassIndex(dataInst);
			
			instanceSets.put(pos, dataInst);
		}

		// set lists in process model
		processModel.setAdaptSet(instanceSets.get(0));
		processModel.setTeSet(instanceSets.get(1));
		
		for (int pos = 2; pos < instanceSets.size(); pos++) {
			processModel.addTrSet(instanceSets.get(pos));
		}
	}
	
	private void setClassIndex(Instances instance) {
		if (instance.classIndex() == -1)
			instance.setClassIndex(instance.numAttributes() - 1);
	}
}
