import java.io.File;
import javax.swing.JFileChooser;

/* This class is used to open file dialog to select a directory to be checked for duplicates*/

public class Directory {
	
	public File selectDirectory() {
		File selectedDirectory = null;
		JFileChooser chooser = new JFileChooser();
		String home = System.getProperty("user.home");
	    chooser.setCurrentDirectory(new java.io.File(home + "/Desktop/"));
	    chooser.setDialogTitle("Directory");
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);
	    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	    		selectedDirectory = chooser.getSelectedFile();
	    } 
		return selectedDirectory;
	}
}
	
