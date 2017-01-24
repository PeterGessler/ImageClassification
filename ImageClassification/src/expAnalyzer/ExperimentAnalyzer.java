package expAnalyzer;

import io.output.ExpFileWriter;

import java.io.File;

import utils.ClassificationUtils;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.lazy.IBk;
import weka.core.Instances;

/**
 * 
 * 
 * @author Peter Gessler
 *
 */
public class ExperimentAnalyzer {

	private IDatabaseInformation processModel = null;
	private int kMaxValue = 0;
	private ExpFileWriter expResultsWriter = null;

	public ExperimentAnalyzer(IDatabaseInformation processModel, int kMaxValue) {
		this.processModel = processModel;
		this.kMaxValue = kMaxValue;
	}

	public void startAnalyzeProcess() throws Exception {

		int currentK = 1;
		expResultsWriter = new ExpFileWriter(new File( ClassificationUtils.getAbsPathFromFile(processModel.getEvalFolderPath()).getAbsolutePath()), "\\" + "ExpAnalyzer_Run_" + currentK + ".txt");
		
		while (currentK <= kMaxValue) {
			
			Instances train = processModel.getImgTrSet();
			Instances test = processModel.getImgTeSet();

			Classifier cls = new IBk(currentK);
			cls.buildClassifier(train);

			Evaluation eval = new Evaluation(train);
			eval.evaluateModel(cls, test);
			
			expResultsWriter.writeOutput(eval.toSummaryString("\nResults\n=== k = " + currentK + " ===\n",
					false));


			currentK++;
		}
		
		expResultsWriter.closeWriter();
	}
}
