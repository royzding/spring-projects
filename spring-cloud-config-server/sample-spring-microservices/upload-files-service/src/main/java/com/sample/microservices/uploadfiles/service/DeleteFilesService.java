package com.sample.microservices.uploadfiles.service;

import java.io.IOException;
import java.util.List;

public interface DeleteFilesService {

  public void deleteFiles(List<String> files) throws IOException;

}
