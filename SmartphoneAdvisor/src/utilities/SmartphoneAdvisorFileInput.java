
package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.ImageIcon;

import objects.SmartPhone;

/*
 * Author: Alan Sun
 * 
 * This class reads the input from a sprite sheet file, or .csv
 * Takes in data and creates all the smart phone objects
 */
public class SmartphoneAdvisorFileInput {
    
	//the constructor of the FileInput class takes in a smart phone array to store all the smart phone objects
    public SmartphoneAdvisorFileInput(SmartPhone[] smartphoneArray) {
        
    	//the phone sheet variable is the file name to be used for input
        String phoneSheet = "smartphones.csv";
        
        //using the Java try and catch to verify if the .csv file exist at the designated location
        try {
            
        	//the Scanner input object reads in a new file with the name of the .csv file
            Scanner input = new Scanner(new File(phoneSheet));
            
            //using the delimiter to split the informations in commas so it could be read separately
            input.useDelimiter(",");
            
            //skips the title line of the file
            input.nextLine();
            
            //index variable to keep track of which phone is being processed
            int index = 0;
            
            //while loop looping until all 30 phones are read
            while(index < 30) {
               
            	//create a new smart phone object for the specific index
                smartphoneArray[index] = new SmartPhone();
                
                //the following lines reads the required inputs for the specific smart phone object
                smartphoneArray[index].setBrand(input.next().replaceAll("\r", "").replaceAll("\n", ""));
                smartphoneArray[index].setModel(input.next());
                smartphoneArray[index].setResolution(input.next());
                smartphoneArray[index].setPlatform(input.next());
                smartphoneArray[index].setBatteryLife(input.nextInt());
                smartphoneArray[index].setBuiltInMemory(input.nextDouble());
                smartphoneArray[index].setFrontCameraResolution(input.nextDouble());
                smartphoneArray[index].setRearCameraResolution(input.nextDouble());
                smartphoneArray[index].setRam(input.nextDouble());
                smartphoneArray[index].setBasePrice(input.nextDouble());
                smartphoneArray[index].setWeight(input.nextDouble());
                smartphoneArray[index].setProcessorSpeed(input.nextDouble());
                
                //the following lines reads the ratings for the specific smart phone object
                smartphoneArray[index].getRatings()[5] = input.nextInt();
                smartphoneArray[index].getRatings()[6] = input.nextInt();
                smartphoneArray[index].getRatings()[7] = input.nextInt();
                
                //reads the last information of each phone, hyperlink
                smartphoneArray[index].setHyperlink(input.next());
                
                //reads in the image for the phone at the specific index
                smartphoneArray[index].setPhoneIcon(new ImageIcon("res/phone" + index +".png"));
                
                //print out all the informations of the specific smart phone to see if there are any potential errors
                //System.out.println(smartphoneArray[index]);
                //System.out.println();
                
                //increment index by one to move on to the next smart phone object
                index++;
                
            }
            
        } catch (FileNotFoundException error) { //the catch statement displays a message if the .csv file is not found
        	
            //if the file is not found, display the file not found message
            System.out.println("The file " + phoneSheet + " is not found");
            
        }
        
    }
    
}
