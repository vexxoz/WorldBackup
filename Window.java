import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Window {

	static Functionality core = new Functionality();
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(300,300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		JLabel title = new JLabel("World Management");
		JButton server = new JButton("Start Server");
		JButton backup = new JButton("Backup Once");
		JTextArea saveTime = new JTextArea();
		JButton backupCycle = new JButton("Start Backup Cycle");
		JButton endBackupCycle = new JButton("Stop Backup Cycle");

		title.setFont(new Font("TimesRoman", Font.PLAIN, 35));
		server.setFont(new Font("TimesRoman", Font.PLAIN, 15));
		backup.setFont(new Font("TimesRoman", Font.PLAIN, 15));
		backupCycle.setFont(new Font("TimesRoman", Font.PLAIN, 15));
		endBackupCycle.setFont(new Font("TimesRoman", Font.PLAIN, 15));
		
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		server.setAlignmentX(Component.CENTER_ALIGNMENT);
		backup.setAlignmentX(Component.CENTER_ALIGNMENT);
		saveTime.setAlignmentX(Component.CENTER_ALIGNMENT);
		backupCycle.setAlignmentX(Component.CENTER_ALIGNMENT);
		endBackupCycle.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel.add(title);
		panel.add(server);
		panel.add(backup);
		panel.add(saveTime);
		panel.add(backupCycle);
		panel.add(endBackupCycle);
		
		class menuListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(server)) {
					core.startServer();
				}
				if(e.getSource().equals(backup)) {
					core.backup();
				}
				if(e.getSource().equals(backupCycle)) {
					String text = saveTime.getText();
					if(text.length() > 0) {
						int saveTime = Integer.parseInt(text);
						core.updateSaveTime(saveTime);
					}
					core.startBackupCycle();
				}if(e.getSource().equals(endBackupCycle)) {
					try {
						core.stopBackupCycle();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}			
		}	
		
		server.addActionListener(new menuListener());
		backup.addActionListener(new menuListener());
		backupCycle.addActionListener(new menuListener());
		
		
		frame.add(panel);
		frame.setVisible(true);
		
	}

}
