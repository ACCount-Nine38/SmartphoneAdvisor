package objects;

//imports used my class
import javax.swing.ImageIcon;

/* Created by Roshawn Jamais
 * 
 * the template class to "build" the
 * smartphones to be used in the other classes
 * 
 */
public class SmartPhone {

	//fields of the smartphones and creating arrays
	private String brand;
	private String model;
	private String resolution;
	private String platform;
	private int batteryLife;
	private double builtInMemory;
	private double ram;
	private double basePrice;
	private double frontCameraResolution;
	private double rearCameraResolution;
	private double weight;
	private double processorSpeed;
	private int CPUBenchmark;
	private int memoryBenchmark;
	private int diskBenchmark;
	private int[] ratings = new int[8];
	private String hyperlink;
	private ImageIcon phoneIcon;
	private int score;

	//the "getters and setters" for all of the fields
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public int getBatteryLife() {
		return batteryLife;
	}

	public void setBatteryLife(int batteryLife) {
		this.batteryLife = batteryLife;
	}

	public double getBuiltInMemory() {
		return builtInMemory;
	}

	public void setBuiltInMemory(double builtInMemory) {
		this.builtInMemory = builtInMemory;
	}

	public double getRam() {
		return ram;
	}

	public void setRam(double ram) {
		this.ram = ram;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public double getFrontCameraResolution() {
		return frontCameraResolution;
	}

	public void setFrontCameraResolution(double frontCameraResolution) {
		this.frontCameraResolution = frontCameraResolution;
	}

	public double getRearCameraResolution() {
		return rearCameraResolution;
	}

	public void setRearCameraResolution(double rearCameraResolution) {
		this.rearCameraResolution = rearCameraResolution;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getProcessorSpeed() {
		return processorSpeed;
	}

	public void setProcessorSpeed(double processorSpeed) {
		this.processorSpeed = processorSpeed;
	}

	public int getCPUBenchmark() {
		return CPUBenchmark;
	}

	public void setCPUBenchmark(int CPUBenchmark) {
		this.CPUBenchmark = CPUBenchmark;
	}

	public int getMemoryBenchmark() {
		return memoryBenchmark;
	}

	public void setMemoryBenchmark(int memoryBenchmark) {
		this.memoryBenchmark = memoryBenchmark;
	}

	public int getDiskBenchmark() {
		return diskBenchmark;
	}

	public void setDiskBenchmark(int diskBenchmark) {
		this.diskBenchmark = diskBenchmark;
	}

	public String getHyperlink() {
		return hyperlink;
	}

	public void setHyperlink(String hyperlink) {
		this.hyperlink = hyperlink;
	}

	public ImageIcon getPhoneIcon() {
		return phoneIcon;
	}

	public void setPhoneIcon(ImageIcon phoneIcon) {
		this.phoneIcon = phoneIcon;
	}

	public int[] getRatings() {
		return ratings;
	}

	public void setRatings(int[] ratings) {
		this.ratings = ratings;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	
	
	@Override
	// the toString method
	public String toString() {
		
		//if the phone doesnt ahve a front camera use this toString
		if(frontCameraResolution == -1) {
			return brand + "\nmodel: " + model + "\nresolution: " + resolution + "\nplatform: " + platform
					+ "\nbatteryLife: " + batteryLife + "\nbuiltInMemory: " + builtInMemory + "\nram: " + ram
					+ "\nbasePrice: " + basePrice + "\nfrontCameraResolution: " + "none"
					+ "\nrearCameraResolution: " + rearCameraResolution + "\nweight: " + weight + "\nprocessorSpeed: "
					+ processorSpeed + "\nCPUBenchmark: " + CPUBenchmark + "\nmemoryBenchmark: " + memoryBenchmark
					+ "\ndiskBenchmark: " + diskBenchmark + "\n";
		}
		
		//other wise use this toString
		return brand + "\nmodel: " + model + "\nresolution: " + resolution + "\nplatform: " + platform
				+ "\nbatteryLife: " + batteryLife + "\nbuiltInMemory: " + builtInMemory + "\nram: " + ram
				+ "\nbasePrice: " + basePrice + "\nfrontCameraResolution: " + frontCameraResolution
				+ "\nrearCameraResolution: " + rearCameraResolution + "\nweight: " + weight + "\nprocessorSpeed: "
				+ processorSpeed + "\nCPUBenchmark: " + CPUBenchmark + "\nmemoryBenchmark: " + memoryBenchmark
				+ "\ndiskBenchmark: " + diskBenchmark + "\n";
	}

}