
package displays;

//imports used for the class
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import objects.SmartPhone;
import objects.User;
import utilities.AudioPlayer;
import utilities.MusicPlayer;

/*
 * Creator: Alan Sun
 * 
 * The report class of the Phone Advisor Program 
 * 
 * this class displays the top 3 options of the smart phone choice 
 * based on the score system from the Ratings and Weightings Screen
 * smartphones values based on what the user selected.
 */
public class ReportScreen extends JFrame {

	// using the java TookKit for screen customizations, such as changing cursors
	// and scaling images
	private Toolkit tk = Toolkit.getDefaultToolkit();

	// create the array that stores the recommended phones
	private SmartPhone[] recommendedPhones = new SmartPhone[3];

	// scale variable for the program
	private double widthScale = tk.getScreenSize().getWidth() / 1280;
	private double heightScale = tk.getScreenSize().getHeight() / 800;

	/*
	 * scale variable is the smallest between the width and height scale variable to
	 * prevent the program from being cutoff
	 */
	private double scaleVariable = Math.min(widthScale, heightScale);

	private String backgroundMusicLocation;

	private boolean musicPlaying = true;
	private boolean audioPlaying = true;

	// array that stores all the smart phones
	private SmartPhone[] smartPhoneArray;
	// the user class is used for the user choices and weightings
	private User user;

	// Constructor of the report screen calls all the other methods
	// Initializes the user variable and smartPhoneArray
	public ReportScreen(SmartPhone[] smartPhoneArray, User user) {

		// set the smartPhoneArray and user in the property into the one taken in the
		// parameter
		this.smartPhoneArray = smartPhoneArray;
		this.user = user;

		// other methods being called
		rankSmartPhones(); // ranks the smart phones based on their score
		addJComponents(); // adds the JComponents
		frameSetUp(); // sets up the frame
		CustomCursor(); // sets a custom cursor
		playBackgroundAudio(); // plays the background audio

	}

	// this method plays the background music for the report screen
	public void playBackgroundAudio() {

		// making a new random variable to use one of the musics stored as background
		Random random = new Random();

		// random variable for the 7 background music
		int randomMusic = random.nextInt(7) + 1;
		// select the background music using the randomMusic variable
		backgroundMusicLocation = "res/audio" + randomMusic + ".wav";

		// plays the selected music using the music player
		MusicPlayer.playMusic(backgroundMusicLocation);

	}

	// method that sets up the JFrame
	public void frameSetUp() {

		// using the TookKit to get screen size and width to set the size of the frame
		setSize((int) (tk.getScreenSize().width * scaleVariable), (int) (tk.getScreenSize().height * scaleVariable));

		// allow the JFrame to exit upon closing the application
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// disable the resize function
		setResizable(false);

		// allow the program to center to the screen, disable auto location
		setLocationRelativeTo(null);

		// allow the frame to be visible
		setVisible(true);

		// disable auto layout from the layout manager
		setLayout(null);

		// set the program title
		setTitle("Smart Phone Advisor");

	}

