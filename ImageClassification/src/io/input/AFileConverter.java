package io.input;

public abstract class AFileConverter implements IFileConverter {

  protected Object rawFileContent;

  protected Object convertedFileContent;

  protected AFileConverter(AFileLoader fileLoader) {

    this.rawFileContent = fileLoader.getFileContent();
  }

  protected abstract void convertContent();

  public Object getFormattedContent() {
    return convertedFileContent;
  }
}
