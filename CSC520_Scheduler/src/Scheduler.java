import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Scheduler {
	
	int desiredClasses;
	ArrayList<Class> desiredClassList = new ArrayList<Class>();

	public static void main(String[] args) {
		Scheduler scheduler = new Scheduler();
		scheduler.fileIntake();
		return;

	}

	void fileIntake() {
		try {
			File inputFile = new File("filename.txt");
			Scanner inputReader = new Scanner(inputFile);
			inputReader.nextLine();
			desiredClasses = Integer.parseInt(inputReader.nextLine());
			inputReader.nextLine();
			String currentLine = inputReader.nextLine();
			while (!currentLine.equals("Input any breaks (if any) you would like, along with their importance on a scale of 1-10 (ex. 9 AM - 10 AM 1):")) {
				String[] lineSplit = currentLine.split(" ");
				Class klasse = new Class(lineSplit[0], lineSplit[1], lineSplit[2]);
				desiredClassList.add(klasse);
				currentLine = inputReader.nextLine();
			}
			while(inputReader.hasNextLine()) {
				String data = inputReader.nextLine();
				System.out.println(data + " time");
			}
			inputReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}
