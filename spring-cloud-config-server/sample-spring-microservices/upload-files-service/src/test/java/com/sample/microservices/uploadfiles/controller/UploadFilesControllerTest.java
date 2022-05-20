package com.sample.microservices.uploadfiles.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.sample.microservices.uploadfiles.service.UploadFilesService;

@ActiveProfiles({"unit","api-security","user-info"})
@WebMvcTest(controllers = UploadFilesController.class)
class UploadFilesControllerTest  { 
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UploadFilesService uploadFilesService;
	
	@Value("${auth.svcKey}")
	String svcKey;
	
	Map<String, String> requestHeaders;
	
	HttpHeaders httpHeaders;
	
	@BeforeEach
	public void setup() {
		
		requestHeaders = new HashMap<>();
		requestHeaders.put("X-SVC-KEY", svcKey);
		
		httpHeaders = new HttpHeaders();
		httpHeaders.setAll(requestHeaders);
		
	}

/*	
	@Test
	void test_uploadFile() throws Exception {
		mockMvc.perform(post("/upload/")
				.contentType("multipart/form-data")
				.headers(httpHeaders))
				.andExpect(status().isOk());
	}
	
	@Test
	void test_getFile() throws Exception {
		mockMvc.perform(get("/upload/files/{filename:.+}", "file")
				.headers(httpHeaders))
				.andExpect(status().isOk());
	}

	
*/
	@Test
	void test_getListFiles() throws Exception {
		mockMvc.perform(get("/upload/files")
				.headers(httpHeaders))
				.andExpect(status().isOk());
	}

}


/*
public class UploadFilesController {

@Autowired
UploadFilesService uploadFilesService;

@PostMapping(value="/file", consumes={MediaType.MULTIPART_FORM_DATA_VALUE})
public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
  String message = "";
  try {

      uploadFilesService.save(file);
      message = "Uploaded the files successfully: " + file + " fileName=" + file.getOriginalFilename();
      
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
  } catch (Exception e) {
    message = "Fail to upload files!";
    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
  }
}

@PostMapping(value="/files", consumes={MediaType.MULTIPART_FORM_DATA_VALUE})
public ResponseEntity<ResponseMessage> uploadFiles(@RequestParam("files") MultipartFile[] files) {
  String message = "";
  try {
    List<String> fileNames = new ArrayList<>();

    Arrays.asList(files).stream().forEach(file -> {
      uploadFilesService.save(file);
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
}
*/