import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* This class contains findDuplicateFiles method to create hashMap for file data content and hash code for data content
 * printDuplicateFiles method to print duplicate and non duplicate files
 * writeOutput method writes name of duplicate and non duplicate files to text file.*/

public class DuplicateFiles {

	// create hashMap for hashcode as key and list of duplicate files as value
	
	public void findDuplicateFiles(Map<String, List<String>> filesMap, File selectedDirectory) {
		if (selectedDirectory.listFiles().length == 0) {
			System.out.println("Sorry, selected directory is empty.");
			return;
		}
		for (File directory : selectedDirectory.listFiles()) {
			// recursively checking for sub directories
			if (directory.isDirectory()) {
				findDuplicateFiles(filesMap, directory);
			} else {
				try {
					FileInputStream fileInputStream = new FileInputStream(directory);
					byte fileBytes[] = new byte[(int) directory.length()];
					fileInputStream.read(fileBytes);
					fileInputStream.close();
					//create hashcode for the file contents
					String hashCode = new BigInteger(1, HashCode.messageDigest.digest(fileBytes)).toString(16);
					List<String> duplicateFilesList = filesMap.get(hashCode);
					if (duplicateFilesList == null) {
						duplicateFilesList = new ArrayList<String>();
					}
					duplicateFilesList.add(directory.getAbsolutePath());
					filesMap.put(hashCode, duplicateFilesList);
				} catch (IOException ioException) {
					throw new RuntimeException("Sorry, unable to read file"+ ioException);
				}
			}
		}
	}

	// prints duplicate and non-duplicte on console and calls other method to write output to text file
	public void printDuplicateFiles(Map<String, List<String>> filesMap) {
		List<String> duplicateFilesFound = new ArrayList<>();
		List<String> nonDuplicateFilesFound = new ArrayList<>();
		Collection<List<String>> duplicateFilesList = filesMap.values();
		for (List<String> duplicateFiles : duplicateFilesList) {
			if (duplicateFiles.size() > 1) {
				for (String file : duplicateFiles) {
					duplicateFilesFound.add(file);
				}
			} 
			else {
				nonDuplicateFilesFound.addAll(duplicateFiles);
			}
		}
		System.out.println("Duplicate files : " + duplicateFilesFound);
		System.out.println("Non duplicate files : " + nonDuplicateFilesFound);
		writeOutput(duplicateFilesFound,nonDuplicateFilesFound);
	}
	
	// Write output to text file
	public void writeOutput(List<String> duplicate, List<String> nonDuplicate) {
        FileWriter writer = null;
		try {
			String home = System.getProperty("user.home"); 
			writer = new FileWriter(home+"/Desktop/Duplicate_NonDuplicateFiles.txt"); // create a text file on User's desktop
			writer.write("****** Duplicate files *********\n\n");
	        for (int i=0;i<duplicate.size();i++) {
	            String str = duplicate.get(i);
	            writer.write(i+1 +" : "+ str);
	            writer.write("\n");
	        }
	        writer.write("\n****** Non Duplicate files *********\n\n");
	        for (int i=0;i<nonDuplicate.size();i++) {
	            String str = nonDuplicate.get(i);
	            writer.write(i+1 +" : "+ str);
	            writer.write("\n");
	        }
	        writer.close();
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
