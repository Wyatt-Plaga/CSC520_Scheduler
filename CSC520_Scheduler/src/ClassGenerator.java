import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.Random;

public class ClassGenerator {
	
	public static void main(String[] args) {
	    try {
	    	FileWriter myWriter = new FileWriter("classRoster.txt");
	    	for(int i=1; i<800; i++) {
	    		myWriter.write(generateRandomClass(0, i));
	    		myWriter.write(generateRandomClass(1, i));
	    		myWriter.write(generateRandomClass(2, i));
	    	}
	        myWriter.close();
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	  }
	
	static String generateRandomClass(int subjectInput, int classNumberInput) {
		String output;
		String subject = null;
		int classNumber;
		String classTime = null;
		String classDay = null;
		Random rand = new Random();
		switch(subjectInput) {
			case 0:
				subject = "CSC";
				break;
			case 1:
				subject = "MA";
				break;
			case 2:
				subject = "GD";
		}
		classNumber = classNumberInput;
		switch(rand.nextInt(7)) {
			case 0:
				classTime = "8:30 AM - 10:00 AM";
				break;
			case 1:
				classTime = "10:15 AM - 11:45 AM";
				break;
			case 2:
				classTime = "12:00 AM - 1:30 PM";
				break;
			case 3:
				classTime = "1:45 PM - 3:15 PM";
				break;
			case 4:
				classTime = "3:30 PM - 5:00 PM";
				break;
			case 5:
				classTime = "5:15 PM - 6:45 PM";
				break;
			case 6:
				classTime = "7:00 PM - 8:30 PM";
				break;
		}
		switch(rand.nextInt(3)) {
			case 0:
				classDay = "MoWe";
				break;
			case 1:
				classDay = "TuTh";
				break;
			case 2:
				classDay = "Fri";
				break;
		}
		output = subject + " " + classNumber + " " + classTime + " " + classDay + "\n";
		return output;
	}

}
