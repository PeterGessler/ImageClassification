package expGen;

import io.output.ExpFileWriter;

import java.io.File;
import java.util.List;

import expGen.container.ImageCluster;
import expGen.container.ImageData;

/**
 * Transform training and test Sets to converted Strings and generate experiment folders.
 * 
 * @author Peter Gessler
 *
 */
public class ExpCSVWriter extends AExpWriter {
	
	public ExpCSVWriter(IWriteInformation processModel, String expSignature) {
		super(processModel, expSignature);
	}

	@Override
	public void writeExperiment(String expPath) {

		StringBuilder imgTrSetTxt = buildImageSetTxt(new StringBuilder(), processModel.getImageTrSet());
		ExpFileWriter.writeOutput(new File(expPath + "/Image_Tr_Set.csv"), imgTrSetTxt.toString());
		
		StringBuilder imgTeSetTxt = buildImageSetTxt(new StringBuilder(), processModel.getImageTeSet());
		ExpFileWriter.writeOutput(new File(expPath + "/Image_Te_Set.csv"), imgTeSetTxt.toString());
		
		StringBuilder imgFeaTrSetTxt = buildImageFeatureSetTxt(new StringBuilder(), processModel.getImageFeatureTrSet());
		ExpFileWriter.writeOutput(new File(expPath + "/ImageFeature_Tr_Set.csv"), imgFeaTrSetTxt.toString());
		
		StringBuilder imgFeaTeSetTxt = buildImageFeatureSetTxt(new StringBuilder(), processModel.getImageFeatureTeSet());
		ExpFileWriter.writeOutput(new File(expPath + "/ImageFeature_Te_Set.csv"), imgFeaTeSetTxt.toString());
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
