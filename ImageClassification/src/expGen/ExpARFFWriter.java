package expGen;

import io.output.ExpFileWriter;

import java.io.File;
import java.util.List;

import utils.ClassificationUtils;
import expGen.container.ImageCluster;
import expGen.container.ImageData;

public class ExpARFFWriter extends AExpWriter {

	public ExpARFFWriter(IWriteInformation processModel, String expSignature) {
		super(processModel, expSignature);
	}

	@Override
	public void writeExperiment(String expPath) {

		StringBuilder arffTrSetText = new StringBuilder();
		arffTrSetText.append("@relation " + "WekaFileType");

		arffTrSetText = buildArffTxt(arffTrSetText,
				processModel.getImageTrSet(),
				processModel.getImageFeatureTrSet());
		ExpFileWriter.writeOutput(new File(expPath + "/Tr_Set.arff"),
				arffTrSetText.toString());

		StringBuilder arffTeSetText = new StringBuilder();
		arffTeSetText.append("@relation " + "WekaFileType");
		arffTeSetText = buildArffTxt(arffTeSetText,
				processModel.getImageTeSet(),
				processModel.getImageFeatureTeSet());
		ExpFileWriter.writeOutput(new File(expPath + "/Te_Set.arff"),
				arffTeSetText.toString());
	}

	private StringBuilder buildArffTxt(StringBuilder arffBuilder,
			List<ImageCluster> clusters,  List<ImageData> imgFeatureData) {
		
		arffBuilder.append(System.getProperty("line.separator"));
		arffBuilder.append(System.getProperty("line.separator"));
		
		// write attributes
		int featureNum = imgFeatureData.get(0).getFeatures().length;
		
		for (int pos = 0; pos < featureNum; pos++) {
			arffBuilder.append("@attribute feature" + pos + " {0,1,2,3,4,5,6,7,8,9}" + System.getProperty("line.separator"));
		}
		
		StringBuilder classes = new StringBuilder();
		classes.append("@attribute class {");
		
		int cnt = 0;
		
		while (cnt < clusters.size()) {
			classes.append(clusters.get(cnt).getName());
			
			if (cnt < (clusters.size() - 1))
				classes.append(",");
			else {
				classes.append("}");
				classes.append(System.getProperty("line.separator"));
			}
			
			cnt++;
		}
		
		arffBuilder.append(classes.toString());
		arffBuilder.append(System.getProperty("line.separator"));
		arffBuilder.append("@data");
		arffBuilder.append(System.getProperty("line.separator"));		
		
		
		for (ImageData imgData : imgFeatureData) {
			
			for (int pos = 0; pos < imgData.getFeatures().length; pos++) {
				arffBuilder.append(String.valueOf(imgData.getFeature(pos)).replace(".0", ""));
				arffBuilder.append(",");
			}
			arffBuilder.append(ClassificationUtils.findImageClass(clusters, imgData.getId()));
			arffBuilder.append("\n");
		}
		return arffBuilder;
	}
}
