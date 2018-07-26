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
	
	public Functionality() {
		// default constructor
	}
	
	public Functionality(int saveTimeIn) {
		saveTime = saveTimeIn;
	}
	
	public void startServer() {
		// starts the server
		System.out.println("Starting Server");
		try {
			Runtime.getRuntime().exec("cmd /c start \"\" start.bat");
			System.out.println("Server Started");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("---------------");		
	}
	
	public void backup() {
		// Backup The World
		// source
		String source = "D:\\Minecraft Server\\World";
		File srcDir = new File(source);

		// getting the timeStamp
		Calendar cal = Calendar.getInstance();
        String timeStamp = sdf.format(cal.getTime());
		
        // destination
		String destination = "D:\\Minecraft Server\\Backups\\World on "+timeStamp;
		File destDir = new File(destination);
		
		//if world has changed
		System.out.println("Attempting a backup");
		//backup
		try {
			System.out.println("Backing up world");
			// make new directory
			destDir.mkdir();
			// save all files to that new directory
		    FileUtils.copyDirectory(srcDir, destDir);
		    // update the save size
		    System.out.println("Successfully Backed up Your World at "+timeStamp);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public void startBackupCycle() {
		System.out.println("Starting Auto Backup every " + saveTime + " mins");
		// backing up world loop
		cycle = new backupCycle();
		thread = new Thread(cycle);
		thread.start();

	}
	
	public void stopBackupCycle() throws InterruptedException {
		if(thread != null) {
			System.out.println("Attempting to stop Auto Backup (Note: this may take up to however long the backup time is)");
			cycle.terminate();
			thread.join();
			System.out.println("Auto Backup has been stopped");
		}
	}
	
	public void updateSaveTime(int saveTimeIn) {
		saveTime = saveTimeIn;
	}

}
