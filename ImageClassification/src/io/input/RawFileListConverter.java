package io.input;

import java.util.LinkedList;
import java.util.List;

public class RawFileListConverter extends AContentConverter {

  private List<Object> list;
  private String[] splittedContent;

  protected RawFileListConverter(ARawTypeConverter rawTypeConverter,
      Object rawContent) {
    super(rawTypeConverter, rawContent);
  }

  @Override
  protected void convertContent(Object rawContent) {

    list = new LinkedList<Object>();
    splittedContent = ((String) rawContent).split(System
        .getProperty("line.separator"));

    for (int index = 0; index < splittedContent.length; index++) {
      list.add(rawTypeConverter.getConvertedObject(splittedContent[index]));
    }

    formattedContent = list;
  }
}
