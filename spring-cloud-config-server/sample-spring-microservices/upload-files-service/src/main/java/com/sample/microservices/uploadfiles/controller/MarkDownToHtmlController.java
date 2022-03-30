package com.sample.microservices.uploadfiles.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.microservices.uploadfiles.service.MarkDownToHtmlService;

@RestController
@RequestMapping("/markdown-to-html")
public class MarkDownToHtmlController {

  @Autowired
  MarkDownToHtmlService  markDownToHtmlService;

  @GetMapping(value="/{filename}")
  public String deleteDirs(@PathVariable("filename") String filename) throws IOException {
        
	  File file = ResourceUtils.getFile("classpath:release" + File.separator + filename);
	  
	  System.out.println("File Path:" + file.toPath());
        
	  return markDownToHtmlService.markdownToHtml(new String(Files.readAllBytes(file.toPath())));
  }
  

}
