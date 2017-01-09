package expAnalyzer;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import kNN.CRR;
import kNN.KNNClassificator;
import kNN.RecResult;
import expGen.container.ImageCluster;

/**
 * 
 * 
 * @author Peter Gessler
 *
 */
public class ExperimentAnalyzer {

	private IDatabaseInformation processModel = null;
	private int maxKValue;
	private List<ImageCluster> repClData = null;
	private Hashtable<Integer, RecResult> kRecResults = null;
	
	public ExperimentAnalyzer(IDatabaseInformation processModel, int clRepsNum,
			int maxKValue) {
		this.processModel = processModel;
		this.maxKValue = maxKValue;

		repClData = new RepresentativeClSelector(processModel, clRepsNum)
				.getRepresentativeClData();
	}

	public void startAnalyzeProcess() {
		
		kRecResults = new Hashtable<Integer, RecResult>();
		List<ImageCluster> imgTeSet = processModel.getImageTeSet();
		
		KNNClassificator kNNC = new KNNClassificator(repClData, (IImageFeatureInformation) processModel);
		
		int kValue = 1;
		
		while (kValue <= maxKValue) {
			
			RecResult recResByKVal = new RecResult();
			
			// classification process with current k-value
			for (ImageCluster imgTeCl : imgTeSet) {
				CRR newCRR = kNNC.classifyClMember(imgTeCl, kValue);
				recResByKVal.addCRR(newCRR);
			}
			
			kRecResults.put(kValue, recResByKVal);
			
			RecResult rResults = kRecResults.get(kValue);
			Collection<CRR> recs = rResults.getRecResults().values();
			Iterator<CRR> itr = recs.iterator(); 
			
			int cnt = 0;
			while (itr.hasNext()) {
				CRR cCRR = itr.next();
				System.out.println(cnt + " | " +cCRR.getImageClName() + " | " + cCRR.getRecognitionQuote());
				cnt++;
			} 
			System.out.println(rResults.getRecognitionQuote());
			
			kValue++;
		}

	}
}
