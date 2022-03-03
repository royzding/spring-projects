package com.sample.microservices.uploadfiles.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

import com.sample.microservices.uploadfiles.model.FileFormat;

@Service
public class DeleteFilesServiceImpl implements DeleteFilesService {

	@Override
	public void deleteFileList(List<String> files) throws IOException {

		for(String file : files) {
			FileSystemUtils.deleteRecursively(Path.of(file));		
/*			
			try(Stream<Path> walk = Files.walk(Path.of(file))) {

				  walk.sorted(Comparator.reverseOrder())
				  .map(Path::toFile)
				  .forEach(File::delete);
				  
			} catch (IOException e) {
				e.printStackTrace();
			}
			    
			Files.deleteIfExists(path);
*/			
		}
						 
					

		
	}

	@Override
	public void deleteFiles(FileFormat fileFormat) throws IOException {
		
		System.out.println(fileFormat.getInputFileName() + ":" + fileFormat.getUpLoadDir());
		
		if(fileFormat.getInputFileName() == null || fileFormat.getUpLoadDir() == null) {
			throw new RuntimeException("Bad/Invalid FileFormat!");
		}
		
		String fileName = fileFormat.getInputFileName().trim();
		String dirName	= fileFormat.getUpLoadDir().trim();
		
		if(!fileName.contains("*")) {
			FileSystemUtils.deleteRecursively(Path.of(dirName, fileName));
		} else {
			String fileExt = fileName.substring(1);
			
			try(Stream<Path> walk = Files.walk(Path.of(dirName))) {

				  walk.filter(path->path.getFileName().toString().endsWith(fileExt))
				  .map(Path::toFile)
				  .forEach(File::delete);
				  
			} 

		}
		
	}	

}
