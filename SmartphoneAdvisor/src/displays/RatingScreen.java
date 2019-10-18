package displays;

//required imports used in my class
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import objects.SmartPhone;
import objects.User;
import utilities.MusicPlayer;

/*
 * Created by Roshawn Jamasi
 * 
 * The ratings class of the Phone Advisor Program. 
 * 
 * This class makes a frame asks the user their preferences 
 * in regards phone characteristics and awards the 30 
 * smartphones values based on what the user selected.
 * Crediting Alan Sun for the use of his music class. 
 * 
 */
public class RatingScreen extends JFrame implements ActionListener {

	// fields and arrays that we will be using
	private SmartPhone[] smartphoneArray;
	private User user;


	//The different GUI Elements that will be incorporated
	JLabel titleLabel = new JLabel("User Ratings Screen");
	JButton weightsButton = new JButton("Go to Weighting Screen");
	JPanel userRatingsPanel = new JPanel();
	JLabel questionLabel[] = new JLabel[5];
	ButtonGroup buttonGroup[] = new ButtonGroup[5];
	JRadioButton[][] radioButton = new JRadioButton[5][3];

	// the constructor based on the fields
	public RatingScreen(SmartPhone[] smartphoneArray, User user) {

		// fields attach to parameter
		this.smartphoneArray = smartphoneArray;
		this.user = user;

		//the different methods that will be called up in the listed order
		userRatingsPanelSetup();
		setupQuestionLabels();
		setupRadioButtons();
		frameSetup();
		playBackgroundAudio();

	}

	// the stored location of the background music. Background music used with assitance of Alan
	private String backgroundMusicLocation;

	//the method creates the questions to display on the panel
	private void setupQuestionLabels() {

		//the questions that will be asked to be displayed on the panel
		questionLabel[0] = new JLabel("Which brand do you prefer?");
		questionLabel[1] = new JLabel("What is your budget?");
		questionLabel[2] = new JLabel("What camera quality is needed?");
		questionLabel[3] = new JLabel("How much memory is needed?");
		questionLabel[4] = new JLabel("How much RAM is needed?");

		//loop setting up the boundries for the questions
		for (int index = 0; index < questionLabel.length; index++) {
			questionLabel[index].setBounds(18 + 250 * index, 75, 300, 25);
			userRatingsPanel.add(questionLabel[index]);

		} 

	}

	//this method sets up radio buttons on the panel
	private void setupRadioButtons() {

		//creating user brand preference options
		radioButton[0][0] = new JRadioButton("Apple");
		radioButton[0][1] = new JRadioButton("Samsung");
		radioButton[0][2] = new JRadioButton("Other");

		//creating users budget preference options
		radioButton[1][0] = new JRadioButton("Less than $200");
		radioButton[1][1] = new JRadioButton("Within $200-$500");
		radioButton[1][2] = new JRadioButton("More than $500");

		//creating users camera quality preference options
		radioButton[2][0] = new JRadioButton("Low quality");
		radioButton[2][1] = new JRadioButton("Medium quality");
		radioButton[2][2] = new JRadioButton("High quality");

		//creating users interenal storage preference options
		radioButton[3][0] = new JRadioButton("Less than 32 GB");
		radioButton[3][1] = new JRadioButton("Between 32-64 GB");
		radioButton[3][2] = new JRadioButton("More than 64 GB");

		//creating users random access memory preference options
		radioButton[4][0] = new JRadioButton("Less than 2 GB");
		radioButton[4][1] = new JRadioButton("Between 2-4 GB");
		radioButton[4][2] = new JRadioButton("More than 4 GB");

		//loops setting up the boundries for the radio buttons (aka the user options) for each of the questions
		for (int groupIndex = 0; groupIndex < buttonGroup.length; groupIndex++) {
			buttonGroup[groupIndex] = new ButtonGroup();

			//
			for (int index = 0; index < radioButton[groupIndex].length; index++) {
				buttonGroup[groupIndex].add(radioButton[groupIndex][index]);
				radioButton[groupIndex][index].setBounds(50 + groupIndex * 250, 100 + index * 25, 200, 25);

				radioButton[groupIndex][index].addActionListener(this);
				userRatingsPanel.add(radioButton[groupIndex][index]);

			}

		}

	}

