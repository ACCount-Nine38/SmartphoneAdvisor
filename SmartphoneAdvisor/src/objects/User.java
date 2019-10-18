package objects;

//import used in my class
import java.util.Arrays;

/* Created by Roshawn Jamais
 * 
 * the template class to "build" 
 * weightings
 * 
 */
public class User {

	// creating an array for weightings and creating needed variables
	private String name;
	private int[] weighting = new int[8];

	public User(String name, int[] weighting) {

		this.name = name;
		this.weighting = weighting;

	}

	// getters and setters for the fields
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int[] getWeighting() {
		return weighting;
	}

	public void setWeighting(int[] weighting) {
		this.weighting = weighting;
	}

	// the toString method
	@Override
	public String toString() {
		return "UserClass [name=" + name + ", weighting=" + Arrays.toString(weighting) + "]";
	}

}