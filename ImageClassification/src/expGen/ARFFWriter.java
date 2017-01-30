package expGen;

import io.output.ExpFileWriter;

import java.io.File;
import java.util.List;

import utils.ClassificationUtils;
import expGen.container.ImageCluster;
import expGen.container.ImageData;

/**
 * Write an {@link File} with .arff structure, see http://www.cs.waikato.ac.nz/ml/weka/arff.html.
 * 
 * @author Peter Gessler & Martin Buschack
 *
 */
public class ARFFWriter extends AExpWriter {

	public ARFFWriter(String expSignature) {
		super(expSignature);
	}

	@Override
	public void writeExperiment(List<ImageCluster> imgCluster, List<ImageData> imgData, String fileName) {

		StringBuilder arffTrSetText = new StringBuilder();
		arffTrSetText.append("@relation " + "WekaFileType");

		// enroll all important *-set data
		arffTrSetText = buildArffTxt(arffTrSetText,
				imgCluster,
				imgData);
		
		// save as file on storage
		ExpFileWriter.writeOutput(new File(expPath + fileName),
				arffTrSetText.toString());
	}

	private StringBuilder buildArffTxt(StringBuilder arffBuilder,
			List<ImageCluster> imgCluster,  List<ImageData> imgData) {
		
		arffBuilder.append(System.getProperty("line.separator"));
		arffBuilder.append(System.getProperty("line.separator"));
		
		// write attributes
		int featureNum = imgData.get(0).getFeatures().length;
		
		for (int pos = 0; pos < featureNum; pos++) {
			arffBuilder.append("@attribute feature" + pos + " {0,1,2,3,4,5,6,7,8,9}" + System.getProperty("line.separator"));
		}
		
		StringBuilder classes = new StringBuilder();
		classes.append("@attribute class {");
		
		int cnt = 0;
		
		while (cnt < imgCluster.size()) {
			classes.append(imgCluster.get(cnt).getName());
			
			if (cnt < (imgCluster.size() - 1))
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
		
		
		for (ImageData imagesData : imgData) {
			
			for (int pos = 0; pos < imagesData.getFeatures().length; pos++) {
				arffBuilder.append(String.valueOf(imagesData.getFeature(pos)).replace(".0", ""));
				arffBuilder.append(",");
			}
			arffBuilder.append(ClassificationUtils.findImageClass(imgCluster, imagesData.getId()));
			arffBuilder.append("\n");
		}
		return arffBuilder;
	}
}