	//method to set up the panel
	private void userRatingsPanelSetup() {

		//settings the border colour and thickness of border around panel
		userRatingsPanel.setBorder(BorderFactory.createLineBorder(Color.white, 3));

		//setting the background colour of the panel
		userRatingsPanel.setBackground(Color.cyan);

		//creating boundries for the panel
		userRatingsPanel.setBounds(50, 100, 1200, 500);
		userRatingsPanel.setLayout(null);

		//adding the panel to the frame
		add(userRatingsPanel);

	}

	//Creates actions to occur from pressing the buttons
	@Override
	public void actionPerformed(ActionEvent event) {

		//these loops set up the radio buttons 
		for (int categoryIndex = 0; categoryIndex < 5; categoryIndex++)
		
			for (int index = 0; index < 3; index++)
			
				if (event.getSource() == radioButton[categoryIndex][index])
					setRatings(categoryIndex, index);
		
		for(int categoryIndex = 0; categoryIndex < 5; categoryIndex++ )
			
			for(int index = 0; index < 3; index++)
			
				if(event.getSource() == radioButton[categoryIndex][index]) setRatings(categoryIndex, index);

		//setting up the button to switch the weighting screen
		if (event.getSource() == weightsButton) {
			MusicPlayer.stopMusic();
			new WeightingsScreen(smartphoneArray, user);
			setVisible(false);
		}
	}

	//this method sets each choice to the correct button group
	private void setRatings(int categoryIndex, int choice) {

		if (categoryIndex == 0)
			setBrandRating(choice);

		else if (categoryIndex == 1)
			setBudgetRating(choice);

		else if (categoryIndex == 2)
			setCameraRating(choice);

		else if (categoryIndex == 3)
			setStorageRating(choice);

		else if (categoryIndex == 4)
			setRamRating(choice);
	}

	//this method assigns values to phones based on user brand preferences
	private void setBrandRating(int choice) {

		//loop assign ratings to all 30 phones based on brand
		for (int phone=0; phone < 30; phone++){

			//if the user selects "apple"
			if (choice==0){

				//give all phones with the brand "apple" a rating value of 100
				if (smartphoneArray[phone].getBrand().equalsIgnoreCase("Apple"))
					smartphoneArray[phone].getRatings()[0]=100;

				//all other brands of phones get rating value of zero
				else
					smartphoneArray[phone].getRatings()[0]=0;

			}

			//if the user selects "samsung"
			else if (choice==1){

				//give all phones with the brand "samsung" a rating value of 100
				if (smartphoneArray[phone].getBrand().equalsIgnoreCase("Samsung")) {
					smartphoneArray[phone].getRatings()[0]=100;

					//all other brands of phones get rating value of zero
				} else
					smartphoneArray[phone].getRatings()[0]=0;

			}

			//if the user selects "other"
			else if (choice==2) {

				//give all phones with the brand "samsung" a rating value of 0
				if (smartphoneArray[phone].getBrand().equalsIgnoreCase("Samsung")) 
					smartphoneArray[phone].getRatings()[0]=0;

				//give all phones with the brand "apple" a rating value of 0
				else if (smartphoneArray[phone].getBrand().equalsIgnoreCase("Apple"))
					smartphoneArray[phone].getRatings()[0]=0;

				//give all other phone brands a rating value of 100
				else 
					smartphoneArray[phone].getRatings()[0]=100;
			}

		}


	}

