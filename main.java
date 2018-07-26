import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;

public class main {

	private static final DateFormat sdf = new SimpleDateFormat("MM.dd.YY HH.mm.ss");
	private static boolean isTrue = true;
	// default of 30 mins
	private static int saveTime = 30;//in min
	private static long lastSaveSize = 0;
	
	public static void main(String[] args) {
		if( args.length > 1 && args[0] != null) {
			saveTime = Integer.parseInt(args[0]);
		}
		
		System.out.println("Starting Server");
		try {
			Runtime.getRuntime().exec("cmd /c start \"\" start.bat");
			System.out.println("Server Started");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("---------------");		
		while(isTrue) {
			//Backup The World
			String source = "D:\\Minecraft Server\\World";
			File srcDir = new File(source);
	
			Calendar cal = Calendar.getInstance();
	        String timeStamp = sdf.format(cal.getTime());
			
			String destination = "D:\\Minecraft Server\\Backups\\World on "+timeStamp;
			File destDir = new File(destination);
			
			System.out.println("Attempting a backup");
			if(lastSaveSize == 0 || FileUtils.sizeOfDirectory(srcDir) != lastSaveSize) {
				//backup
				try {
					System.out.println("Backing up world");
					destDir.mkdir();
				    FileUtils.copyDirectory(srcDir, destDir);
				    lastSaveSize = FileUtils.sizeOfDirectory(destDir);
				    System.out.println("Successfully Backed up Your World at "+timeStamp);
				} catch (IOException e) {
				    e.printStackTrace();
				}
			}else {
				System.out.println("World has not changed. No backup happened");
			}
			
			// SLeep
			try {
				Thread.sleep(saveTime*60000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
