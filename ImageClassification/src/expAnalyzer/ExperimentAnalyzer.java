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

	private static final int TRAINING_ROUNDS = 4;

	private IDatabaseInformation processModel = null;
	private int kMaxValue = 0;
	private ExpFileWriter expResultsWriter = null;
	private Double[][] evalMatrix = null;

	public ExperimentAnalyzer(IDatabaseInformation processModel, int kMaxValue) {
		this.processModel = processModel;
		this.kMaxValue = kMaxValue;
		
		evalMatrix = new Double[kMaxValue][TRAINING_ROUNDS];
	}

	public void startAnalyzeProcess() throws Exception {

		int currentRound = 0;

		// evaluate results for each training set and given k value.		
		while (currentRound < TRAINING_ROUNDS) {

			Instances train = processModel.getTrSet(currentRound);
			Instances adapt = processModel.getAdaptSet();
			int currentK = 1;
			
			expResultsWriter = new ExpFileWriter(new File(ClassificationUtils
					.getAbsPathFromFile(processModel.getEvalFolderPath())
					.getAbsolutePath()), "\\" + "ExpAnalyzer_Run_Adapt_"
					+ currentRound + ".txt");

			while (currentK <= kMaxValue) {

				Evaluation eval = runEvaluation(train, adapt, currentK);
				evalMatrix[currentK-1][currentRound] = eval.correct() / (eval.correct() + eval.incorrect() / 100);
				
				expResultsWriter.writeOutput(eval.toSummaryString(
						"\nResults\n=== k = " + currentK + " ===\n", false));
				currentK++;
			}

			expResultsWriter.closeWriter();

			currentRound++;
		}
		
		
		// calculate best k value
		double[] bestKValue = new double[this.kMaxValue];
		
		for (int kVal = 0; kVal < kMaxValue; kVal++) {
			
			double sumVal = 0;
			
			for (int round = 0; round < TRAINING_ROUNDS; round++) {
				sumVal = sumVal + evalMatrix[kVal][round];
			}
			
			bestKValue[kVal] = sumVal / TRAINING_ROUNDS;
		}
		
		int bestK = argmax(bestKValue) + 1;

		evaluateWithBestKVal(bestK);
		
	}

	private void evaluateWithBestKVal(int bestK) throws Exception {

		int currentRound = 0;

		// evaluate results for each training set and given k value.		
		while (currentRound < TRAINING_ROUNDS) {
			
			Instances train = processModel.getTrSet(currentRound);
			Instances test = processModel.getTeSet();
			
			expResultsWriter = new ExpFileWriter(new File(ClassificationUtils
					.getAbsPathFromFile(processModel.getEvalFolderPath())
					.getAbsolutePath()), "\\" + "ExpAnalyzer_Run_Test_"
					+ currentRound + ".txt");
			
			Evaluation eval = runEvaluation(train, test, bestK);
			
			expResultsWriter.writeOutput(eval.toSummaryString(
					"\nResults\n=== k = " + bestK + " ===\n", false));
			
			expResultsWriter.closeWriter();

			currentRound++;
		}
		
		
	}

	private Evaluation runEvaluation(Instances train, Instances adapt,
			int currentK) throws Exception {

		Classifier cls = new IBk(currentK);
		cls.buildClassifier(train);

		Evaluation eval = new Evaluation(train);
		eval.evaluateModel(cls, adapt);

		return eval;
	}
	
	private int argmax (double [] elems)
    {
      int bestIdx = -1;
      double max = Double.NEGATIVE_INFINITY;
      for (int i = 0; i < elems.length; i++) {
        double elem = elems[i];
        if (elem > max) {
          max = elem;
          bestIdx = i;
        }
      }
      return bestIdx;
    }
}
