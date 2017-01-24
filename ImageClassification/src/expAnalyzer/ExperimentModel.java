package expAnalyzer;

import weka.core.Instances;

/**
 * Model to hold and provide training and test experiment data.
 * 
 * @author Peter Gessler
 *
 */
public class ExperimentModel implements IInstantiateDatabase, IDatabaseInformation {

	private final static String COMMON_EXP_FOLDER_PATH = "database/experiments/";
	
	private final static String COMMON_EVAL_FOLDER_PATH = "database/evaluation/";
	
	private static String expFolderPath;
	
	private static String evalFolderPath;
	
	private Instances imgTrSet = null;
	
	private Instances imgTeSet = null;	
	
	public ExperimentModel(String expFolderSignature) {
		
		// load raw data and convert
		expFolderPath = COMMON_EXP_FOLDER_PATH + expFolderSignature;
		evalFolderPath = COMMON_EVAL_FOLDER_PATH + expFolderSignature;
		
		ExperimentLoader expLoader = new ExperimentLoader(this);
		expLoader.loadAndConvertDatabase();
	}


	@Override
	public void setImageTrSet(Instances trSet) {
		this.imgTrSet = trSet;
		
	}

	@Override
	public void setImageTeSet(Instances teSet) {
		this.imgTeSet = teSet;		
	}

	@Override
	public Instances getImgTrSet() {
		try {
			return this.imgTrSet;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Instances getImgTeSet() {
		try {
			return this.imgTeSet;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getExpFolderPath() {
		return expFolderPath;
	}


	@Override
	public String getEvalFolderPath() {
		return evalFolderPath;
	}

}
