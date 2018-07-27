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
	private Calendar cal = Calendar.getInstance();
    private String timeStamp = sdf.format(cal.getTime());
    
	public Functionality() {
		// default constructor
	}
	
	public Functionality(int saveTimeIn) {
		saveTime = saveTimeIn;
	}
	
	public void startServer() {
		timeStamp = sdf.format(cal.getTime());
		// starts the server
		System.out.println(timeStamp + "> Starting Server");
		try {
			Runtime.getRuntime().exec("cmd /c start \"\" start.bat");
			System.out.println(timeStamp + "> Server started");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("---------------");		
	}
	
	public void backup() {
		timeStamp = sdf.format(cal.getTime());
		// Backup The World
		// source
		String source = "D:\\Minecraft Server\\World";
		File srcDir = new File(source);	
		
        // destination
		String destination = "D:\\Minecraft Server\\Backups\\World on "+timeStamp;
		File destDir = new File(destination);
		
		//if world has changed
		System.out.println(timeStamp + "> Attempting a one time backup");
		//backup
		try {
			System.out.println(timeStamp + "> Backing up World");
			// make new directory
			destDir.mkdir();
			// save all files to that new directory
		    FileUtils.copyDirectory(srcDir, destDir);
		    // update the save size
		    System.out.println(timeStamp + "> Successfully backed up Your World");
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public void startBackupCycle() {
		System.out.println(timeStamp + "> Starting auto backup every " + saveTime + " mins");
		// backing up world loop
		cycle = new backupCycle();
		thread = new Thread(cycle);
		thread.start();

	}
	
	public void stopBackupCycle() {
		if(thread != null) {
			System.out.println(timeStamp + "> Attempting to stop auto backup");
			cycle.terminate();
			thread.interrupt();
			thread = null;
			System.out.println(timeStamp + "> Auto backup has been stopped");
		}
	}
	
	public void updateSaveTime(int saveTimeIn) {
		saveTime = saveTimeIn;
	}

}