	//this method assigns values to phones based on user budget preferences
	private void setBudgetRating(int choice) {

		//loop assign ratings to all 30 phones based on budget
		for (int smartphoneNumber = 0; smartphoneNumber < 30; smartphoneNumber++) {

			//if user selects "less than 200" 
			if (choice == 0) {

				//give phones with price less than $200, 1000 rating
				if (smartphoneArray[smartphoneNumber].getBasePrice() < 200)
					smartphoneArray[smartphoneNumber].getRatings()[1] = 1000;

				//give phones with price between $200 - $500, 500 rating
				else if (smartphoneArray[smartphoneNumber].getBasePrice() > 200
						&& smartphoneArray[smartphoneNumber].getBasePrice() < 500)
					smartphoneArray[smartphoneNumber].getRatings()[1] = 500;

				//give phones with price greater than $500, 0 rating
				else if (smartphoneArray[smartphoneNumber].getBasePrice() > 500)
					smartphoneArray[smartphoneNumber].getRatings()[1] = 0;
			}

			//if user selects "$200 - 500"
			if (choice == 1) {

				//give phones with price less than $200, 500 rating
				if (smartphoneArray[smartphoneNumber].getBasePrice() < 200)
					smartphoneArray[smartphoneNumber].getRatings()[1] = 500;

				//give phones with price between $200 - $500, 1000 rating

				else if (smartphoneArray[smartphoneNumber].getBasePrice() > 200
						&& smartphoneArray[smartphoneNumber].getBasePrice() < 500)
					smartphoneArray[smartphoneNumber].getRatings()[1] = 1000;

				//give phones with price greater than $500, 500 rating
				else if (smartphoneArray[smartphoneNumber].getBasePrice() > 500)
					smartphoneArray[smartphoneNumber].getRatings()[1] = 500;
			}

			//if the user selects "500+"
			if (choice == 2) {

				//give phones with price less than $200, 0 rating
				if (smartphoneArray[smartphoneNumber].getBasePrice() < 200)
					smartphoneArray[smartphoneNumber].getRatings()[1] = 0;

				//give phones with price between $200 - $500, 500 rating
				else if (smartphoneArray[smartphoneNumber].getBasePrice() > 200
						&& smartphoneArray[smartphoneNumber].getBasePrice() < 500)
					smartphoneArray[smartphoneNumber].getRatings()[1] = 500;

				//give phones with price greater than $500, 1000 rating
				else if (smartphoneArray[smartphoneNumber].getBasePrice() > 500)
					smartphoneArray[smartphoneNumber].getRatings()[1] = 1000;

			}

		}
	}

	//this method assigns values to phones based on user camera quality preferences
	private void setCameraRating(int choice) {

		//loop assign ratings to all 30 phones based on camera quality
		for (int smartphoneNumber = 0; smartphoneNumber < 30; smartphoneNumber++) {

			//if user selects "low quality"
			if (choice == 0) {

				//give the phones with quality < 10 MP, 10 rating
				if (smartphoneArray[smartphoneNumber].getRearCameraResolution() < 10)
					smartphoneArray[smartphoneNumber].getRatings()[2] = 10;

				//give the phones with quality between 10-15, 5 rating
				else if (smartphoneArray[smartphoneNumber].getRearCameraResolution() > 10
						&& smartphoneArray[smartphoneNumber].getRearCameraResolution() < 15)
					smartphoneArray[smartphoneNumber].getRatings()[2] = 5;

				//give the phones with quality > 15, 0 rating
				else if (smartphoneArray[smartphoneNumber].getRearCameraResolution() > 15)
					smartphoneArray[smartphoneNumber].getRatings()[2] = 0;
			}

			//if user selects "medium quality"
			if (choice == 1) {

				//give the phones with quality < 10 MP, 5 rating
				if (smartphoneArray[smartphoneNumber].getRearCameraResolution() < 10)
					smartphoneArray[smartphoneNumber].getRatings()[2] = 5;

				//give the phones with quality between 10-15, 10 rating
				else if (smartphoneArray[smartphoneNumber].getRearCameraResolution() > 10
						&& smartphoneArray[smartphoneNumber].getRearCameraResolution() < 15)
					smartphoneArray[smartphoneNumber].getRatings()[2] = 10;

				//give the phones with quality > 15, 5 rating
				else if (smartphoneArray[smartphoneNumber].getRearCameraResolution() > 15)
					smartphoneArray[smartphoneNumber].getRatings()[2] = 5;
			}

			//if user selects "high quality"
			if (choice == 2) {

				//give the phones with quality < 10 MP, 0 rating
				if (smartphoneArray[smartphoneNumber].getRearCameraResolution() < 10)
					smartphoneArray[smartphoneNumber].getRatings()[2] = 0;

				//give the phones with quality between 10-15, 5 rating
				else if (smartphoneArray[smartphoneNumber].getRearCameraResolution() > 10
						&& smartphoneArray[smartphoneNumber].getRearCameraResolution() < 15)
					smartphoneArray[smartphoneNumber].getRatings()[2] = 5;

				//give the phones with quality > 15, 10 rating
				else if (smartphoneArray[smartphoneNumber].getRearCameraResolution() > 15)
					smartphoneArray[smartphoneNumber].getRatings()[2] = 10;
			}
		}
	}

