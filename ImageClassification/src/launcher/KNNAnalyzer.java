package launcher;

import utils.ClassificationUtils;
import expAnalyzer.ExperimentAnalyzer;
import expAnalyzer.ExperimentModel;

public class KNNAnalyzer {

	private static int ARGS_CONST = 3;	
	
	public static void main(String[] args) {
		
		int[] correctTrNum = {3, 5, 10};
		
		if (args == null || args[0].equals("-h")) {
			// TODO help
			return;
		}

		if (args.length < ARGS_CONST) {
			System.out.println("Error - Too few arguments");
			System.out.println("Argument structure is: [expFolder] [clRepresenterNum] [neighbourNum]");
			return;
		} else if (args.length > ARGS_CONST) {
			System.out.println("Error - Too many arguments");
			System.out.println("Argument structure is: [expFolder] [clRepresenterNum] [neighbourNum]");
			return;
		} else if (!ClassificationUtils.isCorrectNum(Integer.valueOf(args[1]), correctTrNum)) {
			System.out
					.println("Error - Choose value 3, 5 or 10 (images who representative class)");
			return;
		}

		final ExperimentModel processModel = new ExperimentModel(args[0]);
		final ExperimentAnalyzer analyzer = new ExperimentAnalyzer(
				processModel, Integer.valueOf(args[1]),
				Integer.valueOf(args[2]));
		analyzer.startAnalyzeProcess();

	}
}
