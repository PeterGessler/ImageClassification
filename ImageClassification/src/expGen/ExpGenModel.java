package expGen;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import launcher.ExpGenerator;
import utils.CsvDbConverter;
import expGen.container.DataSet;
import expGen.container.ImageCluster;
import expGen.container.ImageData;

/**
 * Model to hold all {@link ExpGenerator} informations.
 * 
 * @author Peter Gessler
 *
 */
public class ExpGenModel implements ICtrlInformation, IWriteInformation {

	private List<ImageCluster> imageDb = null;

	private List<ImageData> imageFeaturesDb = null;
	
	private HashMap<Integer, DataSet> trSets = null;
	
	private DataSet adaptKValSet = null;
	
	private DataSet teSet = null;

	public ExpGenModel(File imageDbFile, File edgeHistogramFile) {	

		CsvDbConverter dataConv = new CsvDbConverter(imageDbFile, edgeHistogramFile);
		
		this.imageDb = dataConv.getImageDb();
		this.imageFeaturesDb = dataConv.getImageFeaturesDb();
		
		this.trSets = new HashMap<Integer, DataSet>();
		this.adaptKValSet = new DataSet();
		this.teSet = new DataSet();
	}

	@Override
	public List<ImageCluster> getImageDb() {
		return this.imageDb;
	}

	@Override
	public List<ImageData> getImageFeaturesDb() {
		return this.imageFeaturesDb;
	}

	@Override
	public void addTrSet(Integer key, DataSet set) {
		trSets.put(key, set);		
	}

	@Override
	public void setAdaptKValSet(DataSet set) {
		this.adaptKValSet = set;
	}

	@Override
	public void setTeSet(DataSet set) {
		this.teSet = set;		
	}

	@Override
	public DataSet getTrSet(Integer key) {
		return this.trSets.get(key);
	}

	@Override
	public int sizeOfTrSets() {
		return this.trSets.size();
	}

	@Override
	public DataSet getAdaptKSet() {
		return this.adaptKValSet;
	}

	@Override
	public DataSet getTeSet() {
		return this.teSet;
	}

	
	
}
