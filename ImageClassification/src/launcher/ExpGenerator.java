package launcher;

import utils.ClassificationUtils;
import dataSelection.ChoiceSelection;
import expGen.ExpGenHandler;

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

	public static void main(String[] args) {
		
		String DB_RAW_PATH = "database/raw/";	
		

		/**
		 * Inspect argument list depend by possible system process.
		 */
		if (args != null && args.length < 5) {
			System.out.println("Too few arguments");
			return;
		} else if ( args != null && args.length > 5) {
			System.out.println("Too many arguments");
			return;
		}

		
		/**
		 * Create generator handler to start split process.
		 */
		final ExpGenHandler expGenCtrl = new ExpGenHandler(ClassificationUtils.getAbsPathFromFile(DB_RAW_PATH + args[0]), ClassificationUtils.getAbsPathFromFile(DB_RAW_PATH + args[1]));
		expGenCtrl.startSplitProcess(new ChoiceSelection(Integer.valueOf(args[2]), Integer.valueOf(args[3]), Integer.valueOf(args[4])));
	}
}
