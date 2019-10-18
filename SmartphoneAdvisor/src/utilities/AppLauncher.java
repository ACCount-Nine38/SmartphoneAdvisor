
package utilities;

import displays.RatingScreen;
import objects.SmartPhone;
import objects.User;

public class AppLauncher {

    public static SmartPhone[] smartPhoneArray = new SmartPhone[30];
    
    public static int[] weightingArray = new int[30];
    
    public static User user = new User("Bob", weightingArray);
    
    public static void main(String[] args) {

        new SmartphoneAdvisorFileInput(smartPhoneArray);
        new RatingScreen(smartPhoneArray, user);
        
    }
    
}