	//this method assigns values to phones based on user built-in storage preferences
	private void setStorageRating(int choice) {

		//loop assign ratings to all 30 phones based on built in storage
		for (int smartphoneNumber = 0; smartphoneNumber < 30; smartphoneNumber++) {

			//if user selects "less than 32"
			if (choice == 0) {

				//gives phones with storage < 32, 10 rating
				if (smartphoneArray[smartphoneNumber].getBuiltInMemory() < 32)
					smartphoneArray[smartphoneNumber].getRatings()[3] = 10;

				//gives phones with storage between 32-64, 5 rating
				else if (smartphoneArray[smartphoneNumber].getBuiltInMemory() > 64)
					smartphoneArray[smartphoneNumber].getRatings()[3] = 5;

				//gives phones with storage > 32, 0 rating
				else if (smartphoneArray[smartphoneNumber].getBuiltInMemory() > 32) // variable
					smartphoneArray[smartphoneNumber].getRatings()[3] = 0;

			}

			//if user selects "32-64GB"
			if (choice == 1) {

				//gives phones with storage < 32, 5 rating
				if (smartphoneArray[smartphoneNumber].getBuiltInMemory() < 32)
					smartphoneArray[smartphoneNumber].getRatings()[3] = 5;

				//gives phones with storage between 32-64, 10 rating
				else if (smartphoneArray[smartphoneNumber].getBuiltInMemory() > 32
						&& smartphoneArray[smartphoneNumber].getBuiltInMemory() < 64)
					smartphoneArray[smartphoneNumber].getRatings()[3] = 10;

				//gives phones with storage > 32, 5 rating
				else if (smartphoneArray[smartphoneNumber].getBuiltInMemory() > 64)
					smartphoneArray[smartphoneNumber].getRatings()[3] = 5;

			}

			//if user selects "64GB +"
			if (choice == 2) {

				//gives phones with storage < 32, 0 rating
				if (smartphoneArray[smartphoneNumber].getBuiltInMemory() < 32)
					smartphoneArray[smartphoneNumber].getRatings()[3] = 0;

				//gives phones with storage between 32-64, 5 rating
				else if (smartphoneArray[smartphoneNumber].getBuiltInMemory() > 32
						&& smartphoneArray[smartphoneNumber].getBuiltInMemory() < 64)
					smartphoneArray[smartphoneNumber].getRatings()[3] = 5;

				//gives phones with storage > 32, 10 rating
				else if (smartphoneArray[smartphoneNumber].getBuiltInMemory() > 64)
					smartphoneArray[smartphoneNumber].getRatings()[3] = 10;

			}
		}

	}

