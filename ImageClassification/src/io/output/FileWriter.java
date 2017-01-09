package io.output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Write File with data to physical device.
 * 
 * @author Peter Gessler
 *
 */
public class FileWriter {

	public static void writeOutput(File newFile, String text) {
		
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream(newFile), "utf-8"))) {
	   writer.write(text);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
