package utils;

import java.io.File;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import launcher.ExpGenerator;
import expGen.container.ImageCluster;
import expGen.container.ImageData;

public class ClassificationUtils {

	public static File getAbsPathFromFile(String filePath) {
	
		File f = new File(ExpGenerator.class.getClassLoader().getResource("")
				.getFile()).getParentFile();
	
		f = new File(f.getAbsolutePath() + "/" + filePath);
	
		return f;
	}
	
	public synchronized static boolean isCorrectNum(int toTestNum, int[] numSet) {
		return IntStream.of(numSet).anyMatch(x -> x == toTestNum);
	}

	// find equal ImageCluster in Hashset if exist
	public synchronized static ImageCluster findEqualCluster(List<ImageCluster> imageDb,
			String className) {
	
		Predicate<ImageCluster> pred = cluster -> cluster.equals(className);
		ImageCluster cluster = imageDb.stream().filter(pred).findFirst()
				.orElse(null);
		return cluster;
	}
	
	// find ImageData object to given id
	public synchronized static ImageData findImageData(List<ImageData> imageFeaDb, String id) {
		
		Predicate<ImageData> pred = img -> img.getId().equals(id);
		ImageData imgData = imageFeaDb.stream().filter(pred).findFirst().orElse(null);
		
		return imgData;
	}
	
	

}