	// this method adds all the buttons to the frame
	public void addJComponents() {

		// creating a new JPanel for layout
		JPanel panel = new JPanel();

		addJMenus();

		// set the size of the panel as the screen size and place it at (x,y) = (0,0)
		panel.setBounds(0, 0, tk.getScreenSize().width, tk.getScreenSize().height);
		// set auto layout to null, disable it
		panel.setLayout(null);
		// setting the background color of the panel
		panel.setBackground(Color.BLACK);
		// add the panel to the frame
		add(panel);

		// array to store the three image for the recommended phones
		ImageIcon[] scaledIcons = new ImageIcon[3];

		// for loop to scale all the three recommended phone images
		for (int i = 0; i < 3; i++) {
			// load the image to a imageIcon
			ImageIcon phoneIcon = recommendedPhones[i].getPhoneIcon();
			// get the image from the ImageIcon to scale it
			Image phoneImage = phoneIcon.getImage();

			// Scale the image to the desired size
			Image newPhoneImage = phoneImage.getScaledInstance((int) scaleVariable * 300, (int) scaleVariable * 300,
					java.awt.Image.SCALE_SMOOTH);

			// set the image to the scaled icon array as an image for the ImageIcon
			scaledIcons[i] = new ImageIcon(newPhoneImage);

			// for loop to create all three recommended phone Hyperlink Buttons and
			// TextAreas

			// create a new JButton to work with with the scaled icon at that index
			JButton phoneButton = new JButton(scaledIcons[i]);

			// different buttons have different locations
			if (i == 0)
				// every button will be scaled and first button is set at location (x, y) =
				// (100, 100)
				phoneButton.setBounds(100, 100, scaledIcons[i].getIconWidth(), scaledIcons[i].getIconHeight());

			else if (i == 1)
				// second button is set the center of the window width subtract half of the icon
				// size to center the button
				phoneButton.setBounds(tk.getScreenSize().width / 2 - scaledIcons[i].getIconWidth() / 2, 100,
						scaledIcons[i].getIconWidth(), scaledIcons[i].getIconHeight());

			else
				// third button is set at (x, y) = (window width - 100, 100)
				phoneButton.setBounds(tk.getScreenSize().width - scaledIcons[i].getIconWidth() - 100, 100,
						scaledIcons[i].getIconWidth(), scaledIcons[i].getIconHeight());

			// set a index variable for the index of the component because the
			// ActionListener cannot access the control variable
			int index = i;
			// adds an ActionListener to the button
			phoneButton.addActionListener(new ActionListener() {

				// actionPerformed method from the actionLister interface handles the actions
				// once button is clicked
				@Override
				public void actionPerformed(ActionEvent e) {

					// plays the button sound effect using the music player, if sound effect is
					// enabled
					if (audioPlaying)
						AudioPlayer.playAudio("res/select.wav");

					// opens the web page for the recommended phone using its hyperlink variable
					openWebPage(recommendedPhones[index].getHyperlink());

				}
			});

			// set the phone button background
			phoneButton.setBackground(Color.YELLOW);
			// set the button as non-transparent
			phoneButton.setOpaque(true);

			// add the phoneButton to the frame
			panel.add(phoneButton);

			// Create a TextArea to display the smart phone informations
			JTextArea phoneTextArea = new JTextArea();

			// set the text of the TextArea as the toString method of the smart phone
			phoneTextArea.setText(recommendedPhones[i].toString());

			// set the background color of the TextArea
			phoneTextArea.setBackground(Color.black);

			// set the foreground(text) color of the TextArea
			phoneTextArea.setForeground(Color.yellow);

			// set a custom font to the textArea
			phoneTextArea.setFont(new Font("TimesRoman", Font.ITALIC, 20));

			// attach a JScrollPane to the TextArea
			JScrollPane scroll = new JScrollPane(phoneTextArea);

			// set each of the scroll pane to the location of the button for that phone
			if (i == 0)
				scroll.setBounds(100, scaledIcons[i].getIconHeight() + 150, (int) scaleVariable * 300,
						(int) scaleVariable * 300 / 3 * 2);

			else if (i == 1)
				scroll.setBounds(tk.getScreenSize().width / 2 - scaledIcons[i].getIconWidth() / 2,
						phoneButton.getHeight() + 150, (int) scaleVariable * 300, (int) scaleVariable * 300 / 3 * 2);

			else if (i == 2)
				scroll.setBounds(tk.getScreenSize().width - scaledIcons[i].getIconWidth() - 100,
						phoneButton.getHeight() + 150, (int) scaleVariable * 300, (int) scaleVariable * 300 / 3 * 2);

			// add the scroll pane to the frame
			panel.add(scroll);

		}

		// creates a new JLabel with the name of this frame
		JLabel screenName = new JLabel("Report Screen");

		// set the bound of the JLabel and center it on screen
		screenName.setBounds(tk.getScreenSize().width / 2 - 230 / 2, 25, 242, 50);
		// set the color of the JLaabel
		screenName.setForeground(Color.YELLOW);
		// set the font of the label
		screenName.setFont(new Font("impact", Font.PLAIN, 38));
		// add the label to the panel
		panel.add(screenName);

		// creates a new JLabel with the name of this frame
		JLabel programName = new JLabel("Smart Phone Advisor");

		// set the bound of the JLabel and center it on screen
		screenName.setBounds(tk.getScreenSize().width / 2 - 230 / 2, 25, 242, 50);
		// set the color of the JLaabel
		screenName.setForeground(Color.YELLOW);
		// set the font of the label
		screenName.setFont(new Font("impact", Font.PLAIN, 38));
		// add the label to the panel
		panel.add(screenName);

	}

