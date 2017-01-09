package expGen;

import io.output.FileWriter;

import java.io.File;
import java.util.List;

import utils.ClassificationUtils;
import expGen.container.ImageCluster;
import expGen.container.ImageData;

/**
 * Transform training and test Sets to converted Strings and generate experiment folders.
 * 
 * @author Peter Gessler
 *
 */
public class ExpGenWriter {

	private final static String EXPERIMENTS_PATH = "database/experiments/";
	
	private IWriteInformation processModel = null;

	private String expPath;
	
	public ExpGenWriter(IWriteInformation processModel, String expSignature) {
		
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
		
		expPath = EXPERIMENTS_PATH + "e" + preTxt.toString() + expNum + "_" + expSignature + '/' + "selectedData";
		
		ClassificationUtils.getAbsPathFromFile(expPath).mkdirs();
		
	}

	public void writeExperiment() {

		StringBuilder imgTrSetTxt = buildImageSetTxt(new StringBuilder(), processModel.getImageTrSet());
		FileWriter.writeOutput(new File(expPath + "/Image_Tr_Set.csv"), imgTrSetTxt.toString());
		
		StringBuilder imgTeSetTxt = buildImageSetTxt(new StringBuilder(), processModel.getImageTeSet());
		FileWriter.writeOutput(new File(expPath + "/Image_Te_Set.csv"), imgTeSetTxt.toString());
		
		StringBuilder imgFeaTrSetTxt = buildImageFeatureSetTxt(new StringBuilder(), processModel.getImageFeatureTrSet());
		FileWriter.writeOutput(new File(expPath + "/ImageFeature_Tr_Set.csv"), imgFeaTrSetTxt.toString());
		
		StringBuilder imgFeaTeSetTxt = buildImageFeatureSetTxt(new StringBuilder(), processModel.getImageFeatureTeSet());
		FileWriter.writeOutput(new File(expPath + "/ImageFeature_Te_Set.csv"), imgFeaTeSetTxt.toString());
	}
	
	private StringBuilder buildImageSetTxt(StringBuilder builder, List<ImageCluster> clusters) {
		
		for (ImageCluster cluster : clusters) {
			
			for (String id : cluster.getIds()) {
				
				builder.append(id);
				builder.append(";");
				builder.append(cluster.getName());
				builder.append("\n");
			}
		}
		
		return builder;
	}
	
	private StringBuilder buildImageFeatureSetTxt(StringBuilder builder, List<ImageData> imgFeatureData) {
		
		for (ImageData imgData : imgFeatureData) {
			
			builder.append(imgData.getId());
			
			for (int pos = 0; pos < imgData.getFeatures().length; pos++) {
				builder.append(";");
				builder.append(imgData.getFeature(pos));
			}
			builder.append("\n");
		}
		
		return builder;
	}
}
