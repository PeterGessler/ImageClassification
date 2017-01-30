package expGen.container;

import java.util.ArrayList;
import java.util.List;

/**
 * Container class to hold image cluster and image feature data.
 * 
 * @author Peter Gessler & Martin Buschack
 *
 */
public class DataSet {
	
	private List<ImageCluster> imgCluster = null;
	private List<ImageData> imgData = null;

	public DataSet () {
		this.imgCluster = new ArrayList<ImageCluster>();
		this.imgData = new ArrayList<ImageData>();
	}
	
	public DataSet (List<ImageCluster> imgCluster, List<ImageData> imgData) {
		this.imgCluster = imgCluster;
		this.imgData = imgData;
	}
	
	public List<ImageCluster> getImageCluster() {
		return this.imgCluster;
	}
	
	public List<ImageData> getImageData() {
		return this.imgData;
	}
}
