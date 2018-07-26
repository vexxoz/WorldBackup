import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;

public class backupCycle extends Thread {

	private static final DateFormat sdf = new SimpleDateFormat("MM.dd.YY HH.mm.ss");
	private static boolean isTrue = true;
	private static int saveTime = Functionality.saveTime;//in min
	private static long lastSaveSize = 0;	
	
	public void terminate() {
		isTrue = false;
	}
	
	public void run() {
		while(isTrue) {
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
			System.out.println(timeStamp + "> Attempting an auto backup");
			if(lastSaveSize == 0 || FileUtils.sizeOfDirectory(srcDir) != lastSaveSize) {
				//backup
				try {
					System.out.println(timeStamp + "> Backing up World");
					// make new directory
					destDir.mkdir();
					// save all files to that new directory
				    FileUtils.copyDirectory(srcDir, destDir);
				    // update the save size
				    lastSaveSize = FileUtils.sizeOfDirectory(destDir);
				    System.out.println(timeStamp + "> Successfully auto backed up your World");
				} catch (IOException e) {
				    e.printStackTrace();
				}
			}else {
				System.out.println(timeStamp + "> World has not changed. No backup occured!");
			}
			
			// Sleep
			try {
				Thread.sleep(saveTime*60000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
