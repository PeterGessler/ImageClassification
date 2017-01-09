package io.input;

import java.io.File;

public abstract class AFileLoader {

  protected File file;

  protected AFileLoader(File file) {
    this.file = file;
  }

  public abstract Object getFileContent();
}
