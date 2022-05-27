package com.sample.microservices.uploadfiles.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermission;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.commons.lang.SystemUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFilesServiceImpl implements UploadFilesService {

  @Value("${uploadfiles.topath}")
  private String uploadDirePath;

  @Value("${uploadfiles.ownerPermission}")
  private String ownerPermission;

  @Value("${uploadfiles.groupPermission}")
  private String groupPermission;

  @Value("${uploadfiles.othersPermission}")
  private String othersPermission;

  @Override
  public void save(String fileType, MultipartFile file) {
	
	Path direPath = Paths.get(uploadDirePath);
	
	try {
		
		if(!Files.exists(direPath)) {
	    	Files.createDirectories(direPath);		
		}		
 
		System.out.println(file.getName() + ":" + this.getNewFileName(file.getOriginalFilename()));
		
		Path filePath = direPath.resolve(this.getNewFileName(file.getOriginalFilename()));
		
	    Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
	    
	    setPathsPermission(direPath, filePath);
	    
    } catch (IOException e) {
    	throw new RuntimeException("Could not initialize folder for upload!");
    } catch (Exception e) {
    	throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
    }
  }

  @Override
  public Resource load(String filename) {
	  
	Path filepath = Paths.get(uploadDirePath);

	try {
      Path file = filepath.resolve(filename);
      Resource resource = new UrlResource(file.toUri());

      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new RuntimeException("Could not read the file!");
      }
    } catch (MalformedURLException e) {
      throw new RuntimeException("Error: " + e.getMessage());
    }
  }

  @Override
  public void deleteAll() {
	Path filepath = Paths.get(uploadDirePath);
    FileSystemUtils.deleteRecursively(filepath.toFile());
  }

  @Override
  public Stream<Path> loadAll() {
		
	Path filepath = Paths.get(uploadDirePath);

	try {
      return Files.walk(filepath, 1).filter(path -> !path.equals(filepath)).map(filepath::relativize);
    } catch (IOException e) {
      throw new RuntimeException("Could not load the files!");
    }
  }
  
  private static final String READ = "READ";
  private static final String WRITE = "WRITE";
  private static final String EXECUTE = "EXECUTE";
    
  private void setPathsPermission(Path... paths) throws IOException {

	  if(SystemUtils.OS_NAME != null && !SystemUtils.OS_NAME.toLowerCase().contains("windows")) {
		  
		  //Set<PosixFilePermission> perms = new HashSet<>(Arrays.asList(PosixFilePermission.values()));
		  
		  Set<PosixFilePermission> perms = new HashSet<>();

		  //add owner permissions
		  if(ownerPermission.toUpperCase().contains(READ)) {
		      perms.add(PosixFilePermission.OWNER_READ);		  		
		  }
		  if(ownerPermission.toUpperCase().contains(WRITE)) {
		      perms.add(PosixFilePermission.OWNER_WRITE);		  		
		  }
		  if(ownerPermission.toUpperCase().contains(EXECUTE)) {
		      perms.add(PosixFilePermission.OWNER_EXECUTE);		  		
		  }
		  	
	      //add group permissions
		  if(groupPermission.toUpperCase().contains(READ)) {
		      perms.add(PosixFilePermission.GROUP_READ);		  		
		  }
		  if(groupPermission.toUpperCase().contains(WRITE)) {
		      perms.add(PosixFilePermission.GROUP_WRITE);		  		
		  }
		  if(groupPermission.toUpperCase().contains(EXECUTE)) {
		      perms.add(PosixFilePermission.GROUP_EXECUTE);		  		
		  }
	        
	      //add others permissions
		  if(othersPermission.toUpperCase().contains(READ)) {
		      perms.add(PosixFilePermission.OTHERS_READ);		  		
		  }
		  if(othersPermission.toUpperCase().contains(WRITE)) {
		      perms.add(PosixFilePermission.OTHERS_WRITE);		  		
		  }
		  if(othersPermission.toUpperCase().contains(EXECUTE)) {
		      perms.add(PosixFilePermission.OTHERS_EXECUTE);		  		
		  }
		  		  
		  for(Path path : paths) {
			  Files.setPosixFilePermissions(path, perms);	    				  
		  }
	  }
	  
  }
  
  private String getNewFileName(String originalFileName) {
	  
      String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSS"));

	  String fileName = originalFileName;	  
	  String fileExt =  "";
	  
	  if(originalFileName != null && originalFileName.lastIndexOf(".") >= 0 ) {
		  int pIndex = originalFileName.lastIndexOf(".");		  
		  fileName = originalFileName.substring(0, pIndex);	  
		  fileExt =  originalFileName.substring(pIndex);
	  }
	  	  
	  return fileName + "_" + timeStamp + fileExt;
	  
  }

}
