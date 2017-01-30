package launcher;

import utils.ClassificationUtils;
import dataSelection.ChoiceSelection;
import expGen.ExpGenHandler;

/**
 * Generate an image classification experiment.
 * 
 * Argument list:
 * - {File name - list with associated image classes to image id's}
 * - {File name - list with associated image features to image id's}
 * - |Training elements for each class|
 * - |Adaption elements for each class|
 * - |test elements for each class| 
 * 
 * @author Peter Gessler & Martin Buschack
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
		 * Create experiment generator handler object to start split process.
		 */
		final ExpGenHandler expGenCtrl = new ExpGenHandler(ClassificationUtils.getAbsPathFromFile(DB_RAW_PATH + args[0]), ClassificationUtils.getAbsPathFromFile(DB_RAW_PATH + args[1]));
		expGenCtrl.startSplitProcess(new ChoiceSelection(Integer.valueOf(args[2]), Integer.valueOf(args[3]), Integer.valueOf(args[4])));
	}
}
