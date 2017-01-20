package expGen;

import utils.ClassificationUtils;

public abstract class AExpWriter {

	protected final static String EXPERIMENTS_PATH = "database/experiments/";

	protected IWriteInformation processModel = null;

	protected AExpWriter(IWriteInformation processModel, String expSignature) {

		this.processModel = processModel;		
		int expNum = 0;
		
		StringBuilder preTxt = new StringBuilder();
		preTxt.append("0");
		preTxt.append("0");
		
		while (ClassificationUtils.getAbsPathFromFile(EXPERIMENTS_PATH + "e" + preTxt.toString() + expNum + "_" + expSignature).exists()) {
			
			preTxt = new StringBuilder();
			
			if (expNum < 10)
				preTxt.append("0");
			if (expNum < 100)
				preTxt.append("0");
			
			expNum++;
		}
		
		ClassificationUtils.getAbsPathFromFile(EXPERIMENTS_PATH + "e" + preTxt.toString() + expNum + "_" + expSignature).mkdirs();
		
		String expPath = EXPERIMENTS_PATH + "e" + preTxt.toString() + expNum + "_" + expSignature;
		
		ClassificationUtils.getAbsPathFromFile(expPath).mkdirs();

		writeExperiment(expPath);
	}
	
	public abstract void writeExperiment(String expPath);
}
