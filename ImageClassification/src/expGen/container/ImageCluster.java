package expGen.container;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Container class hold all image ids from equal class signature.
 * 
 * @author Peter Gessler
 *
 */
public class ImageCluster {

	private String signature;
	private List<String> ids;
	
	public ImageCluster (String classSignature) {
		this.signature = classSignature;
		this.ids = new ArrayList<String>();
	}
	
	public ImageCluster (String classSignature, Hashtable<String, Double> ids) {
		this(classSignature);
		this.ids = ids.keySet().stream().collect(Collectors.toList());
	}
	
	/**
	 * Add new image database id with equal class signature.
	 * 
	 * @param newId
	 */
	public void addId (String newId) {
		ids.add(newId);
	}
	
	/**
	 * Remove image id with equal class signature.
	 * 
	 * @param id
	 */
	public void removeId (String id) {
		ids.remove(id);
	}
	
	/**
	 * Get cluster size.
	 * 
	 * @return
	 */
	public int getSetSize() {
		return ids.size();
	}
	
	/**
	 * Get all image ids with same class signature.
	 * 
	 * @return
	 */
	public List<String> getIds() {
		return ids;
	}
	
	/**
	 * Get class signature.
	 * 
	 * @return
	 */
	public String getName() {
		return this.signature;
	}
	
	@Override
	public boolean equals(Object object) {
		boolean result = false;
	    if (object == null) {
	      result = false;
	    } else {
	    	String className = (String) object;
	      if (this.signature.equals(className)) {
	        result = true;
	      }
	    }
	    return result;
	}
}
