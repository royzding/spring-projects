package com.sample.microservices.uploadfiles.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileFormat {
	
	@JsonProperty("INPUT_FILE_NAME")
	private String inputFileName;
	
	@JsonProperty("UPLOAD_DIR")
	private String upLoadDir;
}
