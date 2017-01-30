package expGen.container;

/**
 * Container class hold all image features in a double array.
 * 
 * @author Peter Gessler & Martin Buschack
 *
 */
public class ImageData {

	private String id;
	private double[] features;
	
	// Constructor
	public ImageData (String id, double[] features) {
		
		this.id = id;
		this.features = features;		
	}
	
	/**
	 * Get image database id.
	 * @return
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * Get image features.
	 * @return
	 */
	public double[] getFeatures() {
		return this.features;
	}
	
	/**
	 * Get single image feature by position in array.
	 * 
	 * @param pos
	 * @return
	 */
	public double getFeature(int pos) {
		return this.features[pos];
	}
}
