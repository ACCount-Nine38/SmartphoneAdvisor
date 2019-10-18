/*This class contains a frame that allows the user to enter their weightings.
 * 
 * 
 * author-Matthew Dai
 */
package displays;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import objects.SmartPhone;
import objects.User;
import utilities.MusicPlayer;

public class WeightingsScreen extends JFrame implements ActionListener {

	// Fields
	private SmartPhone[] smartphoneArray;
	private User user;
	private JLabel weightingsTitle = new JLabel("Weighitngs Screen");
	private JButton reportButton = new JButton("Go To Report");

	// Weightings
	JLabel[] weightingsLabelArray = new JLabel[8];
	JComboBox<Integer>[] weightingsComboBoxArray = new JComboBox[8];

	JPanel userWeightingsPanel = new JPanel();

	// Report Button
	JButton reportPanel = new JButton("Create Report");

	// Constructor
	public WeightingsScreen(SmartPhone[] smartphone, User user) {

		// Set fields equal to parameters
		this.smartphoneArray = smartphone;
		this.user = user;

		// Call methods
		playBackgroundAudio();
		frameSetup();
		userWeightingsPanelSetup();

	}

	// This method...
	private void userWeightingsPanelSetup() {

		// Panel setup
		userWeightingsPanel.setBounds(50, 100, 1275, 600);
		userWeightingsPanel.setLayout(null);

		// Call method
		setupWeightings();
	}

	// This method...
	private void setupWeightings() {

		weightingsLabelArray[0] = new JLabel("1.How important is the brand?");
		weightingsLabelArray[1] = new JLabel("2.How important is the budegt?");
		weightingsLabelArray[2] = new JLabel("3.How important is the rear camera quality?");
		weightingsLabelArray[3] = new JLabel("4.How important is the built in memory?");
		weightingsLabelArray[4] = new JLabel("5.How important is the RAM?");
		weightingsLabelArray[5] = new JLabel("6.How important is the processor speed?");
		weightingsLabelArray[6] = new JLabel("7.How important is the front camera?");
		weightingsLabelArray[7] = new JLabel("8.How important is the storage?");

		// Labels
		for (int index = 0; index < weightingsLabelArray.length; index++) {

			// Labels Array
			if (index < 4)
				weightingsLabelArray[index].setBounds(50 + index * 300, 50, 250, 25);
			else
				weightingsLabelArray[index].setBounds(50 + (index - 4) * 280, 250, 250, 25);

			userWeightingsPanel.add(weightingsLabelArray[index]);

			// Combo Boxes
			weightingsComboBoxArray[index] = new JComboBox<Integer>();
			weightingsComboBoxArray[index].addActionListener(this);

			// Add 0 to 10 to boxes
			for (int value = 0; value < 11; value++)
				weightingsComboBoxArray[index].addItem(value);

			// Combo Box Array
			if (index < 4)
				weightingsComboBoxArray[index].setBounds(50 + index * 300, 100, 250, 25);
			else
				weightingsComboBoxArray[index].setBounds(50 + (index - 4) * 300, 300, 250, 25);

			userWeightingsPanel.add(weightingsComboBoxArray[index]);

		}

	}

	// This method...
	private void frameSetup() {

		// Frame Size
		getContentPane().setBackground(Color.black);
		setSize(1375, 1000);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Smart Phone Advisor");

		// Add title label
		JLabel title = new JLabel("Weightings Screen");
		title.setBounds(525, 0, 400, 100);
		title.setFont(new Font("TimesRoman", Font.BOLD, 40));
		title.setForeground(Color.white);
		title.setBackground(Color.black);
		add(title);

		// Add report button
		reportButton.setBounds(575, 620, 200, 50);
		reportButton.setFont(new Font("Default", Font.BOLD, 20));
		reportButton.setForeground(Color.black);
		reportButton.addActionListener(this);
		add(reportButton);
		add(userWeightingsPanel);
		setVisible(true);
	}

	// Action Performed
	public void actionPerformed(ActionEvent event) {

		//
		for (int index = 0; index < 8; index++) {

			if (event.getSource() == weightingsComboBoxArray[index]) {

				user.getWeighting()[index] = weightingsComboBoxArray[index].getSelectedIndex();

			}
		}

		//
		if (event.getSource() == reportButton) {
			MusicPlayer.stopMusic();
			
			ReportScreen reportScreen = new ReportScreen(smartphoneArray, user);
			
			setVisible(false);

		}
	}

	// this method plays the background music for the report screen
	public void playBackgroundAudio() {

		// making a new random variable to use one of the musics stored as background
		Random random = new Random();

		// random variable for the 7 background music
		int randomMusic = random.nextInt(7) + 1;
		// select the background music using the randomMusic variable
		String backgroundMusicLocation = "res/audio" + randomMusic + ".wav";

		// plays the selected music using the music player
		MusicPlayer.playMusic(backgroundMusicLocation);
	}

}
