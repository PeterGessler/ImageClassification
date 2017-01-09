package launcher;

import utils.ClassificationUtils;
import dataSelection.ADataSelection;
import dataSelection.FairSelection;
import dataSelection.SingleSelection;
import dataSelection.UnfairSelection;
import expGen.ExpGenHandler;
import expGen.ExpGenModel;

/**
 * Generate experiment by image and image feature database csv file in directory
 * .../database/raw. Start script in command line.
 * 
 * Image database file structure: 1. column: image id ; 2. column: class. Image
 * feature database file structure: 1. column: image id ; 2.-n. column: feature
 * dimension values.
 * 
 * Start {@link ExpGenerator} with imageDbFile path, imageFeaDbFile path and
 * teSetSizeFlag as input parameters to create a new experiment in
 * /database/experiments directory.
 * 
 * New experiment signature is depend by old available experiment signatures.
 * Note: teSetSizeFlag is only 0 (N/2 test elements) or 1 (N - trainingElements
 * =: testElements).
 * 
 * How system generate experiment data: 1. System map all image database values
 * depend by own mapped class signature to an cluster. 2. Divide cluster with
 * lowest entry N size by two to get number of training and test elements. 3.
 * Fill training and test sets with N/2 random selected elements from each
 * single cluster. 4. Select equal elements in image feature database and write
 * new csv files in generated experiment folder.
 * 
 * @author Peter Gessler
 *
 */
public class ExpGenerator {

	private static final String DB_RAW_PATH = "database/raw/";

	private static ExpGenModel expGenModel = null;

	private static int trFlag = 0;

	private static int minDataNum = 0;

	public static void main(String[] args) {

		if (!inspectArgList(args))
			return;

		expGenModel = new ExpGenModel(
				ClassificationUtils.getAbsPathFromFile(DB_RAW_PATH + args[0]),
				ClassificationUtils.getAbsPathFromFile(DB_RAW_PATH + args[1]));
		final ExpGenHandler expGenCtrl = new ExpGenHandler(expGenModel);
		expGenCtrl.startSplitProcess(getDataSelector(args[2], minDataNum,
				trFlag));
	}

	private static boolean inspectArgList(String[] args) {

		if (args.length > 3) {
			trFlag = Integer.valueOf(args[4]);
			minDataNum = Integer.valueOf(args[3]);
		}
		
		int[] trFlags = { 0, 1 };

		if (args == null || args[0].equals("-h")) {
			// TODO help
			return false;
		}

		if (args.length < 3) {
			System.out.println("Error - Too few arguments");
			return false;
		} else if (args.length > 5) {
			System.out.println("Error - Too many arguments");
			return false;
		} else if (args[2].equals("acc") && args.length == 5) {
			return true;
		} else if (args[2].equals("single") && args.length == 3) {
			return true;
		} else if (args.length == 4 && args[4] != null) {

			if (!ClassificationUtils.isCorrectNum(Integer.valueOf(args[4]),
					trFlags)) {
				System.out
						.println("Error - no correct flag to set number of test data");
				return false;
			}
		}

		System.out.println("Error - Wrong argument combination");
		return false;
	}

	private static ADataSelection getDataSelector(String dataSelector,
			int minDataNum, int trFlag) {

		switch (dataSelector) {
		case "acc":
			if (trFlag == 0) {
				return new FairSelection(expGenModel, minDataNum);
			} else {
				return new UnfairSelection(expGenModel, minDataNum);
			}
		case "single":
			return new SingleSelection(expGenModel);
		default:
			return new FairSelection(expGenModel, minDataNum);
		}
	}
}
