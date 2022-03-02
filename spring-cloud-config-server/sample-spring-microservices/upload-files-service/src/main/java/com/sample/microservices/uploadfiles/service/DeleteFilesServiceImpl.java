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

@Service
public class DeleteFilesServiceImpl implements DeleteFilesService {

	@Override
	public void deleteFiles(List<String> files) throws IOException {

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

}
