package kNN;

/**
 * Image class recognition result.
 * 
 * @author Peter Gessler
 *
 */
public class CRR {

	private String className;
	private int trueClassified;
	private int falseClassified;
	
	public CRR(String clName, int tC, int fC) {
		this.className = clName;
		this.trueClassified = tC;
		this.falseClassified = fC;
	}
	
	public String getImageClName() {
		return this.className;
	}	
	
	/**
	 * Number of true classified test images.
	 * @return
	 */
	public int getTrueClassified() {
		return this.trueClassified;
	}
	
	/**
	 * Number of false classified test images.
	 * @return
	 */
	public int getFalseClassified() {
		return this.falseClassified;
	}
	
	/**
	 * Get recognition result as quote.
	 * @return
	 */
	public double getRecognitionQuote() {
		return ((double) trueClassified / (double) (trueClassified + falseClassified)) * 100;
	}
}