	//this method assigns values to phones based on user Random Access Memory preferences
	private void setRamRating (int choice) {

		//loop assign ratings to all 30 phones based on RAM
		for (int smartphoneNumber=0; smartphoneNumber<30;smartphoneNumber++) {

			//if the user selects "less than 2 GB"
			if (choice ==0) {

				//gives phones with RAM < 2, 10 rating
				if (smartphoneArray [smartphoneNumber].getRam()<2)
					smartphoneArray[smartphoneNumber].getRatings()[4]= 10;

				//gives phones with RAM between 2-4, 5 rating
				else if (smartphoneArray [smartphoneNumber].getRam()>2 && 
						smartphoneArray [smartphoneNumber].getRam()<4)
					smartphoneArray[smartphoneNumber].getRatings()[4]= 5;

				//gives phones with RAM > 4, 0 rating
				else if (smartphoneArray [smartphoneNumber].getRam()>4)
					smartphoneArray[smartphoneNumber].getRatings()[4]= 0;		
			}

			//if the user selects "between 2-4 GB"
			if (choice ==1) {

				//gives phones with RAM < 2, 5 rating
				if (smartphoneArray [smartphoneNumber].getRam()<2)
					smartphoneArray[smartphoneNumber].getRatings()[4]= 5;

				//gives phones with RAM between 2-4, 10 rating
				else if (smartphoneArray [smartphoneNumber].getRam()>2 && 
						smartphoneArray [smartphoneNumber].getRam()<4)
					smartphoneArray[smartphoneNumber].getRatings()[4]= 10;

				//gives phones with RAM > 4, 5 rating
				else if (smartphoneArray [smartphoneNumber].getRam()>4)
					smartphoneArray[smartphoneNumber].getRatings()[4]= 5;		

			}

			//if the user selects "4+ GB"
			if (choice ==2) {

				//gives phones with RAM < 2, 0 rating
				if (smartphoneArray [smartphoneNumber].getRam()<2)
					smartphoneArray[smartphoneNumber].getRatings()[4]= 0;

				//gives phones with RAM between 2-4, 5 rating
				else if (smartphoneArray [smartphoneNumber].getRam()>2 && 
						smartphoneArray [smartphoneNumber].getRam()<4)
					smartphoneArray[smartphoneNumber].getRatings()[4]= 5;

				//gives phones with RAM > 4, 10 rating
				else if (smartphoneArray [smartphoneNumber].getRam()>4)
					smartphoneArray[smartphoneNumber].getRatings()[4]= 10;			

			}
		}
	}

	//this method sets up the frame 
	public void frameSetup() {

		//seting the frame size
		setSize(1280, 800);

		//setting the title of the frame
		setTitle("Rating Screen");
		setLayout(null);

		//setting the background colour for the frame
		getContentPane().setBackground(Color.gray);

		//creating a title and setting the size of the label
		titleLabel.setBounds(570, 50, 200, 25);
		add(titleLabel);

		//changing the font and the font size of the title on the frame
		titleLabel.setFont(new Font("Impact", Font.PLAIN,24));

		//creating and sizing the "go to weighings screen" button on the frame (not panel for me)
		weightsButton.setBounds(565, 620, 200, 50);
		add(weightsButton);

		//changing the font and font size of the text on the button
		weightsButton.setFont(new Font("Impact", Font.PLAIN,16));

		//creating a border around the button, changing the colour, and setting the thickness of the border
		weightsButton.setBorder(BorderFactory.createLineBorder(Color.white, 2));

		weightsButton.addActionListener(this);

		//making the frame visible
		setVisible(true);

		//terminating the program upon closing it
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



	}

	// this method is to make background music play while the user has the program open
	public void playBackgroundAudio() {

		// creating a  "random" variable to allow the background song played to not be the same everytime
		Random random = new Random();

		// the random variable used for our 7 different tracks
		int randomMusic = random.nextInt(7) + 1;

		// seting the music played by using randomMusic variable
		backgroundMusicLocation = "res/audio" + randomMusic + ".wav";

		//plays the selected music 
		MusicPlayer.playMusic(backgroundMusicLocation);
	}
}