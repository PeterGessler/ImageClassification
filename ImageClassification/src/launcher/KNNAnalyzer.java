package launcher;

import utils.ClassificationUtils;
import expAnalyzer.ExperimentAnalyzer;
import expAnalyzer.ExperimentModel;

/**
 * Evaluate an experiment by k-nearest neighborhood process with data mining
 * API http://www.cs.waikato.ac.nz/ml/weka/
 * 
 * Argument list:
 * - {experiment name}
 * - |k value|
 * 
 * @author Peter Gessler & Martin Buschack
 *
 */
public class KNNAnalyzer {

	private static int ARGS_CONST = 2;	
	
	private static String[] fileNames = {"/Adapt_Set", "/Te_Set", "/Tr_Set_0", "/Tr_Set_1", "/Tr_Set_2", "/Tr_Set_3"};
	
	public static void main(String[] args) {
		
		int[] correctTrNum = {3, 5, 10};
		
		if (args == null || args[0].equals("-h")) {
			// TODO help
			return;
		}

		// check argument list
		if (args.length < ARGS_CONST) {
			System.out.println("Error - Too few arguments");
			System.out.println("Argument structure is: [expFolder] [kValue]");
			return;
		} else if (args.length > ARGS_CONST) {
			System.out.println("Error - Too many arguments");
			System.out.println("Argument structure is: [expFolder] [kValue]");
			return;
		} else if (!ClassificationUtils.isCorrectNum(Integer.valueOf(args[1]), correctTrNum)) {
			System.out
					.println("Error - Choose value 3, 5 or 10 (images who representative class)");
			return;
		}

		final ExperimentModel processModel = new ExperimentModel(fileNames, args[0]);
		final ExperimentAnalyzer analyzer = new ExperimentAnalyzer(
				processModel, Integer.valueOf(args[1]));
		try {
			analyzer.startAnalyzeProcess();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
