package expGen;

import java.util.List;

import utils.ClassificationUtils;
import expGen.container.ImageCluster;
import expGen.container.ImageData;

public abstract class AExpWriter {

	protected final static String EXPERIMENTS_PATH = "database/experiments/";
	
	protected String expPath = null;

	protected AExpWriter(String expSignature) {
	
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
		
		this.expPath = EXPERIMENTS_PATH + "e" + preTxt.toString() + expNum + "_" + expSignature;
		
		ClassificationUtils.getAbsPathFromFile(expPath).mkdirs();
	}
	
	public abstract void writeExperiment(List<ImageCluster> imgCluster, List<ImageData> imgData, String fileName);
}
