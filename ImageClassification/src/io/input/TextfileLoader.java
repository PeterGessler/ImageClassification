package io.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TextfileLoader extends AFileLoader {

  public TextfileLoader(File file) {
    super(file);
  }

  @Override
  public String getFileContent() {

    StringBuilder rawContent = new StringBuilder();

    try {
      BufferedReader bufferedReader = new BufferedReader(new FileReader(
          super.file));

      try {
        String rawLine = null;

        while ((rawLine = bufferedReader.readLine()) != null) {

          if (rawLine.startsWith("#") || rawLine.isEmpty()
              || rawLine.trim().equals("") || rawLine.equals("\n"))
            continue;

          rawContent.append(rawLine);
          rawContent.append(System.getProperty("line.separator"));
        }

      } finally {
        bufferedReader.close();
      }

    } catch (IOException ex) {
      System.out.println("Error in TextfileLoader <\br> Reason: "
          + ex.toString());

    }

    return rawContent.toString();
  }
}
