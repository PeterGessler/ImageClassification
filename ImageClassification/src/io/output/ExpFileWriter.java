package io.output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import kNN.RecResult;

/**
 * Write File with data to physical device.
 * 
 * @author Peter Gessler
 *
 */
public class ExpFileWriter {

	private FileWriter fileWriter = null;

	private BufferedWriter buffWriter = null;

	public ExpFileWriter(File file) {

		if (!file.exists()) { // checks whether the file is Exist or not
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // here if file not exist new file created
		}

		try {
			fileWriter = new FileWriter(file.getAbsoluteFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // creating fileWriter object with the file
		buffWriter = new BufferedWriter(fileWriter);
	}

	public void writeOutput(RecResult singleRecResult) {
		// buffWriter.write(content);
		// Buschi ToDo
	}

	public void closeWriter() {

		try {
			buffWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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