	// this method adds all the items related to the menu
	public void addJMenus() {

		// create a new JMenuBar item that stores different menus
		JMenuBar menuBar = new JMenuBar();
		// set the program's menu bar to the menu bar create above
		setJMenuBar(menuBar);

		// create a new control menu
		JMenu controlMenu = new JMenu("Control");

		// add controlMenu drop down to the menu bar
		menuBar.add(controlMenu);

		// creating the exit option under the control menu
		JMenuItem restartOption = new JMenuItem("Restart");

		// add an action listener for button actions when clicked
		restartOption.addActionListener(new ActionListener() {

			// actionPerformed method from the actionLister interface handles the actions
			// once button is clicked
			@Override
			public void actionPerformed(ActionEvent e) {

				// restart the program by closing this frame and creating a new ratingScreen
				destroyFrame(); // destroys this frame

				// stops the music from playing because screen is being exited
				MusicPlayer.stopMusic();

				// plays the button sound effect using the music player, if sound effect is
				// allowed
				if (audioPlaying)
					AudioPlayer.playAudio("res/select.wav");

				// creates the first frame to restart the application
				RatingScreen ratingScreen = new RatingScreen(smartPhoneArray, user);

			}
		});

		// adds the restart button to the control menu
		controlMenu.add(restartOption);

		// creating the exit option under the control menu
		JMenuItem exitOption = new JMenuItem("Exit");

		// add an action listener for button actions when clicked
		exitOption.addActionListener(new ActionListener() {

			// actionPerformed method from the actionLister interface handles the actions
			// once button is clicked
			@Override
			public void actionPerformed(ActionEvent e) {

				// exits the program
				System.exit(1);

			}
		});

		// adds the exit button to the control menu
		controlMenu.add(exitOption);

		// the help menu will include all the help related menu items
		JMenu helpMenu = new JMenu("Help");
		// add the menu to the menu bar
		menuBar.add(helpMenu);

		// the description menu item will specify the screen descriptions and controls
		JMenuItem desriptionOption = new JMenuItem("Description");
		desriptionOption.addActionListener(new ActionListener() {

			// actionPerformed method from the actionLister interface handles the actions
			// once button is clicked
			@Override
			public void actionPerformed(ActionEvent e) {

				// plays a sound effect, if it is allowed
				if (audioPlaying)
					AudioPlayer.playAudio("res/select.wav");

				// shows screen description and controls upon clicking
				JOptionPane.showMessageDialog(null,
						"The current screen shows the top three phone suggestions for your choices\n"
								+ "- clicking the phone images will lead you to the website where you can purchase the phone\n"
								+ "- you may restart your selection by clicking the restart button located at the control panel in the menu bar\n"
								+ "- you may quit the program by clicking exit in the control panel\n\n"
								+ "click 'ok' to continue...",
						"Description", JOptionPane.INFORMATION_MESSAGE);

			}

		});

		// add desriptionOption to the help menu
		helpMenu.add(desriptionOption);

		// create a new control menu
		JMenu audioMenu = new JMenu("Audio");

		// add audioMenu drop down to the menu bar
		menuBar.add(audioMenu);

		// this menu item allows the user to disable music
		JMenuItem disableMusicOption = new JMenuItem("Disable Background Music");
		disableMusicOption.addActionListener(new ActionListener() {

			// actionPerformed method from the actionLister interface handles the actions
			// once button is clicked
			@Override
			public void actionPerformed(ActionEvent e) {

				// set the current state of music availability to false, music closed
				musicPlaying = false;

				// stops the music from playing
				MusicPlayer.stopMusic();

			}

		});

		// add desriptionOption to the help menu
		audioMenu.add(disableMusicOption);

		// this menu item allows the user to play a random Music music
		JMenuItem enableMusicOption = new JMenuItem("Play Random Music");
		enableMusicOption.addActionListener(new ActionListener() {

			// actionPerformed method from the actionLister interface handles the actions
			// once button is clicked
			@Override
			public void actionPerformed(ActionEvent e) {

				// stops the music, if there are any
				if (musicPlaying)
					MusicPlayer.stopMusic();

				// plays a new music
				playBackgroundAudio();

				// set the now playing to true
				musicPlaying = true;

			}

		});

		// add desriptionOption to the help menu
		audioMenu.add(enableMusicOption);

		// this menu item allows the user to play a random Music music
		JMenuItem disableSFXOption = new JMenuItem("Disable Sound Effect");
		disableSFXOption.addActionListener(new ActionListener() {

			// actionPerformed method from the actionLister interface handles the actions
			// once button is clicked
			@Override
			public void actionPerformed(ActionEvent e) {

				// disabling all sounds by turning sound playing into false
				audioPlaying = false;

			}

		});

		// add desriptionOption to the help menu
		audioMenu.add(disableSFXOption);

		// this menu item allows the user to play a random Music music
		JMenuItem enableSFXOption = new JMenuItem("Enable Sound Effect");
		enableSFXOption.addActionListener(new ActionListener() {

			// actionPerformed method from the actionLister interface handles the actions
			// once button is clicked
			@Override
			public void actionPerformed(ActionEvent e) {

				// enable sound effects to play for this screen
				audioPlaying = true;

			}

		});

		// add desriptionOption to the help menu
		audioMenu.add(enableSFXOption);
	}

