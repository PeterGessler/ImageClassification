package utils;

import io.input.LineStringConverter;
import io.input.TextfileListConverter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import expGen.container.ImageCluster;
import expGen.container.ImageData;

/**
 * Parse *.csv database files to lists with {@link ImageCluster}s or {@link ImageData}s.
 * 
 * @author Peter Gessler
 *
 */
public class CsvDbConverter {

	private List<ImageCluster> imageDb = null;
	private List<ImageData> imageFeaturesDb = null;

	public CsvDbConverter(File imageDb, File imageFeaturesDb) {

		this.imageDb = new ArrayList<ImageCluster>();
		this.imageFeaturesDb = new ArrayList<ImageData>();

		TextfileListConverter imageDbLoader = new TextfileListConverter(
				new LineStringConverter());
		List<Object> rawImageEntries = imageDbLoader
				.getFormattedContent(imageDb);
		List<Object> rawImageFeatureEntries = imageDbLoader
				.getFormattedContent(imageFeaturesDb);

		parseImageDb(rawImageEntries);
		parseImageFeatureDb(rawImageFeatureEntries);
	}

	/**
	 * Write raw image signatures as {@link ImageCluster} object in list.
	 * 
	 * @param rawImageEntries
	 */
	private void parseImageDb(List<Object> rawImageEntries) {

		for (Object entry : rawImageEntries) {

			String[] rawEntry = ((String) entry).split(";");

			ImageCluster cluster = ClassificationUtils.findEqualCluster(this.imageDb, rawEntry[1]);

			if (cluster == null) {
				ImageCluster newCluster = new ImageCluster(rawEntry[1]);
				newCluster.addId(rawEntry[0]);
				imageDb.add(newCluster);
			} else {
				cluster.addId(rawEntry[0]);
			}
		}
	}

	/**
	 * Write raw image features as {@link ImageData} object in List.
	 * 
	 * @param rawImageFeatureEntries
	 */
	private void parseImageFeatureDb(List<Object> rawImageFeatureEntries) {

		for (Object entry : rawImageFeatureEntries) {

			String[] newEntry = ((String) entry).split(";");
			double[] features = getFeaturesAsArray(newEntry);

			imageFeaturesDb.add(new ImageData(newEntry[0], features));
		}
	}

	/**
	 * Parse String[] with features to array with double values.
	 * 
	 * @param newEntry
	 * @return
	 */
	private double[] getFeaturesAsArray(String[] newEntry) {

		double[] featureArray = new double[newEntry.length - 1];
		int pos = 0;

		while (pos < (newEntry.length - 1)) {
			featureArray[pos] = Double.valueOf(newEntry[pos + 1]);
			pos++;
		}
		return featureArray;
	}

	/**
	 * Get image database with {@link ImageCluster}s.
	 * @return
	 */
	public List<ImageCluster> getImageDb() {
		return imageDb;
	}

	/**
	 * Get image feature database with {@link ImageData}.
	 * @return
	 */
	public List<ImageData> getImageFeaturesDb() {
		return this.imageFeaturesDb;
	}
}
