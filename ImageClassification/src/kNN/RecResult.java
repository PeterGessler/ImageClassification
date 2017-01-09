package kNN;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * Container to hold all recognition results depend by image class ({@link CRR}).
 * 
 * @author Peter Gessler
 *
 */
public class RecResult {

	private Hashtable<String, CRR> recResults = null;

	public RecResult() {
		recResults = new Hashtable<String, CRR>();
	}

	/**
	 * Add new image class recognition result.
	 * 
	 * @param newCRR
	 */
	public void addCRR(CRR newCRR) {
		recResults.put(newCRR.getImageClName(), newCRR);
	}

	/**
	 * Get all recognition results.
	 * 
	 * @return
	 */
	public Hashtable<String, CRR> getRecResults() {
		return this.recResults;
	}

	/**
	 * Get {@link CRR} object with recognition results by image class.
	 * 
	 * @param key
	 * @return
	 */
	public CRR getCRRByClName(String key) {
		return this.recResults.get(key);
	}
	
	/**
	 * Get recognition quote depend by image class name.
	 * 
	 * @param key
	 * @return
	 */
	public double getRecognitionQuoteByClName(String key) {
		return this.recResults.get(key).getRecognitionQuote();
	}

	/**
	 * Get merged recognition quote.
	 * 
	 * @return
	 */
	public double getRecognitionQuote() {
		
		double summarizedTrueCl = 0;
		double summarizedFalseCl = 0;
		Collection<CRR> recRes = recResults.values();
		Iterator<CRR> itr = recRes.iterator();

		while (itr.hasNext()) {
			CRR cCRR = itr.next();
			summarizedTrueCl = summarizedTrueCl + cCRR.getTrueClassified();
			summarizedFalseCl = summarizedFalseCl + cCRR.getFalseClassified();
		}
		
		return (summarizedTrueCl / (summarizedTrueCl + summarizedFalseCl)) * 100;
	}
}
