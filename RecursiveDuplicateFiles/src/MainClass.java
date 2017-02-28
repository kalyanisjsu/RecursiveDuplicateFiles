import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainClass {

	public static void main(String[] args) {
		Map<String, List<String>> filesMap = new HashMap<String, List<String>>();
		Directory directory = new Directory();
		DuplicateFiles duplicateFiles = new DuplicateFiles();
		
		File selectedDirectory = directory.selectDirectory();
		if (selectedDirectory == null) {
			System.out.println("Please select directory");
			return;
		}
		System.out.println("Selected directory : " + selectedDirectory.toString());
		duplicateFiles.findDuplicateFiles(filesMap, selectedDirectory);
		duplicateFiles.printDuplicateFiles(filesMap);
     }
}
