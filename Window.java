import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Window {

	static Functionality core = new Functionality();
	public static String consoleText = "";
	public static JTextArea console;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(350,600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		JLabel title = new JLabel("World Management");
		JButton server = new JButton("Start Server");
		JButton backup = new JButton("Backup Once");
		
		String[] options = {"1","5","10","15","30","45","60","90","120","180"};
		JComboBox saveTime = new JComboBox(options);
		saveTime.setSelectedIndex(4);
		
		JButton backupCycle = new JButton("Start Backup Cycle");
		JButton endBackupCycle = new JButton("Stop Backup Cycle");
		
		console = new JTextArea();
		console.setEditable(false);
		console.setText(consoleText);
		
		title.setFont(new Font("TimesRoman", Font.PLAIN, 35));
		server.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		backup.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		saveTime.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		backupCycle.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		endBackupCycle.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		server.setAlignmentX(Component.CENTER_ALIGNMENT);
		backup.setAlignmentX(Component.CENTER_ALIGNMENT);
//		saveTime.setAlignmentX(Component.CENTER_ALIGNMENT);
		backupCycle.setAlignmentX(Component.CENTER_ALIGNMENT);
		endBackupCycle.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel.add(title);
		panel.add(server);
		panel.add(backup);
		panel.add(saveTime);
		panel.add(backupCycle);
		panel.add(endBackupCycle);
		
		panel.add(console);
		
		class menuListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(server)) {
					consoleText += core.startServer();
				}
				if(e.getSource().equals(backup)) {
					consoleText += core.backup();
				}
				if(e.getSource().equals(backupCycle)) {
					String time = (String) saveTime.getSelectedItem();
					int saveTime = Integer.parseInt(time);
					core.updateSaveTime(saveTime);
					consoleText += core.startBackupCycle();
				}if(e.getSource().equals(endBackupCycle)) {
					consoleText += core.stopBackupCycle();
				}
				console.setText(consoleText);
			}			
		}	
		
		server.addActionListener(new menuListener());
		backup.addActionListener(new menuListener());
		backupCycle.addActionListener(new menuListener());
		endBackupCycle.addActionListener(new menuListener());
		
		
		frame.add(panel);
		frame.setVisible(true);
		
	}

}
