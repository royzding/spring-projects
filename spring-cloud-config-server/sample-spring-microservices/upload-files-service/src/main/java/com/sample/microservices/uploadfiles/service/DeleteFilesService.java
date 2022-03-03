package com.sample.microservices.uploadfiles.service;

import java.io.IOException;
import java.util.List;

import com.sample.microservices.uploadfiles.model.FileFormat;

public interface DeleteFilesService {

	  public void deleteDirs(String dir) throws IOException;
	  public void deleteFileList(List<String> files) throws IOException;
	  public void deleteFiles(FileFormat fileFormat) throws IOException;

}
