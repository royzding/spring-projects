package com.sample.microservices.uploadfiles.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.sample.microservices.uploadfiles.message.ResponseMessage;
import com.sample.microservices.uploadfiles.model.FileInfo;
import com.sample.microservices.uploadfiles.service.UploadFilesService;

@RestController
@RequestMapping("/upload")
public class UploadFilesController {

  @Autowired
  UploadFilesService uploadFilesService;

  @PostMapping(value="/{fileType}", consumes={MediaType.MULTIPART_FORM_DATA_VALUE})
  public void uploadFilex(@PathVariable String fileType, @RequestParam("textdefault") MultipartFile file) throws IOException{
 
        uploadFilesService.save(fileType, file);
  }

  @PostMapping(value="/file/{fileType}", consumes={MediaType.MULTIPART_FORM_DATA_VALUE})
  public ResponseEntity<ResponseMessage> uploadFile(@PathVariable String fileType, @RequestParam("textdefault") MultipartFile file) {
    String message = "";
    try {
 
        uploadFilesService.save(fileType, file);
        message = "Uploaded the files successfully: " + file + " fileName=" + file.getOriginalFilename();
        
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    } catch (Exception e) {
      message = "Fail to upload files!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }

  @PostMapping(value="/files/{fileType}", consumes={MediaType.MULTIPART_FORM_DATA_VALUE})
  public ResponseEntity<ResponseMessage> uploadFiles(@PathVariable String fileType, @RequestParam("files") MultipartFile[] files) {
    String message = "";
    try {
      List<String> fileNames = new ArrayList<>();

      Arrays.asList(files).stream().forEach(file -> {
        uploadFilesService.save(fileType, file);
        fileNames.add(file.getOriginalFilename());
      });

      message = "Uploaded the files successfully: " + fileNames;
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    } catch (Exception e) {
      message = "Fail to upload files!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }

  @GetMapping("/files")
  public ResponseEntity<List<FileInfo>> getListFiles() {
    List<FileInfo> fileInfos = uploadFilesService.loadAll().map(path -> {
      String filename = path.getFileName().toString();
      String url = MvcUriComponentsBuilder
          .fromMethodName(UploadFilesController.class, "getFile", path.getFileName().toString()).build().toString();

      return new FileInfo(filename, url);
    }).collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
  }

  @GetMapping("/files/{filename:.+}")
  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
    Resource file = uploadFilesService.load(filename);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
  }
  
  @GetMapping(value = {"/PvAndRp/", "/PvAndRp/{pv}"})
  public String getPathVariableAndRequestParam(
		  @PathVariable(required=false) String pv,
		  @RequestParam(required=false) String name,
		  @RequestParam(value="pageNum", defaultValue = "12") int pageNum
		 ) 
  {
	  return "pv:[" + pv + "]; name:[" + name + "]; pageNum:[" + pageNum + "]";

  }

  @GetMapping(value = {"/PvAndRp2/", "/PvAndRp2/{pv}"})
  public String getPathVariableAndRequestParam2(
		  @PathVariable Optional<String> pv,
		  @RequestParam Optional<String> name,
		  @RequestParam(value="pageNum", defaultValue = "12") int pageNum
		 ) 
  {
	  return "pv:[" + pv + "]; name:[" + name + "]; pageNum:[" + pageNum + "]";

  }

}
