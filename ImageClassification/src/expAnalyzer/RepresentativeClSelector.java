package expAnalyzer;

import java.util.ArrayList;
import java.util.List;

import expGen.container.ImageCluster;
import expGen.container.ImageData;

/**
 * Select clRepsNum representative class images depend by minimum euclidean distance
 * to cluster training data.
 * 
 * @author Peter Gessler
 */
public class RepresentativeClSelector {

	private List<ImageCluster> repClData = null;

	public RepresentativeClSelector(IDatabaseInformation processModel,
			int clRepsNum) {

		this.repClData = new ArrayList<ImageCluster>();
		List<ImageCluster> repImgCandidates = processModel.getImageTrSet();
		List<ImageData> imageFeaTrSet = processModel.getImageFeaTrSet();
		
		this.repClData = repImgCandidates;
		/*
		HashSet<RepClImagesCalculator> repClCalculations = new HashSet<RepClImagesCalculator>();
		
		// find representative class images in each class cluster.
		for (ImageCluster cluster : repImgCandidates) {
			repClCalculations.add(new RepClImagesCalculator(cluster, imageFeaTrSet, clRepsNum));
		}
		
		Iterator<RepClImagesCalculator> startRepClCalcs = repClCalculations.iterator();
		while(startRepClCalcs.hasNext())
			startRepClCalcs.next().start();
		
		Iterator<RepClImagesCalculator> stopRepClCalcs = repClCalculations.iterator();
		while(stopRepClCalcs.hasNext()) {
			
			RepClImagesCalculator repClImgCalc = stopRepClCalcs.next();
			try {
				repClImgCalc.join();
				repClData.add(new ImageCluster(repClImgCalc.getClusterName(), repClImgCalc.getCandidates()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
			
		*/
		
	}
	
	/**
	 * Each {@link ImageCluster} contain clRepsNum representative class images.
	 * 
	 * @return
	 */
	public List<ImageCluster> getRepresentativeClData() {
		return this.repClData;
	}
}
