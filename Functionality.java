import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;

public class Functionality {

	private static final DateFormat sdf = new SimpleDateFormat("MM.dd.YY HH.mm.ss");
	// default of 30 mins
	public static int saveTime = 30;//in min
	private Thread thread = null;
    private backupCycle cycle = null;
	// getting the timeStamp
    
	public Functionality() {
		// default constructor
	}
	
	public Functionality(int saveTimeIn) {
		saveTime = saveTimeIn;
	}
	
	public String startServer() {
		String output = "";
		Calendar cal = Calendar.getInstance();
	    String timeStamp = sdf.format(cal.getTime());
		// starts the server
	    output += timeStamp + "> Starting Server\n";
		try {
			Runtime.getRuntime().exec("cmd /c start \"\" start.bat");
			output += timeStamp + "> Server started\n";
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		output += "---------------\n";
		return output;
	}
	
	public String backup() {
		String output = "";
		Calendar cal = Calendar.getInstance();
	    String timeStamp = sdf.format(cal.getTime());
		// Backup The World
		// source
		String source = "D:\\Minecraft Server\\World";
		File srcDir = new File(source);	
		
        // destination
		String destination = "D:\\Minecraft Server\\Backups\\World on "+timeStamp;
		File destDir = new File(destination);
		
		//if world has changed
		output += timeStamp + "> Attempting a one time backup\n";
		//backup
		try {
			output += timeStamp + "> Backing up World\n";
			// make new directory
			destDir.mkdir();
			// save all files to that new directory
		    FileUtils.copyDirectory(srcDir, destDir);
		    // update the save size
		    output += timeStamp + "> Successfully backed up Your World\n";
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return output;
	}
	
	public String startBackupCycle() {
		String output = "";
		Calendar cal = Calendar.getInstance();
	    String timeStamp = sdf.format(cal.getTime());
	    output += timeStamp + "> Starting auto backup every " + saveTime + " mins\n";
		// backing up world loop
		cycle = new backupCycle();
		thread = new Thread(cycle);
		thread.start();
		return output;
	}
	
	public String stopBackupCycle() {
		String output = "";
		Calendar cal = Calendar.getInstance();
	    String timeStamp = sdf.format(cal.getTime());
		if(thread != null) {
			output += timeStamp + "> Attempting to stop auto backup\n";
			cycle.terminate();
			thread.interrupt();
			thread = null;
			output += timeStamp + "> Auto backup has been stopped\n";
		}
		return output;
	}
	
	public void updateSaveTime(int saveTimeIn) {
		saveTime = saveTimeIn;
	}

}
