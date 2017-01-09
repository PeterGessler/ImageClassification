package io.input;

public abstract class AContentConverter implements IFileContentHandler {

  protected ARawTypeConverter rawTypeConverter;

  protected Object formattedContent;

  protected AContentConverter(ARawTypeConverter rawTypeConverter,
      Object rawContent) {
    this.rawTypeConverter = rawTypeConverter;
    convertContent(rawContent);
  }

  protected abstract void convertContent(Object rawContent);

  public Object getFormattedContent() {
    return this.formattedContent;
  }
}
