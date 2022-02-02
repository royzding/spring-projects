package com.sample.microservices.uploadfiles.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.commons.lang.SystemUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFilesServiceImpl implements UploadFilesService {

  @Value("${uploadfiles.topath}")
  private String uploadDirePath;

  @Override
  public void save(MultipartFile file) {
	
	Path direPath = Paths.get(uploadDirePath);
	
	try {
		
		if(!Files.exists(direPath)) {
	    	Files.createDirectories(direPath);		
		}		
 
		Path filePath = direPath.resolve(file.getOriginalFilename());
		
	    Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
	    
	    if(!SystemUtils.OS_NAME.toLowerCase().contains("windows")) {
	    	Set<PosixFilePermission> perms = new HashSet<>(Arrays.asList(PosixFilePermission.values()));
		    Files.setPosixFilePermissions(direPath, perms);	    	
		    Files.setPosixFilePermissions(filePath, perms);	    	
	    }

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

}