	// method that ranks the phones based on the marks calculated
	private void rankSmartPhones() {

		// for loop looping through the smart phone to calculate and set score for each
		// smart phone
		for (SmartPhone currentSmartPhone : smartPhoneArray) {

			// set the score of the phone by calling the weightedDataMetrics method
			currentSmartPhone.setScore(weightedDataMetrics(currentSmartPhone));

		}

		// sorts the smartPhoneArray in reversed order
		Arrays.sort(smartPhoneArray, Comparator.comparing(SmartPhone::getScore).reversed());

		// select the top 3 phones based on the score at index 0, 1, and 2
		for (int i = 0; i < 3; i++) {
			// set the top 3 recommended phones as the top 3 sorted in the smart phone array
			recommendedPhones[i] = smartPhoneArray[i];
		}

	}

	// method that calculates the source for each individual phone based on the
	private int weightedDataMetrics(SmartPhone currentSmartPhone) {

		// initialize the total score variable
		int totalScore = 0;

		// looping through 8 indexes to get the total score from all 8 categories
		for (int i = 0; i < 8; i++) {

			// calculates total score by adding each multiplies of ratings and weightings
			totalScore += currentSmartPhone.getRatings()[i] * user.getWeighting()[i];
			// currentSmartPhone.getRatings()[i] * user.getWeighting()[i]

		}

		// returns the total score to the user
		return totalScore;

	}

	// method opens the URL from the parameter
	public void openWebPage(String url) {

		try {
			// using the java.awt library to open the URL on a browser
			java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));

		} catch (java.io.IOException e) {

			System.out.println("URL not found");
		}

	}

	// method that closes the report screen
	public void destroyFrame() {

		this.dispose();

	}

	// method that changes the cursor icon
	public void CustomCursor() {

		// load an image using ToolKit
		Image mouse = tk.getImage("cursor.png");

		// set the cursor icon giving a new image, point, and name
		setCursor(tk.createCustomCursor(mouse, new Point(getX(), getY()), "Custom Cursor"));

	}

}
