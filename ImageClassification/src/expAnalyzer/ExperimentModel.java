package expAnalyzer;

import java.util.HashMap;

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
	
	private HashMap<Integer, Instances> trSets = null;
	
	private Instances adaptSet = null;
	
	private Instances teSet = null;	
	
	public ExperimentModel(String[] fileNames, String expFolderSignature) {
		
		this.trSets = new HashMap<Integer, Instances>();
		
		// load raw data and convert
		expFolderPath = COMMON_EXP_FOLDER_PATH + expFolderSignature;
		evalFolderPath = COMMON_EVAL_FOLDER_PATH + expFolderSignature;
		
		ExperimentLoader expLoader = new ExperimentLoader(this);
		try {
			expLoader.loadAndConvertDatabase(fileNames);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@Override
	public void addTrSet(Instances trSet) {
		this.trSets.put(trSets.size(), trSet);		
	}

	@Override
	public void setAdaptSet(Instances trSet) {
		this.adaptSet = trSet;
	}

	@Override
	public void setTeSet(Instances teSet) {
		this.teSet = teSet;		
	}

	@Override
	public Instances getAdaptSet() {
		try {
			return this.adaptSet;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Instances getTeSet() {
		try {
			return this.teSet;
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


	@Override
	public Instances getTrSet(int index) {
		return trSets.get(index);
	}
}
