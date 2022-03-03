package com.sample.microservices.uploadfiles.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.microservices.uploadfiles.message.ResponseMessage;
import com.sample.microservices.uploadfiles.model.FileFormat;
import com.sample.microservices.uploadfiles.service.DeleteFilesService;

@RestController
@RequestMapping("/delete")
public class DeleteFilesController {

  @Autowired
  DeleteFilesService deleteFilesService;

  @PostMapping(value="/delete-dir")
  public ResponseEntity<ResponseMessage> deleteDirs(@RequestBody String dir) throws IOException {
	    String message = "deleteDirs succeeded!";

	    deleteFilesService.deleteDirs(dir);
        
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
  }
  
  @PostMapping(value="/file-list")
  public ResponseEntity<ResponseMessage> deleteFileList(@RequestBody List<String> files) throws IOException {
	    String message = "deleteFileList succeeded!";

	    deleteFilesService.deleteFileList(files);
        
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
  }
  
  @PostMapping(value="/files")
  public ResponseEntity<ResponseMessage> deleteFiles(@RequestBody FileFormat fileFormat) throws IOException {
	    String message = "deleteFiles succeeded!";

	    deleteFilesService.deleteFiles(fileFormat);
        
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
  }
  

}
